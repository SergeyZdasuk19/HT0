package music;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Track {
    private String path;
    private String track;
    private String artist;
    private String title;
    private String album;
    private String year;
    private String genre;
    private String comment;
    private long length;

    public Track(String path, String track, String artist, String title, String album, String year, String genre, String comment, long length) {
        this.path = path;
        this.track = track;
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.comment = comment;
        this.length = length;
    }

    public String controlSum() throws IOException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(Files.readAllBytes(Paths.get(getPath())));
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public String getPath() {
        return path;
    }

    public String getTrack() {
        return track;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getComment() {
        return comment;
    }

    public long getLength() {
        return length;
    }
}
