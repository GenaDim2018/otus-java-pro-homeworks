package ru.otus.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AnnotationProcessor {
    public static void runTests(Class cls) {
        Map<Method, Integer> executionOrder = new HashMap<>();
        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                executionOrder.put(m, 0);
                continue;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                executionOrder.put(m, 11);
                continue;
            }
            if (m.isAnnotationPresent(Test.class)) {
                int order = m.getDeclaredAnnotation(Test.class).priority();
                if (order >= 1 && order <= 10) {
                    executionOrder.put(m, order);
                }
            } else throw (new IllegalArgumentException());
        }
        executionOrder.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    try {
                        entry.getKey().invoke(cls);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
