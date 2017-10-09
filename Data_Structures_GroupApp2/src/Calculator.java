import java.util.EmptyStackException;

//Comp 2000-03
//VectorStack Application 2
//Group 32
// Brandon Horowitz, Aiden Wells
// 10/9/2017

/**
 * A calculator for integers that follows order of operations.
 * Can compute operands: '+','-','/', & '*' operands and parentheses.
 * 
 * @author Brandon Horowitz
 * @author Aiden Wells
 */
public class Calculator
{
	
	//instance variables
	private String 	equationString;
	private Boolean initialized;
	
	/**
	 * empty constructor
	 * @author horowitzb
	 */
	public Calculator()
	{
		initialized = false;
	}//end Constructor
	
	/**
	 * String constructor
	 * @param toParse
	 * @author horowitzb
	 */
	public Calculator( String toParse )
	{
		equationString = 	toParse;
		initialized = 		true;
	}//end Constructor

	/**
	 * sets equationString
	 * @param equation
	 * @author horowitzb
	 */
	public void setEquationString(String equation) {
		equationString = equation;
		initialized = true;
	}//end setEquation
	
	/**
	 * gets equationString
	 * @return equationString
	 * @author horowitzb
	 */
	public String getEquationString()
	{
		checkInitialization();
		return equationString;
	}//end getEquationString
	
	/**
	 * gets solved equation
	 * @return string form of the result. may return error messages, but will not crash
	 * @author wellsa
	 * @author horowitzb
	 */
	public String getResult()
	{
		checkInitialization();
		
		VectorStack<Character> operands = 			new VectorStack<Character>();
		VectorStack<Integer> numbers = 				new VectorStack<Integer>();
		VectorStack<Boolean> tempHasSignToCompare = new VectorStack<Boolean>();
		
		boolean lastWasNum = 			false;	//true if last character was a number
		boolean hasSignToCompare = 		false;	//true if a previous operand has yet to be computed in the string
		char current;							//current character in iterator
		
		try 
		{
			for ( int i = 0; i < equationString.length(); i++ )
			{
				current = equationString.charAt( i );
				
				if ( current == ' ' )
				{
					//keep empty. makes sure spaces aren't treated as an error
				}//end if
				else if ( lastWasNum )
				{
					if ( Character.isDigit( current ) ) //multi-digit numbers
					{
						int toFormat = numbers.pop();
						toFormat = toFormat * 10 + current - '0';
						numbers.push( toFormat );
					}//end if
					else if ( current == ')' )
					{
						while ( operands.peek() != '(' )
						{
							solveEquation( numbers, operands );
						}//end while
						operands.pop(); //removes '(' from operands stack
						hasSignToCompare = tempHasSignToCompare.pop();
					}//end if
					else if ( isSign( current ) )
					{
						lastWasNum = false;
						if ( hasSignToCompare )
						{
							if ( peekedHasPrecedence (	operands.peek (), 	//solve peeked if it has precedence,
														current ) )			//otherwise push current operand to stack
							{
								solveEquation( numbers, operands );
								operands.push( current );
							}//end if
							else //peeked did not have precedence
							{
								operands.push( current );
							}//end else
						}//end if
						else //there was no previous sign to compare
						{
							operands.push( current ); //push current operand to stack and set corresponding boolean
							hasSignToCompare = true;
						}//end else
					}//end else if
					else
					{
						return "Error: Invalid character entered";
					}//end else
				}//end if
				else //lastWasNum = false
				{
					if ( Character.isDigit( current ) )
					{
						lastWasNum = true;
						numbers.push( current - '0' );
					}//end if
					else if ( current == '(' )
					{
						operands.push( current );
						tempHasSignToCompare.push(hasSignToCompare);
						hasSignToCompare = false;
					}//end else if
					else if ( isSign( current ) )
					{
						return "Error: cannot have two adjacent signs";
					}//end else if
					else
					{
						return "Error: Invalid character entered";
					}//end else
				}//end else
			}//end for
			while ( !operands.isEmpty() )
			{
				solveEquation( numbers, operands );
			}//end while
		}//end try
		catch (ArithmeticException ae) 
		{
			return ae.toString();
		}//end catch ArithmeticException
		catch (EmptyStackException ese) {
			return ese.toString() + " Make sure all parenthesis have a matching pair";
		}//end catch EmptyStackException
		return numbers.pop().toString();
	}//end getResult
	
	/**
	 * returns string representation of the equation and the result
	 * @author horowitzb
	 */
	public String toString()
	{
		checkInitialization();
		return equationString + " = " + getResult();
	}//end toString
	
