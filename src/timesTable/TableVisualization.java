package timesTable;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * John Tran
 *
 * Holds all the information for the color values, times table number and
 * increment value, and radius of the times table circle
 */
public class TableVisualization {
    private final double incrementValue;

    private double r;
    private double g;
    private double b;
    private double opacity;

    private boolean increment;

    private double timesTableNum;
    private double radius;

    /**
     * Constructor for TableVisualization
     * @param timesTableNum current times table number
     * @param radius radius of times table circle
     */
    public TableVisualization(double timesTableNum, double radius) {
        this.incrementValue = 0.1 * ThreadLocalRandom.current().nextDouble();
        // to make sure at least 10 different colors are shown during the visualization

        this.r = ThreadLocalRandom.current().nextDouble();
        this.g = ThreadLocalRandom.current().nextDouble();
        this.b = 0.0;
        this.opacity = 1;
        this.increment = true;

        this.timesTableNum = timesTableNum;
        this.radius = radius;
    }

    public double getTimesTableNum() {
        return timesTableNum;
    }

    public void incrementTimesTableNum(double stepNum) {
        timesTableNum += stepNum;
    }

    public void setTimesTableNum(double timesTableNum) {
        this.timesTableNum = timesTableNum;
    }

    public void setColors(double r, double g, double b, double opacity) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.opacity = opacity;
    }

    /**
     * Increments the blue color value of the circle that cycles up and down while
     * keeping the red and green color values constant
     *
     * The blue color value was chosen arbitrarily to be incremented
     *
     * The blue value initially set to zero while the other two color values are
     * randomly set (once the blue value hits the min/max value, the direction of
     * the incrementation reverses)
     */
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

    /**
     * Creates all the lines we see in the visualization of the times table circle
     * The color of the lines is determined by the color values (and opacity) determined above
     * (only the blue color value is incremented: see incrementBlue() above)
     *
     * @param numPoints number of points used to create the lines around the circle (the specified modulo)
     * @return the Group of lines created
     */
    public Group generateLines(double numPoints) {
        incrementBlue();

        Color color = new Color(r, g, b, opacity);
        Group linesGroup = new Group();
        List<Point> pointList = Point.fillInPoints(radius, numPoints);

        double currIndex;
        double nextIndex;
        for (Point point : pointList) {
            currIndex = point.getIndex();
            nextIndex = (currIndex * timesTableNum) % numPoints;

            Point tailPoint = pointList.get(point.getIndex());
            Point arrowPoint = pointList.get((int) nextIndex);

            Line line = new Line(tailPoint.getX(), tailPoint.getY(),
                    arrowPoint.getX(), arrowPoint.getY());

            line.setStroke(color);
            linesGroup.getChildren().add(line);
        }

        return linesGroup;
    }
}
