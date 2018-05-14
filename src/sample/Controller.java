package sample;

import com.jfoenix.controls.JFXCheckBox;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import org.json.JSONObject;

import java.net.URI;

public class Controller {

    @FXML
    CheckBox CheckBoxOption1;
    @FXML
    JFXCheckBox CheckBoxOption2;

    private Socket socket;

    public Controller () {
        socket = IO.socket(URI.create("http://localhost:3000"));
        socket.on("server_checked", new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                try {
                    JSONObject obj = (JSONObject)objects[0];
                    System.out.println(obj);
                    System.out.println("Checked: " + obj.getBoolean("checked_1"));
                    System.out.println("Checked: " + obj.getBoolean("checked_2"));
                    CheckBoxOption1.setSelected(obj.getBoolean("checked_1"));
                    CheckBoxOption2.setSelected(obj.getBoolean("checked_2"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });
        socket.connect();
    }

    public void onActionCheckBoxOption1 (ActionEvent event) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("checked_1", CheckBoxOption1.isSelected());
            obj.put("checked_2", CheckBoxOption2.isSelected());
            System.out.println("Object" + obj);
            socket.emit("client_checked",obj);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void onActionCheckBoxOption2 (ActionEvent event) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("checked_1", CheckBoxOption1.isSelected());
            obj.put("checked_2", CheckBoxOption2.isSelected());
            System.out.println("Object" + obj);
            socket.emit("client_checked",obj);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}

