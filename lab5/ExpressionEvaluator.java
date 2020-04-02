package eg.edu.alexu.csd.datastructure.stack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator implements IExpressionEvaluator {

  public static void main(String[] args) {
    ExpressionEvaluator trial = new ExpressionEvaluator();
    String input = "6 2 / 3 - 4 22 * +";
//    input = trial.infixToPostfix(input);
//    System.out.println(input);
    System.out.println(trial.evaluate(input));
  }
  static int precedence(String c){
    switch (c){
      case "+":
      case "-":
        return 1;
      case "*":
      case "/":
        return 2;
    }
    return -1;
  }
  @Override
  public String infixToPostfix(String expression) {
    String newS = expression.replaceAll(" ","");
    newS = newS.replaceAll("([+|\\-|*|/])[-]([a-zA-Z0-9]+)","$1(0-$2)").replaceAll("^[-]([a-zA-Z0-9]+)","(0-$1)");
    StringBuffer postfix = new StringBuffer();
    Stack stack = new Stack();
    expression = newS;

    String simplified = newS.replaceAll("[a-zA-Z0-9]+","T");
    String[] terms = newS.replaceAll("[(|)]","").split("[+\\-*/]");
    int termsCounter = 0;
    String[] chars = simplified.split("");
    for (int i = 0; i <simplified.length(); i++) {
      if(chars[i].equals("T")){
        postfix.append(terms[termsCounter++]);
        postfix.append(" ");
      }
      if(chars[i].matches("[+\\-*/]")){
        if(stack.size==0)stack.push(chars[i]);
        else{
          if(precedence(chars[i])>=precedence((String) stack.peek()))stack.push(chars[i]);
          if(precedence(chars[i])< precedence((String) stack.peek())){
            while(!(stack.isEmpty()) && precedence(chars[i])< precedence((String) stack.peek())){
              postfix.append(stack.pop());
              postfix.append(" ");
            }
            stack.push(chars[i]);
          }
        }
      }
      if(chars[i].equals("("))stack.push(chars[i]);
      if(chars[i].equals(")")){
        while (!(stack.peek().equals("("))){
          postfix.append(stack.pop());
          postfix.append(" ");
        }
        stack.pop();
      }
    }
    while(stack.isEmpty() == false){
      postfix.append(stack.pop());
    }
    System.out.println(postfix.toString());
    System.out.println(newS);
    System.out.println(Arrays.toString(terms));
    System.out.println(simplified);
    return newS;
  }
  @Override
  public int evaluate(String expression) {
    String[] operAndOperands = expression.split(" ");
    Stack stack = new Stack();
    float result = 0 ;
    for (int i = 0; i < operAndOperands.length  ; i++){
      if (operAndOperands[i].matches("[*+/\\-]")){
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
            result = firstOperand / secondOperand;break;
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
