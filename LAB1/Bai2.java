package LAB1;
import java.util.Random;

public class Bai2 {
    public static void main(String[] args) {
        int totalPoints = 1000000; 
        int pointsInsideCircle = 0;

        Random random = new Random();

        for (int i = 0; i < totalPoints; i++) {
            double x = 2 * random.nextDouble() - 1; 
            double y = 2 * random.nextDouble() - 1; 

            if (x * x + y * y <= 1) { /
                pointsInsideCircle++;
            }
        }

        double estimatedPi = 4.0 * pointsInsideCircle / totalPoints;
        System.out.println("Giá trị xấp xỉ của π: " + estimatedPi);
    }
}
