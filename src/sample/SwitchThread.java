package sample;

public class SwitchThread extends Thread{

    private Controller objController;
    private Boolean attrState = false;

    public SwitchThread(Controller paramController) {
        objController = paramController;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                attrState = !attrState;
                objController.changeState(attrState);
            } catch (Exception e) {
                System.out.println("ErrorSwitchThread: " + e.toString());
            }
        }
    }
}
