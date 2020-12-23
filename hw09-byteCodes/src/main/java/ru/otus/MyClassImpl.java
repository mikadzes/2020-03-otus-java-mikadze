package ru.otus;


import ru.otus.annotations.Log;

public class MyClassImpl implements MyClassInterface {

    @Log
    @Override
    public void secureAccess() {
        System.out.println("Method secureAccess with annotation and overloading");
    }

    @Override
    public void secureAccess(String param) {
        System.out.println("Method secureAccess without annotation, param:" + param);
    }

    @Log
    @Override
    public void secureAccess(String param1, String param2) {
        System.out.println("Method secureAccess with annotation, param:" + param1 + " and " + param2);
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
