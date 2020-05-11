package classes;

import eg.edu.alexu.csd.datastructure.linkedlist.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class repeatedMethods {

  public static boolean saveDraft(IMail email) {
    String Date =repeatedMethods.getDate();
    String fileSeparator =System.getProperty("file.separator");
    //mail data will be inserted through the constructor from the gui
    Mail obj=(Mail)email;
    //first let's check if one of recievers is not found to stop the process
    String[] test=obj.check.split("\\s");
    for(int i=0;i<test.length;i++) {
      if(!repeatedMethods.checkuser(test[i])) {
        return false;
      }
    }//then all receivers are found in system
    String path1 = "System" + fileSeparator + obj.getSender() + fileSeparator + "drafts";//path to sent folder
    String path2 = path1 + fileSeparator + Date;
    File file1 = new File(path2);
    try {int counter1=0;
      while(!file1.mkdir()) {
        file1=new File(path2+counter1);
        counter1++;
      }
      String paths=file1.getAbsolutePath()+fileSeparator+"component.txt";
      File fileComponents = new File(paths);
      if(fileComponents.createNewFile()) {
        System.out.println("it works");
      }

      File rename =new File(path1+fileSeparator+Date);
      file1.renameTo(rename);
      String receivers = "";
      for(int i = 0; i < obj.receiver.size(); i++) {
        receivers = receivers + obj.receiver.dequeue() + " ";
      }
      String inComponentSent=repeatedMethods.textInSent(Date, obj.getComponent(), obj.getSubject(), obj.getImportance(), receivers);
      //now data is ready to be set in component of email in email folder in sent folder.
      repeatedMethods.WriteInFile(inComponentSent, fileComponents.getAbsolutePath());
      //let's add attachements
      String nameOfAttachements = "";
      for(int i = 0; i < obj.attachements.size(); i++) {//each node will be a file object
        File file = (File)obj.attachements.get(i);
        //determine type of file
        File newFile= new File(file1.getAbsoluteFile() + fileSeparator + file.getName());
        newFile.createNewFile();
        repeatedMethods.transferAttachements(file, newFile);
        //get names of attachements
        nameOfAttachements = nameOfAttachements + " " + file.getName();
      }
      //attachements are added to email in sent and inbox
      //let's add index to index file of sent Folder
      String indexText = repeatedMethods.indexMessage(obj.getSender(), receivers, obj.getSubject(), obj.getImportance(),Date,nameOfAttachements ,path2);
      repeatedMethods.writeInIndex(path1, indexText);
    }catch(Exception e ) {
      System.out.println(e);
    }
    return true;

  }

  public static void writeInIndex2(String path,String mail) {
    String fileSeparator =System.getProperty("file.separator");
    String path1=path+fileSeparator+"index.txt" ;
    File file=new File(path1);
    if(!file.exists()) {
      System.out.println("cannot write in the index");
      return; }
    try{FileWriter fw=new FileWriter(file);
      BufferedWriter br=new BufferedWriter(fw);
      PrintWriter pw=new PrintWriter(br);
      pw.println(mail);
      pw.close();
      br.close();
      fw.close();
    }catch(Exception e) {
      System.out.println(e);
    }

  }

  public static void writeInIndex(String path,String mail) {//write mails in index
    String fileSeparator =System.getProperty("file.separator");
    String path1=path+fileSeparator+"index.txt" ;
    File file=new File(path1);
    if(!file.exists()) {
      System.out.println("cannot write in the index");
      return; }
    try{FileWriter fw=new FileWriter(file,true);
      BufferedWriter br=new BufferedWriter(fw);
      PrintWriter pw=new PrintWriter(br);
      pw.println(mail);
      pw.close();
      br.close();
      fw.close();
    }catch(Exception e) {
      System.out.println(e);
    }
  }
  public static  boolean checkuser(String name) {//kda bashof al asm mawgod gowa al system wala la .
    boolean valid =false;
    File file1=new File("System");
    String [] namesOfFiles=file1.list();
    if(namesOfFiles.length==0) {
      return false;
    }
    for(int i=0;i<namesOfFiles.length;i++) {
      if(name.equals(namesOfFiles[i])) {
        valid=true;
        break;
      }
    }
    return valid;
  }
  public static void WriteInFile(String mail,String path) {//to append messages in files//hkteb feha gowa al component.
    File file=new File(path);
    try{FileWriter fw=new FileWriter(file,true);
      BufferedWriter br=new BufferedWriter(fw);
      PrintWriter pw=new PrintWriter(br);
      pw.println(mail);
      pw.close();
      br.close();
      fw.close();
    }catch(Exception e) {
      System.out.println(e);
    }

  }
  public static LinkedList readFromIndex(String path) {
    String fileSeparator =System.getProperty("file.separator");
    LinkedList obj=new LinkedList();
    try { BufferedReader reader=new BufferedReader(new FileReader(path+fileSeparator+"index.txt"));
      String line ;
      while((line=reader.readLine())!=null){
        obj.add(line);
      }
    }catch(Exception e) {
      System.out.println(e);
    }

    return obj;
  }
  public static void transferAttachements(File file1,File file2) {
    try {//not a text file then we will use byte arrays
      //then we will use fileinputStream
      InputStream inputStream = new FileInputStream(file1);
      OutputStream outputStream = new FileOutputStream(file2);
      byte []buffer = new byte[4096];
      int bytesRead = -1;
      while((bytesRead=inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);

      }
      inputStream.close();
      outputStream.close();
    } catch (IOException e) {

      System.out.println("al shebsheb da3");
    }
  }
  public static String textInSent(String date,String component,String subject,String importance,String reciever ) {
    String mail="subject : "+subject+"\n"+"importance : "+importance+"\n"+"recievers : "+reciever+"\n"+"Date : "+date+"\n"+component;
    return mail;
  }
  public static String textIninbox(String sender,String subject,String importance,String component,String date) {
    String mail="Sender : "+sender+"\n"+"Subject : "+subject+"\n"+"importance : "+importance+"\n"+"Date : "+date+"\n"+component;
    return mail;
  }
  public static String indexMessage(String sender,String reciever,String subject ,String importance ,String Date , String nameOfAttachements,String path) {
    String mail="Sender: "+sender+" receiver: "+reciever+"Subject: "+subject+" Importance: "+importance+" Date: "+Date+" path: "+path+" nameOfAttachements : "+nameOfAttachements;
    return mail;
  }
  public static String creationDate(Path file) {
    String x="";
    try {
      BasicFileAttributes attr = Files.readAttributes(file,BasicFileAttributes.class);
      x=attr.creationTime()+"";
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return x;

  }
  public static String subsPath(String folder) {
    if(folder.contains("inbox")) {folder=folder.replace("inbox", "trash");}
    else if(folder.contains("drafts")) {folder=folder.replace("drafts", "trash");}
    else if(folder.contains("sent")) {folder=folder.replace("sent", "trash");}
    return folder;
  }
  public static String getDate() {

    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy,MM,dd,HH,mm,ss");
    LocalDateTime now=LocalDateTime.now();
    String ahmed= dtf.format(now);
    return ahmed;

  }
  public static String dateToString(LocalDateTime date){
    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy,MM,dd,HH,mm,ss");
    String formattedDate= dtf.format(date);
    return formattedDate;
  }


}