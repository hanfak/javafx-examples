import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EnumSet;

public class FrontCard extends Application {

    private final static Color SCENE_BACKGROUND_COLOR = Color.RED.deriveColor(1, 1, 1, 0.8);

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        VBox vBox = new VBox();
        ScrollBar sc = new ScrollBar();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        createSetOfCards(vBox);
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 1600, 920, SCENE_BACKGROUND_COLOR);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createSetOfCards( VBox vBox) {
        for (Suit suit : EnumSet.allOf(Suit.class)) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            EnumSet.allOf(Rank.class).stream()
                    .forEach(rank -> {
                        try {
                            if (suit.equals(Suit.DIAMOND) || suit.equals(Suit.HEART)) {
                                Node card = createCard(95, 140, rank.value(), suit.name(), Color.RED);
                                hBox.getChildren().add(card);
                            } else {
                                Node card = createCard(95, 140, rank.value(), suit.name(), Color.BLACK);
                                hBox.getChildren().add(card);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                );
            vBox.getChildren().add(hBox);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private Node createCard(double width, double height, String rank, String suit, Color color) throws IOException {

        StackPane frontFace = new StackPane();

        Shape shape = setShape(width, height);
        Text rankTopLeft = setRank(rank, color);
        Text rankBottomRight = setRank(rank, color);
        ImageView imageView = setImage(suit);

        frontFace.getChildren().addAll(shape, rankTopLeft, rankBottomRight, imageView);
        setStylingOfFaceCard(rankTopLeft, rankBottomRight);

        return frontFace;
    }

    private void setStylingOfFaceCard(Text rankTopLeft, Text rankBottomRight) {
        StackPane.setAlignment(rankTopLeft, Pos.TOP_LEFT);
        StackPane.setAlignment(rankBottomRight, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(rankTopLeft, new Insets(5, 5, 5, 5));
        StackPane.setMargin(rankBottomRight, new Insets(5, 5, 5, 5));
    }

    private Shape setShape(double width, double height) {
        Shape shape = createShape(width, height);
        shape.setFill(Color.WHITE);
        return shape;
    }

    private Text setRank(String cardValue, Color color) {
        Text text = new Text();
        text.setText(cardValue);
        text.setFont(Font.font("Dekko", FontWeight.BOLD, 20));
        text.setFill(color);
        return text;
    }

    private ImageView setImage(String suit) {
        ImageView imageView = new ImageView(new Image(suit + ".png"));
        imageView.setX(50);
        imageView.setY(25);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        return imageView;
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

    public static void main(String[] args) {
        launch(args);
    }
}
