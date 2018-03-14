import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

public class ButtonClickExample extends Application {

    private Button button;
    private Scene scene;
    private HBox hbox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HBox Experiment 1");

        Label label = new Label("Not clicked");
        button = new Button("Click");

        button.setOnAction(value -> buttonAction(value, label));

        hbox = new HBox(button, label);

        scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // Random button action
    private void buttonAction(ActionEvent event, Label label) {
        if (event.getSource() == button) {
            List<String> colors = Arrays.asList("#0000ff", "#894e73","#90d858");
            Collections.shuffle(colors);
            String styles =
                    format("-fx-background-color: %s;",colors.get(0));
            hbox.setStyle(styles);
        }
        label.setText("Clicked!");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
