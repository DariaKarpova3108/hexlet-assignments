package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
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
        return "Квартира площадью " +
                getArea() + " метров на "
                + floor +
                " этаже";
    }
}


// END
