package classes;
import javafx.beans.property.SimpleStringProperty;
public class Contact implements IContact {
  /*will add this info to  a text file in each mail folder so when we add
  a contact from email to another we just copy that file to folder of contacts in the user mail.*/
  private String name;
  private String  otherEmails;//linkedlist contain other emails the contact have.
  private String password;
  private String userName;
  private SimpleStringProperty otherEmailsTable;
  private SimpleStringProperty nameTable;
  private SimpleStringProperty userNameTable;
  //private
  public Contact(){
  }
  public Contact(String name,String username,String emails) {
    this.name=name;
    this.userName=username;
    this.otherEmails=emails;
    this.nameTable=new SimpleStringProperty(name);
    this.otherEmailsTable=new SimpleStringProperty(emails);
    this.userNameTable=new SimpleStringProperty(username);
  }
  public Contact(String name,String emails) {
    this.name=name;
    this.otherEmails=emails;
    this.nameTable=new SimpleStringProperty(name);
    this.otherEmailsTable=new SimpleStringProperty(emails);
  }
  public Contact(String name, String userName,String password,String emails){
    this.name=name;
    this.otherEmails=emails;
    this.password=password;
    this.userName=userName;
  }
  public String getUserNameTable() {
    return this.userNameTable.get();
  }
  public String getNameTable() {
    return this.nameTable.get();
  }
  public String getOtherEmailsTable() {
    return this.otherEmailsTable.get();
  }
  public void setName(String text) {
    this.name=text;
  }
  public void addEmail(String text) {
    this.otherEmails=text;

  }
  public void setPassword(String text) {
    this.password=text;
  }
  public void setUserName(String text) {
    this.userName=text;
  }
  public String getName() {
    return this.name;
  }
  public String getPassword() {
    return this.password;
  }
  public String getUserName() {
    return this.userName;
  }
  public String getEmails(){
    return otherEmails;
  }
  public void setOtherEmailsTable(String emails) {
    this.otherEmailsTable=new SimpleStringProperty(emails);
    this.otherEmails=emails;

  }
  @Override
  public String toString() {
    String info=this.name+"\n";
    info=info+this.userName+"\n";
    info=info+this.otherEmails+'\n';

    return info;
  }

}
