import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// Please run the code under Java 8


public class FacultyInfoQuery {

    final static String INVALID = "Invalid command";
    final static String INVALID_NAME = "Invalid name";

    final static String FILE_NAME = "FacultyList.csv";
    final static List<Faculty> facultyList = new ArrayList<>();

    private static class Faculty {
        String name;
        String p;
        String dep;

        public String getName() {
            return name;
        }

        public String getP() {
            return p;
        }

        public String getDep() {
            return dep;
        }

        Faculty(String name, String p, String dep) {
            this.name = name;
            this.p = p;
            this.dep = dep;
        }

        @Override
        public String toString() {
            return String.format("%s, %s, %s", name, p, dep);
        }
    }

    public static String getData(int n){
        return facultyList.get(n).toString();
    }

    public static String getName(int n){
        return facultyList.get(n).getName();
    }

    public static void readFile() {
        File file = new File(FILE_NAME);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String s = reader.readLine();
            while (s != null) {
                String[] strs = s.replace("\n", "").split(",");
                facultyList.add(new Faculty(strs[0].trim(), strs[1].trim(), strs[2].trim()));
                s = reader.readLine();
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IO Exception occurred when reading the file:" + FILE_NAME);
        }

    }

    public static String handleCommand(String s) {
        s = s.replace("\n", "");
        s = s.trim();

        int idx = s.indexOf(' ');
        if (idx == -1) {
            return INVALID;
        }

        String command = s.substring(0, idx);
        String content = s.substring(idx + 1);

        String result = "";
        switch (command.toUpperCase()) {
            case "NAME":
                result = handleNameCommand(content);
                break;
            case "FIRSTLETTER":
                result = handleFirstLetterCommand(content);
                break;
            case "DEP":
                result = handleDepCommand(content);
                break;
            default:
                result = INVALID;
        }
        return result;
    }

    public static String handleNameCommand(String s) {

        String content = facultyList.stream()
                .filter(e -> e.getName().equals(s))
                .map((e)->e.toString())
                .collect(Collectors.joining("\n"));
        return content;
    }

    public static String handleFirstLetterCommand(String s) {
        String content = facultyList.stream()
                .filter(e -> e.getName().toLowerCase().charAt(0) == s.toLowerCase().charAt(0))
                .map((e)->e.toString())
                .collect(Collectors.joining("\n"));
        return content;
    }

    public static String handleDepCommand(String s) {
        String content = facultyList.stream()
                .filter(e -> e.getDep().toLowerCase().contains(s.toLowerCase()))
                .map((e)->e.toString())
                .collect(Collectors.joining("\n"));
        return content;
    }


    public static void main(String[] args)  {

        readFile();
        while (true) {
            try {
                Scanner scaner = new Scanner(System.in);
                String s = scaner.nextLine();
                String result = handleCommand(s);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}