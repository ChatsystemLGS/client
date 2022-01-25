package client.interfaces;

public class UpdaterMessages implements Runnable {
    @Override
    public void run() {
        while (true) {
            // TODO: receiveMessages
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
