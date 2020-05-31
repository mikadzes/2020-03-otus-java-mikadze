package ru.otus;

public interface MyClassInterface {
    void secureAccess(String param);

    void methodWithoutAnnotation(int param);

    void methodWithAnnotationAndTwoParameters(int param1, int param2);
}
