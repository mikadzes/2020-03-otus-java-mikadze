package ru.otus;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class Methods {
    public static String concatNameWithParameters(Method method) {
        return method.getName() + Arrays.stream(method.getParameters())
                .map(Parameter::getName)
                .reduce("", String::concat);
    }
}
