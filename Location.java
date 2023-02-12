import java.io.File;
import java.io.IOException;
public class Location {
    int index;
    File file;

    public Location(int idx, File file) {
        this.index = idx;
        this.file = file;
    }

    public Location previous() {
        return new Location(index - 1, file);
    }

    public Location next() {
        return new Location(index + 1, file);
    }

    public String value() throws IOException {
        if(Reader.listWords(file).size() > index) {
            return Reader.listWords(file).get(index);
        }
        return null;
    }

}
