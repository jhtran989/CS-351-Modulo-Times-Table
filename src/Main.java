import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
    public static final double WIDTH = 1800;
    public static final double HEIGHT = 1024;
    public static final double WIDTH_CORRECTION = WIDTH / 2;
    public static final double HEIGHT_CORRECTION = HEIGHT / 2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Modulo Times Table Visualization");

        double initialTimesTableNum = 2;
        final Circle circle = new Circle(WIDTH_CORRECTION, HEIGHT_CORRECTION, 500);
        // radius of 500 was an arbitrary choice
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.DARKBLUE);

        double controlSpacing = 10;

        VBox controls = new VBox(controlSpacing);
        controls.setLayoutX(15);
        controls.setLayoutY(90);

        Button start = new Button("Start");
        Button pause = new Button("Pause");

        HBox timesTableBox = new HBox(controlSpacing);
        Label timesTableLabel = new Label("Times Table Number: ");
        Label timesTableValueLabel = new Label(Double.toString(initialTimesTableNum));
        timesTableBox.getChildren().addAll(timesTableLabel, timesTableValueLabel);

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

        Button jump = new Button("Jump to the following:");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        Pane root = new Pane(canvas, circle, controls);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        TableVisualization tableVisualization = new TableVisualization(initialTimesTableNum,
                500);


    }
}
