import javafx.application.Application;
import javafx.scene.control.Button;
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


    }
}
