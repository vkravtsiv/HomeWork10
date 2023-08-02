package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;


public class ToJsonFile {

    private String fileIn="file2.txt";
    private String fileOut="user.json";

    public String getFileIn() {
        return fileIn;
    }

    public void setFileIn(String fileIn) {
        this.fileIn = fileIn;
    }

    public String getFileOut() {
        return fileOut;
    }

    public void setFileOut(String fileOut) {
        this.fileOut = fileOut;
    }

    public ToJsonFile() {
    }

    public ToJsonFile(String fileIn, String fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    static class User<T> {
        private String name;
        private T age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getValue() {
            return age;
        }

        public void setValue(T value) {
            this.age = value;
        }

        public User() {
        }

        public User(String name, T value) {
            this.name = name;
            this.age = value;
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + ", value=" + age + '}';
        }
    }
    static class Data {
        private String path = "file2.txt";
        private List<ToJsonFile.User> list;

        public Data(List<ToJsonFile.User> list) {
            this.list = list;
        }

        public Data() {
        }

        public Data(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public List<ToJsonFile.User> getList() {
            return list;
        }

        public void setList(List<ToJsonFile.User> list) {
            this.list = list;
        }

        public List<ToJsonFile.User> objToList(String path) {
            List<ToJsonFile.User> list = new ArrayList<>();
            ToJsonFile.User u = new ToJsonFile.User();
            File file = new File(new ToJsonFile().getFileIn());
            if (!file.exists()) {
                throw new RuntimeException("File with name" + file.getName() + " not found");
            }
            try (InputStream inputStream = new FileInputStream(file); Scanner sc = new Scanner(inputStream)) {
                String str = sc.nextLine();
                while (sc.hasNext()) {
                    str = sc.nextLine();
                    String[] arr = str.split("  *");
                    u = new ToJsonFile.User(arr[0], arr[1]);
                    list.add(u);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return list;
        }

    }


    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ToJsonFile toJs=new ToJsonFile();
        ToJsonFile.Data data=new ToJsonFile.Data(toJs.getFileIn());
        FileWriter out = null;
        try {
            out = new FileWriter(toJs.getFileOut());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gson.toJson(data.objToList(data.getPath()), out);
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}




