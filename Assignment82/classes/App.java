package classes;

import eg.edu.alexu.csd.datastructure.linkedlist.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedlist.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class App implements IApp{
  public Folder currentFolder;
  public Sort currentSort;
  public Filter currentFilter;
  @Override
  public boolean signin(String email, String password) {
    boolean valid=false;
    File file1=new File("System");
    String fileSeparator =System.getProperty("file.separator");
    String path1= file1.getAbsolutePath();
    try {   BufferedReader reader=new BufferedReader(new FileReader(path1+fileSeparator+"info.txt"));
      String line ;
      while((line=reader.readLine())!=null) {//read the whole file
        String []arr=line.split("\\s");
        if(arr[0].equals(email)&&arr[1].equals(password)) {//if user and pass matches with input it will return true
          valid=true;
          reader.close();
          break;
        }
      }
    }catch(Exception e) {
      System.out.println();
    }
    return valid;


  }

  @Override
  public boolean signup(IContact contact) {

    Contact obj=(Contact)contact;

    String fileSeparator =System.getProperty("file.separator");
    File file1=new File("System");
    String userName=obj.getUserName();
    String password=obj.getPassword();
    String path = file1.getAbsolutePath();
    if(!repeatedMethods.checkuser(userName)) {
      //creating a user in system after checking for and username .
      try {
        File file2=new File(path+fileSeparator+userName);//creating a mail user
        file2.mkdir();
        String path2 = file2.getAbsolutePath();
        File file3=new File(path2 + fileSeparator + "inbox");
        File file4=new File(path2 + fileSeparator + "trash");
        File file5=new File(path2 + fileSeparator + "sent");
        File file9=new File(path2 + fileSeparator + "contacts");
        File file16=new File(path2 + fileSeparator + "drafts");
        File file14=new File(path2 + fileSeparator + userName + "info.txt");//create a text file contains information about user(doesn't include password)
        file3.mkdir();
        file4.mkdir();
        file5.mkdir();
        file9.mkdir();
        file16.mkdir();
        file14.createNewFile();
        String path3 = file3.getAbsolutePath();
        String path4 = file4.getAbsolutePath();
        String path5 = file5.getAbsolutePath();
        String path16=file16.getAbsolutePath();
        File file6 = new File(path3+fileSeparator+"index.txt");
        File file7 = new File(path4+fileSeparator+"index.txt");
        File file8 = new File(path5+fileSeparator+"index.txt");
        File file20= new File(path16+fileSeparator+"index.txt");

        file6.createNewFile();
        file7.createNewFile();
        file8.createNewFile();
        file20.createNewFile();
        File file13=new File(path+fileSeparator+"info.txt");//let's push that password and username to info file.
        String path13=file13.getAbsolutePath();
        FileWriter fr=new FileWriter(path13,true);
        BufferedWriter bf=new BufferedWriter(fr);
        PrintWriter pr=new PrintWriter(bf);
        pr.println(userName+" "+password);//using println to write line by line in info.txt file of system.
        pr.close();
        bf.close();
        fr.close();
        //add information of mail to the text file
        String path14=file14.getAbsolutePath();
        FileWriter frx=new FileWriter(path14);
        BufferedWriter bfx=new BufferedWriter(frx);
        PrintWriter prx=new PrintWriter(bfx);
        String info=obj.toString();
        prx.println(info);
        prx.close();
        bf.close();
        fr.close();
      }
      catch(Exception e) {
        System.out.println(e);
      }
      return true;
    }else {
      return false;//kda al asm tl3 mawgod abl kda

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

  @Override
  public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
      this.currentSort = (Sort) sort;
      this.currentFilter = (Filter) filter;
      if(folder==null) {return;}
      this.currentFolder = (Folder) folder;
  }

  @Override
  public Mail[] listEmails(int page) {
    //currentFolder.stringToMail();
    currentFolder.setSMails(currentSort.quickSort(currentFolder.index));
    currentFolder.setFMails(currentFilter.filter(currentFolder.index));
    Mail[] toBeListedMails = new Mail[currentFolder.index.size()];
    int j = 0;
    for (int i = page*10; i <currentFolder.index.size() && i < (page+1)*10  ; i++,j++) {
//      String indexStrings = (String)currentFolder.index.get(i);
//      toBeListedMails[j] = new Mail(indexStrings);
      toBeListedMails[j] = (Mail)currentFolder.index.get(i);
    }
    return toBeListedMails;
  }

  @Override
  public void deleteEmails(ILinkedList mails) {
    LinkedList mailsx=(LinkedList)mails;
    for(int i = 0; i < mailsx.size(); i++) {
      String indexIntrash;
      LinkedList indexFile;
      Mail obj = (Mail) mailsx.get(i);
      String currentPath=obj.getPath();
      String pathToTrash = "";
      pathToTrash=repeatedMethods.subsPath(currentPath);
      File source=new File(currentPath);
      File destination=new File(pathToTrash);
      String nameOfFile=source.getName();
      System.out.println(nameOfFile);
      indexFile=repeatedMethods.readFromIndex(source.getParent());
      String deletedIndex="";
      //search in index to get file
      System.out.println(indexFile.size());
      for(int y=0;y<indexFile.size();y++) {
        String insideLinkedList=(String)indexFile.get(y);
        if(insideLinkedList.contains(nameOfFile)) {
          deletedIndex=insideLinkedList;
          indexFile.remove(y);
          break;
        }
      }System.out.println(indexFile.size());
      //writing index message in index of trash.
      repeatedMethods.writeInIndex(destination.getParent(), deletedIndex);
      //now i will collect all the strings in  one string
      String updateIndex="";
      for(int g = 0; g <indexFile.size();g++) {
        updateIndex=updateIndex+indexFile.get(g)+"\n";
      }
      System.out.println(updateIndex);
      repeatedMethods.writeInIndex2(source.getParent(), updateIndex);
      try {
        destination.mkdir();
        //now let's delete the directory of email
        if(source.listFiles() != null) {
          File[]arr=source.listFiles();
          for(int z=0;z<arr.length;z++) {
            String ToTrash=repeatedMethods.subsPath((arr[z].getAbsolutePath()));
            File subs=new File(ToTrash);
            repeatedMethods.transferAttachements(arr[z], subs);
          }
          for(int j=0;j<arr.length;j++) {
            Files.delete(arr[j].toPath());
          }
        }
        //now directory is empty
        Files.delete(source.toPath());

      } catch (IOException e) {
        // TODO Auto-generated catch block
        System.out.println(e);
      }


    }
  }

  @Override
  public void moveEmails(ILinkedList mails, IFolder des) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean compose(IMail email) {
    String Date=repeatedMethods.getDate();
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
    String path1 = "System" + fileSeparator + obj.getSender() + fileSeparator + "sent";//path to sent folder
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
      String indexpath=file1.getAbsolutePath();
      String indexText1 = repeatedMethods.indexMessage(obj.getSender(), receivers, obj.getSubject(), obj.getImportance(),Date,nameOfAttachements ,indexpath);
      repeatedMethods.writeInIndex(path1, indexText1);
      // the sent folder is finished
      //now recievers turn
      int size=obj.receiver.size();
      for(int i = 0; i < size; i++) {
        System.out.println(obj.receiver.size());
        String pathx = "System" + fileSeparator + obj.receiver.dequeue() + fileSeparator + "inbox";
        String pathx2 = pathx + fileSeparator + Date;//files in inbox will be teh date of creation of Mail.
        String indexText2=repeatedMethods.indexMessage(obj.getSender(), receivers, obj.getSubject(), obj.getImportance(),Date , nameOfAttachements, pathx2);
        repeatedMethods.writeInIndex(pathx, indexText2);//write index message in indexfile.

        File newFile = new File(pathx2);
        int counter = 0;
        while(!newFile.mkdir()) {//if same sender send an email
          newFile = new File(pathx2 + counter);
          counter++;
        }
        String pathx3 = newFile.getAbsolutePath() + fileSeparator + "component.txt";
        File fileComponent=new File(pathx3);
        fileComponent.createNewFile();
        String recieverComponent = repeatedMethods.textIninbox(obj.getSender(), obj.getSubject(), obj.getImportance(), obj.getComponent(),Date );
        repeatedMethods.WriteInFile(recieverComponent, pathx3);
        for(int j = 0; j < obj.attachements.size() ; j++) {
          File file = (File)obj.attachements.get(j);
          File newFilex = new File(newFile.getAbsoluteFile()+fileSeparator+file.getName());
          if(newFilex.createNewFile()) {
            repeatedMethods.transferAttachements(file, newFilex);}
        }
      }
    }catch(Exception e) {
      System.out.println(e);
    }
    return true;
  }





}
