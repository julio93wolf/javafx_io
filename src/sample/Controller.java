package sample;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    JFXToggleButton tglSwitch1,tglSwitch2;

    private ClientSocket objClientSocket;
    private SwitchThread objSwitchThread;

    public void onActionToggleButtonSwitch1 (ActionEvent event) {
        objClientSocket.emmitClientChecked();
    }

    public void onActionToggleButtonSwitch2 (ActionEvent event) {
        objClientSocket.emmitClientChecked();
    }

    public void changeState (Boolean paramState) {
        objClientSocket.emmitChangeState(paramState);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        objClientSocket = new ClientSocket(this);
        objSwitchThread = new SwitchThread(this);
        objSwitchThread.start();
    }
}