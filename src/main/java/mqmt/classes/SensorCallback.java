package mqmt.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

public class SensorCallback implements MqttCallback {
    public ArrayList<WaterDisplayStatistic> waterDisplayStatisticArrayList;
    public String Token;

    public SensorCallback(ArrayList<WaterDisplayStatistic> waterDisplayStatisticArrayList) {
        this.waterDisplayStatisticArrayList = waterDisplayStatisticArrayList;
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String messageString = mqttMessage.toString();
        Sensor[] sensorsArrayList = mapper.readValue(messageString, Sensor[].class);
        for (Sensor sensor : sensorsArrayList) {
            for (WaterDisplayStatistic waterDisplayStatistic : waterDisplayStatisticArrayList) {
                waterDisplayStatistic.updateIfCorrectType(sensor);
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
