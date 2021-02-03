package timesTable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.text.DecimalFormat;

public class Main extends Application {
    public static final double WIDTH = 1800;
    public static final double HEIGHT = 1024;
    public static final double WIDTH_CORRECTION = WIDTH / 2;
    public static final double HEIGHT_CORRECTION = HEIGHT / 2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Modulo Times Table Visualization");

        double initialTimesTableNum = 2;
        final Circle circle = new Circle(WIDTH_CORRECTION, HEIGHT_CORRECTION, 500);
        // radius of 500 was an arbitrary choice
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.DARKBLUE);

        double controlSpacing = 10; // 10

        VBox controls = new VBox(controlSpacing);
        controls.setLayoutX(90);
        controls.setLayoutY(15);

        Button startButton = new Button("Start");
        Button pauseButton = new Button("Pause");

        HBox currentTimesTableBox = new HBox(controlSpacing);
        Label currentTimesTableLabel = new Label("Current Times Table Number: ");
        Label currentTimesTableValueLabel = new Label(Double.toString(initialTimesTableNum));
        currentTimesTableBox.getChildren().addAll(currentTimesTableLabel, currentTimesTableValueLabel);

        HBox stepNumBox = new HBox(controlSpacing);
        Label stepNumLabel = new Label("Incrementing by:");
        Slider stepNumSlider = new Slider(0, 5, 2);
        stepNumSlider.setShowTickLabels(true);
        stepNumSlider.setShowTickMarks(true);
        stepNumSlider.setMajorTickUnit(0.5);
        stepNumSlider.setBlockIncrement(0.1);
        stepNumBox.getChildren().addAll(stepNumLabel, stepNumSlider);

        HBox delayBox = new HBox(controlSpacing);
        Label delayLabel = new Label("Delay increment by (in seconds):");
        Slider delaySlider = new Slider(0, 5, 2);
        delaySlider.setShowTickLabels(true);
        delaySlider.setShowTickMarks(true);
        delaySlider.setMajorTickUnit(0.5);
        delaySlider.setBlockIncrement(0.1);
        delayBox.getChildren().addAll(delayLabel, delaySlider);

        HBox timesTableOptionBox = new HBox(controlSpacing);
        Label timesTableNumLabel = new Label("Times Table Number: ");
        TextField timesTableNumText = new TextField("2");
        timesTableOptionBox.getChildren().addAll(timesTableNumLabel, timesTableNumText);

        HBox numPointsBox = new HBox(controlSpacing);
        Label numPointsLabel = new Label("Number of points on Circle: ");
        TextField numPointsText = new TextField("360");
        numPointsBox.getChildren().addAll(numPointsLabel, numPointsText);

        HBox jumpBox = new HBox(controlSpacing);
        Label jumpLabel = new Label("Jump to Parameter Selection");
        Button jumpButton = new Button("Jump");
        jumpBox.getChildren().addAll(jumpLabel, jumpButton);

        ComboBox<FavoriteVisualization> favoriteVisualizationComboBox = new ComboBox<>();
        favoriteVisualizationComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(FavoriteVisualization favoriteVisualization) {
                if (favoriteVisualization == null) {
                    return null;
                }

                return favoriteVisualization.toString();
            }

            @Override
            public FavoriteVisualization fromString(String string) {
                return null;
            }
        });
        favoriteVisualizationComboBox.getItems().addAll(
                new FavoriteVisualization(16, 360, 153, 0, 0, 1.0, 1),
                new FavoriteVisualization(28, 360, 204, 0, 0, 1.0, 2),
                new FavoriteVisualization(29, 360, 51, 204,255, 1.0, 3),
                new FavoriteVisualization(46, 360, 51, 153,255, 1.0, 4),
                new FavoriteVisualization(49, 360, 102, 255, 102, 1.0, 5),
                new FavoriteVisualization(61, 360, 0, 255, 51, 1.0, 6),
                new FavoriteVisualization(73, 360, 0, 204, 0, 1.0, 7),
                new FavoriteVisualization(76, 360, 0, 153, 0, 1.0, 8),
                new FavoriteVisualization(86, 200, 0, 102, 0, 1.0, 9),
                new FavoriteVisualization(91, 200, 51, 51, 51, 1.0, 10)
        );

        controls.getChildren().addAll(currentTimesTableBox, stepNumBox, delayBox,
                timesTableOptionBox, numPointsBox, jumpBox, favoriteVisualizationComboBox,
                startButton,
                pauseButton);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        Pane root = new Pane(canvas, circle, controls);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        TableVisualization tableVisualization = new TableVisualization(initialTimesTableNum,
                500);

        TableAnimationTimer tableAnimationTimer = new TableAnimationTimer(delaySlider,
                numPointsText, root, currentTimesTableValueLabel,
                tableVisualization, stepNumSlider);

        startButton.setOnAction(event -> tableAnimationTimer.start());
        pauseButton.setOnAction(event -> tableAnimationTimer.stop());

        jumpButton.setOnAction(event -> {
            tableAnimationTimer.stop();
            tableVisualization.setTimesTableNum(Double.parseDouble(timesTableNumText.getText()));
            tableAnimationTimer.run(true);
        });

        favoriteVisualizationComboBox.setOnAction(event -> {
            tableAnimationTimer.stop();
            tableVisualization.setTimesTableNum(favoriteVisualizationComboBox.getValue().getTimesTableNum());
            numPointsText.setText(Double.toString(favoriteVisualizationComboBox.getValue().getNumPoints()));
            tableVisualization.setColors(favoriteVisualizationComboBox.getValue().getR(),
                    favoriteVisualizationComboBox.getValue().getG(),
                    favoriteVisualizationComboBox.getValue().getB(),
                    favoriteVisualizationComboBox.getValue().getOpacity());
            tableAnimationTimer.run(true);
        });
    }
}
