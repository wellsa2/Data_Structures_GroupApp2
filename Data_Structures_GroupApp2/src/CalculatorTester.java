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
		ArrayList<String> expected1 = new ArrayList<String>();
		for(int i = 0; i < equation1.length(); i++) {
			expected1.add(equation1.substring(i, i+1));
		}//end for
		ArrayList<String> result1 = calc1.getEquationList();
		//TODO: wait for ExpressionParser to be completed. then uncomment printTest
		//printTest(true, "Testing String constructor with String " + equation1 + ". ArrayList is", result1.toString(), expected1.toString());
		
		System.out.println("\n----------\nTesting ArrayList<String> constructor");
		ArrayList<String> equation2 = expected1;
		Calculator calc2 = new Calculator(equation2);
		String expected2 = "(5*5*2*2)-(2*5)-(5*2*(2+3+4))";
		String result2 = calc2.getEquationString();
		printTest(true, "Testing ArrayList constructor with ArrayList" + equation2 + ". String is", result2, expected2);
	}//end testConstructors()
	
	private static void testGetters() {
		System.out.println("\n----------\nTesting getters");
		String equation = "(5*5*2*2)-(2*5)-(5*2*(2+3+4))";
		ArrayList<String> equationList = new ArrayList<String>();
		for(int i = 0; i < equation.length(); i++) {
			equationList.add(equation.substring(i, i+1));
		}//end for
		Calculator calc = new Calculator(equation);
		System.out.println("\n----------\nTesting getEquationList()");
		//TODO: wait for ExpressionParser to be completed. then uncomment printTest
		//printTest(true, "Testing getEquationList() with equation " + equation, equationList.toString(), calc.getEquationList().toString());
		System.out.println("\n----------\nTesting getEquationString()");
		printTest(true, "Testing getEquationString() with equation " + equation, equation, calc.getEquationString());
		System.out.println("\n----------\nTesting getResult()");
		String expected = Integer.toString((5*5*2*2)-(2*5)-(5*2*(2+3+4)));
		String result = calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
	}//end testGetters()
	
	private static void testToString() {
		System.out.println("\n----------\nTesting toString();");
		String equation = "(5*5*2*2)-(2*5)-(5*2*(2+3+4))";
		Calculator calc = new Calculator(equation);
		String result = calc.toString();
		String expected = "(5*5*2*2)-(2*5)-(5*2*(2+3+4)) = 0";
		printTest(true, "testing toString() with valid equation " + equation, result, expected);
	}//end tesToString
	
	private static void printTest(boolean isValid, String description, String recieved, String expected) {
		System.out.println(String.format("Is Valid: %s%nDescription: %s%nRecieved: %s%nExpected: %s%n", isValid, description, recieved, expected));
	}//end printTest()
}
