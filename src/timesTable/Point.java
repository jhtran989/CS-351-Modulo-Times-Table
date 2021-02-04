package timesTable;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

import static timesTable.Main.*;

/**
 * John Tran
 *
 * The class used to store the coordinates of a given point on the times
 * table circle (x and y coordinates) and to provide a way to connect two points
 */
public class Point {
    private int index;
    private double x;
    private double y;

    /**
     * Constructor for Point
     * @param index the index used to find a given point on the times table
     *              circle (starts at 0 with theta = pi and increases clockwise)
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public double getX() {
        return x + WIDTH_CORRECTION;
    }

    public double getY() {
        return y + HEIGHT_CORRECTION;
    }

    /**
     * Creates a list of Point objects that circles around the times table
     * circle, given its radius and a specified number of points
     * @param radius radius of times table circle
     * @param numPoints specified number of points to be created
     * @return a list of Point objects
     */
    public static List<Point> fillInPoints(double radius, double numPoints) {
        List<Point> pointList = new ArrayList<>();

        double pointSpacing = 2 * PI / numPoints;
        double start = PI / 2;
        double end = start + 2 * PI;

        int currIndex = 0;
        double xPos;
        double yPos;
        for (double theta = start; theta < end; theta += pointSpacing) {
            xPos = cos(theta) * radius;
            yPos = sin(theta) * radius;

            Point point = new Point(currIndex, xPos, yPos);
            pointList.add(point);
            currIndex++;
        }

        return pointList;
    }
}
