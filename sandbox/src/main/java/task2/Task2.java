package task2;

public class Task2 {
  public static void main(String[] args) {
    Point p1 = new Point(0,0);
    Point p2 = new Point(4,3);
    p1.printDistance(p1,p2);
    p1.printDistance(p2);

    Point p3 = new Point(4,3);
    Point p4 = new Point(8,6);
    p3.printDistance(p3,p4);

    p3.printDistance(p4);
  }

}
