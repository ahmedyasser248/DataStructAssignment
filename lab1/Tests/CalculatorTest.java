import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {
	Calculator x = new Calculator();
	@Test
	void testAdd() {
		assertEquals(99,x.add(62,37));
		assertEquals(66666,x.add(12345,54321));
	}

	@Test
	void testDivide() {
		assertEquals(0.0,x.divide(0, 5));
		assertEquals(0.0,x.divide(0, -5));
		assertEquals(2.5,x.divide(5, 2));
		assertEquals(5.0,x.divide(5, 1));
		assertThrows(RuntimeException.class,()-> x.divide(5, 0));
	}

}
