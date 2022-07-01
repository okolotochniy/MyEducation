public class Song implements Comparable<Song> {
    String title;
    String artist;
    String raring;
    String bmp;

    public Song(String title, String artist, String raring, String bmp) {
        this.title = title;
        this.artist = artist;
        this.raring = raring;
        this.bmp = bmp;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getRaring() {
        return raring;
    }

    public String getBmp() {
        return bmp;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int compareTo(Song o) {
        return title.compareTo(o.getTitle());
    }
}
