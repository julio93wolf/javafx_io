package sample;

import com.jfoenix.controls.JFXToggleButton;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.json.JSONObject;

import java.net.URI;

public class Controller {

    @FXML
    JFXToggleButton tglSwitch1,tglSwitch2;

    private Socket socket;

    public Controller () {
        socket = IO.socket(URI.create("https://juliosocketapp.herokuapp.com:443"));
        socket.on("server_checked", new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                try {
                    JSONObject obj = (JSONObject)objects[0];
                    System.out.println("OnServerChecked: " + obj.toString());
                    tglSwitch1.setSelected(obj.getBoolean("checked_1"));
                    tglSwitch2.setSelected(obj.getBoolean("checked_2"));
                } catch (Exception e) {
                    System.out.println("ErrorServerChecked: " + e.toString());
                }
            }
        });
        socket.connect();
    }

    public void onActionToggleButtonSwitch1 (ActionEvent event) {
        client_checked();
    }

    public void onActionToggleButtonSwitch2 (ActionEvent event) {
        client_checked();
    }

    private void client_checked () {
        try {
            JSONObject obj = new JSONObject();
            obj.put("checked_1", tglSwitch1.isSelected());
            obj.put("checked_2", tglSwitch2.isSelected());
            socket.emit("client_checked",obj);
            System.out.println("EmmitClientChecked: " + obj.toString());
        } catch (Exception e) {
            System.out.println("ErrorClientChecked: " + e.toString());
        }
    }

}

