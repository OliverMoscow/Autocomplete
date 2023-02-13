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
        System.out.println("getting files...");
        for (File f : allFiles("/data")) {
            // System.out.println(f.getName());
            files.addAll(allFiles("/data/" + f.getName()));
        }
        ;
        // files.addAll(allFiles("/data/fic"));
        System.out.println("loaded " + files.size() + " files!");
    }

    public ArrayList<File> allFiles(String dir) {
        String filePath = new File("").getAbsolutePath();
        filePath += dir;
        // System.out.println(filePath);
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            return new ArrayList<>(Arrays.asList(listOfFiles));
        }
        return new ArrayList<>();
    }

    public static ArrayList<String> listWords(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String content = "";
        String str;
        // Condition holds true till
        // there is character in a string
        while ((str = br.readLine()) != null) {
            content += str;
        }
        return Reader.splitByWord(content);
    }

    public static ArrayList<String> splitByWord(String input) {
        String s = input;
        // https://stackoverflow.com/questions/7380626/how-to-replace-dot-in-a-string-in-java
        // https://www.w3schools.com/java/ref_string_tolowercase.asp
        s = s.toLowerCase();
        // https://javarevisited.blogspot.com/2016/10/how-to-split-string-in-java-by-whitespace-or-tabs.html#axzz7sl65SBat
        //Algorith to format text. I used Chat GPT to help with the regex.
        String[] formated = s.split("[^\\w']+");

        // System.out.println(Arrays.toString(formated));

        return words;
    }
}
