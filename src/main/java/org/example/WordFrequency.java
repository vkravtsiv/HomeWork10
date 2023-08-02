package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class WordFrequency {
    private String fileName = "word.txt";

    public WordFrequency() {

    }

    public WordFrequency(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String wordfreq(String fileName) {
        StringBuilder res = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("File with name" + file.getName() + " not found");
        }
        try (InputStream inputStream = new FileInputStream(file); Scanner sc = new Scanner(inputStream)) {
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] arr = str.split("  *");
                for (String s : arr) {
                    if (map.containsKey(s)) {
                        map.put(s, map.get(s) + 1);
                    } else {
                        map.put(s, 1);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res.append(entry);
            res.append("\n");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        WordFrequency wf = new WordFrequency();
        System.out.println(wf.wordfreq(wf.getFileName()));

    }
};




