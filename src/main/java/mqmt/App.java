package mqmt;

import com.fasterxml.jackson.databind.ObjectMapper;
import mqmt.classes.Configuration;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException, MqttException {
        String filePath = "C:\\\\Users\\\\Ante\\\\Desktop\\\\Faks\\\\Java\\\\05_Vježba\\\\src\\\\main\\\\java\\\\mqmt\\\\settings\\\\uiconfiguration.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(file, Configuration.class);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new WaterStatisticFrame(configuration);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
