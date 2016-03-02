import java.sql.*;
import java.sql.Connection;
import java.text.ParseException;

/**
 * Created by austinnafziger on 2/9/16.
 */
public class AddressBook {

    Connection conn;

    public AddressBook(){

        try {
            // Step 1: "Load" the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Step 2: Establish the connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sys?" +
                            "user=javeney&password=@Rtisan8319&useSSL=false");
        }
        catch(Exception e){
        }
    }

    public Entry addContact(String nameF, String nameM, String nameL, String ph1, String ph2, String ph1Type, String ph2Type, 
                           String st, String ap, String cit, String sta, String zp, String em, String comp, String not, String img){

        Entry entry = new Entry(nameF, nameM, nameL, ph1, ph1Type, ph2, ph2Type, st, ap, cit, sta, zp, em, comp, not, img);

        try {
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`AddContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

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
            callSt.setString(16, img);

            callSt.execute();



        }catch (SQLException e){


        }

        return entry;
    }

    public void removeContact(Entry entry){

        try {
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`DropContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

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
            callSt.setString(16, entry.getImage());

            callSt.execute();
        }catch (SQLException e){

        }
    }

    public void editContact(Entry entry, String nameF, String nameM, String nameL, String ph1, String ph1Type, String ph2,  String ph2Type, 
            String st, String ap, String cit, String sta, String zp, String em, String comp, String not, String img){

        removeContact(entry);

        addContact(nameF, nameM, nameL, ph1, ph1Type, ph2, ph2Type, st, ap, cit, sta, zp, em, comp, not, img);
    }
    
    public void search(String keyword, int command){
    	//SQL stuff
    }
    
    public void sort(int command, int keyword){
    	//SQL stuff
    }

    public static void main(String[] args) {

       AddressBook ab = new AddressBook();
       
       try {
		MainWindow screen = new MainWindow(ab);
		screen.initialize();
       } catch (ParseException e) {
		e.printStackTrace();
       }

	   Entry ent = new Entry("Austin", "R", "Nafziger", "3524280702", "Mobile", "3524280702", "Home", "4544 street", "rm 1", "ft hell", "fl", "36942", "email@email.email",
			   "horrible company", "blah", "/AddressBook/src/images/lime.png");
	 
	   ab.addContact("Austin", "R", "Nafziger", "3524280702", "Mobile", "3524280702", "Home", "4544 street", "rm 1", "ft hell", "fl", "36942", "email@email.email",
			   "horrible company", "blah", "/AddressBook/src/images/lime.png");
	
	   ab.removeContact(ent);
    }

}
