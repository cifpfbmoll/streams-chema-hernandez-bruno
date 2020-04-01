/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author chema
 */
public class WrongPathException extends Exception {

    private String message;
    private int errorCode;

    public WrongPathException() {
    }

    public WrongPathException(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public WrongPathException(int errorCode) {
        if (errorCode == 123) {
            this.setMessage("Not a valid origin file route.");
        } else if (errorCode == 321) {
            this.setMessage("File route not found.");
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static void logErrors(String errorMessage, StackTraceElement[] stack) {
        String newLine = //Returns the OS dependent line separator.
                System.getProperty("line.separator");
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("errorLog.txt", true), StandardCharsets.UTF_8)) {
            dateLog(writer);
            writer.write(errorMessage);
            writer.append(newLine);
            for (StackTraceElement stack1 : stack) { //Enhanced for loop (stack.length)
                writer.write(stack1.toString());
                writer.append(newLine);
            }
        } catch (IOException ex) {
            System.out.println("Unexpected error.");
        }
    }

    public static void dateLog(OutputStreamWriter writer) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        writer.write(dateFormat.format(date));
    }
}
