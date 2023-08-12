package com.connor.java.collection;

import com.connor.java.common.Student;
import com.connor.java.leetcode.Solution;
import com.connor.java.leetcode.SolutionTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CollectionTest {
    private static final Logger logger = Logger.getLogger(CollectionTest.class.getName());



    @Test
    public void testListIterator() {
        List<Student> students = Student.build(new Student(1,"一一"),new Student(2,"二二"));


    }
}
