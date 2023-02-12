import java.io.Console;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LanguageModel {
    HashMap<String, ArrayList<Location>> data;

    public LanguageModel() throws IOException {
        Reader reader = new Reader();
        data = reader.mapAll();
    }

    public String generateResponse(String input) throws IOException {

        System.out.println("Generating...");
        String output = input;

        while (generateWord(output) != null) {
            output = output + " " + generateWord(output);
            System.out.println(output);
        }
        return output;
    }

    private String generateWord(String input) throws IOException {
        ArrayList<String> words = Reader.splitByWord(input);
        Location best = null;
        for (Location l : data.get(words.get(words.size() - 1))) {
            Location next = l.next();
            if(next != null) {
                if (best == null) {
                    best = next;
                } else if (score(words, next) > score(words, best)) {
                    best = next;
                }
            }  else {
                return null;
            }
        }

        if (data.get(words.get(words.size() - 1)) != null) {
            
        } else {
            return null;
        }
        return best.value();
    }

    private double score(ArrayList<String> input, Location location) throws IOException {
        int similarity = similarity(input, location);
        int occurences = occurences(location);
        if (occurences == 0) {
            return 0;
        }
        return Math.random() + (similarity + (1 + 1 / occurences));
    }

    private int similarity(ArrayList<String> input, Location location) throws IOException {
        Location l = location;
        int counter = 0;

        while (l.value() == input.get(input.size() - 1 - counter)) {
            l = l.previous();
            counter++;
            if (l.value() == null) {
                return counter;
            }
        }
        return counter;
    }

    private int occurences(Location location) throws IOException {
        Location next = location.next();
        if (next == null) {
            return 0;
        }

        if (data.get(next.value()) == null) {
            return 0;
        }
        
        return data.get(next.value()).size();
    }
}
