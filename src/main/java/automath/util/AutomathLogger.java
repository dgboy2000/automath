package automath.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Global logger with these requirements:
 * -Usable from anywhere with a minimum of effort
 * -Should generate strings to log if the log-level isn't met
 */
public class AutomathLogger {
    private final static Logger LOGGER = Logger.getLogger(AutomathLogger.class.getName());
    private final static Level LOG_LEVEL = Level.FINE;
    private final static Handler CONSOLE_HANDLER = new ConsoleHandler();

    static {
        LOGGER.setLevel(LOG_LEVEL);
        CONSOLE_HANDLER.setLevel(LOG_LEVEL);
        LOGGER.addHandler(CONSOLE_HANDLER);

        LOGGER.info("AutomathLogger log level: " + LOGGER.getLevel().toString());
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
        if (logMessage != null) {
            LOGGER.severe(logMessage);
            return;
        } else if (logLevel.equals(Level.SEVERE)) return;

        logMessage = warning();
        if (logMessage != null) {
            LOGGER.warning(logMessage);
            return;
        } else if (logLevel.equals(Level.WARNING)) return;

        logMessage = info();
        if (logMessage != null) {
            LOGGER.info(logMessage);
            return;
        } else if (logLevel.equals(Level.INFO)) return;

        logMessage = config();
        if (logMessage != null) {
            LOGGER.config(logMessage);
            return;
        } else if (logLevel.equals(Level.CONFIG)) return;

        logMessage = fine();
        if (logMessage != null) {
            LOGGER.fine(logMessage);
            return;
        } else if (logLevel.equals(Level.FINE)) return;

        logMessage = finer();
        if (logMessage != null) {
            LOGGER.finer(logMessage);
            return;
        } else if (logLevel.equals(Level.FINER)) return;

        logMessage = finest();
        if (logMessage != null) {
            LOGGER.finest(logMessage);
            return;
        } else if (logLevel.equals(Level.FINEST)) return;
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
