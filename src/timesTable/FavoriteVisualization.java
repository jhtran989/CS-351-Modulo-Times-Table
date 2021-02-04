package timesTable;

/**
 * John Tran
 *
 * The class that encompasses most of the information needed to describe a given
 * favorite visualization (opacity could have been omitted, but there
 * for fullness -- all of my favorites just use a value of 1.0)
 */
public class FavoriteVisualization {
    private int timesTableNum;
    private int numPoints;

    private double r;
    private double g;
    private double b;
    private double opacity;

    private int id; // used to differentiate between different objects
    // (displayed on the ControlBox drop down box)

    // another way could have worked, but this holds the initial message of
    // the drop down menu (ComboBox) with no other purpose (filled with some
    // default values)
    public static final FavoriteVisualization initialFavorite =
            new FavoriteVisualization(0, 1, 0, 0,
                    0, 0, -1) {
                @Override
                public String toString() {
                    return "-- Please select a favorite visualization below --";
                }
            };

    /**
     * Constructor for FavoriteVisualization
     * @param timesTableNum given times table number
     * @param numPoints given number of points on circle (modulo)
     * @param r red color value
     * @param g green color value
     * @param b blue color value
     * @param opacity opacity value
     * @param id arbitrary ID to differentiate different favorite visualizations
     */
    public FavoriteVisualization(int timesTableNum, int numPoints, int r,
                                 int g, int b, double opacity, int id) {
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
        return "Favorite Visualization #" + getId() + "\n {Times Table Number: "
                + timesTableNum + "}, {Number of Points: " + numPoints + "}";
    }
}
