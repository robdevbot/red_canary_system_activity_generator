import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

class Main {
    public static void main(String[] args) {
        simpleGenerateEdrEndpointActivity();
    }

    public static void simpleGenerateEdrEndpointActivity() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")) {
            String[] processArgs = {"notepad", "new.txt"};
            startProcess(processArgs);
        } else if (osName.contains("linux")) {
            String[] processArgs = {"gedit", "new.txt"};
            startProcess(processArgs);
        } else if (osName.contains("mac")) {
            String[] processArgs = {"open", "-t new.txt"};
            startProcess(processArgs);
        } else {
            System.out.println("This is an unknown or unsupported operating system.");
            Logger.log("Process start failed: unknown or unsupported operating system");
        }

        performFileModificationTest("/dummyFile", "txt", "dummyFile");
        sendNetworkData();
    }

    public static void startProcess(String[] processArgs) {
        String commandName = processArgs[0];
        String suppliedArgs = processArgs[1];

        try {
            Process p = new ProcessBuilder(commandName, suppliedArgs).start();
        } catch (Exception err) {
            System.out.println("Could not start process");
            err.printStackTrace();
        }

        Logger.log("Process start:");
        Logger.log("    Timestamp: " + getTimeStamp());
        Logger.log("    Username: " + getUserName());
        Logger.log("    Command line: " + commandName + " " + suppliedArgs);
        Logger.log("    Process id: " + getProcessName());
    }

    public static void createFile(String file_type, String file_location) {
        try {
            new File(file_location);
        } catch (Exception err) {
            err.printStackTrace();
        }

        Logger.log("File creation/modification/deletion:");
        Logger.log("    Timestamp: " + getTimeStamp());
        Logger.log("    File path: " + file_location);
        Logger.log("    Activity: Create");
        Logger.log("    Username: " + getUserName());
        Logger.log("    Command line: ");
        Logger.log("    Process id: " + getProcessName());
    }

    public static void modifyFile(String filename) {

        try {
            FileWriter fileModifier = new FileWriter(filename);
            fileModifier.write("Placeholder addition to file");
            fileModifier.close();
        } catch (IOException err) {
            System.out.println("Could not write to file.");
            err.printStackTrace();
        }

        Logger.log("File creation/modification/deletion:");
        Logger.log("    Timestamp: " + getTimeStamp());
        Logger.log("    File path: " + filename);
        Logger.log("    Activity: Modify");
        Logger.log("    Username: " + getUserName());
        Logger.log("    Command line: ");
        Logger.log("    Process id: " + getProcessName());
    }

    public static void deleteFile(String filename) {
        try {
            File fileToDelete = new File(filename); 
            fileToDelete.delete();
        } catch (Exception err) {
            System.out.println("Could not delete file.");
            err.printStackTrace();
        }

        Logger.log("File creation/modification/deletion:");
        Logger.log("    Timestamp: " + getTimeStamp());
        Logger.log("    File path: " + filename);
        Logger.log("    Activity: Delete");
        Logger.log("    Username: " + getUserName());
        Logger.log("    Command line: ");
        Logger.log("    Process id: " + getProcessName());
    }

    public static void sendNetworkData() {
        try {
            String urlString = "http://www.google.com";
            var url = new URI(urlString).toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // int responseCode = connection.getResponseCode();
            // System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            // System.out.println("Response Body:\n" + response.toString());

            connection.disconnect();
        } catch (Exception err) {
            System.out.println("Could not send network request.");
            err.printStackTrace();
        }

        Logger.log("Send network activity:");
        Logger.log("    Timestamp: " + getTimeStamp());
        Logger.log("    Username: " + getUserName());

        Logger.log("    Destination address + port: " + "");
        Logger.log("    Source address + port: " + "");
        Logger.log("    Amount of data sent: " + "");
        Logger.log("    Protocol of data sent: " + "");
        
        Logger.log("    Process id: " + getProcessName());
        Logger.log("    Command line: ");
    }

    public static void performFileModificationTest(String file_location, String file_type, String filename) {
        System.out.println("creating " + file_location + "of type " + file_type);
        createFile(file_type, file_location);
        System.out.println("modifying " + filename);
        modifyFile(filename);
        System.out.println("deleting " + filename);
        deleteFile(filename);
    }

    public static String getTimeStamp() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = DATE_FORMAT.format(new Date());
        return timeStamp;
    }
    
    public static String getUserName() {
        String username = System.getProperty("user.name");
        return username;
    }

    public static String getProcessName() {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        String processName = runtimeMxBean.getName();
        return processName;
    }

    public class Logger {
        private static final String LOG_FILE = "application.yaml";
        public static void log(String message) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // public static String backupFile(String filename) {
    //     try {
    //         File fileCopy = new File(filename);
    //         Scanner fileReader = new Scanner(fileCopy);
    //         String fileContents = "";

    //         while (fileReader.hasNextLine()) {
    //             fileContents += fileReader.nextLine();
    //         }
    //         fileReader.close();

    //         return fileContents;
    //     } catch (FileNotFoundException err) {
    //         System.out.println("File not found.");
    //         err.printStackTrace();
    //     }

    // }

    // public static void performFileCreationDeletionTest(String file_location, String file_type, String filename) {
    //     createFile(file_type, file_location);
    //     deleteFile(filename);
    // }
}