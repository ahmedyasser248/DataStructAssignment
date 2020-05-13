package classes;

import eg.edu.alexu.csd.datastructure.linkedlist.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
public class contactsMethods {
  public static boolean addContacts(String userName,String friend) {

    boolean valid = true;
    //first check if the fried you want to add is found
    if(!repeatedMethods.checkuser(friend)) {//if it's not found it will return false
      return false;
    }
    //name of the file
    String fileSeparator =System.getProperty("file.separator");
    //create a text file inside contacts folder of user that added a friend
    File file3=new File("System"+fileSeparator+userName+fileSeparator+"contacts");
    File file1=new File("System"+fileSeparator+userName+fileSeparator+"contacts"+fileSeparator+friend+".txt");
    File file2=new File("System"+fileSeparator+friend+fileSeparator+friend+"info.txt");
    try {file3.mkdir();
      File []arr=file3.listFiles();
      String friend2=friend+".txt";
      if(file3.listFiles()!=null) {
        for(int i=0;i<arr.length;i++) {
          if(friend2.equals(arr[i].getName())) {
            return false;
          }
        }
      }
      file1.createNewFile();
      file2.createNewFile();
      repeatedMethods.transferAttachements(file2,file1);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return valid;
  }
  public static boolean deleteContacts(String userName,String friend) {
    String fileSeparator =System.getProperty("file.separator");
    File file1=new File("System"+fileSeparator+userName+fileSeparator+"contacts"+fileSeparator+friend+".txt");
    if(!file1.exists()) {
      return false;
    }
    else {
      try {
        Files.delete(file1.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  public static LinkedList readContatcs(String username) {
    String fileSeparator =System.getProperty("file.separator");
    File file1=new File("System"+fileSeparator+username+fileSeparator+"contacts");
    if(!file1.isDirectory()) {
      return null;
    }
    File []arr=file1.listFiles();
    Contact obj;
    LinkedList contacts =new LinkedList();
    for(int i=0;i<arr.length;i++) {
      try {
        boolean nameB =true;
        boolean userC=true;
        BufferedReader reader=new BufferedReader(new FileReader(arr[i].getAbsolutePath()));
        String line;
        String name="";
        String userName="";
        String otherEmails="";
        while((line=reader.readLine())!=null) {
          if(nameB) {
            name=line;
            nameB=false;
          }else if(userC) {
            userName=line;
            userC=false;
          }
          else {
            otherEmails=otherEmails+line;
          }
        }
        obj=new Contact(name,userName,otherEmails);
        contacts.add(obj);
      } catch (FileNotFoundException e) {
        System.out.println(e);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        System.out.println(e);
      }
    }
    return contacts;
  }

  public static Contact getContact(String username) {
    Contact obj=null;
    String fileSeparator =System.getProperty("file.separator");
    File file1=new File("System"+fileSeparator+username+fileSeparator+username+"info.txt");
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file1.getAbsolutePath()));
      boolean nameB=true;
      boolean userC=true;
      String line ;
      String userName="";
      String name ="";
      String otherEmails="";

      while((line=reader.readLine())!=null) {
        if(nameB) {
          nameB=false;
          name=line;
        }else if(userC) {
          userName=line;
          userC=false;
        }
        else {
          otherEmails=otherEmails+line+" ";}

      }obj=new Contact(name,userName,otherEmails);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println(e);
    }
    return obj;
  }
  public static void editContact(Contact contact,String userName) {
    String fileSeparator =System.getProperty("file.separator");
    File file1=new File("System"+fileSeparator+userName+fileSeparator+"contacts"+fileSeparator+contact.getUserName()+".txt");
    try {
      file1.createNewFile();
      FileWriter fw=new FileWriter(file1);
      BufferedWriter br=new BufferedWriter(fw);
      PrintWriter pw=new PrintWriter(br);
      pw.println(contact.toString());
      pw.close();
      br.close();
      fw.close();
    } catch (IOException e) {
      System.out.println(e);
    }

  }

}
