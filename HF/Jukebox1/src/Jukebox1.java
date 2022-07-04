import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Jukebox1 {
    ArrayList<Song> songList = new ArrayList<>();

    public static void main(String[] args) {
        new Jukebox1().go();
    }
    public void go() {
        getSongs();
        System.out.println(songList);

        Song.TitleCompare titleCompare = new Song.TitleCompare();
        songList.sort(titleCompare);
        System.out.println(songList);

        Song.ArtistCompare artistCompare = new Song.ArtistCompare();
        songList.sort(artistCompare);
        System.out.println(songList);

       TreeSet<Song> songSet = new TreeSet<>(songList);
        System.out.println(songSet);
    }

    void getSongs() {
        try {
            File file = new File("SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                addSong(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    void addSong(String lineToParse) {
        String[] tokens = lineToParse.split("/");
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }
}
