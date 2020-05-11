package eg.edu.alexu.csd.datastructure.stack;
import java.util.Arrays;
public class ExpressionEvaluator implements IExpressionEvaluator {
  /**
   * returns a specific integer indicating the precedence
   * of a given operator to compare it against another operator.
   *
   * @param c
   * the operator whose precedence we want to know
   * @return the precedence of given operator
   */
  static int precedence(String c){
    char c1 = c.charAt(0);
    switch (c1){
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
    }
    return -1;
  }
  @Override
  public String infixToPostfix(String expression) {
    String newS = expression.replaceAll(" ","");
    newS = newS.replaceAll("([+\\-*/(])[-]([a-zA-Z0-9]+)","$1(0-$2)").replaceAll("^[-]([a-zA-Z0-9]+)","(0-$1)");
    StringBuffer postfix = new StringBuffer();
    Stack stack = new Stack();
    String simplified = newS.replaceAll("[a-zA-Z0-9]+","T");
    String[] terms = newS.replaceAll("[(|)]","").split("[+\\-*/]");
    int termsCounter = 0;
    String[] chars = simplified.split("");
    for (int i = 0; i <simplified.length(); i++) {
      if(chars[i].equals("T")){
        if(terms[termsCounter].equals(""))throw new RuntimeException("invalid expression - two consecutive operators");
        if( !(terms[termsCounter].matches("[a-zA-Z]+"))&&!(terms[termsCounter].matches("[0-9]+")))throw new RuntimeException("invalid expression - characters and digits in one term");
        postfix.append(terms[termsCounter++]);
        postfix.append(" ");
      }
      else if(chars[i].matches("[+\\-*/]")){
        if(stack.size==0){
          stack.push(chars[i]);
        }
        else{

            while(!(stack.isEmpty()) && precedence(chars[i])<= precedence((String) stack.peek())){
              postfix.append(stack.pop());
              postfix.append(" ");
            }
            stack.push(chars[i]);
        }
      }
      else if(chars[i].equals("("))stack.push(chars[i]);
      else if(chars[i].equals(")")){
        try{
        while (!(stack.peek().equals("("))) {

          postfix.append(stack.pop());
          postfix.append(" ");
        }
        } catch (Exception e) {

          throw new RuntimeException("a right parenthesis with no closing one");
        }
        stack.pop();
      }
    }
    while(stack.isEmpty() == false){
      if(stack.peek().equals("("))throw new RuntimeException("a left parenthesis with no closing one");
      postfix.append(stack.pop());
      postfix.append(" ");
    }
    System.out.println(postfix.toString());
    System.out.println(newS);
    System.out.println(Arrays.toString(terms));
    System.out.println(simplified);
    return postfix.toString();
  }


  @Override
  public int evaluate(String expression) {
    String[] operAndOperands = expression.split(" ");
    Stack stack = new Stack();
    float result = 0 ;
    for (int i = 0; i < operAndOperands.length  ; i++){
      if (operAndOperands[i].matches("[*+/\\-]")){
        try {
          float secondOperand = (float) stack.pop();
          float firstOperand = (float) stack.pop();

        switch (operAndOperands[i]){
          case "+":
            result = firstOperand + secondOperand;break;
          case "-":
            result = firstOperand - secondOperand;break;
          case "*":
            result = firstOperand * secondOperand;break;
          case "/":
            if(secondOperand==0)
              throw new RuntimeException("division by zero");
            result = firstOperand / secondOperand;break;

        }
        } catch (Exception e){
          throw new RuntimeException("number of operators greater than operands");
        }
        stack.push(result);
      }
      else if(operAndOperands[i].matches("[0-9]+")){
        stack.push(Float.parseFloat(operAndOperands[i]));
      }
      else
        throw new RuntimeException("invalid expression");
    }

    return (int) Float.parseFloat(stack.pop().toString());
  }
}
