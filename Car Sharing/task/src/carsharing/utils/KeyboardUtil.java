package carsharing.utils;

import java.util.Scanner;

public class KeyboardUtil {
    private static final Scanner KEYBOARD = new Scanner(System.in);

    public static String readLine() {
        return KEYBOARD.nextLine();
    }

    public static int readInt() {
        return Integer.parseInt(readLine());
    }
}
