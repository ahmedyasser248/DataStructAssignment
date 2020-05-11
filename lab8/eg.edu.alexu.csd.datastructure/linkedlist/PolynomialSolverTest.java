package eg.edu.alexu.csd.datastructure.linkedlist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomialSolverTest {

  @Test
  void WrongInput() {
		/* the method responsible to change string to 2d array.
		 if the input string is invalid it will return null. */
    PolynomialSolver test=new PolynomialSolver();
    String test1="(ddfa,fda)";
    assertNull(test.change(test1));
    test1="( , ),(34_+3,32)";
    assertNull(test.change(test1));
    test1="       ";
    assertNull(test.change(test1));
    test1="   13431   13413";
    assertNull(test.change(test1));
    test1="";
    assertNull(test.change(test1));
  }

  @Test
  void operationOnPolynomial(){
    PolynomialSolver test=new PolynomialSolver();
    String test1="(2,3),(4,5),(0,5)";
    String test2="(-2,3),(-6,7),(-4,5)";
    int[][]array1=test.change(test1);
    int[][]array2=test.change(test2);
    test.setPolynomial('A', array1);
    String A=test.print('A');
    test.setPolynomial('B', array2);
    String B=test.print('B');
    assertEquals("4x^5+2x^3",A);
    assertEquals("-6x^7-4x^5-2x^3",B);
    //testing the sum of arrays.
    int[][] arraySum=test.add('A','B' );
    test.setPolynomial('R', arraySum);
    String R=test.print('R');
    assertEquals("-6x^7",R);
    //testing difference  between arrays
    int[][] arrayDifference=test.subtract('A','B');
    test.setPolynomial('R', arrayDifference);
    R= test.print('R');
    assertEquals("6x^7+8x^5+4x^3",R);
    //test multiply
    int[][] arrayMultiply=test.multiply('A', 'B');
    test.setPolynomial('R', arrayMultiply);
    R=test.print('R');
    assertEquals("-24x^12-28x^10-16x^8-4x^6",R);


  }
}
