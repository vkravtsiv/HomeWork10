package org.example;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadTelNumbers {
    private static String fileName = "file.txt";
    public static void main(String[] args) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("File with name" + file.getName() + " not found");
        }
        try (InputStream inputStream = new FileInputStream(file); Scanner sc = new Scanner(inputStream)) {
            Pattern pattern = Pattern.compile("(\\(\\d{3}\\)\\s|\\d{3}-)\\d{3}-\\d{4}");
            while (sc.hasNext()) {
                String str = sc.nextLine();
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
//                    System.out.println(str.substring(matcher.start(), matcher.end()));
                    System.out.println(str);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

