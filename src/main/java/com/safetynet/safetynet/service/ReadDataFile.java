package com.safetynet.safetynet.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadDataFile {
    public static String read() {
        try {
            InputStream fis = ReadDataFile
                    .class
                    .getClassLoader()
                    .getResourceAsStream("data.json");
            String s = new String(fis.readAllBytes());
            return s;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
