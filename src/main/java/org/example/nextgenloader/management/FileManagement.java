package org.example.nextgenloader.management;

import java.io.File;
import java.util.Objects;

public class FileManagement {

    static public boolean validFiles(File[] files) {
        for(File file:files) {
           // System.out.println(file.getName());
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

        return Objects.requireNonNull(directory.listFiles()).length != 0 ||
                validFiles(Objects.requireNonNull(directory.listFiles()));
    }

}
