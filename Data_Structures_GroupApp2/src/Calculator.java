import java.util.EmptyStackException ;

// Comp 2000-03
// VectorStack Application 2
// Group 32
// Brandon Horowitz, Aiden Wells
// 10/9/2017

/**
 * A calculator for integers that follows order of operations.
 * Can compute operators '+', '-', '/', '*' and parentheses.
 * 
 * @author Brandon Horowitz
 * @author Aiden Wells
 */
public class Calculator
{
	
	//instance variables
	private String 	equationString ;
	private Boolean initialized ;
	
	/**
	 * empty constructor
	 * @author Brandon Horowitz
	 */
	public Calculator()
	{
		initialized = false ;
	} //end Constructor
	
	/**
	 * String constructor
	 * @param toParse
	 * @author Brandon Horowitz
	 */
	public Calculator( String toParse )
	{
		equationString = 	toParse ;
		initialized = 		true ;
	} //end Constructor

	/**
	 * sets equationString
	 * @param equation
	 * @author Brandon Horowitz
	 */
	public void setEquationString(String equation)
	{
		equationString = equation ;
		initialized = true ;
	} //end setEquation
	
	/**
	 * gets equationString
	 * @return equationString
	 * @author Brandon Horowitz
	 */
	public String getEquationString()
	{
		checkInitialization() ;
		return equationString ;
	} //end getEquationString
	
	/**
	 * Calculates solution of the equation in equationString
	 * @return String of the result. Invalid equations return error messages, but will not crash
	 * @author Aiden Wells
	 * @author Brandon Horowitz
	 */
	public String getResult()
	{
		checkInitialization() ;
		
		VectorStack<Character> operators = 			new VectorStack<Character>() ;
		VectorStack<Integer> numbers = 				new VectorStack<Integer>() ;
		VectorStack<Boolean> tempHasSignToCompare = new VectorStack<Boolean>() ;
		
		boolean lastWasNum = 			false ;	//true if last character was a number
		boolean hasSignToCompare = 		false ;	//true if a previous operator has yet to be computed in the string
		char current ;							//current character in iterator
		boolean hasNum =				false ; //boolean value for error return message
		try 
		{
			for ( int i = 0 ; i < equationString.length() ; i++ )
			{
				current = equationString.charAt( i ) ;
				
				if ( current == ' ' )
				{
					//keep empty. makes sure spaces aren't treated as an error
				} //end if
				else if ( lastWasNum )
				{
					if ( Character.isDigit( current ) ) //multi-digit numbers
					{
						int toFormat = numbers.pop() ;
						toFormat = toFormat * 10 + current - '0' ;
						numbers.push( toFormat ) ;
						hasNum = true;
					} //end if
					else if ( current == ')' )
					{
						while ( operators.peek() != '(' )
						{
							solveEquation( numbers, operators ) ;
						} //end while
						operators.pop() ; //removes '(' from operators stack
						hasSignToCompare = tempHasSignToCompare.pop() ;
					} //end if
					else if ( isOperator( current ) )
					{
						lastWasNum = false ;
						if ( hasSignToCompare )
						{
							if ( peekedHasPrecedence (	operators.peek (), 	//solve peeked if it has precedence,
														current ) )			//otherwise push current operator to stack
							{
								solveEquation( numbers, operators ) ;
								operators.push( current ) ;
							} //end if
							else //peeked did not have precedence
							{
								operators.push( current ) ;
							} //end else
						} //end if
						else //there was no previous sign to compare
						{
							operators.push( current ) ; //push current operator to stack and set corresponding boolean
							hasSignToCompare = true ;
						} //end else
					} //end else if
					else
					{
						return "Error: Invalid character entered" ;
					} //end else
				} //end if
				else //lastWasNum = false
				{
					if ( Character.isDigit( current ) )
					{
						hasNum = true;
						lastWasNum = true ;
						numbers.push( current - '0' ) ;
					} //end if
					else if ( current == '(' )
					{
						operators.push( current ) ;
						tempHasSignToCompare.push(hasSignToCompare) ;
						hasSignToCompare = false ;
					} //end else if
					else if ( isOperator( current ) )
					{
						if(hasNum) {
						return "Error: cannot have two adjacent signs" ;
						}
						else {
							return "Error: equation must start with a number";
						}
					} //end else if
					else
					{
						return "Error: Invalid character entered" ;
					} //end else
				} //end else
			} //end for
			while ( !operators.isEmpty() )
			{
				solveEquation( numbers, operators ) ;
			} //end while
		} //end try
		catch (ArithmeticException ae) 
		{
			return ae.toString() ;
		} //end catch ArithmeticException
		catch (EmptyStackException ese) {
			return ese.toString() + " Make sure all parenthesis have a matching pair, and all operators have a pair of numbers" ;
		} //end catch EmptyStackException
		return numbers.pop().toString() ;
	} //end getResult
	
