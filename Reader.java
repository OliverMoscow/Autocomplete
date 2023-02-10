import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Reader {

    ArrayList<File> files = new ArrayList<>();

    public Reader() throws IOException {
        for (File f : allFiles("/data")) {
            // System.out.println(f.getName());
            files.addAll(allFiles("/data/" + f.getName()));
        };
        System.out.println(files.size());
    }

    public HashMap<String, ArrayList<Location>> mapAll() throws IOException  {
        HashMap<String, ArrayList<Location>> map = new HashMap<>();
        for(File f : files) {
            ArrayList<String> list = listWords(f);
            for(String word : list) {
                
            }
        }
        return map;
    }
    public ArrayList<File> allFiles(String dir) {
        String filePath = new File("").getAbsolutePath();
        filePath += dir;
        // System.out.println(filePath);
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null) {
            return new ArrayList<>(Arrays.asList(listOfFiles));
        }
        return new ArrayList<>();
    }
    private ArrayList<String> listWords(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String content = "";
        String str;
        // Condition holds true till
        // there is character in a string
        while ((str = br.readLine()) != null) {
            content += str;
        }

        // https://stackoverflow.com/questions/7380626/how-to-replace-dot-in-a-string-in-java
        content = content.replaceAll("\\.\'\\”", "");
        // https://www.w3schools.com/java/ref_string_tolowercase.asp
        content = content.toLowerCase();
        // https://javarevisited.blogspot.com/2016/10/how-to-split-string-in-java-by-whitespace-or-tabs.html#axzz7sl65SBat
        String[] formated = content.split(" ");

        return  new ArrayList<>(Arrays.asList(formated));
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
    }
}
