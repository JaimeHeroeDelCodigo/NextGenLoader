package org.example.nextgenloader.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileManagement {

    static public boolean validFiles(File[] files) {
        for(File file:files) {
            if(!file.getName().endsWith(".csv") ) {
                return false;
            }
        }
        return true;
    }

    static public boolean validDirectory(File directory) {
        if(!directory.exists()) {
            return false;
        }

        return Objects.requireNonNull(directory.listFiles()).length != 0 &&
                validFiles(Objects.requireNonNull(directory.listFiles()));
    }

    public static String pathNewDirectoryString(File directory) {
        return directory.getPath().concat("/OUTPUT" );
    }

    public static void createControlFile(File directory) throws IOException {
        String outputDir = directory.getPath().concat("/OUTPUT/control.txt");
        Files.createFile(Paths.get(outputDir));
    }
}
