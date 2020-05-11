package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class ExpressionEvaluatorTest {
  @Test
  void testMethods() {
    ExpressionEvaluator testObject = new ExpressionEvaluator();

    String testString = "6+3-2*4/1+2";
    assertEquals("6 3 + 2 4 * 1 / - 2 + ", testObject.infixToPostfix(testString));
    assertEquals(3, testObject.evaluate(testObject.infixToPostfix(testString)));

    testString = "(23--2)*3  +5/2";
    assertEquals("23 0 2 - - 3 * 5 2 / + ", testObject.infixToPostfix(testString));
    assertEquals(77, testObject.evaluate(testObject.infixToPostfix(testString)));

    testString = "ab + cde * xyz / (lmn - opr)";
    assertEquals("ab cde xyz * lmn opr - / + ", testObject.infixToPostfix(testString));

    String finalTestString1 = "((abc-def)";       //extra left parenthesis
    assertThrows(RuntimeException.class, () -> {
      testObject.infixToPostfix(finalTestString1); });

    String finalTestString2 = "(abc*mno))";       //extra right parenthesis
    assertThrows(RuntimeException.class, () -> {
      testObject.infixToPostfix(finalTestString2); });

    String finalTestString3 = "abcde + * fgh";    //two consecutive operators
    assertThrows(RuntimeException.class, () -> {
      testObject.infixToPostfix(finalTestString3); });

    String finalTestString4 = "abc - fge";        //non-numeric input in evaluation
    assertThrows(RuntimeException.class, () -> {
      testObject.evaluate(finalTestString4); });

    String finalTestString5 = "5 $ - ";        //special characters input
    assertThrows(RuntimeException.class, () -> {
      testObject.evaluate(finalTestString5); });
  }
}