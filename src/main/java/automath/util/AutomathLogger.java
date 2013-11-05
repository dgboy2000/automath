package automath.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Global logger with these requirements:
 * -Usable from anywhere with a minimum of effort
 * -Should generate strings to log if the log-level isn't met
 */
public class AutomathLogger {

    // assumes the current class is called logger
    private final static Logger LOGGER = Logger.getLogger(AutomathLogger.class.getName());

    static {
        LOGGER.setLevel(Level.FINER);
        LOGGER.info("AutomathLogger starting up");
    };

    public AutomathLogger() {
        logIfLevelAppropriate();
    }

    /**
     * Go down the log levels until we reach either the lowest
     * log level that should display or a non-null message
     */
    private void logIfLevelAppropriate() {
        Level logLevel = LOGGER.getLevel();

        String logMessage = severe();
        if (logLevel.equals(Level.SEVERE) || logMessage != null) {
            LOGGER.severe(logMessage);
            return;
        }

        logMessage = warning();
        if (logLevel.equals(Level.WARNING) || logMessage != null) {
            LOGGER.warning(logMessage);
            return;
        }

        logMessage = info();
        if (logLevel.equals(Level.INFO) || logMessage != null) {
            LOGGER.info(logMessage);
            return;
        }

        logMessage = config();
        if (logLevel.equals(Level.CONFIG) || logMessage != null) {
            LOGGER.config(logMessage);
            return;
        }

        logMessage = fine();
        if (logLevel.equals(Level.FINE) || logMessage != null) {
            LOGGER.fine(logMessage);
            return;
        }

        logMessage = finer();
        if (logLevel.equals(Level.FINER) || logMessage != null) {
            LOGGER.finer(logMessage);
            return;
        }

        logMessage = finest();
        if (logLevel.equals(Level.FINEST) || logMessage != null) {
            LOGGER.finest(logMessage);
            return;
        }
    }

    /**
     * Override the appropriate level message to generate a log message
     * @return
     */

    public String severe() { return null; }
    public String warning() { return null; }
    public String info() { return null; }
    public String config() { return null; }
    public String fine() { return null; }
    public String finer() { return null; }
    public String finest() { return null; }

}
