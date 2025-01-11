import org.example.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CatTests {

    @Test
    void simpleCatTest(){
        Cat cat = new Cat("Barsik");
        String catName = cat.getName();

        Assertions.assertEquals("Barsik", catName);
    }


}
