import java.util.*;

class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ConvexHull {
    static boolean ccw(Point A, Point B, Point C) {
        return 1L * (B.x - A.x) * (C.y - A.y) - 1L * (C.x - A.x) * (B.y - A.y) > 0;
    }

    static List<Point> convexHull(List<Point> p) {
        int n = p.size();
        if (n < 2) return p;
        
        p.sort((A, B) -> A.x != B.x ? Integer.compare(A.x, B.x) : Integer.compare(A.y, B.y));
        
        List<Point> hull = new ArrayList<>();
        
        for (Point point : p) {
            while (hull.size() >= 2 && ccw(hull.get(hull.size() - 2), hull.get(hull.size() - 1), point)) {
                hull.remove(hull.size() - 1);
            }
            hull.add(point);
        }
        
        // Dựng bao dưới
        int t = hull.size() + 1;
        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() >= t && ccw(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p.get(i))) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p.get(i));
        }
        
        // Xoá điểm đầu bị lặp lại ở cuối
        if (hull.size() > 1) hull.remove(hull.size() - 1);
        
        return hull;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Point> points = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y));
        }
        
        List<Point> hull = convexHull(points);
        
    
        for (Point p : hull) {
            System.out.println(p.x + " " + p.y);
        }
        
        scanner.close();
    }
}