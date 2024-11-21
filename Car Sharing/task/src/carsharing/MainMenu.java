package carsharing;

import carsharing.utils.KeyboardUtil;

public class MainMenu {
    public static void show() {
        System.out.println();
        System.out.println("""
                1. Log in as a manager
                0. Exit""");
        int choice = KeyboardUtil.readInt();

        switch(choice) {
            case 0 -> exit();
            case 1 -> ManagerMenu.show();
            default -> System.out.println("Invalid choice");
        }
    }

    public static void exit() {
        new ShutdownThread(); // REMOVE BEFORE CHECKING WITH HYPERSKILL
        System.exit(0);
    }
}
