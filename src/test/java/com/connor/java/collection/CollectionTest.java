package com.connor.java.collection;

import com.connor.java.common.Student;
import com.connor.java.leetcode.Solution;
import com.connor.java.leetcode.SolutionTest;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CollectionTest {
    private static final Logger logger = Logger.getLogger(CollectionTest.class.getName());



    @Test
    public void testListIterator() {
        List<Student> students = Student.build(new Student(1,"一一"),new Student(2,"二二"));


    }
    @Test
    public void testString() {
        String a = "ab";
        String ab = "a" + "b";
        String ac = "a" + getString();
        // == 是比较内存地址， "a" + "b" 在编译期会被优化
        logger.info("a == ab: "+ (a == ab));
        logger.info("a == ab: "+ (a == ac));
    }
    private String getString() {
        return "b";
    }

    @Test
    public void testConvert(){
        String utf8String = "你好，世界！"; // UTF-8编码的字符串

        Charset utf8Charset = Charset.forName("UTF-8");
        Charset gbkCharset = Charset.forName("GBK");

        CharsetEncoder gbk = gbkCharset.newEncoder();

        try {
//            ByteBuffer utf8Bytes = utf8Charset.encode(utf8String);
//            CharBuffer utf8Chars = utf8Charset.decode(utf8Bytes);
            CharBuffer utf8Chars = CharBuffer.wrap(utf8String);

            ByteBuffer gbkBytes = gbk.encode(utf8Chars);
            CharBuffer gbkChars = gbkCharset.decode(gbkBytes);

            String gbkString = gbkChars.toString();
            System.out.println("GBK: " + gbkString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
