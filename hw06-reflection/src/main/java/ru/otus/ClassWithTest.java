package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class ClassWithTest {
    private static final String BEFORE_TEXT = "Этот метод выполняется перед тестом";
    private static final String TEST_TEXT_1 = "Это первый тестовый метод";
    private static final String TEST_TEXT_2 = "Это второй тестовый метод";
    private static final String TEST_TEXT_3 = "Это третий тестовый метод";
    private static final String AFTER_TEXT = "Этот метод выполняется после теста";

    @Before
    public void beforeTest() {
        System.out.println(BEFORE_TEXT);
    }

    @Test
    public void test1() {
        System.out.println(TEST_TEXT_1);
    }

    @Test
    public void test2() {
        System.out.println(TEST_TEXT_2);
        throw new RuntimeException("Этот тест выбрасывает исключение");
    }

    @Test
    public void test3() {
        System.out.println(TEST_TEXT_3);
    }

    @After
    public void afterTest() {
        System.out.println(AFTER_TEXT);
    }
}
