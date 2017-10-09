import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller {

    Calculator calculator = new Calculator();

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


    public void displayClear() {
        display.setText("");
    }


    public void displayBackspace() {
        display.setText(display.getText().substring(0, display.getText().length() - 1));
    }


    public void displayZero() {
        display.setText(display.getText() + "0");
    }


    public void displayOne() {
        display.setText(display.getText() + "1");
    }


    public void displayTwo() {
        display.setText(display.getText() + "2");
    }


    public void displayThree() {
        display.setText(display.getText() + "3");
    }


    public void displayFour() {
        display.setText(display.getText() + "4");
    }


    public void displayFive() {
        display.setText(display.getText() + "5");
    }


    public void displaySix() {
        display.setText(display.getText() + "6");
    }


    public void displaySeven() {
        display.setText(display.getText() + "7");
    }


    public void displayEight() {
        display.setText(display.getText() + "8");
    }


    public void displayNine() {
        display.setText(display.getText() + "9");
    }


    public void displayOpenParen() {
        display.setText(display.getText() + "(");
    }


    public void displayCloseParen() {
        display.setText(display.getText() + ")");
    }


    public void displayDivide() {
        display.setText(display.getText() + "/");
    }


    public void displayMultiply() {
        display.setText(display.getText() + "*");
    }


    public void displayAdd() {
        display.setText(display.getText() + "+");
    }


    public void displaySubtract() {
        display.setText(display.getText() + "-");
    }


    public void displaySolve() {
        calculator.setEquationString(display.getText());
        if (calculator.getResult().equals("java.lang.ArithmeticException: / by zero"))
        {
           display.setText(calculator.getEquationString() + " = " + "Error: Cannot divide by zero");
        }
        else
        {
            display.setText(calculator.toString());
        }
    }


    public void quitCalculator() {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }
}
