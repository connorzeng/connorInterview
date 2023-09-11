package com.connor.java.common;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int age;
    private String name;

    public Student(){}
    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Student> build(Student... students) {
        List<Student> studentList = new ArrayList<>();

        // 将传入的学生对象添加到列表中
        for (Student student : students) {
            studentList.add(student);
        }

        return studentList;
    }
}
