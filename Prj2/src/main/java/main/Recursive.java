package main;

import Exceptions.WrongInputException;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import music.Track;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import parser.AudioParserAndDescribe;
import filter.FileNameFilterExample;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Recursive {
    private FileNameFilterExample filterExample;
    private String ext = ".mp3";
    private static ArrayList<File> listOfPath = new ArrayList<File>();
    private static ArrayList<Track> listOfTrack = new ArrayList<Track>();

    public void fetchChild(File file) {
        if (!file.exists()) {
            System.out.println(file.getAbsolutePath() + " папка не существует");
        } else {
            filterExample = new FileNameFilterExample();
            if (file.isDirectory()) {
                filterExample.findFiles(file, ext, listOfPath);
                File[] children = file.listFiles();
                for (File child : children) {
                    this.fetchChild(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            Recursive example = new Recursive();
            AudioParserAndDescribe parser = new AudioParserAndDescribe();
            File dir = new File(args[0]);

            if (args.length < 1 || args.length > 1) {
                throw new WrongInputException("Неправильный ввод, пример:D:\\Директория");
            }
            example.fetchChild(dir);
            for (int i = 0; i < listOfPath.size(); i++) {
                parser.parser(listOfPath.get(i).getAbsolutePath(), listOfTrack);
            }
            parser.describeMp3(listOfTrack);
            parser.describeMp3DublicateWithControlSum(listOfTrack);
            parser.describeMp3DublicareWithoutControlSum(listOfTrack);
        } catch (IOException | SAXException | TikaException | InvalidDataException | UnsupportedTagException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
         catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
