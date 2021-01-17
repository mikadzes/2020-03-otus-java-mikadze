import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.otus.mygson.MyGson;
import ru.otus.mygson.TestObject;

import java.util.Arrays;

public class SerializationTest {

    @Test
    public void MyGsonTest() {
        TestObject testObject1 = TestObject.builder()
                .aBoolean(true)
                .aFloat(123.45f)
                .anInt(987)
                .ints(new int[]{1, 2, 3, 4, 5})
                .string("Any string")
                .aChar('f')
                .strings(new String[]{"a", "b", "c"})
                .collection(Arrays.asList("one", "two", "three"))
                .build();
        Gson gson = new Gson();
        System.out.println("Gson: " + gson.toJson(testObject1));
        System.out.println("Gson null: " + gson.toJson(null));
        MyGson myGson = new MyGson();
        String myJsonString = myGson.toJson(testObject1);
        System.out.println("myGson: " + myJsonString);
        System.out.println("myGson null: " + myGson.toJson(null));
        TestObject testObject2 = gson.fromJson(myJsonString, TestObject.class);
        assert (testObject1.equals(testObject2));
        System.out.println(testObject1.equals(testObject2));
    }


}
