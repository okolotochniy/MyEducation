import java.util.Comparator;

public class Song implements Comparable<Song> {
    String title;
    String artist;
    String raring;
    String bmp;

    static class TitleCompare implements Comparator<Song>{
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    static class ArtistCompare implements Comparator<Song>{
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getArtist().compareTo(o2.getArtist());
        }
    }
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
