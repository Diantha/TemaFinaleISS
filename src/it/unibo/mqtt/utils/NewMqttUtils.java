package it.unibo.mqtt.utils;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import it.unibo.qactors.akka.QActor;

public class NewMqttUtils extends MqttUtils {
	
	
	protected QActor actor;
	protected MqttMessage msg;
	
	public void setActor(QActor actor){
		this.actor = actor;
	}
	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {
		System.out.println("			%%% MqttUtils messageArrived on "+ topic /*+ "="+msg.toString()*/);
		this.msg = msg;
		System.out.println("			%%% MqttUtils messageArrived actor " + actor.getName());
 		actor.sendMsg("mqttmsg", actor.getName().replace("_ctrl", ""), "dispatch", "mqttmsg");
 		
	}	
	
	public MqttMessage getMsg(){
		return this.msg;
	}

}