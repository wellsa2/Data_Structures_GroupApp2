import java.io.BufferedReader;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        	String current;
        	BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
	}
	
	try {
		System.out.println("Enter txt file name: ");
		while((current = in.readLine()) != null) {
			
			System.out.prinln("Answers: " + current);
			Calculator calc = new Calculator(current);
			ExpressionParser ep = new ExpressionParser(current);
			System.out.println(calc.getValue());
		}
	}
}
