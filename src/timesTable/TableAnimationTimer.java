package timesTable;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * John Tran
 *
 * Custom animation timer that holds the run method (main loop) to execute the
 * visualization
 */
public class TableAnimationTimer extends AnimationTimer {
    private long currentDuration;
    private final Slider delaySlider;
    private final TextField numPointsText;

    private final Pane root;
    private final Label currentTimesTableValueLabel;
    private final Label currentNumPointsValueLabel;
    private final TableVisualization tableVisualization;
    private final Slider stepNumSlider;

    /**
     * Constructor for TableAnimationTimer
     * @param delaySlider slider that changes how fast each new frame is created
     * @param numPointsText text field that holds the number of points
     * @param root holds the Pane
     * @param currentTimesTableValueLabel changes the current times table
*                                    number being displayed
     * @param currentNumPointsValueLabel changes the current number of points
     *                                  on the times table circle being
     *                                   displayed
     * @param tableVisualization holds the visualization object
     * @param stepNumSlider slider that changes the increment of the times
     */
    public TableAnimationTimer(Slider delaySlider, TextField numPointsText,
                               Pane root, Label currentTimesTableValueLabel,
                               Label currentNumPointsValueLabel,
                               TableVisualization tableVisualization,
                               Slider stepNumSlider) {
        currentDuration = 0;
        this.delaySlider = delaySlider;
        this.numPointsText = numPointsText;
        this.currentNumPointsValueLabel = currentNumPointsValueLabel;
        this.root = root;
        this.currentTimesTableValueLabel = currentTimesTableValueLabel;
        this.tableVisualization = tableVisualization;
        this.stepNumSlider = stepNumSlider;
    }

    /**
     * The main visualization loop
     * @param jump if the times table does not need to be incremented
     */
    public void run(boolean jump) {
        root.getChildren().removeIf(node -> node instanceof Group);

        double numPoints = Double.parseDouble(numPointsText.getText());
        Group linesGroup = tableVisualization.generateLines(
                numPoints);
        root.getChildren().add(linesGroup);

        //FIXME (originally displayed an arbitrarily big number of decimal places)
        //currentTimesTableValueLabel.setText(
        // Double.toString(tableVisualization.getTimesTableNum()));

        currentNumPointsValueLabel.setText(String.format("%.1f",
                numPoints));
        currentTimesTableValueLabel.setText(String.format("%.2f",
                tableVisualization.getTimesTableNum()));

        if (!jump) {
            tableVisualization.incrementTimesTableNum(
                    stepNumSlider.getValue());
        }
    }

    /**
     * Updates the visualization according the value set on the delay slider
     * (converted to nanosecond from seconds) if the TextField for the number
     * of points is not empty
     * During the wait time, nothing is updated
     *
     * @param now the total time of the animation in nanoseconds
     */
    @Override
    public void handle(long now) {
        if (now - currentDuration >= delaySlider.getValue() * 1_000_000_000) {
            if (!numPointsText.getText().equals("")) {
                run(false);
                currentDuration = now;
            }
        }
    }
}
