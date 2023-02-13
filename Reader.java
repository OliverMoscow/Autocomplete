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
        // for (File f : allFiles("/data")) {
        //     // System.out.println(f.getName());
        //     files.addAll(allFiles("/data/" + f.getName()));
        // };
        files.addAll(allFiles("/data/fic"));
        System.out.println("loaded " +files.size() + " files!");
    }

    public HashMap<String, ArrayList<Location>> mapAll() throws IOException  {
        HashMap<String, ArrayList<Location>> map = new HashMap<>();
        for(File f : files) {
            ArrayList<String> list = listWords(f);
            for(int i = 0; i < list.size(); i++) {
                Location l = new Location(i, f);
                ArrayList<Location> prev = new ArrayList<>();
                if(map.get(list.get(i)) != null) {
                    prev = map.get(list.get(i));
                }
                prev.add(l);
                map.put(list.get(i), prev);
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
        ArrayList<String> words = new ArrayList<>();
        String newText = input;
        // /*
        while (newText.length() != 0) {
            if (newText.indexOf(" ") != -1) {
                if (newText.substring(0, newText.indexOf(" ")).indexOf(";") != -1
                        || newText.substring(0, newText.indexOf(" ")).indexOf(":") != -1) {
                    words.add(newText.substring(0, (newText.indexOf(" ") - 1)));
                } else {
                    words.add(newText.substring(0, newText.indexOf(" ")));
                }
                newText = newText.substring(newText.indexOf(" ") + 1);
            }
        else {
            if (newText.indexOf(".") != -1 
                    || newText.indexOf("!") != -1 
                    || newText.indexOf("?") != -1){
                words.add(newText.substring(0, newText.length() - 1));
                words.add(newText.substring(newText.length() - 1));
                newText = "";
               // words.add("passed");
                    }
            else{
                words.add(newText);
                newText = "";
            }
        }
    }

        return words;
    }


    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        reader.mapAll();
    }
}
