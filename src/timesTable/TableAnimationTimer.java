package timesTable;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class TableAnimationTimer extends AnimationTimer {
    private long currentDuration;
    private Slider delaySlider;
    private TextField numPointsText;

    private Pane root;
    private Label timesTableValueLabel;
    private TableVisualization tableVisualization;
    private Slider stepNumSlider;


    public TableAnimationTimer(Slider delaySlider, TextField numPointsText, Pane root,
                               Label timesTableValueLabel, TableVisualization tableVisualization,
                               Slider stepNumSlider) {
        currentDuration = 0;
        this.delaySlider = delaySlider;
        this.numPointsText = numPointsText;

        this.root = root;
        this.timesTableValueLabel = timesTableValueLabel;
        this.tableVisualization = tableVisualization;
        this.stepNumSlider = stepNumSlider;
    }

    public void run(boolean jump) {
        root.getChildren().removeIf(node -> node instanceof Group);

        Group linesGroup = tableVisualization.generateLines(
                Double.parseDouble(numPointsText.getText()));
        root.getChildren().add(linesGroup);

        timesTableValueLabel.setText(Double.toString(tableVisualization.getTimesTableNum()));

        if (!jump) {
            tableVisualization.incrementTimesTableNum(stepNumSlider.getValue());
        }
    }

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
