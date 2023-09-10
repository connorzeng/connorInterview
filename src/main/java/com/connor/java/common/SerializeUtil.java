package com.connor.java.common;

import java.io.*;

public class SerializeUtil {

    public static void serialize(Object obj, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        fos.close();
    }

    public static Object deserialize(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        fis.close();
        return obj;
    }
}
