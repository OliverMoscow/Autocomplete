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
