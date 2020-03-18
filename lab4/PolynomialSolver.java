package eg.edu.alexu.csd.datastructure;
import eg.edu.alexu.csd.datastructure.DLinkedList.Node;
import java.util.HashMap;
import java.math.*;
public class PolynomialSolver implements IPolynomialSolver {

  public static void main(String[] args) {
    PolynomialSolver test = new PolynomialSolver();
    int[][] function = { {1,2},{1,4},{-5,6} };
    test.setPolynomial('A',function);
    test.print('A');
    System.out.println(test.evaluatePolynomial('A',2));



  }
  private class term {
    int coeff;
    int exp;
  }
  public HashMap<Character, Node> polynomials=new HashMap<>();


  @Override
  public void setPolynomial(char poly, int[][] terms) {
    DLinkedList newLL = new DLinkedList(); // new DLinkedList to store the polynomial expression


    for (int[] term : terms) { //loop to go over the "terms" array and store it in a "term" object
      term termTemp = new term();
      termTemp.coeff = term[0];
      termTemp.exp = term[1];
      newLL.add(termTemp);
    }

    polynomials.put(poly,newLL.head);
  }

  @Override
  public String print(char poly) {
    Node temp = polynomials.get(poly);
    String expression;
    term currentTerm = (term) temp.element;
    if(currentTerm.coeff==1)
      expression = "x^"  + currentTerm.exp;
    else
      expression = currentTerm.coeff + "x^"  + currentTerm.exp;
    temp = temp.next;
    while (temp != null){
      currentTerm = (term) temp.element;
      if (currentTerm.coeff<0){
        expression += currentTerm.coeff + "x^"  + currentTerm.exp  ;
      }
      else if(currentTerm.coeff==1){
        expression += "+" +  "x^"  + currentTerm.exp;
      }
      else {
        expression += "+" + currentTerm.coeff + "x^"  + currentTerm.exp;
      }
      temp = temp.next;
    }
    System.out.println(expression);
    return expression;
  }

  @Override
  public void clearPolynomial(char poly) {
    polynomials.remove(poly);
  }

  @Override
  public float evaluatePolynomial(char poly, float value) {
    double result = 0;
    Node temp = polynomials.get(poly);
    term currentTerm;
    while (temp != null){
      currentTerm = (term) temp.element;
      result += currentTerm.coeff * Math.pow(value,currentTerm.exp) ;
      temp = temp.next;
    }
    return (float) result;
  }

  @Override
  public int[][] add(char poly1, char poly2) {
    return new int[0][];
  }

  @Override
  public int[][] subtract(char poly1, char poly2) {
    return new int[0][];
  }

  @Override
  public int[][] multiply(char poly1, char poly2) {
    return new int[0][];
  }
}
