import java.util.ArrayList;

//TODO: documentation, private methods, implementation, parenthesis, check invalid
public class Calculator {
	
	private String equationString;
	private Boolean initialized;
	private final char[] signs = {'*', '/', '+', '-'};
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
		boolean hasSignToCompare = false;
		boolean valid = true;
		char current;
		for(int i = 0; i < equationString.length() && valid; i++) {
			current = equationString.charAt(i);
			if(lastWasNum) {
				if(Character.isDigit(current)) {
					int toFormat = numbers.pop();
					toFormat = toFormat * 10 + current;
					numbers.push(toFormat);
				}else if(/*signs contains current.*/) {
					lastWasNum = false;
					//stuff
					
				}else if(current == ' ') {
					//keep empty. makes sure spaces aren't treated as anything
				}else {
					System.out.println("Invalid character entered.");
				}
			}else {
				if(Character.isDigit(current)) {
					
				}else if(/*signs contains current.*/) {
					System.out.println("Error: cannot");
				}else if(current == ' ') {
					//keep empty. makes sure spaces aren't treated as anything
				}else {
					
				}
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
