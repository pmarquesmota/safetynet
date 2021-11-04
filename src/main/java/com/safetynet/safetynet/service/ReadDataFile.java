package com.safetynet.safetynet.service;

import com.safetynet.safetynet.repository.ReadDataFileRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ReadDataFile implements ReadDataFileRepository {
    public String read() {
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
