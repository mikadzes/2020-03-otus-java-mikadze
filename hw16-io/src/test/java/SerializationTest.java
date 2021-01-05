import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.otus.mygson.MyGson;
import ru.otus.mygson.TestObject;

import java.util.Collections;

public class SerializationTest {

    @Test
    public void MyGsonTest() {
        TestObject testObject = TestObject.builder()
                .aBoolean(true)
                .aFloat(123.45f)
                .anInt(987)
                .ints(new int[]{1, 2, 3, 4, 5})
                .string("Any string")
                .collection(Collections.singleton("a"))
                .build();
        System.out.println(testObject.toString());
        System.out.println(new Gson().toJson(testObject));
        MyGson myGson = new MyGson();
        String myJson = myGson.toJson(testObject);
        System.out.println(myJson);
    }


}
