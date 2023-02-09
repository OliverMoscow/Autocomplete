import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LanguageModel {
    HashMap<String, ArrayList<Location>> data;

    public LanguageModel() {
        FileReader reader = new FileReader();
        data = reader.mapAll();
    }

    public String generateResponse(String input) {
        return null;
    }

    public int similarity(String[] input, Location location) {
        return 0;
    }

    public int occurences(String[] input, Location location) {
        return 0;
    }

}
