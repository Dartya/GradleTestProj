import com.example.Main;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    @Test
    public void mainTest(){
        Assert.assertEquals(2, Main.sum(1, 1));
    }
}
