import eg.edu.alexu.csd.datastructure.PolynomialSolver;
import java.util.Arrays;
import java.util.Scanner;

public class UIApplication {

  public static void main(String[] args) {
    int choice = 0;
    char polyChar1,polyChar2;
    boolean isSet = false;
    PolynomialSolver solver = new PolynomialSolver();
    Scanner input = new Scanner(System.in);
    String inputPoly;
    while(choice != 9 ) {
      choice = menu();
      switch (choice) {
        case 1:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          System.out.println("Insert the polynomial terms in the form:\n"
              + "(coeff1, exponent1), (coeff2, exponent2), ..");
          input.nextLine();
          inputPoly = input.nextLine();
          solver.setPolynomial(polyChar1, solver.change(inputPoly));
          System.out.println("Polynomial " + polyChar1 + " is set");
          solver.print(polyChar1);
          break;
        case 2:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          System.out.println("Value in "+polyChar1+": " +solver.print(polyChar1));
          break;
        case 3:
          System.out.println("Insert first operand variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          polyChar1 = isSet(polyChar1, solver);
          System.out.println("Insert second operand variable name: A, B or C");
          polyChar2 = input.next().charAt(0);
          input.nextLine();
          polyChar2 = isSet(polyChar2, solver);
          solver.add(polyChar1, polyChar2);
          System.out.println("Result set in R:" + Arrays.deepToString(solver.add(polyChar1, polyChar2)));
          break;
        case 4:
          System.out.println("Insert first operand variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          polyChar1 = isSet(polyChar1, solver);
          System.out.println("Insert second operand variable name: A, B or C");
          polyChar2 = input.next().charAt(0);
          input.nextLine();
          polyChar2 = isSet(polyChar2, solver);
          solver.add(polyChar1, polyChar2);
          System.out.println("Result set in R:" + Arrays.deepToString(solver.subtract(polyChar1, polyChar2)));
          break;
        case 5:
          System.out.println("Insert first operand variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          polyChar1 = isSet(polyChar1, solver);
          System.out.println("Insert second operand variable name: A, B or C");
          polyChar2 = input.next().charAt(0);
          input.nextLine();
          polyChar2 = isSet(polyChar2, solver);
          solver.add(polyChar1, polyChar2);
          System.out.println("Result set in R:" + Arrays.deepToString(solver.multiply(polyChar1, polyChar2)));
        case 6:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          System.out.println("Insert a point (float) for evaluation");
          float point = input.nextFloat();
          solver.evaluatePolynomial(polyChar1,point);
        case 7:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          solver.clearPolynomial(polyChar1);
          System.out.println("Variable "+polyChar1+" is cleared");
        default:
          throw new IllegalStateException("Unexpected value: " + choice);
      }
      System.out.println("====================================================================");

    }
  }

  static int menu(){
    System.out.println("Please choose an action\n ----------------------- \n 1- Set a polynomial variable \n 2- Print the value of a polynomial variable \n 3- Add two polynomials \n 4- Subtract two polynomials \n 5- Multiply two polynomials \n 6- Evaluate a polynomial at some point\n"
        + " 7- Clear a polynomial variable\n");
    Scanner in = new Scanner(System.in);
    int choice = in.nextInt();
    return choice;
  }
  static char isSet(char inputChar,PolynomialSolver temp){
    boolean isSet = temp.polynomials.containsKey(inputChar);
    Scanner input = new Scanner(System.in);
    while (!isSet){

      System.out.println("Variable not set\nInsert another variable name: A, B or C");
      inputChar = input.next().charAt(0);
      input.nextLine();
      isSet = temp.polynomials.containsKey(inputChar);
    }
    return inputChar;
  }
}
