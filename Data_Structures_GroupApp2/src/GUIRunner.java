import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class GUIRunner extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage)
    {
        Parent root = new Pane();
        try {
            root = FXMLLoader.load(getClass().getResource("CalculatorGUI.fxml"));
        }
        catch (Exception e){
            System.exit(1);
        }

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 240, 384));
        primaryStage.show();
    }
}
