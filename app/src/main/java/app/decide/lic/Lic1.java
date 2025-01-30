package app.decide.lic;
import app.decide.Decide;
import java.lang.Math;
import java.util.Arrays;

import static java.awt.geom.Point2D.distance;


public class Lic1 implements ILic{

    /**
     * Computes the circumradius of the triangle defined by the three points
     * @param x1 point 1 x coordinate
     * @param y1 point 1 y coordinate
     * @param x2 point 2 x coordinate
     * @param y2 point 2 y coordinate
     * @param x3 point 3 x coordinate
     * @param y3 point 3 y coordinate
     * @return the circumradius of the circle
     */
    static double circumRadius(double x1, double y1, double x2, double y2, double x3, double y3) {
        // Formula from https://math.stackexchange.com/a/2836488

        double dx2 = x2 - x1;
        double dy2 = y2 - y1;
        double dx3 = x3 - x1;
        double dy3 = y3 - y1;

        double numerator1 = dx2*dx2*dy3 - dx3*dx3*dy2 + dy2*dy2*dy3 - dy3*dy3*dy2;
        double numerator2 = dx2*dx2*dx3 - dx3*dx3*dx2 + dy2*dy2*dx3 - dy3*dy3*dx2;

        double denominator = 2 * (dx2*dy3 - dx3*dy2);

        if(denominator == 0) { // The three points are aligned
            return Double.POSITIVE_INFINITY;
        }

        return Math.sqrt(Math.pow(numerator1/denominator, 2) + Math.pow(numerator2/denominator, 2));
    }

    /**
     * Computes the smallest enclosing circle of three points
     * @param x1 point 1 x coordinate
     * @param y1 point 1 y coordinate
     * @param x2 point 2 x coordinate
     * @param y2 point 2 y coordinate
     * @param x3 point 3 x coordinate
     * @param y3 point 3 y coordinate
     * @return the radius of the smallest enclosing circle
     */
    static double smallestEnclosingCircle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double[] distances = {distance(x1, y1, x2, y2), distance(x1, y1, x3, y3), distance(x2, y2, x3, y3)};
        Arrays.sort(distances);

        // Case 1 : the triangle is obtuse, two points on the circle
        // c² > a² + b² if the triangle is obtuse because of the law of cosines : c² = a²+b² - 2ab cos(theta)
        if(Math.pow(distances[2], 2) >= Math.pow(distances[0], 2) + Math.pow(distances[1], 2)) {
            return distances[2]/2;
        }

        // Case 2 : three points on the circle
        return circumRadius(x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        if(num_points < 3 || parameters.RADIUS1 < 0) {
            return false;
        }
        if((x.length != y.length) || (num_points != y.length)){
            throw new IllegalArgumentException("The length of the inputs do not match");
        }
        for (int i = 0; i < num_points-2; i++) {
            if(smallestEnclosingCircle(x[i], y[i], x[i+1], y[i+1], x[i+2], y[i+2]) > parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }

    public boolean in_circle(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        for (int i = 0; i < num_points-2; i++) {

            double ab = Math.sqrt(Math.pow(x[i+1] - x[i], 2) + Math.pow(y[i+1] - y[i], 2));
            double bc = Math.sqrt(Math.pow(x[i+2] - x[i+1], 2) + Math.pow(y[i+2] - y[i+1], 2));
            double ca = Math.sqrt(Math.pow(x[i+2] - x[i], 2) + Math.pow(y[i+2] - y[i], 2));
            
            double s = (ab + bc + ca) / 2;
            double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
            double circumradius = (ab * bc * ca) / (4 * area);

            if(circumradius <= parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }
}
