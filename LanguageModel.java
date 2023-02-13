import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LanguageModel {
    HashMap<String, HashMap<String, Score>> data;

    public LanguageModel() throws IOException {
    }

    private void mapAll(ArrayList<File> files, ArrayList<String> input) throws IOException  {
        HashMap<String, HashMap<String, Score>> map = new HashMap<>();
        //loop through files passed from file reader
        for(File f : files) {
            ArrayList<String> list = Reader.listWords(f);
            //loop through an array of all words in file
            for(int i = 0; i < list.size() - 1; i++) {
                // System.out.println(list.get(i));
                //i is index of current word
                //map every next item for this word
                //current variable is checking if there is next item assigned to the given key
                HashMap<String, Score> current = map.get(list.get(i));
                if (current != null) {
                    // System.out.println(list.get(i+1));
                    //check if there is an existing score attached to the word
                    Score score = current.get(list.get(i+1));
                    if(score != null) {
                        Score s = updateScore(input, list, i, score);
                        current.put(list.get(i + 1), s);
                    } else {
                        current.put(list.get(i+1), new Score(0,0));
                    }
                    map.put(list.get(i), current);
                } else {
                    //create an empty hashmap with initial key of the next item for this word
                    HashMap<String, Score> newMap = new HashMap<>();
                    newMap.put(list.get(i + 1), new Score(0, 0));
                    //enbed new hashmap as the value for this word
                    map.put(list.get(i), newMap);
                }
            }
        }
        data = map;
    }

    private Score updateScore(ArrayList<String> input, ArrayList<String> list, int i, Score score) {
        score.count ++;
        int matches = 0;
        int idx = 0;
        while(inputMatch(input, list, i, idx)) {
            matches ++;
            idx ++;
        }
        if (matches > score.matches) {
            score.matches = matches;
        }
        return score;
    }

    private boolean inputMatch(ArrayList<String> input, ArrayList<String> list, int i, int idx) {
        if(input.size() < 1 + idx) {
            return false;
        }
        String x = input.get(input.size() - 1 - idx);
        String y = list.get(i - idx);
        if (x == null) {
            return false;
        }
        if (y == null) {
            return false;
        }
        return x.equals(y);
    }

    public String generateResponse(String input) throws IOException {
        Reader reader = new Reader();
        ArrayList<String> words = Reader.splitByWord(input);
        mapAll(reader.files, words);

        System.out.println("Generating...");

        while (shouldContinue(words)) {
            words.add(generateWord(words.get(words.size() - 1)));
            System.out.println(words.toString());
        }
        return words.toString();
    }

    private boolean shouldContinue(ArrayList<String> words) throws IOException {
        if(words.size() > 20) {
            return false;
        }
        String word = generateWord(words.get(words.size() - 1));
        if (word.equals(".")) {
            return false;
        }
        return word != null;
    }

    private String generateWord(String input) throws IOException {
        HashMap<String, Score> nextValues = data.get(input);
        String best = null;
        if (nextValues != null) {
            for (String key : nextValues.keySet()) {
                if (best == null) {
                    best = key;
                } else if(score(input, key) > score(input, best)) {
                    best = key;
                }
            }
        }
        return best;


        // Location best = null;
        // for (Location l : data.get(words.get(words.size() - 1))) {
        //     Location next = l.next();
        //     if(next != null) {
        //         if (best == null) {
        //             best = next;
        //         } else if (score(words, next) > score(words, best)) {
        //             best = next;
        //         }
        //     }  else {
        //         return null;
        //     }
        // }

        // if (data.get(words.get(words.size() - 1)) != null) {
            
        // } else {
        //     return null;
        // }
        // return best.value();
    }

    private double score(String input, String next) throws IOException {
        Score s = data.get(input).get(next);
        if (s == null) {
            return 0;
        }
        int occurences = s.count;
        int similarity = s.matches;
        if (occurences == 0) {
            return 0;
        }
        return Math.random() + (similarity + (1 + 1 / occurences));
    }

    public static void main(String[] args) throws IOException {
        LanguageModel model = new LanguageModel();
        String res = model.generateResponse("how many people");
        System.out.println(res);
    }

    // private int similarity(ArrayList<String> input, Location location) throws IOException {
    //     Location l = location;
    //     int counter = 0;

    //     while (l.value() == input.get(input.size() - 1 - counter)) {
    //         l = l.previous();
    //         counter++;
    //         if (l.value() == null) {
    //             return counter;
    //         }
    //     }
    //     return counter;
    // }

    // private int occurences(Location location) throws IOException {
    //     Location next = location.next();
    //     if (next == null) {
    //         return 0;
    //     }

    //     if (data.get(next.value()) == null) {
    //         return 0;
    //     }
        
    //     return data.get(next.value()).size();
    // }
}
