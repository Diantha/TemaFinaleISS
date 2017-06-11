package it.unibo.mqtt.utils;
 
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import it.unibo.qactors.akka.QActor;

public class MqttUtils implements MqttCallback{
private static MqttUtils myself = null;	
 	protected String clientid = null;
	protected String eventId = "mqtt";
	protected String eventMsg = "";
	protected  QActor actor = null;
	protected  MqttClient client = null;
	int count = 1;
	
	public static MqttUtils getMqttSupport( ){
		return myself  ;
	}
	public MqttUtils(){
		try {
 			myself   = this;
			System.out.println("			%%% MqttUtils created "+ myself );
		} catch (Exception e) {
			System.out.println("			%%% MqttUtils WARNING: "+ e.getMessage() );
		} 
	}
 
 	public void connect(QActor actor, String brokerAddr, String topic ) throws MqttException{
		System.out.println("			%%% MqttUtils connect/3 " );
 		clientid = MqttClient.generateClientId();
 		connect(actor,   clientid,   brokerAddr,   topic);		
	}
	public void connect(QActor actor, String clientid, String brokerAddr, String topic ) throws MqttException{
		System.out.println("			%%% MqttUtils connect/4 "+ clientid );
		this.actor = actor;
		client = new MqttClient(brokerAddr, clientid);
		MqttConnectOptions options = new MqttConnectOptions();
		options.setWill("unibo/clienterrors", "crashed".getBytes(), 2, true);
		client.connect(options);		
	}
	public void disconnect( ) throws MqttException{
		System.out.println("			%%% MqttUtils disconnect "+ client );
		if( client != null ) client.disconnect();
	}	
	public void publish(QActor actor, String clientid, String brokerAddr, String topic, String msg, int qos, boolean retain) throws MqttException{
 		MqttMessage message = new MqttMessage();
		message.setRetained(retain);
		if( qos == 0 || qos == 1 || qos == 2){//qos=0 fire and forget; qos=1 at least once(default);qos=2 exactly once
			message.setQos(0);
		}
		message.setPayload(msg.getBytes());
 		System.out.println("			%%% MqttUtils publish "  + message + " on " + topic);
		client.publish(topic, message);
	}	
 
	public void subscribe(QActor actor, String  clientid, String brokerAddr, String topic) throws Exception {
		try{
			this.actor = actor;
			client.setCallback(this);
 			client.subscribe(topic);  
 		}catch(Exception e){
				System.out.println("			%%% MqttUtils subscribe error "+  e.getMessage() );
				eventMsg = "mqtt(" + eventId +", failure)";
				System.out.println("			%%% MqttUtils subscribe error "+  eventMsg );
 				if( actor != null ) actor.sendMsg("mqttmsg", actor.getName(), "dispatch", "error");
	 			throw e;
		}
	}
	@Override
	public   void connectionLost(Throwable cause) {
		System.out.println("			%%% MqttUtils connectionLost  = "+ cause.getMessage() );
	}
	@Override
	public   void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("			%%% MqttUtils deliveryComplete token= "+ token );
	}
	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {
		System.out.println("			%%% MqttUtils messageArrived on "+ topic + "="+msg.toString());
		String mqttmsg = "mqttmsg(" + topic +"," + msg.toString() +")";
 		
 		if( actor != null ){
 			System.out.println("			%%% MqttUtils messageArrived actor " + actor.getName() +" mqttmsg "+ mqttmsg);
 			actor.emit("mqtt", mqttmsg);
 			//actor.sendMsg("mqttmsg", actor.getName().replace("_ctrl", ""), "dispatch", mqttmsg);
 		}
 		
 		storePhoto(msg);
	}
	
	private void storePhoto(MqttMessage msg){
		// Convert Base64 String to byte[]
		//byte[] imageBytes = Convert.FromBase64String(base64String);
		String mqttmsg = msg.toString();
		byte[] imageBytes = Base64.getDecoder().decode(mqttmsg);
		InputStream in = new ByteArrayInputStream(imageBytes);
		BufferedImage bufferedImage;
		try {
		bufferedImage = ImageIO.read(in);
		String path = "./photo_store/"+count+".jpg";
		System.out.println(path);
		File outputfile = new File(path);
		outputfile.createNewFile();
		ImageIO.write(bufferedImage, "jpg", outputfile);
		count++;
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	
	/*
	 * =================================================================
	 * TESTING	
	 * =================================================================
	 */
		
	public void test() throws Exception{
		System.out.println(" %%% MqttUtils test " );
		connect(null,"qapublisher_mqtt", "tcp://m2m.eclipse.org:1883",
		"unibo/mqtt/qa");
		publish(null,"qapublisher_mqtt","tcp://m2m.eclipse.org:1883",
		"unibo/mqtt/qa", "sensordata(aaa)", 1, true);
		
		connect( null,"observer_mqtt", "tcp://m2m.eclipse.org:1883",
		"unibo/mqtt/qa");
		subscribe(null,"observer_mqtt", "tcp://m2m.eclipse.org:1883",
		"unibo/mqtt/qa");
		for(int i=1; i<=3; i++)
		publish(null,"qapublisher_mqtt","tcp://m2m.eclipse.org:1883",
		"unibo/mqtt/qa", "distance("+ i +")", 1, true);
		 }
			 public static void main(String[] args) throws Exception{
		new MqttUtils().test();
		}	
}
