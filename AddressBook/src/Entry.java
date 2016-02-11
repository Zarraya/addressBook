import javax.swing.*;

/**
 * Created by austinnafziger on 2/9/16.
 */
public class Entry {



    private String nameFirst;
    private String nameMiddle;
    private String nameLast;
    private String notes;
    private String phoneNumber1;
    private String phoneNumber2;
    private String email;
    private String street;
    private String apt;
    private String City;
    private String State;
    private String zip;



    private ImageIcon image;

    public Entry(String nameF, String nameM, String nameL, String not, String ph1, String ph2, String em, String st, String ap, String cit, String sta, String zp){

        nameFirst = nameF;
        nameMiddle = nameM;
        nameLast = nameL;
        notes = not;
        phoneNumber1 = ph1;
        phoneNumber2 = ph2;
        email = em;
        street = st;
        apt = ap;
        City = cit;
        State = sta;
        zip = zp;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameMiddle() {
        return nameMiddle;
    }

    public void setNameMiddle(String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
