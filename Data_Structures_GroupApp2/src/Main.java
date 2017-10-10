// COMP 2000 - 03
// VectorStack Application 2
// Group 32
// Avery Loftin
// 10/9/2017

import javafx.application.Application ;
import javafx.fxml.FXMLLoader ;
import javafx.scene.Parent ;
import javafx.scene.Scene ;
import javafx.scene.layout.Pane ;
import javafx.stage.Stage ;
import java.io.FileNotFoundException ;
import java.io.FileReader ;
import java.util.Scanner ;

/**
 * Main class to run a gui and solve the invalid multi digit file with the calculator class
 * @author Avery Loftin
 */
public class Main extends Application
{

	/**
	 * Main main method
	 * @param args main method args
	 */
	public static void main( String[] args )
	{
		String file = System.getProperty( "user.dir" ) + "/Infix Calculator Expressions - multi-digit with invalid expressions -- 2016-10-04 01.txt" ;

		solveFile( file ) ;

		launch( args ) ;
	}// end main


	/**
	 * Solves every equation in a file
	 * @param fileName a file with one equation per line with no spaces and only whole numbers, +, -, *, /, (, and )
	 */
	private static void solveFile( String fileName )
	{
		try
		{
			Scanner fileScanner =	new Scanner( new FileReader( fileName ) ) ;
			Calculator calculator = new Calculator() ;
			while ( fileScanner.hasNextLine() )
			{
				calculator.setEquationString( fileScanner.nextLine() ) ;
				if ( calculator.getResult().equals( "java.lang.ArithmeticException: / by zero" ) )
				{
					System.out.println( calculator.getEquationString() + " = " + "Error: Cannot divide by zero" ) ;
				} // end if
				else
				{
					System.out.println( calculator.toString() ) ;
				} // end else
			} // end while
		} // end try
		catch ( FileNotFoundException fileNotFound )
		{
			System.out.println( "File " + fileName + " not found!" ) ;
		} // end catch
	} // end solveFile


	/**
	 * start method to launch the application
	 * @param primaryStage the primaryStage to start and show
	 */
	@Override
	public void start( Stage primaryStage )
	{
		Parent root = new Pane() ;
		try
		{
			root = FXMLLoader.load(getClass().getResource( "CalculatorGUI.fxml" ) ) ;
		} // end try
		catch ( java.io.IOException e )
		{
			System.out.print( "ERROR" ) ;
			System.exit( 0 ) ;
		} // end catch

		primaryStage.setTitle( "Calculator" ) ;
		primaryStage.setScene( new Scene( root, 240, 384 ) ) ;
		primaryStage.show() ;
	} // end start
} // end Main
