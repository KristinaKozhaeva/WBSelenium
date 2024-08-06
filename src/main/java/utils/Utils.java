package utils;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static void setPause(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}