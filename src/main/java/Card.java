import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Card extends Application {

    private final static Color SCENE_BACKGROUND_COLOR = Color.RED.deriveColor(1, 1, 1, 0.8);

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(50));
        root.getChildren().add(vBox);
        HBox hBox = new HBox();

        hBox.setSpacing(10);

        IntStream.rangeClosed(1, 10)
                .forEach(x -> {
                    Node card = createCard(120, 180);
                    hBox.getChildren().add(card);
                }
        );

        vBox.getChildren().add(hBox);

        Scene scene = new Scene(root, 1600, 920, SCENE_BACKGROUND_COLOR);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @SuppressWarnings("SameParameterValue")
    private Node createCard(double width, double height) {

        Pane backFace = new Pane();

//        Shape shape = createShape(width, height);
        Shape shape = createCircle(50);
        shape.setFill(Color.LIGHTBLUE); // Plain backface

        ImagePattern pattern = createBackOFCardPattern();
        shape.setFill(pattern);

        backFace.getChildren().add(shape);

        return backFace;

    }

    private ImagePattern createBackOFCardPattern() {

        final double width = 12;
        final double height = 12;
        final Canvas canvas = new Canvas(width, height);
        final GraphicsContext context = canvas.getGraphicsContext2D();

        // full area color no pattern
        context.setFill(Color.GREEN);
        context.fillRect(0, 0, width, height);

        // Fill pattern
        makePattern(width, height, context);

        final Image PATTERN_IMAGE = canvas.snapshot(new SnapshotParameters(), null);

        return new ImagePattern(PATTERN_IMAGE, 0, 0, width, height, false);
    }

    private void makePattern(double width, double height, GraphicsContext context) {
        context.setFill(Color.BLACK);
        double[] xPointsOfPolygon = {width / 2, width, width / 2, 0, width};
        double[] yPointsOfPolygon = {0, height / 2, height, height / 2, height};
        int numberOfPointOnPolygon = 5;
        context.fillPolygon(xPointsOfPolygon, yPointsOfPolygon, numberOfPointOnPolygon);
    }

    private Shape createShape(double width, double height) {

        Rectangle shape = new Rectangle();
        shape.setWidth(width);
        shape.setHeight(height);
        shape.setArcWidth(12);
        shape.setArcHeight(12);
        shape.setStroke(Color.BLACK);
        shape.setFill(Color.TRANSPARENT);

        return shape;
    }

    private Shape createCircle(int radius) {

        Circle shape = new Circle();
        shape.setRadius(radius);
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(3);
        shape.setFill(Color.TRANSPARENT);

        return shape;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
