package ru.otus.mygson;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Builder
@EqualsAndHashCode
public class TestObject {
    private int anInt;
    private String string;
    private char aChar;
    private boolean aBoolean;
    private Float aFloat;
    private int[] ints;
    private String[] strings;
    private Collection collection;
}
