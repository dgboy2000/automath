package automath.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AutomathLoggerTest {
    @Test
    public void testLog() {
        new AutomathLogger() { @Override public String warning() { return "Test warning"; }};
        new AutomathLogger() { @Override public String finest() { return "Test finest"; }};
    }
}
