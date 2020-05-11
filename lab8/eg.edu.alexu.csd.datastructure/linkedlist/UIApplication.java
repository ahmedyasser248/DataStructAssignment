package eg.edu.alexu.csd.datastructure.linkedlist;

import java.util.Arrays;
import java.util.Scanner;

public class UIApplication {

  public static void main(String[] args) {
    int choice = 0;
    char polyChar1,polyChar2;
    PolynomialSolver solver = new PolynomialSolver();
    Scanner input = new Scanner(System.in);
    String inputPoly;
    while(choice != 9 ) {
      choice = menu();
      switch (choice) {
        case 1:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C'))
            throw new RuntimeException("Invalid Character");
          System.out.println("Insert the polynomial terms in the form:\n"
              + "(coeff1, exponent1), (coeff2, exponent2), ..");
          inputPoly = input.nextLine();
          if(solver.change(inputPoly)==null)
            throw new RuntimeException("Invalid String");
          solver.setPolynomial(polyChar1, solver.change(inputPoly));
          System.out.println("Polynomial " + polyChar1 + " is set");
          solver.print(polyChar1);
          break;
        case 2:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = Character.toUpperCase(input.next().charAt(0));
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar1 = isSet(polyChar1,solver);
          System.out.println("Value in "+polyChar1+": " +solver.print(polyChar1));
          break;
        case 3:
          System.out.println("Insert first operand variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar1 = isSet(polyChar1, solver);
          System.out.println("Insert second operand variable name: A, B or C");
          polyChar2 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar2 = isSet(polyChar2, solver);
          solver.add(polyChar1, polyChar2);
          System.out.println("Result set in R:" + Arrays.deepToString(solver.add(polyChar1, polyChar2)));
          break;
        case 4:
          System.out.println("Insert first operand variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar1 = isSet(polyChar1, solver);
          System.out.println("Insert second operand variable name: A, B or C");
          polyChar2 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar2 = isSet(polyChar2, solver);
          solver.add(polyChar1, polyChar2);
          System.out.println("Result set in R:" + Arrays.deepToString(solver.subtract(polyChar1, polyChar2)));
          break;
        case 5:
          System.out.println("Insert first operand variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar1 = isSet(polyChar1, solver);
          System.out.println("Insert second operand variable name: A, B or C");
          polyChar2 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar2 = isSet(polyChar2, solver);
          solver.add(polyChar1, polyChar2);
          System.out.println("Result set in R:" + Arrays.deepToString(solver.multiply(polyChar1, polyChar2)));
          break;
        case 6:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar1 = isSet(polyChar1,solver);
          System.out.println("Insert a point (float) for evaluation");
          float point = input.nextFloat();
          solver.evaluatePolynomial(polyChar1,point);
          break;
        case 7:
          System.out.println("Insert the variable name: A, B or C");
          polyChar1 = input.next().charAt(0);
          input.nextLine();
          if((polyChar1!='A') && (polyChar1!='B') && (polyChar1!='C' )&& (polyChar1 != 'R'))
            throw new RuntimeException("Invalid Character");
          polyChar1 = isSet(polyChar1,solver);
          solver.clearPolynomial(polyChar1);
          System.out.println("Variable "+polyChar1+" is cleared");
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + choice);
      }
      System.out.println("====================================================================\n");

    }
  }

  static int menu(){
    System.out.println("Please choose an action\n ----------------------- \n 1- Set a polynomial variable \n 2- Print the value of a polynomial variable \n 3- Add two polynomials \n 4- Subtract two polynomials \n 5- Multiply two polynomials \n 6- Evaluate a polynomial at some point\n"
        + " 7- Clear a polynomial variable\n");
    System.out.println("====================================================================\n");
    Scanner in = new Scanner(System.in);
    int choice = in.nextInt();
    return choice;
  }
  static char isSet(char inputChar,PolynomialSolver temp){
    if((inputChar!='A') && (inputChar!='B') && (inputChar!='C')&&(inputChar != 'R'))
      throw new RuntimeException("Invalid Character");
    boolean isSet = temp.polynomials.containsKey(inputChar);
    Scanner input1 = new Scanner(System.in);

    while (!isSet){

      System.out.println("Variable not set\nInsert another variable name: A, B or C");
      inputChar = input1.next().charAt(0);
      input1.nextLine();
      if((inputChar!='A') && (inputChar!='B') && (inputChar!='C')&& (inputChar != 'R'))
        throw new RuntimeException("Invalid Character");
      isSet = temp.polynomials.containsKey(inputChar);

    }

    return inputChar;
  }
}
