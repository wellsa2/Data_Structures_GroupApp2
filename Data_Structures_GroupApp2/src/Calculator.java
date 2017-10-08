import java.util.ArrayList;
import java.util.EmptyStackException;

//TODO: documentation, private methods, implementation, parenthesis, return invalid error as string 

/**
 * A calculator for integers that follows order of operations.
 * Can compute operands: '+','-','/', & '*' operands and parentheses.
 * @author Aiden Wells
 * @author Brandon Horowitz
 */
public class Calculator
{
	
	//instance variables
	private String 	equationString ;
	private Boolean initialized ;
	
	public Calculator()
	{
		initialized = false ;
	}//end Constructor
	
	public Calculator( String toParse )
	{
		equationString = 	toParse ;
		initialized = 		true ;
	}//end Constructor

	public String getEquationString()
	{
		checkInitialization() ;
		return equationString ;
	}//end getEquationString
	
	public String getResult()
	{
		checkInitialization() ;
		
		VectorStack<Character> operands = 	new VectorStack<Character>() ;
		VectorStack<Integer> numbers = 		new VectorStack<Integer>() ;
		
		boolean lastWasNum = 		false ;	//true if last character was a number
		boolean hasSignToCompare = 	false ;	//true if a previous operand has yet to be computed in the string
		boolean valid = 			true ;	//set to false if given string is found to be an invalid equation
		char current ;						//current character in iterator
		boolean tempHasSignToCompare = false;
		
		try {
		for ( int i = 0; i < equationString.length() && valid; i++ )
		{
			current = equationString.charAt( i ) ;
			
			if ( current == ' ' )
			{
				//keep empty. makes sure spaces aren't treated as an error
			}//end else if
			else if ( lastWasNum )
			{
				if ( Character.isDigit( current ) ) //multi-digit numbers
				{
					int toFormat = numbers.pop() ;
					toFormat = toFormat * 10 + current ;
					numbers.push( toFormat ) ;
				}//end if
				else if ( current == ')' )
				{
					while ( operands.peek() != '(' )
					{
						solveEquation( numbers, operands );
					}
					operands.pop() ; //removes '(' from operands stack
					hasSignToCompare = tempHasSignToCompare;
				}//end if
				else if ( isSign( current ) )
				{
					lastWasNum = false ;
					if ( hasSignToCompare )
					{
						if ( peekedHasPrecedence (	operands.peek (), 	//solve peeked if it has precedence,
													current ) )			//otherwise push current operand to stack
						{
							solveEquation( numbers, operands ) ;
							operands.push( current ) ;
						}//end if
						else //peeked did not have precedence
						{
							operands.push( current ) ;
						}//end else
					}//end if
					else //there was no previous sign to compare
					{
						operands.push( current ) ; //push current operand to stack and set corresponding boolean
						hasSignToCompare = true ;
					}//end else
				}//end else if
				else
				{
					valid = false ;
					System.out.println( "Invalid character entered." ) ;
				}//end else
			}//end if
			else //lastWasNum = false
			{
				if ( Character.isDigit( current ) )
				{
					lastWasNum = true ;
					numbers.push( current - '0' ) ;
				}//end if
				else if ( current == '(' )
				{
					operands.push( current ) ;
					tempHasSignToCompare = hasSignToCompare;
					hasSignToCompare = false;
				}
				else if ( isSign( current ) )
				{
					valid = false ;
					System.out.println( "Invalid expression entered." ) ;
				}//end else if
				else
				{
					
				}//end else
			}//end else
		}//end for
		while ( !operands.isEmpty() )
		{
			solveEquation( numbers, operands );
		}//end while
		}catch (ArithmeticException ae) {
			return ae.toString();
		}catch (EmptyStackException ese) {
			return ese.toString();
		}
		return numbers.pop().toString() ;
	}//end getResult
	
	public String toString()
	{
		checkInitialization() ;
		return equationString + " = " + getResult() ;
	}//end toString
	
	private void checkInitialization()
	{
		if ( !initialized )
		{
			throw new SecurityException( "Calculator is not properly initialized." ) ;
		}//end if
	}// end checkInitialization
	
	private boolean peekedHasPrecedence(	char peeked,
											char current )
	{
		if ( ( peeked == '+' || peeked == '-' ) && ( current == '*' || current == '/' ) )
		{
			return false ;
		}//end if
		else
		{
			return true ;
		}//end else
	}//end peekedHasPrecendence
	
	private boolean isSign( char in )
	{
		return ( in=='*' || in=='/' || in=='+' || in=='-' ) ;
	}//end isSign
	
	private boolean solveEquation( 	VectorStack<Integer> numbers,		//If the vector stacks aren't referencing the original addresses correctly,
									VectorStack<Character> operands )	//convert this method to return an int, being fed the parameters of two
	{																	//popped ints and one popped operand char
		boolean success = 	true ;
		int rightValue = 	numbers.pop() ;
		int leftValue = 	numbers.pop() ;
		char operand = 		operands.pop() ;
		
		switch ( operand )
		{
			case '*': 	numbers.push( leftValue * rightValue ) ;
						break ;
			case '/':	numbers.push( leftValue / rightValue ) ;
						break ;
			case '+':	numbers.push( leftValue + rightValue ) ;
						break;
			case '-':	numbers.push( leftValue - rightValue ) ;
						break;
			case '(':
			case ')':	System.out.println( "solveEquation called improperly."
						+ "Parantheses should be peeked and solveEquation not called on them" ) ;
						success = false ;
						break;
			default:	System.out.println( "Invalid character in operands stack." ) ;
						success = false ;
						break;
		}//end switch
		return success ;
	}
}//end Calculator
