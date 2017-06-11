/* Generated by AN DISI Unibo */ 
package it.unibo.robot;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import akka.actor.FSM.Timer;
import cli.System.IO.FileNotFoundException;
import cli.System.IO.IOException;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.mqtt.utils.MqttUtils;
import it.unibo.qactors.QActorContext;
import it.unibo.bls.highLevel.interfaces.IDevLed.LedColor;
import it.unibo.bls.raspberry.components.DeviceLedPi4j;

//
public class Robot extends AbstractRobot {
	
	protected MqttUtils mqttUtil;
	protected DeviceLedPi4j ledOnRasp;
	protected Timer timer;
	
	public Robot(String actorId, QActorContext myCtx, IOutputEnvView outEnvView ) throws Exception{
		super(actorId,myCtx,outEnvView ,it.unibo.qactors.QActorUtils.robotBase );
	}
	
	protected MqttUtils getMqttUtils(){
		if( mqttUtil == null ) mqttUtil = new MqttUtils();
		return mqttUtil;
		}
		
	public void connect( String clientid, String brokerAddr, String topic
		) throws Exception{
		println(" %%% connecting to:" + brokerAddr + " topic=" +
		topic);
		getMqttUtils().connect(this,clientid,brokerAddr, topic);
		println(" %%% connect done " );
		}
		public void discconnect() throws Exception{
		getMqttUtils().disconnect();
		}
		public void publish( String clientid,
		String brokerAddr, String topic, String msg, int qos, boolean
		retain) throws Exception{
		getMqttUtils().publish(this, clientid, brokerAddr,
		topic,msg,qos,retain);
		println(" %%% publish done " );
		println(" %%% publish actor name "+this.getName() );
		}
		public void subscribe( String clientid, String brokerAddr, String
		topic) throws Exception {
		getMqttUtils().subscribe(this,clientid,brokerAddr, topic);
		println(" %%% subscribe done " );
		}
		
		private String encodeFileToBase64Binary(File file){
		String encodedfile = null;
		try {
		FileInputStream fileInputStreamReader = new
		FileInputStream(file);
		byte[] bytes = new byte[(int)file.length()];
		
		fileInputStreamReader.read(bytes);
		encodedfile = Base64.getEncoder().encodeToString(bytes);
		fileInputStreamReader.close();
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return encodedfile;
		}
		
		private String encodePhoto(int numfoto){
		System.out.println(""+numfoto);
		String path = "./image/"+numfoto+".jpg";
		System.out.println(path);
		File f = new File(path);
		String encodestring = encodeFileToBase64Binary(f);
		return encodestring;
		}
		
		public void encodeAndSendPhoto(int numfoto){
		String msg= encodePhoto(numfoto);
		try {
		publish("obse_mqtt","tcp://test.mosquitto.org:1883",
		"photo",msg, 1, true);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		
		public void initLed(int pinNumber){
			try {
				this.println("initled");
				ledOnRasp = new DeviceLedPi4j("led", outEnvView, LedColor.RED, pinNumber);
			} catch (Exception e) {
				this.println("Init led Error");
			}
		}
		
		 public void startLedBlink(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
		@Override
		public void run() {
		ledOnRasp.doSwitch();
		};
		}, 0, 250);
		}
		
		 public void stopBlink(){
		timer.cancel();
		timer.purge();
		ledOnRasp.turnOff();
		}
		}
}
