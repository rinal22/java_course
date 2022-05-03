package my.pkg;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("Rinal");

  }

  public static void hello(String somebody) {

    System.out.println("Hello, " + somebody + "!");
    double l = 5;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));
    double a = 4;
    double b = 6;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a,b));
  }

  public static double area(double l){
    return l*l;
  }

  public static double area(double a, double b){
    return a*b;
  }
}