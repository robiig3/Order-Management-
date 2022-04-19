package main.java.presentation;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    /**
     *
     * @param bill using for print bill
     */
    public static void printBill(String bill) {
        try {
            FileWriter myWriter = new FileWriter("src/main/bill.txt");
            myWriter.write(bill);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}