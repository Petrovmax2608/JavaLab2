package UI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new UI(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}