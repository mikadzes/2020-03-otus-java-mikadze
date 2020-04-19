package ru.otus;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DIYarrayListTest {

    @Test
    public void addAllTest() {
        DIYarrayList<String> classUnderTest = new DIYarrayList<>();
        for (int i = 0; i < 25; i++) {
            classUnderTest.add(Integer.toString(i));
        }
        assertEquals(25, classUnderTest.size());
        Collections.addAll(classUnderTest, "a", "b", "c");
        assertEquals(28, classUnderTest.size());
    }

    @Test
    public void copyTest() {
        DIYarrayList<String> srcList = new DIYarrayList<>();
        for (int i = 0; i < 25; i++) {
            srcList.add(Integer.toString(i));
        }
        DIYarrayList<String> destList = new DIYarrayList<>();
        for (int i = 25; i > 0; i--) {
            destList.add(Integer.toString(i));
        }
        Collections.copy(destList, srcList);
        assertEquals(srcList.size(), destList.size());
        for (int i = 0; i < srcList.size(); i++) {
            assertEquals(srcList.get(i), destList.get(i));
        }
    }

    @Test
    public void sortTest() {
        DIYarrayList<String> sortedList = new DIYarrayList<>();
        for (int i = 0; i < 25; i++) {
            sortedList.add(Integer.toString(i));
        }
        Collections.sort(sortedList);
        DIYarrayList<String> listToBeSorted = new DIYarrayList<>();
        for (int i = 24; i >= 0; i--) {
            listToBeSorted.add(Integer.toString(i));
        }
        Collections.sort(listToBeSorted);
        assertEquals(sortedList.size(), listToBeSorted.size());
        for (int i = 0; i < sortedList.size(); i++) {
            assertEquals(sortedList.get(i), listToBeSorted.get(i));
        }
    }
}