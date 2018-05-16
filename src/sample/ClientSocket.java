package sample;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;
import java.net.URI;

public class ClientSocket {

    private Socket attrSocket;
    private Controller objController;

    public ClientSocket(Controller paramController) {
        objController = paramController;
        attrSocket = IO.socket(URI.create("https://juliosocketapp.herokuapp.com:443"));
        attrSocket.on("server_checked", onServerChecked);
        attrSocket.connect();
    }
    
    private Emitter.Listener onServerChecked = new Emitter.Listener() {
        @Override
        public void call(Object... objects) {
            try {
                JSONObject obj = (JSONObject)objects[0];
                System.out.println("OnServerChecked: " + obj.toString());
                objController.tglSwitch1.setSelected(obj.getBoolean("checked_1"));
                objController.tglSwitch2.setSelected(obj.getBoolean("checked_2"));
            } catch (Exception e) {
                System.out.println("ErrorServerChecked: " + e.toString());
            }
        }
    };

    public void emmitClientChecked () {
        try {
            JSONObject obj = new JSONObject();
            obj.put("checked_1", objController.tglSwitch1.isSelected());
            obj.put("checked_2", objController.tglSwitch2.isSelected());
            attrSocket.emit("client_checked",obj);
            System.out.println("EmmitClientChecked: " + obj.toString());
        } catch (Exception e) {
            System.out.println("ErrorClientChecked: " + e.toString());
        }
    }

    public void emmitChangeState (Boolean paramState) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("checked_1", paramState);
            obj.put("checked_2", !paramState);
            attrSocket.emit("client_checked",obj);
            objController.tglSwitch1.setSelected(paramState);
            objController.tglSwitch2.setSelected(!paramState);
            System.out.println("EmmitChangeState: " + obj.toString());
        } catch (Exception e) {
            System.out.println("ErrorChangeState: " + e.toString());
        }
    }
}
