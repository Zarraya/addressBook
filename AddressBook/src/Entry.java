/**
 * Created by austinnafziger on 2/9/16.
 */
public class Entry {

    private String nameFirst;
    private String nameMiddle;
    private String nameLast;
    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneType1;
    private String phoneType2;
    private String street;
    private String apt;
    private String City;
    private String State;
    private String zip;
    private String email;
    private String company;
    private String notes;
    private String image;

    public Entry(String nameF, String nameM, String nameL, String ph1, String ph1Type, String ph2, String ph2Type,
            String st, String ap, String cit, String sta, String zp, String em, String comp, String not){

        nameFirst = nameF;
        nameMiddle = nameM;
        nameLast = nameL;
        phoneNumber1 = ph1;
        phoneType1 = ph1Type;
        phoneNumber2 = ph2;
        phoneType2 = ph2Type;
        street = st;
        apt = ap;
        City = cit;
        State = sta;
        zip = zp;
        email = em;
        company = comp;
        notes = not;
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
    
    public String getCompany(){
    	return company;
    }
    
    public void setCompany(String company){
    	this.company = company;
    }
    
    public String getPhoneType1(){
    	return phoneType1;
    }
    
    public void setPhoneType1(String phoneType1){
    	this.phoneType1 = phoneType1;
    }
    
    public String getPhoneType2(){
    	return phoneType2;
    }
    
    public void setPhoneType2(String phoneType2){
    	this.phoneType2 = phoneType2;
    }
}
