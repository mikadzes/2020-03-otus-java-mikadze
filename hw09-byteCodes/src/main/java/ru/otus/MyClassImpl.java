package ru.otus;


import ru.otus.annotations.Log;

public class MyClassImpl implements MyClassInterface {

    @Log
    @Override
    public void secureAccess(String param) {
        System.out.println("Method secureAccess with annotation, param:" + param);
    }

    @Override
    public void methodWithoutAnnotation(int param) {
        System.out.println("Method without annotations and one parameter: " + param);
    }

    @Log
    @Override
    public void methodWithAnnotationAndTwoParameters(int param1, int param2) {
        System.out.println("Method with annotation and two parameters: " + param1 + " and " + param2);
    }

    @Override
    public String toString() {
        return "MyClassImpl{}";
    }
}
