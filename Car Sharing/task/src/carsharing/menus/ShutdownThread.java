package carsharing.menus;

public class ShutdownThread extends Thread {

    public ShutdownThread() {
        super(new Runnable() {
            @Override
            public void run() {
                System.out.println("\nShutting down, this may take a moment...");
                while(true) {
                    System.out.print("* ");
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });

        this.setName("Shut down service");
        this.setDaemon(true);
        this.start();
    }
}
