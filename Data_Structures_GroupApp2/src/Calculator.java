import java.util.ArrayList;

//TODO: documentation, private methods, implementation, check invalid
public class Calculator {
	
	private String equationString;
	private Boolean initialized;
	private final char[] signs = {'(', ')', '*', '/', '+', '-'};
	public Calculator() {
		initialized = false;
	}//end Constructor
	
	public Calculator(String toParse) {
		equationString = toParse;
		initialized = true;
	}//end Constructor

	
	public String getEquationString() {
		checkInitialization();
		return equationString;
	}//end getEquationString
	
	public String getResult() {
		checkInitialization();
		VectorStack<Character> operands = new VectorStack<Character>();
		VectorStack<Integer> numbers = new VectorStack<Integer>();
		boolean lastWasNum = false;
		char current;
		for(int i = 0; i < equationString.length(); i++) {
			current = equationString.charAt(i);
			if(lastWasNum) {
				if(Character.isDigit(current)) {
					int toFormat = operands.pop();
				}else if(/*signs contains current. delete true*/true) {
					
				}
			}else {
				
			}
			
		}
		return "";
		
	}//end getResult
	
	public String toString() {
		checkInitialization();
		return equationString + " = " + getResult();
	}//end toString
	
	
	private void checkInitialization() {
		if (!initialized)
		{
			throw new SecurityException("Calculator is not properly initialized.");
		} // end checkInitialization
	}
	
}
