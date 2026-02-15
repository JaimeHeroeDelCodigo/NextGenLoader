package org.example.nextgenloader.files;

import javafx.collections.ObservableList;
import org.controlsfx.control.PropertySheet;
import org.example.nextgenloader.visual.DisplayableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.createDirectory;
import static org.example.nextgenloader.alerts.Alerts.errorAlertGenerator;

public class FileManagement {

    public static Logger log = LoggerFactory.getLogger(FileManagement.class);


    public static final String CSV_ROLLOUT_HEADER = "\"type\",\"identity\"";

    public static final String CSV_ROLLOUT_CONSTANT_STORE = "\"STORE\",\"";

    public static final String INGENICO_PHRASE = ",\"IngenicoLane8000\",,,,\"UPP\",\"Ingenico\",\"Lane 8000\",\"SERIAL\",,\"COM9\",";

    public static final String VERIFONE_PHRASE = ",\"Default\",,,,\"FormAgent\",\"Verifone\",\"MX925\",\"SERIAL\",,\"COM9\",";

    public static final String CSV_FILE_HEADER = "\"store_name\",\"tid\",\"terminal_profile\",\"merchant_id\",\"merchant_type\",\"short_name\",\"device_application\",\"manufacturer\",\"model\",\"type\",\"host\",\"port\",\"serial_number\"";


    static public void createDir(String directory) {
        try {
            Path pathDirectoryOutput = Paths.get(directory);
            createDirectory(pathDirectoryOutput);
        } catch(IOException ioe) {
            errorAlertGenerator("Directory Error","Error at folder creation", "Error at creating " +
                    "the directory " + directory);
        }
    }


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
        String controlPath = directory.getPath().concat("/OUTPUT/control.txt");
        Files.createFile(Paths.get(controlPath));
    }


    private static void tenderNumberLoadingInControlFile(File controlFile,String tenderNumber) throws IOException {
            String tenderNumberToRegister = tenderNumber.concat("\n");
            Files.write(Paths.get(controlFile.getPath()) ,tenderNumberToRegister.getBytes());
    }

    public static boolean fileProcessing(File file,String terminalNumber) throws IOException {

        String pathTempFile = Paths.get(file.getParent()) + "/tempFile.csv";

        Files.createFile(Paths.get(pathTempFile));

        File tempFile = new File( pathTempFile);

        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println(file.getAbsolutePath());

        BufferedReader reader = new BufferedReader(new FileReader(file));

        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


        // terminalChecker
        String currentLine;
        writer.write(CSV_FILE_HEADER);
        writer.write("\n");
        while((currentLine = reader.readLine()) !=null) {
            if(terminalChecker(currentLine,terminalNumber)) {
                if (currentLine.contains(VERIFONE_PHRASE)) {
                    currentLine = currentLine.replace(VERIFONE_PHRASE,INGENICO_PHRASE);
                }
                writer.write(currentLine);
                writer.write("\n");
            }
        }
        writer.close();
        reader.close();

        if(file.delete()) {
            return tempFile.renameTo(file);
        } else {
            return  false;
        }
    }

    public static boolean fileRegister(File controlFile,String fileName) throws IOException {

        String pathOfControlFile= controlFile.getParent();
        String pathTempFile = Paths.get(pathOfControlFile) + "/tempControl.txt";
        Files.createFile(Paths.get(pathTempFile));
        File tempControl = new File(pathTempFile);
        BufferedReader reader = new BufferedReader(new FileReader(controlFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempControl));
        String currentLine;
        while( (currentLine = reader.readLine())!=null ) {
            writer.write(currentLine);
            writer.write("\n");
        }
        writer.write(fileName);

        writer.close();
        reader.close();

        if(controlFile.delete()) {
            return tempControl.renameTo(controlFile);
        } else {
            return  false;
        }
    }

    public static String searchCurrentWorkingFile(File control) {
        try {
            if(control==null || !control.getName().equals("control.txt")) {
                log.error("File control.txt is not present or has wrong name or format");
            } else {
                String currentLine;
                BufferedReader reader = new BufferedReader(new FileReader(control));
                while((currentLine= reader.readLine())!=null) {
                    String[] trace = currentLine.split(" ");
                    if(trace.length==2 && "X".equals(trace[1])) {
                        return trace[0];
                    }
                }
                reader.close();
            }
            return "";
        } catch(IOException ioe) {
            log.error("File exception thrown",ioe);
            return "";
        }


    }


    public static String  searchPathOfFile(Path outputPath, String fileName) {
        Path searchedFileFolderPath = Paths.get(outputPath.toString().concat("/").concat(fileName));
        if (Files.exists(searchedFileFolderPath)) {
            File[] filesInside = searchedFileFolderPath.toFile().listFiles(File::isFile);
            if (filesInside!=null && filesInside.length==1) {
                String filePrefix = "INGENICO - " + fileName;
                String[] nameSegments = filesInside[0].getName().split("_");


                if (nameSegments[0].equals(filePrefix) ) {
                    return searchedFileFolderPath.toString();
                }
            }
        } else {
            log.error("{} terminal INGENICO-file was not found inside the path {}",fileName,searchedFileFolderPath);
        }
        return "";
    }

    public static List<String> getFilesNamesFromControlFile(File control) {

        List<String> names = new ArrayList<>();
        System.out.println("The file name is : " + control.getName());
        System.out.println("The file path is : " + control.getAbsolutePath());
        String fileNameToBeAdded;
        try {
            if(control==null || !control.getName().equals("control.txt")) {
                log.error("File control.txt is not present or has wrong name or format");
            } else {
                String currentLine;
                BufferedReader reader = new BufferedReader(new FileReader(control));
                while( (currentLine= reader.readLine())!=null) {
                    fileNameToBeAdded = currentLine.split(" ")[0];
                    names.add(fileNameToBeAdded.trim());
                }
                reader.close();
            }
            return names;

        } catch(IOException ioe) {
            log.error("File exception thrown",ioe);
            return null;
        }
    }

    public static boolean rollOutFileCreation(Path path, List<String> fileNames) throws IOException {

        File csvFile = new File(path.toString() + "/rollout_group.csv");
        Files.createFile(csvFile.toPath());
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write(CSV_ROLLOUT_HEADER);

        for (String fileName:fileNames) {
            writer.write("\n");
            writer.write(CSV_ROLLOUT_CONSTANT_STORE);
            writer.write(fileName);
            writer.write("\"");
        }
        writer.close();
        return false;
    }

    public static void saveLoadingForLater(List<String> fileNames,File controlFile, String currentFileName)  {

        File newControlFile = new File(controlFile.toPath().toString());

        try(
             BufferedWriter writer = new BufferedWriter(new FileWriter(newControlFile))) {

            writer.write("");

            for (String fileName: fileNames) {
                writer.write(fileName);
                if(fileName.equals(currentFileName) ) {
                    writer.write(" X");
                }
                if( fileNames.indexOf(fileName)!= fileNames.size()-1) {
                    writer.write("\n");
                }
            }
        } catch(IOException e) {
            log.error("IOException",e);
        }
    }

    public static int searchPendingIndex (ObservableList<PropertySheet.Item> viewList, String fileName ) {
        for (PropertySheet.Item item :viewList) {
            if(fileName.equals(item.getName())) {
                return viewList.indexOf(item);
            }
        }
        return -1;
    }


    public static boolean terminalChecker(String line,String terminalNumber) {
        String[] values = line.split(",");
        String numberInLine = values[0].replace("\"","");
        return numberInLine.equals(terminalNumber);
    }
}
