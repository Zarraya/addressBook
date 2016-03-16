import java.sql.*;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by austinnafziger on 2/9/16.
 */
public class AddressBook {

    Connection conn;

    private ArrayList<Entry> entries = new ArrayList<Entry>();

    public ArrayList<Entry> getEntries(){

        return entries;
    }

    public AddressBook() {

        try {
            // Step 1: "Load" the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Step 2: Establish the connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sys?" +
                    "user=root&password=motoa84&useSSL=false");


            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`SetUp`()}");
            callSt.execute();

            sort(1, 0);

        } catch (Exception e) {
        }
    }

    public Entry addContact(String nameF, String nameM, String nameL, String ph1, String ph1Type, String ph2, String ph2Type,
                           String st, String ap, String cit, String sta, String zp, String em, String comp, String not){

        Entry entry = new Entry(nameF, nameM, nameL, ph1, ph1Type, ph2, ph2Type, st, ap, cit, sta, zp, em, comp, not);

        try {
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`AddContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

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

            callSt.execute();



        }catch (SQLException e){

        	e.printStackTrace();
        }

        return entry;
    }

    public void removeContact(Entry entry){

        try {
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`DropContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

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

            callSt.execute();
        }catch (SQLException e){

        }
    }

    public void editContact(Entry entry, String nameF, String nameM, String nameL, String ph1, String ph1Type, String ph2,  String ph2Type, 
            String st, String ap, String cit, String sta, String zp, String em, String comp, String not){

        removeContact(entry);

        addContact(nameF, nameM, nameL, ph1, ph1Type, ph2, ph2Type, st, ap, cit, sta, zp, em, comp, not);
    }
    
    public void search(String keyword, int command, int aod){

        try{

            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`Search`(?,?,?)}");

            callSt.setString(1, keyword);
            callSt.setInt(2, command); //what are you sorting by
            callSt.setInt(3, aod); //ascending or decending

            ResultSet results = callSt.executeQuery();

            entries = analyzeResults(results);

        }catch(SQLException e){

        }
    }
    
    public void sort(int command, int aod){

        try{

            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`Sort`(?,?)}");


            callSt.setInt(1, command); //what are you sorting by
            callSt.setInt(2, aod); //ascending or decending

            ResultSet results = callSt.executeQuery();

            entries = analyzeResults(results);

        }catch(SQLException e){

        }
    }

    private ArrayList<Entry> analyzeResults(ResultSet set){

        try{

            entries.clear();

            while(set.next()){

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

            Entry temp = new Entry(first, middle, last, phone1, phoneType1, phone2, phoneType2, street, apt, city, state,
                    zip, email, company, notes);

            entries.add(temp);
        }

    }catch(SQLException e){


    }

        return entries;
    }

    public static void main(String[] args) {

       AddressBook ab = new AddressBook();


       
       try {
		MainWindow screen = new MainWindow(ab);
       } catch (ParseException e) {
		e.printStackTrace();
       }


    }

}
