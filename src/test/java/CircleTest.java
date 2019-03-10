import com.example.circle.Circle;
import org.junit.Assert;
import org.junit.Test;

public class CircleTest{
    @Test
    public void testArea(){
        Circle circle = new Circle(1.0);
        Assert.assertEquals(3.1415, circle.getArea(), 0.0001);
    }
}