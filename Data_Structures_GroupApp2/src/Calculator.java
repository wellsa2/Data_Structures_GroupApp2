import java.util.ArrayList;

//TODO: documentation, private methods, implementation, check invalid
public class Calculator {
	
	private String equationString;
	private ArrayList<String> equationList;
	private Boolean initialized;
	
	public Calculator() {
		initialized = false;
	}//end Constructor
	
	public Calculator(String toParse) {
		equationString = toParse;
		equationList = ExpressionParser.StringToArray(toParse);
		initialized = true;
	}//end Constructor
	
	public Calculator(ArrayList<String> input) {
		equationList = input;
		StringBuffer sb = new StringBuffer();
		for(String numsAndSigns : input) {
			sb.append(numsAndSigns);
		}//end for
		equationString = sb.toString();
		initialized = true;
	}//end Constructor
	
	public ArrayList<String> getEquationList(){
		return equationList;
	}//end getEquationList
	
	public String getEquationString() {
		return equationString;
	}//end getEquationString
	public String getResult() {
		checkInitialization();
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
