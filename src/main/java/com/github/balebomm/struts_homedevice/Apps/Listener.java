package com.github.balebomm.struts_homedevice.Apps;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public final class Listener implements MqttCallback {

  private final int qos = 1;
  private String topic = "example/temperature";
  private MqttClient client;

  public Listener() throws MqttException {
    try {
      String host = String.format("tcp://localhost:1883");
      String clientId = "example/temperature";

      MqttConnectOptions conOpt = new MqttConnectOptions();
      conOpt.setCleanSession(true);

      this.client = new MqttClient(host, clientId, new MemoryPersistence());
      this.client.setCallback(this);
      this.client.connect(conOpt);

      this.client.subscribe(this.topic, qos);
    } catch (MqttException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void sendMessage(String payload) throws MqttException {
    MqttMessage message = new MqttMessage(payload.getBytes());
    message.setQos(qos);
    this.client.publish(this.topic, message); // Blocking publish
  }

  /**
   * @see MqttCallback#connectionLost(Throwable)
   */
  public void connectionLost(Throwable cause) {
    System.out.println("Connection lost because: " + cause);
    System.exit(1);
  }

  /**
   * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
   */
  public void deliveryComplete(IMqttDeliveryToken token) {
  }

  /**
   * @see MqttCallback#messageArrived(String, MqttMessage)
   */
  public void messageArrived(String topic, MqttMessage message) throws MqttException {
    System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
  }

  public static void main(String[] args) throws MqttException {
    new Listener();
  }
}
