package com.Pteropticon.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class CalculatorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void CalculatorTest() {
		 final Calculator calculator = new Calculator();
			assertEquals(5, calculator.sum(2,3));

	}


}
