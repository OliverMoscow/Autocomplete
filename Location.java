public class Location {
    int index;
    String directory;

    public Location(int idx, String directory) {
        this.index = idx;
        this.directory = directory;
    }

    public Location previous() {
        return null;
    }

    public Location next() {
        return null;
    }

}
