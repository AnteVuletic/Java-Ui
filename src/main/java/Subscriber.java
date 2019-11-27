package mqmt;

import mqmt.classes.SensorCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber
{
    public static Subscriber subscriber;
    private MqttClient mqttClient;


    private Subscriber(String brokerUrl, String clientId) throws MqttException {
        this.mqttClient = new MqttClient(brokerUrl, clientId);
        this.mqttClient.connect();
    }

    public void SubscribeTopic(String topic, SensorCallback callback) throws MqttException {
        this.mqttClient.subscribe(topic);
        this.mqttClient.setCallback(callback);
    }

    public static void createClient(String brokerUrl, String clientId, String topic, SensorCallback callbacks) throws MqttException {
        if(Subscriber.subscriber == null) {
            Subscriber.subscriber = new Subscriber(brokerUrl, clientId);
        }
        Subscriber.subscriber.SubscribeTopic(topic, callbacks);
    }
}
