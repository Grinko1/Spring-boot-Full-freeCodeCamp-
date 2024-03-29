package com.example.demo;

import org.junit.jupiter.api.*;

public class BaseAnnotations {
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all method");
    }

    @BeforeEach
    void setUp() {
        System.out.println("before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each method");
    }

    @Test
    public void testMethod1(){
        System.out.println("first test");
    }
    @Test
    public void testMethod2(){
        System.out.println("second test");
    }
}
