package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    private static Class<ClassWithTest> clazz = ClassWithTest.class;
    private static Method beforeMethod;
    private static List<Method> testMethods = new ArrayList<>();
    private static Method afterMethod;
    private static int successful = 0;
    private static int errors = 0;


    public static void main(String[] args) throws Exception {
        Method[] methods = clazz.getDeclaredMethods();
        setMethods(methods);
        for (Method testMethod : testMethods) {
            ClassWithTest object = new ClassWithTest();
            beforeMethod.invoke(object);
            try {
                testMethod.invoke(object);
                successful++;
            } catch (Exception e) {
                errors++;
                System.out.println("Тест выбросил исключение");
            }
            afterMethod.invoke(object);
            System.out.println("========================================");
        }
        System.out.println("Всего тестов: " + testMethods.size());
        System.out.println("Прошло успешно: " + successful);
        System.out.println("Завершилось ошибкой: " + errors);
    }

    private static void setMethods(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) beforeMethod = method;
            else if (method.isAnnotationPresent(After.class)) afterMethod = method;
            else if (method.isAnnotationPresent(Test.class)) testMethods.add(method);
        }
    }
}