	/**
	 * private method to check whether calculator has been properly initialized
	 * throws SecurityException if initialized is false
	 * @author horowitzb
	 */
	private void checkInitialization()
	{
		if ( !initialized )
		{
			throw new SecurityException( "Calculator is not properly initialized." );
		}//end if
	}// end checkInitialization
	
	/**
	 * private method to check which operation to do first
	 * @param peeked
	 * @param current
	 * @return true if peeked should be calculated before current
	 * @author horowitzb
	 */
	private boolean peekedHasPrecedence(	char peeked,
											char current )
	{
		if ( ( peeked == '+' || peeked == '-' ) && ( current == '*' || current == '/' ) )
		{
			return false;
		}//end if
		else
		{
			return true;
		}//end else
	}//end peekedHasPrecendence
	
	/**
	 * determines if the input is a sign
	 * @param in
	 * @return true if input is a valid sign
	 * @author wellsa
	 */
	private boolean isSign( char in )
	{
		return ( in=='*' || in=='/' || in=='+' || in=='-' );
	}//end isSign
	
	/**
	 * private method to do individual operations
	 * @param numbers
	 * @param operands
	 * @author wellsa
	 */
	private void solveEquation( 	VectorStack<Integer> numbers,		
									VectorStack<Character> operands )	
	{																	
		int rightValue = 	numbers.pop();
		int leftValue = 	numbers.pop();
		char operand = 		operands.pop();
		
		switch ( operand )
		{
			case '*': 	numbers.push( leftValue * rightValue );
						break;
			case '/':	numbers.push( leftValue / rightValue );
						break;
			case '+':	numbers.push( leftValue + rightValue );
						break;
			case '-':	numbers.push( leftValue - rightValue );
						break;
			case '(':
			case ')':	System.out.println( "solveEquation called improperly."
						+ "Parantheses should be peeked and solveEquation not called on them" );
						break;
			default:	System.out.println( "Invalid character in operands stack." );
						break;
		}//end switch
	}
	
	/**
	 * Tests Calculator Constructors
	 * @author horowitzb
	 */
	private static void testConstructors() {
		System.out.println("\n----------\nTesting default constructor");
		Calculator calc = new Calculator();
		String calcString = "";
		try {
			calcString = calc.getEquationString();
		}catch (Exception e) {
			calcString = e.toString();
		}
		printTest(false, "Calling empty constructor", calcString, "java.lang.SecurityException: Calculator is not properly initialized.");
		
		
		System.out.println("\n----------\nTesting String constructor");
		String equation1 = "(5*5*2*2)-(2*5)-(5*2*(2+3+4))";
		Calculator calc1 = new Calculator(equation1);
		printTest(true, "Testing String constructor with equation " + equation1, equation1, calc1.getEquationString());
	}//end testConstructors()
	
	/**
	 * tests Calculator setters
	 * @author horowitzb
	 */
	private static void testSetters() {
		System.out.println("\n----------\nTesting setEquationString()");
		Calculator calc = new Calculator();
		String calcString = "";
		try {
			calcString = calc.getEquationString();
		}catch (Exception e) {
			calcString = e.toString();
		}
		printTest(false, "Calling empty constructor", calcString, "java.lang.SecurityException: Calculator is not properly initialized.");
		
		calc.setEquationString("(5*5*2*2)-(2*5)-(5*2*(2+3+4))");
		printTest(true, "setting equationString to (5*5*2*2)-(2*5)-(5*2*(2+3+4))", calc.getEquationString(), "(5*5*2*2)-(2*5)-(5*2*(2+3+4))");
	}
	
	/**
	 * tests Calculator getters
	 * @author horowitzb
	 */
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
		
		equation = "x";
		calc.setEquationString(equation);
		expected = "Error: Invalid character entered";
		result= calc.getResult();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
	}//end testGetters()
	
	/**
	 * tests calculator toString method
	 * @author horowitzb
	 */
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
		
		equation = "x";
		calc.setEquationString(equation);
		expected = "x = Error: Invalid character entered";
		result= calc.toString();
		printTest(true, "Testing getResult() with equation " + equation, result, expected);
	}//end tesToString
	
	/**
	 * private testing method to compare expected values to actual values
	 * 
	 * @param isValid
	 * @param description
	 * @param recieved
	 * @param expected
	 * @author horowitzb
	 */
	private static void printTest(boolean isValid, String description, String recieved, String expected) {
		System.out.println(String.format("Is Valid: %s%nDescription: %s%nRecieved: %s%nExpected: %s%n", isValid, description, recieved, expected));
	}//end printTest()
	
	/**
	 * Main for testing
	 * @param args
	 * @author horowitzb
	 */
	public static void main(String[] args) {
		System.out.println("Testing Calculator");
		testConstructors();
		testSetters();
		testGetters();
		testToString();
	}//end main
	
}//end Calculator


