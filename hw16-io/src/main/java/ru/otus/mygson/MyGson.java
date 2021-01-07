package ru.otus.mygson;

import lombok.SneakyThrows;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

import static java.lang.String.format;

public class MyGson {
    private final String FIELD_PATTERN = "\"%s\":%s,";
    private final String QUOTED_PATTERN = "\"%s\"";
    private final String SQUARE_BRACKETS_PATTERN = "[%s]";
    private final String BRACES_PATTERN = "{%s}";
    private final String UNQUOTED_PATTERN = "%s";

    @SneakyThrows
    public String toJson(Object testObject) {
        StringBuilder sb = new StringBuilder();
        if (testObject == null) {
            return null;
        } else {
            Field[] fields = testObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                String fieldValue;
                field.setAccessible(true);
                if (field.canAccess(testObject)) {
                    final Class<?> clazz = field.getType();
                    if (clazz.isAssignableFrom(Collection.class)) {
                        Collection<?> collection = (Collection<?>) field.get(testObject);
                        fieldValue = getJsonValueFromArray(collection.toArray());
                    } else if (clazz.isArray()) {
                        Object array = field.get(testObject);
                        fieldValue = getJsonValueFromArray(array);
                    } else {
                        fieldValue = wrapInQuotes(field.get(testObject));
                    }
                    sb.append(format(FIELD_PATTERN, fieldName, fieldValue));
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return format(BRACES_PATTERN, sb.toString());
    }

    private String getJsonValueFromArray(Object array) {
        final StringBuilder sb = new StringBuilder();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            final Object obj = Array.get(array, i);
            sb.append(wrapInQuotes(obj));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return wrapInSquareBrackets(sb.toString());
    }

    private String wrapInQuotes(Object object) {
        final Class<?> clazz = object.getClass();
        if (clazz.isAssignableFrom(Character.class) || clazz.isAssignableFrom(String.class)) {
            return format(QUOTED_PATTERN, object);
        }
        return format(UNQUOTED_PATTERN, object);
    }

    private String wrapInSquareBrackets(String string) {
        return format(SQUARE_BRACKETS_PATTERN, string);
    }

}