	/**
	 * returns string representation of the equation and the result
	 * @author Brandon Horowitz
	 */
	public String toString()
	{
		checkInitialization() ;
		return equationString + " = " + getResult() ;
	} //end toString
	
	/**
	 * Checks whether Calculator has been properly initialized.
	 * throws SecurityException if initialized is false
	 * @author Brandon Horowitz
	 */
	private void checkInitialization()
	{
		if ( !initialized )
		{
			throw new SecurityException( "Calculator is not properly initialized." ) ;
		} //end if
	} // end checkInitialization
	
	/**
	 * Determines which operator should be evaluated first according to order of operations.
	 * @param peeked	operator peeked from stack
	 * @param current	current operator from iterator
	 * @return true if peeked operator should be calculated before current, otherwise false
	 * @author Brandon Horowitz
	 */
	private boolean peekedHasPrecedence(	char peeked,
											char current )
	{
		if ( ( peeked == '+' || peeked == '-' ) && ( current == '*' || current == '/' ) )
		{
			return false ;
		} //end if
		else
		{
			return true ;
		} //end else
	} //end peekedHasPrecendence
	
	/**
	 * Checks if given char is a valid operator (+,-,*,/ only).
	 * @param in
	 * @return true if input is a valid operator
	 * @author wellsa
	 */
	private boolean isOperator( char in )
	{
		return ( in=='*' || in=='/' || in=='+' || in=='-' ) ;
	} //end isOperator
	
	/**
	 * Pops two numbers and one operator from their respective stacks and pushes the solution to the numbers stack.
	 * @param numbers stack of numbers to be popped and pushed from
	 * @param operators 
	 * @author Aiden Wells
	 */
	private void solveEquation( 	VectorStack<Integer> numbers,		
									VectorStack<Character> operators )	
	{																	
		int rightValue = 	numbers.pop() ;
		int leftValue = 	numbers.pop() ;
		char operator = 		operators.pop() ;
		
		switch ( operator )
		{
			case '*': 	numbers.push( leftValue * rightValue ) ;
						break ;
			case '/':	numbers.push( leftValue / rightValue ) ;
						break ;
			case '+':	numbers.push( leftValue + rightValue ) ;
						break ;
			case '-':	numbers.push( leftValue - rightValue ) ;
						break ;
			case '(':
			case ')':	System.out.println( "solveEquation called improperly."
						+ "Parantheses should be peeked and solveEquation not called on them" ) ;
						break ;
			default:	System.out.println( "Invalid character in operators stack." ) ;
						break ;
		} //end switch
	}
	
	/**
	 * Tests the Calculator constructor.
	 * @author Brandon Horowitz
	 */
	private static void testConstructors()
	{
		System.out.println( "\n----------\nTesting default constructor" ) ;
		Calculator calc =	new Calculator() ;
		String calcString = "" ;
		try
		{
			calcString = 	calc.getEquationString() ;
		}
		catch (Exception e)
		{
			calcString = e.toString() ;
		}
		printTest( false, "Calling empty constructor", calcString, "java.lang.SecurityException: Calculator is not properly initialized." ) ;
		
		
		System.out.println( "\n----------\nTesting String constructor" ) ;
		String equation1 =	"(5*5*2*2)-(2*5)-(5*2*(2+3+4))" ;
		Calculator calc1 = 	new Calculator(equation1) ;
		printTest( true, "Testing String constructor with equation " + equation1, equation1, calc1.getEquationString() ) ;
	} //end testConstructors()
	
	/**
	 * Tests Calculator set methods.
	 * @author Brandon Horowitz
	 */
	private static void testSetters()
	{
		System.out.println( "\n----------\nTesting setEquationString()" ) ;
		Calculator calc = 	new Calculator() ;
		String calcString = "" ;
		try
		{
			calcString = calc.getEquationString() ;
		}
		catch ( Exception e )
		{
			calcString = e.toString() ;
		}
		printTest( false, "Calling empty constructor", calcString, "java.lang.SecurityException: Calculator is not properly initialized." ) ;
		
		calc.setEquationString( "(5*5*2*2)-(2*5)-(5*2*(2+3+4))" ) ;
		printTest( true, "setting equationString to (5*5*2*2)-(2*5)-(5*2*(2+3+4))", calc.getEquationString(), "(5*5*2*2)-(2*5)-(5*2*(2+3+4))" ) ;
	} //end testSetters
	
