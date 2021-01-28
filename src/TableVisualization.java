import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class TableVisualization {
    private final double incrementValue;

    private final double r;
    private final double g;
    private double b;
    private final double opacity;

    private boolean increment;

    private double timesTableNum;
    private double radius;

    public TableVisualization(double timesTableNum, double radius) {
        this.incrementValue = 0.1 * ThreadLocalRandom.current().nextDouble();

        this.r = ThreadLocalRandom.current().nextDouble();
        this.g = ThreadLocalRandom.current().nextDouble();
        this.b = 0.0;
        this.opacity = 1;
        this.increment = true;

        this.timesTableNum = timesTableNum;
        this.radius = radius;
    }

    public double getB() {
        return b;
    }

    public double getTimesTableNum() {
        return timesTableNum;
    }

    public double getRadius() {
        return radius;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }

    public void incrementTimesTableNum(double stepNum) {
        timesTableNum += stepNum;
    }

    private void incrementBlue() {
        if (increment) {
            if (Double.compare(b + incrementValue, 1.0) > 0.0) {
                b = 1.0;
                increment = false;
            } else {
                b += incrementValue;
            }
        } else {
            if (Double.compare(b + incrementValue, 1.0) < 0.0) {
                b = 0.0;
                increment = true;
            } else {
                b -= incrementValue;
            }
        }
    }

    public Group generateLines(double numPoints) {
        incrementBlue();

        Color color = new Color(r, g, b, opacity);
        Group lines = new Group();


    }
}
