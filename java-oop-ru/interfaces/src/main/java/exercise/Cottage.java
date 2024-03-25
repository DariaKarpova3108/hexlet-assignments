package exercise;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        double areaTotal = getArea();
        if (areaTotal == another.getArea()) {
            return 0;
        } else if (areaTotal > another.getArea()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "" + floorCount + " этажный коттедж площадью "
                + getArea() +
                " метров";
    }
}
// END
