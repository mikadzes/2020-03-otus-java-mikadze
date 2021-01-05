package ru.otus.mygson;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.String.format;

public class MyGson {
    private final String NUM_PATTERN = "\"%s\":%s";
    private final String STR_PATTERN = "\"%s\":\"%s\"";


    @SneakyThrows
    public String toJson(Object testObject) {
        if (testObject != null) {

            Field[] fields = testObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println(field.getName());
                if (field.canAccess(testObject)) {
                    if (field.getType().isAssignableFrom(String.class)) {
                        System.out.println(format(STR_PATTERN, field.getName(), field.get(testObject)));
                    } else if (field.getType().isAssignableFrom(Collection.class)) {
                        System.out.println("зыс из коллекшн");
                    } else if (field.getType().isArray()) {
                        unpackArray(field.getType());
                        if (field.getType().getComponentType().isAssignableFrom(int.class)) {
                            int[] array = (int[]) field.get(testObject);
                            System.out.println(Arrays.toString(array));
                            System.out.println("зыс из аррэй");
                        }

                    } else {
                        System.out.println(format(NUM_PATTERN, field.getName(), field.get(testObject)));
                    }


                }

            }
        }
        return null;
    }

    private <T> T[] unpackArray(Class<?> obj) {
        return (T[]) new Object[]{obj};
    }
}
