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

/**
 * John Tran
 *
 * Holds the main and start methods to setup the window and visualization
 */
public class Main extends Application {
    /**
     * The visualization (window) constants used
     * They were made public because they were accessed in the Point class
     * (specifically, to create points based off the center of the window)
     */
    public static final double WIDTH = 1800;
    public static final double HEIGHT = 1024;
    public static final double WIDTH_CORRECTION = WIDTH / 2;
    public static final double HEIGHT_CORRECTION = HEIGHT / 2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Window name
        primaryStage.setTitle("Modulo Times Table Visualization");

        // Initial times table number and initialization of the times table
        // circle
        double initialTimesTableNum = 2;
        double initialNumPoints = 360;
        final Circle circle = new Circle(WIDTH_CORRECTION,
                HEIGHT_CORRECTION, 500);
        // radius of 500 was an arbitrary choice
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.DARKBLUE);

        // Arbitrary spacing of 10 between the controls
        double controlSpacing = 10;

        // Holds all of the below controls vertically (approx. in upper left
        // hand of the window)
        VBox controls = new VBox(controlSpacing);
        controls.setLayoutX(90);
        controls.setLayoutY(15);

        // Buttons for start and pause
        Button startButton = new Button("Start");
        Button pauseButton = new Button("Pause");

        // The following controls will be stored in HBox (corresponding label
        // will also be added, but not discussed below since they're inferred)

        // Display the current times table number being shown
        HBox currentTimesTableBox = new HBox(controlSpacing);
        Label currentTimesTableLabel = new Label(
                "Current Times Table Number: ");
        Label currentTimesTableValueLabel = new Label(
                Double.toString(initialTimesTableNum));
        currentTimesTableBox.getChildren().addAll(
                currentTimesTableLabel, currentTimesTableValueLabel);

        // Display the current times table number being shown
        HBox currentNumPointsBox = new HBox(controlSpacing);
        Label currentNumPointsLabel = new Label(
                "Current Number of Points on Circle: ");
        Label currentNumPointsValueLabel = new Label(
                Double.toString(initialNumPoints));
        currentNumPointsBox.getChildren().addAll(
                currentNumPointsLabel, currentNumPointsValueLabel);

        // Creates the slider for choosing the increment of the times table
        // number (parameter values like tick marks were kind of arbitrary)
        HBox stepNumBox = new HBox(controlSpacing);
        Label stepNumLabel = new Label("Incrementing by:");
        Slider stepNumSlider = new Slider(0, 5, 2);
        stepNumSlider.setShowTickLabels(true);
        stepNumSlider.setShowTickMarks(true);
        stepNumSlider.setMajorTickUnit(0.5);
        stepNumSlider.setBlockIncrement(0.1);
        stepNumBox.getChildren().addAll(stepNumLabel, stepNumSlider);

        // Creates the slider for choosing how long the visualization is
        // delayed for processing each successive times table (parameter values
        // like tick marks were kind of arbitrary)
        HBox delayBox = new HBox(controlSpacing);
        Label delayLabel = new Label("Delay increment by (in seconds):");
        Slider delaySlider = new Slider(0, 5, 2);
        delaySlider.setShowTickLabels(true);
        delaySlider.setShowTickMarks(true);
        delaySlider.setMajorTickUnit(0.5);
        delaySlider.setBlockIncrement(0.1);
        delayBox.getChildren().addAll(delayLabel, delaySlider);

        // Creates a text field to enter a desired times table number
        HBox timesTableOptionBox = new HBox(controlSpacing);
        Label timesTableNumLabel = new Label("Times Table Number: ");
        TextField timesTableNumText = new TextField(
                Double.toString(initialTimesTableNum));
        timesTableOptionBox.getChildren().addAll(timesTableNumLabel,
                timesTableNumText);

        // Creates a text field to enter a desired modulo (number of points
        // used to generate the visualization)
        HBox numPointsBox = new HBox(controlSpacing);
        Label numPointsLabel = new Label("Number of Points on Circle: ");
        TextField numPointsText = new TextField(
                Double.toString(initialNumPoints));
        numPointsBox.getChildren().addAll(numPointsLabel, numPointsText);

        // Creates the jump button to setup the times table circle and
        // visualization using the inputs given in the Textfield areas
        HBox jumpBox = new HBox(controlSpacing);
        Label jumpLabel = new Label("Jump to Parameter Selection");
        Button jumpButton = new Button("Jump");
        jumpBox.getChildren().addAll(jumpLabel, jumpButton);

        // Creates the ComboBox (drop down menu) for the favorite visualizations
        // (can be viewed at any time by selecting the option)
        ComboBox<FavoriteVisualization> favoriteVisualizationComboBox =
                new ComboBox<>();
        favoriteVisualizationComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(FavoriteVisualization
                                           favoriteVisualization) {
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
                FavoriteVisualization.initialFavorite,
                new FavoriteVisualization(16, 360, 153,
                        0, 0, 1.0, 1),
                new FavoriteVisualization(28, 360, 204,
                        0, 0, 1.0, 2),
                new FavoriteVisualization(29, 360, 51,
                        204,255, 1.0, 3),
                new FavoriteVisualization(46, 360, 51,
                        153,255, 1.0, 4),
                new FavoriteVisualization(49, 360, 102,
                        255, 102, 1.0, 5),
                new FavoriteVisualization(61, 360, 0,
                        255, 51, 1.0, 6),
                new FavoriteVisualization(73, 360, 0,
                        204, 0, 1.0, 7),
                new FavoriteVisualization(76, 360, 0,
                        153, 0, 1.0, 8),
                new FavoriteVisualization(86, 200, 0,
                        102, 0, 1.0, 9),
                new FavoriteVisualization(91, 200, 51,
                        51, 51, 1.0, 10)
        );
        favoriteVisualizationComboBox.getSelectionModel().selectFirst();
        //

        // Adds all the above controls to the controls VBox (so they can be
        // viewed by the user)
        controls.getChildren().addAll(currentTimesTableBox,
                currentNumPointsBox, stepNumBox,
                delayBox, timesTableOptionBox, numPointsBox, jumpBox,
                favoriteVisualizationComboBox,
                startButton,
                pauseButton);

        // Creates a canvas to "paint" on
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        // Sets up the window with the specified parameters and controls
        Pane root = new Pane(canvas, circle, controls);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Displays the window with the controls and visualizations
        primaryStage.setScene(scene);
        primaryStage.show();

        // Creates a visualization object that will creates the lines for
        // each iteration of the visualization at the given times table
        // number and times table circle radius
        TableVisualization tableVisualization = new TableVisualization(
                initialTimesTableNum, 500);

        // Creates the visualization timer with the necessary parameters for
        // the run() method
        TableAnimationTimer tableAnimationTimer = new TableAnimationTimer(
                delaySlider, numPointsText, root,
                currentTimesTableValueLabel,
                currentNumPointsValueLabel,
                tableVisualization, stepNumSlider);

        // Set up all the action events for the start, stop, and jump buttons
        // with the drop down menu for the favorite selections

        startButton.setOnAction(event -> tableAnimationTimer.start());
        pauseButton.setOnAction(event -> tableAnimationTimer.stop());

        jumpButton.setOnAction(event -> {
            tableAnimationTimer.stop();
            tableVisualization.setTimesTableNum(Double.parseDouble(
                    timesTableNumText.getText()));
            tableAnimationTimer.run(true);
        });

        favoriteVisualizationComboBox.setOnAction(event -> {
            tableAnimationTimer.stop();
            tableVisualization.setTimesTableNum(
                    favoriteVisualizationComboBox
                            .getValue().getTimesTableNum());
            numPointsText.setText(Double.toString(
                    favoriteVisualizationComboBox.getValue().getNumPoints()));
            tableVisualization.setColors(
                    favoriteVisualizationComboBox.getValue().getR(),
                    favoriteVisualizationComboBox.getValue().getG(),
                    favoriteVisualizationComboBox.getValue().getB(),
                    favoriteVisualizationComboBox.getValue().getOpacity());
            tableAnimationTimer.run(true);
        });
    }
}
