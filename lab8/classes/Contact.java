package classes;

public class Contact implements IContact {
  /*will add this info to  a text file in each mail folder so when we add
  a contact from email to another we just copy that file to folder of contacts in the user mail.*/
  private String name;
  private String  otherEmails;//linkedlist contain other emails the contact have.
  private String password;
  private String userName;
  Contact(){ }
  public Contact(String name, String userName,String password,String emails){
    this.name=name;
    this.otherEmails=emails;
    this.password=password;
    this.userName=userName;
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
  @Override
  public String toString() {
    String info="Name : "+this.name+"\n";
    info=info+"userName : "+this.userName+"\n";
    info=info+"Other emails : "+this.otherEmails+'\n';

    return info;
  }

}
