package com.connor.java.basic;

import com.connor.java.common.SerializeUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * clone
 */
public class CloneTest implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L; // 保持不变

    private List<String> names;

    private String desc;

    private int id;

    private int age;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("hello1");
        names.add("hello2");
        CloneTest orgin = new CloneTest();

        orgin.setNames(names);
        orgin.setDesc("这是一段描述");
        orgin.setId(1);

        // clone
        CloneTest cloned = (CloneTest) orgin.clone();
        CloneTest cloned1 = (CloneTest) orgin.clone();

        // 修改cloned的属性
        cloned.setDesc("更改描述");
        cloned.setId(2);
        List<String> clonedNames = new ArrayList<>();
        clonedNames.add("hello3");
        clonedNames.add("hello4");
        cloned.setNames(clonedNames);
        cloned1.getNames().add("hello5");

        // 打印orgin,cloned的对象信息
        System.out.println("orgin:" + orgin.toString());
        System.out.println("cloned:" + cloned.toString());
        System.out.println("cloned1:" + cloned1.toString());


        // 新建一个clone对象,并且序列化写入到当前用户的目录下.
        CloneTest cloneTest = new CloneTest();
        cloneTest.setDesc("这是一个clone对象");
        cloneTest.setId(3);
        List<String> cloneNames = new ArrayList<>();
        cloneNames.add("hello6");
        cloneNames.add("hello7");
        cloneTest.setNames(cloneNames);
        // 将 cloneTest 对象序列化输出到磁盘
        SerializeUtil.serialize(cloneTest, "cloneTest_3.txt");

        // 从磁盘反序列化出一个对象
        CloneTest cloneTest1 = (CloneTest) SerializeUtil.deserialize("cloneTest_2.txt");
        System.out.println("cloneTest1:" + cloneTest1.toString());
        cloneTest1.getNames().add("hello8");
        // 打印age
        System.out.println("cloneTest1:" + cloneTest1.getAge());
        System.out.println("cloneTest1:" + cloneTest1.toString());
        System.out.println("cloneTest:" + cloneTest.toString());

        // 打印当前classpath
        System.out.println(System.getProperty("java.class.path"));
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current Working Directory: " + currentDirectory);
    }



    // 重写toString方法
    @Override
    public String toString() {
        return "CloneTest{" +
                "names=" + names +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                '}';
    }
}
