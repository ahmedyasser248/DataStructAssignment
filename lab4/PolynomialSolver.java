package eg.edu.alexu.csd.datastructure;
import eg.edu.alexu.csd.datastructure.DLinkedList.Node;
import java.util.HashMap;
import java.math.*;
import java.util.regex.Pattern;

public class PolynomialSolver implements IPolynomialSolver {

  public static void main(String[] args) {
    PolynomialSolver test = new PolynomialSolver();
    int[][] function = { {9,0},{-2,1},{5,3} };
    int[][] function1 = { {1,0},{-1,2},{2,3} };
    test.setPolynomial('A',function);
    test.setPolynomial('B',function1);
    test.print('A');
    System.out.println(test.evaluatePolynomial('A',2));
    test.multiply('A','B');
    test.print('M');
  }
  public class term {
    int coeff;
    int exp;
    term(int coeff,int exp){
      this.coeff=coeff;
      this.exp=exp;
    }
    term(){};
  }
  public HashMap<Character, Node> polynomials=new HashMap<>();

  void removeDuplicates(DLinkedList result) {
    Node temp1=result.head;
    Node temp2;
    term currentTerm1;
    term currentTerm2;
    while(temp1!=null) {
      temp2=temp1.next;
      currentTerm1=(term)temp1.element;
      while(temp2!=null) {
        currentTerm2=(term)temp2.element;
        if(currentTerm1.exp==currentTerm2.exp) {
          currentTerm1.coeff=currentTerm1.coeff+currentTerm2.coeff;
          Node temp3=temp2.prev;
          Node temp4=temp2.next;
          temp3.next=temp4;
          temp4.prev=temp3;
          result.size--;
        }
        temp2=temp2.next;
      }
      temp1=temp1.next;
    }
  }
  public static void sort2dArray(int[][] terms) {
    int temp1;int temp2;
    for(int i=0;i<terms.length;i++) {
      for(int j=i;j<terms.length;j++) {
        if(terms[i][1]<terms[j][1]) {
          temp1=terms[i][1];
          temp2=terms[i][0];
          terms[i][1]=terms[j][1];
          terms[i][0]=terms[j][0];
          terms[j][1]=temp1;
          terms[j][0]=temp2;
        }
      }
    }
  }
  public  int [][]   change(String input) {
    int count1=0;int count2=0;int rows=0;
    String [] help=input.split("[\\ |\\(|\\)|\\,]");
    for(int i = 0; i<help.length;i++) {
      if(Pattern.matches("[-?0-9]+",help[i])) {
        count1++;}
    }
    int [][] array=new int[count1/2][2];
    for(int i = 0; i<help.length;i++) {
      if(Pattern.matches("[-?0-9]+",help[i]) && count2 % 2 == 0) {
        array[rows][0]=Integer.parseInt(help[i]);
        count2++;
      }
      else if(Pattern.matches("[-?0-9]+",help[i])&& count2 % 2 == 1) {
        array[rows][1]=Integer.parseInt(help[i]);
        rows++;
        count2++;
      }
    }
    PolynomialSolver test=new PolynomialSolver();
    test.sort2dArray(array);
    return array;
  }
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
    if(currentTerm.exp!=0) {
      if (currentTerm.coeff == 1)
        expression = "x^" + currentTerm.exp;
      else
        expression = currentTerm.coeff + "x^" + currentTerm.exp;
    }
    else {
      expression = String.valueOf(currentTerm.coeff);
    }

