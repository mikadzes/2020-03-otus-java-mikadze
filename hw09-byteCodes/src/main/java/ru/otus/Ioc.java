package ru.otus;

import ru.otus.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Ioc {

    private Ioc() {
    }

    static MyClassInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new MyClassImpl());
        return (MyClassInterface) Proxy.newProxyInstance(ru.otus.Ioc.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;
        private List<String> methods;

        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
            this.methods = Arrays.stream(myClass.getClass().getDeclaredMethods())
                    .filter((method) -> method.isAnnotationPresent(Log.class))
                    .map(Method::getName)
                    .collect(Collectors.toList());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println();
            if (methods.contains(method.getName())) {
                System.out.println("executed method: " + method.getName() + ", param: ");
                Stream.of(args).forEach(System.out::println);
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
