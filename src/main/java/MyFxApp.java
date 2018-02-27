import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyFxApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        Button button1 = new Button("Button 1");
        button1.getStyleClass().add("button1");

        Button button2 = new Button("Button 2");
        String styles =
                "-fx-background-color: #0000ff;" +
                        "-fx-border-color: #ff0000;" ;
        button2.setStyle(styles);

        VBox vbox = new VBox(button1, button2);
        vbox.getStyleClass().add("vbox");

        Scene scene1 = new Scene(vbox, 400, 200);
        scene1.getStylesheets().add("button-styles.css");
        primaryStage.setScene(scene1);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
