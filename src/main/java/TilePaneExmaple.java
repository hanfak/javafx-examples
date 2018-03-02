import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExmaple extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TilePane Experiment");

        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button Number 2");
        Button button3 = new Button("Button No 3");
        Button button4 = new Button("Button No 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button Number 6");

        FlowPane flowPane = new FlowPane();
        FlowPane flowPane1 = new FlowPane();
        TilePane tilePane2 = new TilePane();


        flowPane1.getChildren().add(button1);
        flowPane1.getChildren().add(button2);
        flowPane1.getChildren().add(button3);
        tilePane2.getChildren().add(button4);
        tilePane2.getChildren().add(button5);
        tilePane2.getChildren().add(button6);

        flowPane1.setHgap(10);
        tilePane2.setTileAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(flowPane);
        String styles =
                "-fx-background-color: #0000ff;" +
                        "-fx-border-color: #ff0000;" ;
        flowPane.setStyle(styles);
        flowPane1.setStyle("-fx-border-color: green;");
        flowPane.setVgap(50);
        tilePane2.setStyle("-fx-border-color: black;");
        flowPane.getChildren().addAll(flowPane1, tilePane2);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
