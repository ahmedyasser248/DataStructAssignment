package eg.edu.alexu.csd.datastructure.stack;
import java.util.Scanner;
/**
 * @author Mohamed Osama Ahmed Elsamni.
 */
public class UIStack {

  /**
   * the application's entry point.
   * performs operations on a stack according to user's input.
   * @param args
   * command-line statements
   */
  public static void main(String[] args) {
    int choice = 0;
    Stack stack = new Stack();
    Scanner input = new Scanner(System.in);
    while (choice != 6) {
      choice = menu();
      switch (choice) {
        case 1:
          System.out.println("enter your desired input:");
          String temp = input.nextLine();
          stack.push(temp);
          System.out.println(temp + " is added to the stack.");
          break;
        case 2:
          System.out.println("the popped item is: " + stack.pop());
          break;
        case 3:
          System.out.println("the top item in the stack is: " + stack.peek());
          break;
        case 4:
          System.out.println("the size of the stack is: " + stack.size());
          break;
        case 5:
          if (stack.isEmpty() == true) {
            System.out.println("the stack is empty.");
            break;
          }
          System.out.println("the stack isn't empty.");
          break;
        case 6:
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + choice);
      }

      System.out.println("====================================================================\n");
    }
    System.out.println("the program will exit.");
  }
  /**
   * prints a menu of options for the user to choose from.
   * @return choice
   * the user's chosen option.
   */
  static int menu(){
    System.out.println("Please choose an action\n ----------------------- \n 1- Push an Object to stack \n 2- Pop an Object from stack \n 3- See the top item in the stack \n 4- Get the current size of the stack \n 5- Check whether stack is empty or not\n"
    +" 6- Exit");
    System.out.println("====================================================================\n");
    Scanner in = new Scanner(System.in);
    int choice = in.nextInt();
    return choice;
  }
}
