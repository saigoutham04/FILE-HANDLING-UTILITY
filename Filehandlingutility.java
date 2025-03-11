package com.internship.task1;


import java.io.*;
import java.nio.file.*;
import java.util.*;

// A Java program to demonstrate file operations: reading, writing, and modifying text files dynamically.
 
public class Filehandlingutility {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        
        System.out.print("Enter content to write to the file: ");
        String content = scanner.nextLine();
        writeFile(fileName, content);
        readFile(fileName);
        
        System.out.print("Enter word to replace: ");
        String targetWord = scanner.nextLine();
        System.out.print("Enter new word: ");
        String newWord = scanner.nextLine();
        
        if (!modifyFile(fileName, targetWord, newWord)) {
            System.out.println("The word to replace was not found in the file.");
        }
        readFile(fileName);
        
        
    }
    
    // Reads and prints the content of the file.
    
    public static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Writes the given content to a file.
     
    public static void writeFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

  

    // Modifies the content of the file by replacing a target word.
     
    public static boolean modifyFile(String fileName, String targetWord, String newWord) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            boolean modified = false;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(targetWord)) {
                    lines.set(i, lines.get(i).replace(targetWord, newWord));
                    modified = true;
                }
            }
            if (modified) {
                Files.write(Paths.get(fileName), lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("File modified successfully.");
            }
            return modified;
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
            return false;
        }
    }
}
