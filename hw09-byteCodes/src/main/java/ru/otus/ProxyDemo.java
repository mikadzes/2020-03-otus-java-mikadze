package ru.otus;

public class ProxyDemo {
    public static void main(String[] args) {
        MyClassInterface myClass = Ioc.createMyClass();
        myClass.secureAccess();
        myClass.secureAccess("Security Param");
        myClass.secureAccess("Security Param","overloading");
        myClass.methodWithoutAnnotation(1);
        myClass.methodWithAnnotationAndTwoParameters(2, 3);
    }
}



