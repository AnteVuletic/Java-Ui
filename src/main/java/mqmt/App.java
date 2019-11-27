package mqmt;

import com.fasterxml.jackson.databind.ObjectMapper;
import mqmt.classes.Configuration;
import mqmt.classes.SensorCallback;
import mqmt.classes.WaterDisplayStatistic;
import mqmt.classes.WaterDisplayStatisticsDto;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException, MqttException {
        String filePath = "C:\\\\Users\\\\Ante\\\\Desktop\\\\Faks\\\\Java\\\\05_Vježba\\\\src\\\\main\\\\java\\\\mqmt\\\\settings\\\\uiconfiguration.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(file, Configuration.class);
        ArrayList<WaterDisplayStatistic> waterDisplayStatisticArrayList = new ArrayList<WaterDisplayStatistic>();
        for (WaterDisplayStatisticsDto display : configuration.displays) {
            waterDisplayStatisticArrayList.add(new WaterDisplayStatistic(display.unitType));
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        waterDisplayStatisticArrayList.forEach(waterDisplayStatistic -> waterDisplayStatistic.getLabelArray().forEach(panel::add));

        SensorCallback callback = new SensorCallback(waterDisplayStatisticArrayList);
        mqmt.Subscriber.createClient(configuration.broker, configuration.clientId, configuration.topic, callback);
        panel.setVisible(true);
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }
}