	/**
	 * Tests Calculator get methods.
	 * @author Brandon Horowitz
	 */
	private static void testGetters() {
		System.out.println( "\n----------\nTesting getters" ) ;
		String equation =	"(5*5*2*2)-(2*5)-(5*2*(2+3+4))" ;
		Calculator calc = 	new Calculator(equation) ;
		System.out.println( "\n----------\nTesting getEquationString()" ) ;
		printTest( true, "Testing getEquationString() with equation " + equation, equation, calc.getEquationString() ) ;
		System.out.println( "\n----------\nTesting getResult()" ) ;
		String expected = 	Integer.toString( (5*5*2*2)-(2*5)-(5*2*(2+3+4)) ) ;
		String result = 	calc.getResult() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"0 + 12345" ;
		calc.setEquationString( equation ) ;
		expected = 	"12345" ;
		result= 	calc.getResult() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"1/0" ;
		calc.setEquationString( equation ) ;
		expected = 	"Java.lang.ArithmeticException: / by zero" ;
		result= 	calc.getResult() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"5++5" ;
		calc.setEquationString( equation ) ;
		expected = 	"Error: cannot have two adjacent signs" ;
		result= 	calc.getResult() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"5+7)" ;
		calc.setEquationString( equation ) ;
		expected = 	"java.util.EmptyStackException Make sure all parenthesis have a matching pair" ;
		result= 	calc.getResult() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"(5+7" ;
		calc.setEquationString( equation ) ;
		expected = 	"java.util.EmptyStackException Make sure all parenthesis have a matching pair" ;
		result= 	calc.getResult() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"x" ;
		calc.setEquationString( equation ) ;
		expected = 	"Error: Invalid character entered" ;
		result= 	calc.getResult() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
	} //end testGetters
	
	/**
	 * Tests Calculator toString method.
	 * @author Brandon Horowitz
	 */
	private static void testToString()
	{
		System.out.println( "\n----------\nTesting toString();" ) ;
		String equation =	"(5*5*2*2)-(2*5)-(5*2*(2+3+4))" ;
		Calculator calc = 	new Calculator( equation ) ;
		String result = 	calc.toString() ;
		String expected = 	"(5*5*2*2)-(2*5)-(5*2*(2+3+4)) = 0" ;
		printTest( true, "testing toString() with valid equation " + equation, result, expected ) ;
		
		equation = 	"0 + 12345" ;
		calc.setEquationString( equation ) ;
		expected = 	"0 + 12345 = 12345" ;
		result=		calc.toString() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"1/0" ;
		calc.setEquationString( equation ) ;
		expected = 	"1/0 = Java.lang.ArithmeticException: / by zero" ;
		result= 	calc.toString() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"5++5" ;
		calc.setEquationString( equation ) ;
		expected = 	"5++5 = Error: cannot have two adjacent signs" ;
		result= 	calc.toString() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"5+7)" ;
		calc.setEquationString( equation ) ;
		expected = 	"5+7) = java.util.EmptyStackException Make sure all parenthesis have a matching pair" ;
		result= 	calc.toString() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"(5+7" ;
		calc.setEquationString( equation ) ;
		expected = 	"(5+7 = java.util.EmptyStackException Make sure all parenthesis have a matching pair" ;
		result= 	calc.toString() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
		
		equation = 	"x" ;
		calc.setEquationString( equation ) ;
		expected = 	"x = Error: Invalid character entered" ;
		result= 	calc.toString() ;
		printTest( true, "Testing getResult() with equation " + equation, result, expected ) ;
	} //end testToString
	
	/**
     * Utility function to print out testing info.
     * @param isValid  is this testing valid parameters or invalid ones
     * @param description  a description of the test being run
     * @param received  output received by the test
     * @param expected  what the received output should be
     * @author Brandon Horowitz
     */
	private static void printTest( 	boolean isValid,
									String description,
									String recieved,String expected )
	{
		System.out.println( String.format( "Is Valid: %s%nDescription: %s%nRecieved: %s%nExpected: %s%n", isValid, description, recieved, expected ) ) ;
	} //end printTest
	
	/**
	 * Main for testing
	 * @param args
	 * @author Brandon Horowitz
	 */
	public static void main( String[] args )
	{
		System.out.println( "Testing Calculator" ) ;
		testConstructors() ;
		testSetters() ;
		testGetters() ;
		testToString() ;
	} //end main
	
} //end Calculator


