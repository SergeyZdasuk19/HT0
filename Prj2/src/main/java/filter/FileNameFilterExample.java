package filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileNameFilterExample {


    public void findFiles(File dir, String ext, ArrayList<File> listOfMusic) {
        File file = dir;

        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if (listFiles.length == 0) {
            System.out.println(dir + " не содержит файлов с расширением " + ext);

        } else {
            for (File f : listFiles) {
                listOfMusic.add(f);
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    public static class MyFileNameFilter implements FilenameFilter {

        private String ext;

        public MyFileNameFilter(String ext) {
            this.ext = ext.toLowerCase();
        }

        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }
    }

}
