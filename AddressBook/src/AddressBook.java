import javax.swing.border.EtchedBorder;
import java.sql.*;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by austinnafziger on 2/9/16.
 */
public class AddressBook {

    //variable that stores the connection to the database
    Connection conn;

    //an arraylist of the currently visible entires in the application
    private ArrayList<Entry> entries = new ArrayList<Entry>();

    //getter for entries
    public ArrayList<Entry> getEntries(){

        return entries;
    }

    /*
    Constructor for AddressBook, this will connect to the databas and then prepare the system for use.
     */
    public AddressBook() {

        try {
            // Step 1: "Load" the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Step 2: Establish the connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sys?" +
                    "user=root&password=motoa84&useSSL=false");

            //call the stored procedure "SetUp" that will ensure that the needed tables are present
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`SetUp`()}");
            callSt.execute();

            //perform an initial sort to add content to entires and ensure that it is not null
            sort(1, 0);

        } catch (Exception e) {
        }
    }

    /*
    A method to add a contact to the address book taking all of the needed information.
     */
    public Entry addContact(String nameF, String nameM, String nameL, String ph1, String ph1Type, String ph2, String ph2Type,
                           String st, String ap, String cit, String sta, String zp, String em, String comp, String not){

        //create an instance of entry with the provided information
        Entry entry = new Entry(nameF, nameM, nameL, ph1, ph1Type, ph2, ph2Type, st, ap, cit, sta, zp, em, comp, not);

        try {

            //call the stored procedure "AddContact" to add a new row to the database
            //each of the ?'s will be filled with the provided information later on
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`AddContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            //prepare the call to the database with the provided information
            callSt.setString(1, nameF);
            callSt.setString(2, nameM);
            callSt.setString(3, nameL);
            callSt.setString(4, ph1);
            callSt.setString(5, ph1Type);
            callSt.setString(6, ph2);
            callSt.setString(7, ph2Type);
            callSt.setString(8, st);
            callSt.setString(9, ap);
            callSt.setString(10, cit);
            callSt.setString(11, sta);
            callSt.setString(12, zp);
            callSt.setString(13, em);
            callSt.setString(14, comp);
            callSt.setString(15, not);

            //execute the call to the database
            callSt.execute();

        }catch (SQLException e){

        	e.printStackTrace();
        }

        //add the new entry to the list of currently visible entries
        entries.add(entry);

        //return entry for any needed purpose
        return entry;
    }

    /*
    A method to remove a contact from the database
     */
    public void removeContact(Entry entry){

        try {

            //find the entry that is being removed in the list of visible entries and remove it
            for(int i=0; i<entries.size(); i++){

                if(entries.get(i).getNameFirst().equals(entry.getNameFirst()) &&
                        entries.get(i).getNameMiddle().equals(entry.getNameMiddle()) &&
                        entries.get(i).getNameLast().equals(entry.getNameLast()) &&
                        entries.get(i).getPhoneNumber1().equals(entry.getPhoneNumber1()) &&
                        entries.get(i).getPhoneType1().equals(entry.getPhoneType1()) &&
                        entries.get(i).getPhoneNumber2().equals(entry.getPhoneNumber2()) &&
                        entries.get(i).getPhoneType2().equals(entry.getPhoneType2()) &&
                        entries.get(i).getApt().equals(entry.getApt()) &&
                        entries.get(i).getState().equals(entry.getState()) &&
                        entries.get(i).getStreet().equals(entry.getStreet()) &&
                        entries.get(i).getCity().equals(entry.getCity()) &&
                        entries.get(i).getEmail().equals(entry.getEmail()) &&
                        entries.get(i).getNotes().equals(entry.getNotes()) &&
                        entries.get(i).getCompany().equals(entry.getCompany())){

                    entries.remove(i);
                }
            }

            //call a stored procedure "DropContact" from the database
            //all ?'s will be filled with provided information
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`DropContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            //provide the needed information given in entry
            callSt.setString(1, entry.getNameFirst());
            callSt.setString(2, entry.getNameMiddle());
            callSt.setString(3, entry.getNameLast());
            callSt.setString(4, entry.getPhoneNumber1());
            callSt.setString(5, entry.getPhoneType1());
            callSt.setString(6, entry.getPhoneNumber2());
            callSt.setString(7, entry.getPhoneType2());
            callSt.setString(8, entry.getStreet());
            callSt.setString(9, entry.getApt());
            callSt.setString(10, entry.getCity());
            callSt.setString(11, entry.getState());
            callSt.setString(12, entry.getZip());
            callSt.setString(13, entry.getEmail());
            callSt.setString(14, entry.getCompany());
            callSt.setString(15, entry.getNotes());

            //execute the  call to the database
            callSt.execute();

        }catch (SQLException e){

        }
    }

    /*
    A method to edit a contact in the database. It provides the old entry as well as the updated information
     */
    public void editContact(Entry entry, String nameF, String nameM, String nameL, String ph1, String ph1Type, String ph2,  String ph2Type, 
            String st, String ap, String cit, String sta, String zp, String em, String comp, String not){

        //remove the old contact
        removeContact(entry);

        //add a new contact with the updated information
        addContact(nameF, nameM, nameL, ph1, ph1Type, ph2, ph2Type, st, ap, cit, sta, zp, em, comp, not);
    }

    /*
    A method to search the address book. It takes in a search term as well as commands for order and type of search.
     */
    public void search(String keyword, int command, int aod){

        try{

            //call stored procedure "Search"
            //?'s will be filled with provided information
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`Search`(?,?,?)}");

            //provide needed information to the procedure call
            callSt.setString(1, keyword);
            callSt.setInt(2, command); //what are you sorting by
            callSt.setInt(3, aod); //ascending or decending

            //execute the query to the database in a way as to get a table or "ResultSet" returned
            ResultSet results = callSt.executeQuery();

            //update entries with the new data from the resultset via an analyzation method
            entries = analyzeResults(results);

        }catch(SQLException e){

        }
    }

    /*
    A method to sort the visible results in various ways
     */
    public void sort(int command, int aod){

        try{

            //call stored procedure "Sort"
            //?'s will be filled with needed information
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`Sort`(?,?)}");

            callSt.setInt(1, command); //what are you sorting by
            callSt.setInt(2, aod); //ascending or decending

            //execute the query so that a result set is obtained
            ResultSet results = callSt.executeQuery();

            //update entires with the new information by analyzing the result set
            entries = analyzeResults(results);

        }catch(SQLException e){

        }
    }

    /*
    A method to analyze a result set and return an arraylist of entries containing the information from the database
     */
    private ArrayList<Entry> analyzeResults(ResultSet set){

        try{

            //if the result set is null then set an empty list
            if(set == null){

                return new ArrayList<Entry>();
            }

            //clear the current list
            entries.clear();

            //while there is more information in the result set move the pointer to the next line
            while(set.next()){

                //get the information from the result set
                String first = set.getString("FirstName");
                String middle = set.getString("MiddleName");
                String last = set.getString("LastName");
                String phone1 = set.getString("PhoneNumber1");
                String phoneType1 = set.getString("PhoneType1");
                String phone2 = set.getString("PhoneNumber2");
                String phoneType2 = set.getString("PhoneType2");
                String street = set.getString("Street");
                String apt = set.getString("Apt");
                String city = set.getString("City");
                String state = set.getString("State");
                String zip = set.getString("Zip");
                String email = set.getString("Email");
                String company = set.getString("Company");
                String notes = set.getString("Notes");

                //create a new entry using the obtained information
                Entry temp = new Entry(first, middle, last, phone1, phoneType1, phone2, phoneType2, street, apt, city, state,
                        zip, email, company, notes);

                //add the new entry to the list of entries
                entries.add(temp);
            }

        }catch(SQLException e){


        }

        //return the new list
        return entries;
    }


    /*
    The main method of the address book
     */
    public static void main(String[] args) {

        //create a new address book
       AddressBook ab = new AddressBook();
       
       try {
           //create a new main window with the address book as a parameter
		    MainWindow screen = new MainWindow(ab);
       } catch (ParseException e) {
		e.printStackTrace();
       }


    }

}
