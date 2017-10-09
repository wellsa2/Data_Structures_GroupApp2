// COMP 2000 - 03
// VectorStack Application 2
// Group 32
// Avery Loftin
// 10/9/2017

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


/**
 * A controller class for the CalculatorGUI fxml file
 * @author Avery Loftin
 */
public class Controller {

    private Calculator calculator = new Calculator();

    // makes controls on GUI accessible
    @FXML
    TextArea display;
    @FXML
    Button clear;
    @FXML
    Button backspace;
    @FXML
    Button quit;
    @FXML
    Button divide, multiply, add, subtract;
    @FXML
    Button openParen, closeParen;
    @FXML
    Button zero, one, two, three, four, five, six, seven, eight, nine;


    /**
     * clears the calculator's display
     */
    public void displayClear()
    {
        display.setText("");
    } // end displayClear


    /**
     * deletes the last character typed to the display
     */
    public void displayBackspace()
    {
        display.setText(display.getText().substring(0, display.getText().length() - 1));
    } // end displayBackspace


    /**
     * writes digit zero to the display
     */
    public void displayZero()
    {
        display.setText(display.getText() + "0");
    } // end displayZero


    /**
     * writes digit one to the display
     */
    public void displayOne()
    {
        display.setText(display.getText() + "1");
    } // end displayOne


    /**
     * writes digit two to the display
     */
    public void displayTwo()
    {
        display.setText(display.getText() + "2");
    } // end displayTwo


    /**
     * writes digit three to the display
     */
    public void displayThree()
    {
        display.setText(display.getText() + "3");
    } // end displayThree


    /**
     * writes digit four to the display
     */
    public void displayFour()
    {
        display.setText(display.getText() + "4");
    } // end displayFour


    /**
     * writes digit five to the display
     */
    public void displayFive()
    {
        display.setText(display.getText() + "5");
    } // end displayFive


    /**
     * writes digit six to the display
     */
    public void displaySix()
    {
        display.setText(display.getText() + "6");
    } // end displaySix


    /**
     * writes digit seven to the display
     */
    public void displaySeven()
    {
        display.setText(display.getText() + "7");
    } // end displaySeven


    /**
     * writes digit eight to the display
     */
    public void displayEight()
    {
        display.setText(display.getText() + "8");
    } // end displayEight


    /**
     * writes digit nine to the display
     */
    public void displayNine()
    {
        display.setText(display.getText() + "9");
    } // end displayNine


    /**
     * writes open parenthesis to the display
     */
    public void displayOpenParen()
    {
        display.setText(display.getText() + "(");
    } // end displayOpenParen


    /**
     * writes close parenthesis to the display
     */
    public void displayCloseParen()
    {
        display.setText(display.getText() + ")");
    } // end displayCloseParen


    /**
     * writes division sign to the display
     */
    public void displayDivide()
    {
        display.setText(display.getText() + "/");
    } // end displayDivide


    /**
     * writes multiplication sign to the display
     */
    public void displayMultiply()
    {
        display.setText(display.getText() + "*");
    } // end displayMultiply


    /**
     * writes addition sign to the display
     */
    public void displayAdd()
    {
        display.setText(display.getText() + "+");
    } // end displayAdd


    /**
     * writes subtraction sign to the display
     */
    public void displaySubtract()
    {
        display.setText(display.getText() + "-");
    } // end displaySubtract


    /**
     * solves the current equation on the screen using calculator
     */
    public void displaySolve()
    {
        calculator.setEquationString(display.getText());
        if (calculator.getResult().equals("java.lang.ArithmeticException: / by zero"))
        {
           display.setText(calculator.getEquationString() + " = " + "Error: Cannot divide by zero");
        } // end if
        else
        {
            display.setText(calculator.toString());
        } // end else
    } // end displaySolve


    /**
     * ends the calculator GUI
     */
    public void quitCalculator()
    {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    } // end quitCalculator
} // end Controller
