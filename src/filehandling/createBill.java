package filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class createBill {

    public void createLayanBill(String b, int q) throws IOException {
        System.out.println("I found the book");

        File databaseFolder = new File("database");
        if (!databaseFolder.exists()) {
            if (databaseFolder.mkdir()) {
                System.out.println("Database folder created successfully");
            } else {
                System.out.println("Failed to create database folder");
                return; 
            }
        }

        File newFile = new File(databaseFolder, "newBill.txt");

        if (newFile.exists()) {
            System.out.println("File already exists");
        } else {
            try (PrintWriter nfw = new PrintWriter(newFile)) {
                System.out.println("File is " + newFile.getAbsolutePath());
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for (int j = 0; j < 5; j++) {
                    int num = random.nextInt(5);
                    sb.append(num).append(" ");
                }
                nfw.write(sb.toString());
                System.out.println("Bill success");
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

   
}
