package classes;

import eg.edu.alexu.csd.datastructure.Queue.QueueLinkedBased;
import eg.edu.alexu.csd.datastructure.linkedlist.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Mail implements IMail {
  public String check;
  private String subject;
  private String sender;
  private String receivers;
  public QueueLinkedBased receiver;
  private String mailText;
  private LocalDateTime dateReceived;
  private int numberOfAttachments;
  public LinkedList attachements;
  private String path;
  public String component;
  private String importance;
  public String nameOfAttachements;

  public Mail(String indexString){
    String[] indexInfo = indexString.split("Sender: |receiver: | path: | Importance: | Subject: | Mail: | nameOfAttachements:| Date: ");
    this.sender = indexInfo[1];
    this.receivers = indexInfo[2];
    this.importance = indexInfo[4];
    this.subject = indexInfo[3];
    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy,MM,dd,HH,mm,ss");
    this.dateReceived = LocalDateTime.from(dtf.parse(indexInfo[5]));
    this.path = indexInfo[6];
    if(indexInfo.length==8) {this.nameOfAttachements = indexInfo[7];}
  }

  public Mail(String sender,String receivers,String subject,String component,String importance){
    this.importance=importance;
    this.sender=sender;
    this.check=receivers;
    String []arr=receivers.split("\\s+");
    this.receiver=new QueueLinkedBased();
    for(int i=0;i<arr.length;i++) {
      System.out.println("i am here");
      receiver.enqueue(arr[i]);
    }
    //listing receivers in queue.
    for(int i=0;i<arr.length;i++) {
      receiver.enqueue(arr[i]);
    }
    this.component=component;
    this.attachements=new LinkedList();
    this.subject=subject;
  }

  public Mail(String subject,String sender,String receivers,String mailText){
    this.sender = sender;
    this.receivers = receivers;
    this.mailText = mailText;
    this.subject = subject;
  }

  public String getCheck() {
    return check;
  }

  public void setCheck(String check) {
    this.check = check;
  }

  public QueueLinkedBased getReceiver() {
    return receiver;
  }

  public void setReceiver(QueueLinkedBased receiver) {
    this.receiver = receiver;
  }

  public LinkedList getAttachements() {
    return attachements;
  }

  public void setAttachements(LinkedList attachements) {
    this.attachements = attachements;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public String getImportance() {
    return importance;
  }

  public void setImportance(String importance) {
    this.importance = importance;
  }



  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public void setReceivers(String receivers) {
    this.receivers = receivers;
  }

  public void setNumberOfAttachments(int numberOfAttachments) {
    this.numberOfAttachments = numberOfAttachments;
  }

  public void setMailText(String mailText) {
    this.mailText = mailText;
  }

  public int getNumberOfAttachments() {
      return numberOfAttachments;
  }

  public String getMailText() {
    return mailText;
  }

  public String getReceivers() {
    return receivers;
  }

  public String getSender() {
    return sender;
  }

  public LocalDateTime getDateReceived() {
    return dateReceived;
  }

  public void setDateReceived(LocalDateTime dateReceived) {
    this.dateReceived = dateReceived;
  }
  static public ObservableList<Mail> setObservableMails(Mail[] givenMails){
    ObservableList<Mail> mails = FXCollections.observableArrayList();
    for (int i = 0; i < givenMails.length; i++) {
      mails.add(givenMails[i]);
    }
    return mails;
  }

  static public Mail[] fakeMails(){
    return new Mail[]{new Mail("subj1", "user1", "user2", "kalam"),
        new Mail("subj2", "user2", "user3", "kalam"),
        new Mail("subj3", "user3", "user4", "kalam"),
        new Mail("subj4", "user4", "user5", "kalam"),
        new Mail("subj5", "user5", "user6", "kalam")};
  }



}