    temp = temp.next;
    while (temp != null){
      currentTerm = (term) temp.element;
      if(currentTerm.exp!=0) {
        if (currentTerm.coeff < 0) {
          expression += currentTerm.coeff + "x^" + currentTerm.exp;
        } else if (currentTerm.coeff == 1) {
          expression += "+" + "x^" + currentTerm.exp;
        } else {
          expression += "+" + currentTerm.coeff + "x^" + currentTerm.exp;
        }
      }
      else{
        if (currentTerm.coeff < 0) {
          expression += String.valueOf(currentTerm.coeff);
        }
        else {
          expression += "+" + currentTerm.coeff;
        }
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
    Node temp1=polynomials.get(poly1);//head of first linked list,will be used to traverse the linkedlist.
    Node temp2=polynomials.get(poly2);//head of second linked list.
    DLinkedList Result=new DLinkedList();
    term currentTerm1=(term)temp1.element;
    term currentTerm2=(term)temp2.element;
    while(temp2 != null && temp1 != null) {
      currentTerm1=(term)temp1.element;
      currentTerm2=(term)temp2.element;
      if(currentTerm1.exp==currentTerm2.exp) {
        int sumOfCoeff=currentTerm1.coeff+currentTerm2.coeff;
        int sumOfExp=currentTerm1.exp;
        term termResult=new term(sumOfCoeff,sumOfExp);
        Result.add(termResult);
        temp1=temp1.next;
        temp2=temp2.next;
      }else if(currentTerm2.exp>currentTerm1.exp) {
        term termResult=new term(currentTerm2.coeff,currentTerm2.exp);
        Result.add(termResult);
        temp2=temp2.next;
      }else if(currentTerm1.exp>currentTerm2.exp) {
        term termResult=new term(currentTerm1.coeff,currentTerm1.exp);
        Result.add(termResult);
        temp1=temp1.next;
      }
    }//one of them is finished so let's check
    while(temp2!=null) {
      currentTerm2=(term)temp2.element;
      term termResult=new term(currentTerm2.coeff,currentTerm2.exp);
      Result.add(termResult);
      temp2=temp2.next;

    }
    while(temp1 != null) {
      currentTerm1=(term)temp1.element;
      term termResult=new term(currentTerm1.coeff,currentTerm1.exp);
      Result.add(termResult);
      temp1=temp1.next;
    }
    polynomials.put('R', Result.head);//set it in the hashtable.
    //now we transfer data in linkedlist result to 2d array.
    int [][] sumArray=new int[Result.size][2];
    Node temp3=Result.head;
    term currentTerm=(term)temp3.element;
    for(int i=0;i<Result.size&&temp3!=null;i++) {
      currentTerm=(term)temp3.element;
      sumArray[i][0]=currentTerm.coeff;
      sumArray[i][1]=currentTerm.exp;
      temp3=temp3.next;
    }
    return sumArray;
  }

  @Override
  public int[][] subtract(char poly1, char poly2) {
    Node temp1=polynomials.get(poly1);//head of first linked list,will be used to traverse the linkedlist.
    Node temp2=polynomials.get(poly2);//head of second linked list.
    DLinkedList Result=new DLinkedList();
    term currentTerm1=(term)temp1.element;
    term currentTerm2=(term)temp2.element;
    while(temp2 != null && temp1 != null) {
      currentTerm1=(term)temp1.element;
      currentTerm2=(term)temp2.element;
      if(currentTerm1.exp==currentTerm2.exp) {
        int diffOfCoeff=currentTerm1.coeff-currentTerm2.coeff;
        int sumOfExp=currentTerm1.exp;
        if(diffOfCoeff!=0) {
          term termResult = new term(diffOfCoeff, sumOfExp);
          Result.add(termResult);
        }
        temp1=temp1.next;
        temp2=temp2.next;
      }else if(currentTerm2.exp>currentTerm1.exp) {
        term termResult=new term(-currentTerm2.coeff,currentTerm2.exp);
        Result.add(termResult);
        temp2=temp2.next;
      }else if(currentTerm1.exp>currentTerm2.exp) {
        term termResult=new term(currentTerm1.coeff,currentTerm1.exp);
        Result.add(termResult);
        temp1=temp1.next;
      }
    }//one of them is finished so let's check
    while(temp2!=null) {
      currentTerm2=(term)temp2.element;
      term termResult=new term(-currentTerm2.coeff,currentTerm2.exp);
      Result.add(termResult);
      temp2=temp2.next;

    }
    while(temp1 != null) {
      currentTerm1=(term)temp1.element;
      term termResult=new term(currentTerm1.coeff,currentTerm1.exp);
      Result.add(termResult);
      temp1=temp1.next;
    }
    polynomials.put('R', Result.head);  //set it in the hashtable.
    //now we transfer data in linkedlist result to 2d array.
    int [][] sumArray=new int[Result.size][2];
    Node temp3=Result.head;
    term currentTerm=(term)temp3.element;
    for(int i=0;i<Result.size&&temp3!=null;i++) {
      currentTerm=(term)temp3.element;
      sumArray[i][0]=currentTerm.coeff;
      sumArray[i][1]=currentTerm.exp;
      temp3=temp3.next;
    }
    return sumArray;
  }

  @Override
  public int[][] multiply(char poly1, char poly2) {
    Node temp1=polynomials.get(poly1);//head of first linked list,will be used to traverse the linkedlist.
    Node temp2=polynomials.get(poly2);//head of second linked list.
    DLinkedList Result=new DLinkedList();
    while(temp1 != null){
      term currentTerm1=(term)temp1.element;
      while (temp2 != null){
        int coeff , power;


        term currentTerm2=(term)temp2.element;
        coeff = currentTerm1.coeff * currentTerm2.coeff;
        power = currentTerm1.exp + currentTerm2.exp;
        term resultTerm = new term(coeff,power);
        Result.add(resultTerm);
        temp2 = temp2.next;
      }
      temp2 = polynomials.get(poly2);
      temp1 = temp1.next;
    }
    removeDuplicates(Result);

    int [][] productArray=new int[Result.size][2];
    Node temp3=Result.head;
    term currentTerm=(term)temp3.element;
    for(int i=0;i<Result.size&&temp3!=null;i++) {
      currentTerm=(term)temp3.element;
      productArray[i][0]=currentTerm.coeff;
      productArray[i][1]=currentTerm.exp;
      temp3=temp3.next;
    }
    sort2dArray(productArray);
    setPolynomial('M',productArray);
    return productArray;
  }
}
