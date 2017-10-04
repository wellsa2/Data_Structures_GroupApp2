
public class ExpressionParserTester {

	public static void main(String[] args) {
		ExpressionParser expressionParser = new ExpressionParser();
		for (String expression : expressionParser.getExpressions()) {
			System.out.println(expression);
		}
		for (Character c : ExpressionParser.stringToArray(expressionParser.getExpressionAt(50))) {
			System.out.println(c);
		}
	}

}
