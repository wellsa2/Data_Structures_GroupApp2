import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.Character;
import java.util.Arrays;

public class ExpressionParser {
	
	private ArrayList<String> expressions;
	private boolean initialized;


	public ExpressionParser() {
		this(System.getProperty("user.dir") + "/Infix Calculator Expressions - valid -- 2016-10-13 01.txt");
	}


	public ExpressionParser(String expressionTextFile) {

		expressions = new ArrayList<>();

		try {
			Scanner scanner = new Scanner(new FileReader(expressionTextFile));
			System.out.println("File " + expressionTextFile + " found!");

			while (scanner.hasNextLine()) {
				expressions.add(scanner.nextLine());
			}
			initialized = true;
		}
		catch (FileNotFoundException fileNotFound) {
			System.out.println("File " + expressionTextFile + " not found!");
			initialized = false;
		}
	}


	public ArrayList<String> getExpressions() {
		ArrayList<String> returnArray = new ArrayList<>();

		for (String expression : expressions) {
			returnArray.add(expression);
		}
		return returnArray;
		
	}


	public String getExpressionAt(int i) {
		return expressions.get(i);
	}


	public String toString() {
		String resultString = "";
		for (String expression : expressions) {
			resultString += expression;
		}
		return resultString;
	}
}
