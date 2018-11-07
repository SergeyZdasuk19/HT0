package parser;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import music.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class AudioParserAndDescribe {
    Logger log = LogManager.getLogger(AudioParserAndDescribe.class.getName());

    public void parser(String music, ArrayList<Track> list) throws FileNotFoundException, SAXException, TikaException, IOException, UnsupportedTagException,InvalidDataException {
        Mp3File mp3file = new Mp3File(music);
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            if (id3v2Tag.getArtist() == null) {
                id3v2Tag.setArtist("N/A");
            } else if (id3v2Tag.getAlbum() == null) {
                id3v2Tag.setAlbum("N/Album");
            }
            list.add(new Track(music, id3v2Tag.getTrack(), id3v2Tag.getArtist(), id3v2Tag.getTitle(), id3v2Tag.getAlbum(), id3v2Tag.getYear(), id3v2Tag.getGenreDescription(), id3v2Tag.getComment(), mp3file.getLengthInSeconds()));
        }
    }

    public void describeMp3(ArrayList<Track> list) throws IOException {
        Set<String> listOfArtist = new HashSet<String>();
        ArrayList<Track> listWithoutDuplication = new ArrayList<Track>();
        listWithoutDuplication.addAll(list);
        BufferedWriter bw = new BufferedWriter(new FileWriter("Table.html"));
        bw.write("<html><head><meta charset=\"UTF-8\"><link href=\"CSS.css\" rel=\"stylesheet\"><title>New Page</title></head><body><div class=\"container flex-container\"> <div class=\"flex-element\" id=\"test\" style=\"background: sandybrown\">");
        bw.write("</div><div class=\"article flex-element\" style=\"flex-grow: 1\"><table id=\"Table\" border=\"1\">");
        for (int i = 0; i < listWithoutDuplication.size(); i++) {
            int k = 0;
            for (int j = 0; j < listWithoutDuplication.size(); j++) {
                if (listWithoutDuplication.get(i).getTitle().equals(listWithoutDuplication.get(j).getTitle())) {
                    k++;
                    if (k > 1) {
                        listWithoutDuplication.remove(j);
                    }
                }
            }
        }
        for (Track s : listWithoutDuplication) {
            if ((s.getArtist() != null)) {
                listOfArtist.add(s.getArtist().toLowerCase());
            }
        }

        for (String listArtist : listOfArtist) {
            bw.write("<tr><td><p>Artist: </p></td><td><p>" + listArtist + "</p></td><td></td><td></td><td></td><td></td></tr>");
            Set<String> listOfAlbum = new HashSet<String>();
            for (Track s : list) {
                if (s.getArtist().toLowerCase().equals(listArtist)) {
                    listOfAlbum.add(s.getAlbum());
                }
            }
            for (String listAlbum : listOfAlbum) {
                bw.write("<tr><td></td><td><p>Альбом:<p></td><td><p>" + listAlbum + "</p></td><td></td><td></td><td></td></tr>");
                bw.write("<tr><td></td><td></td><td><p>Tracks:</p></td><td></td><td></td><td></td></tr>");
                for (Track s : listWithoutDuplication) {
                    if (s.getArtist().toLowerCase().equals(listArtist) && s.getAlbum().equals(listAlbum)) {
                        //System.out.println("        " + " " + s.getTitle() + " " + s.getLength() + " " + s.getGenre() + " " + s.getYear());
                        bw.write("<tr><td></td><td></td><td><p>" + s.getTitle() + "</p></td><td><p>" + s.getLength() + "</p></td><td><p>" + s.getGenre() + "</p></td><td><p>" + s.getYear() + "</p></td></tr>");

                    }
                }
            }
        }

        bw.write("</table></div><div class=\"flex-element\" style=\"background: sandybrown\"> </div></div></body></html>");
        bw.close();
    }

    public void describeMp3DublicateWithControlSum(ArrayList<Track> list) throws IOException, NoSuchAlgorithmException {
        ArrayList<Track> listWithDublication = new ArrayList<>();
        listWithDublication.addAll(list);
        String str = null;
        int n = 0;
        for(int i = 0; i < listWithDublication.size(); i++){
            Track object1 = listWithDublication.get(i);
            for(int j = i +1; j < listWithDublication.size(); j++){
                Track object2 = listWithDublication.get(j);
                if((object1.controlSum().equals(object2.controlSum()))){
                    n++;
                    str = object1.controlSum();
                }
            }
            if(n == 1){
                for(Track s : listWithDublication){
                    if(s.controlSum().equals(str)){
                       log.info("Dublicate file with control sum " + s.getPath());
                    }
                }
            }
            n = 0;
        }
    }

    public void describeMp3DublicareWithoutControlSum(ArrayList<Track> list){
        ArrayList<Track> listWithoutDublication = new ArrayList<>();
        listWithoutDublication.addAll(list);
        String str = null;
        int n = 0;
        for(int i = 0; i < listWithoutDublication.size(); i++){
            Track object1 = listWithoutDublication.get(i);
            for(int j = i +1; j < listWithoutDublication.size(); j++){
                Track object2 = listWithoutDublication.get(j);
                if((object1.getArtist().equalsIgnoreCase(object2.getArtist())) && (object1.getAlbum().equalsIgnoreCase(object2.getAlbum())) && (object1.getTitle().equalsIgnoreCase(object2.getTitle()))){
                    n++;
                    str = object1.getArtist() + ", " + object1.getAlbum() + ", " + object1.getTitle();
                }
            }
            if(n == 1){
                log.info("\n");
                log.info(str);
                for(Track s : listWithoutDublication){
                    if((s.getArtist() + ", " + s.getAlbum() + ", " + s.getTitle()).equalsIgnoreCase(str)){
                        log.info("Dublicate file without control sum " + s.getPath());
                    }
                }
            }
            n = 0;
        }
        log.info("\n");
        log.info("__________________________________________________________________________");
    }
}