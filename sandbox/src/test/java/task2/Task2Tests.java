package task2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2Tests {

  @Test
  public void testDistance() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(4,3);
    Assert.assertEquals(p1.distance(p1,p2),5);
  }

  @Test
  public void testDistance2() {
    Point p3 = new Point(4,3);
    Point p4 = new Point(8,6);
    Assert.assertEquals(p3.distance(p4),5);
  }
}
