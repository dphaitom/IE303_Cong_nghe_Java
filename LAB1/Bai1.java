package LAB1;
import java.util.Random;

public class Bai1 {
    public static void main(String[] args) {
        double r = 1; 
        int totalPoints = 1000000; 
        int pointsInsideCircle = 0;
        
        Random random = new Random();

        for (int i = 0; i < totalPoints; i++) {
            double x = (2 * r) * random.nextDouble() - r; 
            double y = (2 * r) * random.nextDouble() - r;

            if (x * x + y * y <= r * r) { 
                pointsInsideCircle++;
            }
        }

        double estimatedPi = 4.0 * pointsInsideCircle / totalPoints;
        double estimatedArea = estimatedPi * (r * r);

        System.out.println("Diện tích xấp xỉ của hình tròn bán kính r: " + estimatedArea);
    }
}
