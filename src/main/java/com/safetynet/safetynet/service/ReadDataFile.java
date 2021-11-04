package com.safetynet.safetynet.service;

import com.safetynet.safetynet.repository.ReadDataFileRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

// Read the data.json file into a String
@Service
public class ReadDataFile implements ReadDataFileRepository {
    public String read() {
        try {
            // Open the file in the resources directory and read it
            InputStream fis = ReadDataFile
                    .class
                    .getClassLoader()
                    .getResourceAsStream("data.json");
            // Convert it into a String
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
