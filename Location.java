import java.io.File;

public class Location {
    int index;
    File file;

    public Location(int idx, File file) {
        this.index = idx;
        this.file = file;
    }

    public Location previous() {
        return null;
    }

    public Location next() {
        return null;
    }

}
