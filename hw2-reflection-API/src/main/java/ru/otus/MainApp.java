package ru.otus;

import ru.otus.annotations.AnnotationProcessor;

public class MainApp {
    public static void main(String[] args) {
        AnnotationProcessor.runTests(Tests.class);
    }
}
