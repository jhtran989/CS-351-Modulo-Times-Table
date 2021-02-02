package timesTable;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class TableAnimationTimer extends AnimationTimer {
    private long currentDuration;

    public TableAnimationTimer() {
        currentDuration = 0;
    }

    public void run(boolean jump, Pane root, double numPoints, Label timesTableValueLabel,
                    TableVisualization tableVisualization, Slider stepNumSlider) {
        root.getChildren().removeIf(node -> {
            return node instanceof Group;
        });

        Group linesGroup = tableVisualization.generateLines(numPoints);
        root.getChildren().add(linesGroup);

        timesTableValueLabel.setText(Double.toString(tableVisualization.getTimesTableNum()));

        if (!jump) {
            tableVisualization.incrementTimesTableNum(stepNumSlider.getValue());
        }
    }

    @Override
    public void handle(long now) {
        if (now - currentDuration >= ) {

        }
    }
}
