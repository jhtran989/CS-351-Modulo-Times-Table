package timesTable;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

import static timesTable.Main.*;

public class Point {
    private int index;
    private double x;
    private double y;

    public Point(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getX() {
        return x + WIDTH_CORRECTION;
    }

    public double getY() {
        return y + HEIGHT_CORRECTION;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

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
