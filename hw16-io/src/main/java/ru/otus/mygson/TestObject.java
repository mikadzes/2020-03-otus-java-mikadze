package ru.otus.mygson;

import lombok.Builder;
import lombok.ToString;

import java.util.Collection;

@Builder
@ToString
public class TestObject {
    private int anInt;
    private String string;
    private boolean aBoolean;
    private Float aFloat;
    private int[] ints;
    private Collection collection;
}
