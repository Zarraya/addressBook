import java.sql.*;
import java.sql.Connection;

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
                            "user=root&password=motoa84&useSSL=false");
        }
        catch(Exception e){


        }
    }

    public Entry addContact(String nameF, String nameM, String nameL, String not, String ph1, String ph2, String em,
                           String st, String ap, String cit, String sta, String zp){

        Entry entry = new Entry(nameF, nameM, nameL, not, ph1, ph2, em, st, ap, cit, sta, zp);

        try {
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`AddContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            callSt.setString(1, "img");
            callSt.setString(2, nameF);
            callSt.setString(3, nameM);
            callSt.setString(4, nameL);
            callSt.setString(5, ph1);
            callSt.setString(6, ph2);
            callSt.setString(7, em);
            callSt.setString(8, st);
            callSt.setString(9, ap);
            callSt.setString(10, cit);
            callSt.setString(11, sta);
            callSt.setString(12, zp);
            callSt.setString(13, not);

            callSt.execute();



        }catch (SQLException e){


        }

        return entry;
    }

    public void removeContact(Entry entry){



        try {
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`DropContact`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            callSt.setString(1, "img");
            callSt.setString(2, entry.getNameFirst());
            callSt.setString(3, entry.getNameMiddle());
            callSt.setString(4, entry.getNameLast());
            callSt.setString(5, entry.getPhoneNumber1());
            callSt.setString(6, entry.getPhoneNumber2());
            callSt.setString(7, entry.getEmail());
            callSt.setString(8, entry.getStreet());
            callSt.setString(9, entry.getApt());
            callSt.setString(10, entry.getCity());
            callSt.setString(11, entry.getState());
            callSt.setString(12, entry.getZip());
            callSt.setString(13, entry.getNotes());

            callSt.execute();
        }catch (SQLException e){


        }
    }

    public void editContact(Entry entry, String nameF, String nameM, String nameL, String not, String ph1, String ph2, String em,
                            String st, String ap, String cit, String sta, String zp){

        removeContact(entry);

        addContact(nameF, nameM, nameL, not, ph1, ph2, em, st, ap, cit, sta, zp);
    }

//    public static void main(String[] args) {
//
//       AddressBook ab = new AddressBook();
//
//        Entry ent = new Entry("Austin", "R", "Nafziger", "", "5402208162", "", "hello@me", "F", "1", "or", "fl", "");
//
//        ab.addContact("Austin", "R", "Nafziger", "", "5402208162", "", "hello@me", "F", "1", "or", "fl", "");
//
//        ab.removeContact(ent);
//    }

}
