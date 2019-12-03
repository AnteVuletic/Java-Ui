package mqmt;

import mqmt.classes.Configuration;
import mqmt.classes.SensorCallback;
import mqmt.classes.WaterDisplayStatistic;
import mqmt.classes.WaterDisplayStatisticsDto;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WaterStatisticFrame extends JFrame {
    public ArrayList<WaterDisplayStatistic> waterDisplayStatisticArrayList;
    public JPanel panel;

    public WaterStatisticFrame(Configuration configuration) throws MqttException {
        waterDisplayStatisticArrayList = new ArrayList<WaterDisplayStatistic>();
        for (WaterDisplayStatisticsDto display : configuration.displays) {
            waterDisplayStatisticArrayList.add(new WaterDisplayStatistic(display.unitType));
        }
        this.panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setVisible(true);
        waterDisplayStatisticArrayList.forEach(waterDisplayStatistic -> waterDisplayStatistic.getLabelArray().forEach(panel::add));

        SensorCallback callback = new SensorCallback(waterDisplayStatisticArrayList);
        mqmt.Subscriber.createClient(configuration.broker, configuration.clientId, configuration.topic, callback);

        super.add(panel);
        super.setSize(600, 500);
        super.setVisible(true);
    }
}
