package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mohamed Osama Ahmed Elsamni.
 */
public class UIApplication {

  /**
   * the application's entry point.
   * takes a symbolic or numeric expression  consisting of one or more digit or letter
   * and converts them to postfix notation.
   * replaces symbols with desired values if found.
   * @param args
   * command-line statements
   */
  public static void main(String[] args) {
    Boolean repeat = true;
    while(repeat) {
      System.out.println("Enter a valid symbolic or number mathematical expression:");
      Scanner input = new Scanner(System.in);
      String expression = input.nextLine();
      ExpressionEvaluator app = new ExpressionEvaluator();
      expression = app.infixToPostfix(expression);
      expression = symbolicToNumeric(expression);
      System.out.println(app.evaluate(expression));
      switch (menu()){
        case 1:
          break;
        case 2:
          repeat = false;
        default:
          throw new RuntimeException("invalid option.");
      }

    }
  }

  /**
   * prints a menu of options for the user to choose from.
   * @return choice
   * the user's chosen option.
   */
  static int menu(){
    System.out.println("Please choose an action\n ----------------------- \n 1- Enter another expression \n 2- Exit \n");
    System.out.println("====================================================================\n");
    Scanner in = new Scanner(System.in);
    int choice = in.nextInt();
    in.nextLine();
    return choice;
  }

  /**
   * takes a user submitted expression and looks for
   * symbols to replace with desired numeric values.
   *
   * @param expression
   * the expression given by the user.
   * @return expression
   * the symbol-free expression
   */
  static String symbolicToNumeric(String expression){
    Scanner in = new Scanner(System.in);

    Pattern pattern = Pattern.compile("[a-zA-Z]+");
    Matcher matcher = pattern.matcher(expression);
    while(matcher.find()){
      System.out.println("enter the value of the symbol "+matcher.group());
      String value = in.nextLine();
      expression = expression.replace(matcher.group(),value);

    }
    System.out.println(expression);
    return expression;
  }
}
