/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
 */
package it.unibo.controller;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.mqtt.utils.MqttUtils;
import it.unibo.mqtt.utils.NewMqttUtils;
import it.unibo.qactors.QActorContext;
import it.unibo.qactors.QActorMessage;
import it.unibo.qactors.akka.QActor;


public class Controller extends AbstractController { 
	private Map<Integer, List<Integer>> values = new HashMap<>(); //SID, Distanza
	private static final int DMIN=70;
	private int num_of_sonar=3;
	private int firstSensorNotReached=1;
	private boolean robotStopped = false;
	//private MqttUtils util;
	private MqttMessage mess;
	private NewMqttUtils util;
	public Controller(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
		util = new NewMqttUtils();
	}
	
	public void connect(String clientid, String brokerAddr, String topic){
		try {
			util.connect(this,clientid,brokerAddr, topic);
			util.setActor(this);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
	}
	
	public void subscribe(String clientid, 
			String brokerAddr, String topic){
		try {
			util.subscribe(this,clientid,brokerAddr, topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void initialization()
	{
		this.firstSensorNotReached=1;
		this.robotStopped=false;
		this.values.get(1).clear();
		this.values.get(2).clear();
	}
	
	
	//I dati del sonar vengono presi dalla queue
	public String evaluateExpr(){
		System.out.println("Sto valutando l'espressione");
		if(this.firstSensorNotReached==4){
			this.robotStopped=true;
			return "stop";
		}
		else{
			QActorMessage msg = this.getMsgFromQueue();
			int totalLenght=values.get(0).get(0)+values.get(1).get(0)+values.get(2).get(0);
			if(this.firstSensorNotReached==2)
				totalLenght=totalLenght-values.get(0).get(0);
			if(firstSensorNotReached==3)
				totalLenght=totalLenght-values.get(1).get(0);

			if((totalLenght/(num_of_sonar-this.firstSensorNotReached+1)<DMIN) && totalLenght!=0){
				this.firstSensorNotReached++;
				return "alarm";
			}
			return "";
		}
	}
	//Decodifico l'immagine ricevuta come byte array in base64.
	public void retrieveAndSavePhoto(){
	MqttMessage msg = mess;
		byte[] photoByte=Base64.getDecoder().decode(msg.getPayload());
	      BufferedImage image;
		try {
			image = ImageIO.read( new ByteArrayInputStream( photoByte ) );
			ImageIO.write(image, "JPG", new File("imageReceived.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
