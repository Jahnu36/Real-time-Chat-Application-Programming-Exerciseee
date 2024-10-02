import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

class Logger {
    private static Logger instance;
    private final String logFile = "application.log";
    private PrintWriter writer;

    private Logger() {
        try {
            FileWriter fw = new FileWriter(logFile, true);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void log(String message) {
        writer.println(timestamp() + " - " + message);
    }

    private String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public void close() {
        writer.close();
    }
}

public class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Application started");

        // Simulate some application events
        for (int i = 1; i <= 5; i++) {
            logger.log("Processing item " + i);
            try {
                Thread.sleep(1000); // Simulate some work being done
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.log("Application finished");
        logger.close();

        System.out.println("Log entries have been written to application.log");
    }
}