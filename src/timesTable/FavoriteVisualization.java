package timesTable;

public class FavoriteVisualization {
    private int timesTableNum;
    private int numPoints;

    private double r;
    private double g;
    private double b;
    private double opacity;

    private int id; // used to differentiate between different objects (displayed on the pane)

    public FavoriteVisualization(int timesTableNum, int numPoints, int r, int g, int b, double opacity, int id) {
        this.timesTableNum = timesTableNum;
        this.numPoints = numPoints;

        this.r = r / 255.0;
        this.g = g / 255.0;
        this.b = b / 255.0;
        this.opacity = opacity;


        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTimesTableNum() {
        return timesTableNum;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double getOpacity() {
        return opacity;
    }

    @Override
    public String toString() {
        return "Favorite Visualization #" + getId();
    }
}
