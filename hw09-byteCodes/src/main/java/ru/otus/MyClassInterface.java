package ru.otus;

public interface MyClassInterface {
    void secureAccess();

    void secureAccess(String param);

    void secureAccess(String param1, String param2);

    void methodWithoutAnnotation(int param);

    void methodWithAnnotationAndTwoParameters(int param1, int param2);
}
