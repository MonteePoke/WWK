package kurlyk.common;

import javafx.geometry.Point2D;

public class PointGenerator {
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double rangeX;
    private double rangeY;

    public PointGenerator(double maxX, double maxY) {
        this(0, 0, maxX, maxY);
    }

    public PointGenerator(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.rangeX = maxX - minX;
        this.rangeY = maxY - minY;
        if(minX < 0 || minY < 0 || maxX < 0 || maxY < 0 || rangeX < 0 || rangeY < 0){
            throw new RuntimeException();
        }
    }

    public Point2D getRandomPoint(){
        return new Point2D(rangeX * Math.random(), rangeY * Math.random());
    }
}
