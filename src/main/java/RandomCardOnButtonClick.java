import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;

public class RandomCardOnButtonClick extends Application {

    private final static Color SCENE_BACKGROUND_COLOR = Color.RED.deriveColor(1, 1, 1, 0.8);
    private Button button;
    private VBox vBox;

    // TODO: create 2 sets of 5 random cards with in separate hBoxes and change upon click button
    // If changing hbox/vbox need to clear them all and replace them
    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        HBox hBox = new HBox();
        createSetOfCards(hBox);
        createButton(hBox);

        root.getChildren().add(vBox);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(root, primScreenBounds.getWidth() - primaryStage.getHeight(), primScreenBounds.getHeight() - primaryStage.getHeight(), SCENE_BACKGROUND_COLOR);

        primaryStage.setTitle("Random Card");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createButton(HBox hBox) {
        button = new Button("Get Random Card");
        button.setOnAction(event -> buttonAction(event, hBox));
        button.setPadding(new Insets(10));
        vBox.getChildren().add(button);
    }

    private void buttonAction(ActionEvent event, HBox hBox) {
        if (event.getSource() == button) {
            hBox.getChildren().clear();
            createSetOfCards(hBox);
            createButton(hBox);
            System.out.println("hello");
        }
    }

    private void createSetOfCards(HBox hBox) {
        List<Suit> suits = new ArrayList<>(EnumSet.allOf(Suit.class));
        List<Rank> ranks = new ArrayList<>(EnumSet.allOf(Rank.class));

        IntStream.range(0, 5).forEach(x -> {
                    Collections.shuffle(suits);
                    Collections.shuffle(ranks);
                    Suit randomSuit = suits.get(0);
                    Rank randomRank = ranks.get(0);

                    try {
                        if (randomSuit.equals(Suit.DIAMOND) || randomSuit.equals(Suit.HEART)) {
                            Node card = createCard(95, 140, randomRank.value(), randomSuit.name(), Color.RED);
                            hBox.getChildren().add(card);
                        } else {
                            Node card = createCard(95, 140, randomRank.value(), randomSuit.name(), Color.BLACK);
                            hBox.getChildren().add(card);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    vBox.getChildren().clear();
                    vBox.getChildren().add(hBox);

                }
        );
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
