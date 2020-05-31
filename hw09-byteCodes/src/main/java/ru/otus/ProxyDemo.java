package ru.otus;

public class ProxyDemo {
    public static void main(String[] args) {
        MyClassInterface myClass = Ioc.createMyClass();
        myClass.secureAccess("Security Param");
        myClass.methodWithoutAnnotation(1);
        myClass.methodWithAnnotationAndTwoParameters(2, 3);
    }
}



