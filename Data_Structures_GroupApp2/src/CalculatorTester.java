import java.util.ArrayList;

/**
 * 
 * @author horowitzb
 *
 */
public class CalculatorTester {

	public static void main(String[] args) {
		System.out.println("Testing Calculator");
		testConstructors();
		testGetters();
		testToString();
	}//end main

	private static void testConstructors() {
		System.out.println("\n----------\nTesting default constructor");
		
		System.out.println("\n----------\nTesting String constructor");
		String equation1 = "(5*5*2*2)-(2*5)-(5*2*(2+3+4))";
		Calculator calc1 = new Calculator(equation1);
		printTest(true, "Testing String constructor with equation " + equation1, equation1, calc1.getEquationString());
	}//end testConstructors()
	
	private static void testGetters() {
		System.out.println("\n----------\nTesting getters");
		String equation = "(5*5*2*2)-(2*5)-(5*2*(2+3+4))";
		Calculator calc = new Calculator(equation);
		System.out.println("\n----------\nTesting getEquationString()");
		printTest(true, "Testing getEquationString() with equation " + equation, equation, calc.getEquationString());
		System.out.println("\n----------\nTesting getResult()");
		String expected = Integer.toString((5*5*2*2)-(2*5)-(5*2*(2+3+4)));
		String result = calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "0 + 12345";
		calc.setEquationString(equation);
		expected = "12345";
		result= calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "1/0";
		calc.setEquationString(equation);
		expected = "Java.lang.ArithmeticException: / by zero";
		result= calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "5++5";
		calc.setEquationString(equation);
		expected = "Error: cannot have two adjacent signs";
		result= calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "5+7)";
		calc.setEquationString(equation);
		expected = "java.util.EmptyStackException Make sure all parenthesis have a matching pair";
		result= calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "(5+7";
		calc.setEquationString(equation);
		expected = "java.util.EmptyStackException Make sure all parenthesis have a matching pair";
		result= calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
	}//end testGetters()
	
	private static void testToString() {
		System.out.println("\n----------\nTesting toString();");
		String equation = "(5*5*2*2)-(2*5)-(5*2*(2+3+4))";
		Calculator calc = new Calculator(equation);
		String result = calc.toString();
		String expected = "(5*5*2*2)-(2*5)-(5*2*(2+3+4)) = 0";
		printTest(true, "testing toString() with valid equation " + equation, result, expected);
		
		equation = "0 + 12345";
		calc.setEquationString(equation);
		expected = "0 + 12345 = 12345";
		result= calc.toString();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "1/0";
		calc.setEquationString(equation);
		expected = "1/0 = Java.lang.ArithmeticException: / by zero";
		result= calc.toString();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "5++5";
		calc.setEquationString(equation);
		expected = "5++5 = Error: cannot have two adjacent signs";
		result= calc.toString();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "5+7)";
		calc.setEquationString(equation);
		expected = "5+7) = java.util.EmptyStackException Make sure all parenthesis have a matching pair";
		result= calc.toString();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
		
		equation = "(5+7";
		calc.setEquationString(equation);
		expected = "(5+7 = java.util.EmptyStackException Make sure all parenthesis have a matching pair";
		result= calc.toString();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
	}//end tesToString
	
	private static void printTest(boolean isValid, String description, String recieved, String expected) {
		System.out.println(String.format("Is Valid: %s%nDescription: %s%nRecieved: %s%nExpected: %s%n", isValid, description, recieved, expected));
	}//end printTest()
}
