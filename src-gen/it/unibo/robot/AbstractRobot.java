/* Generated by AN DISI Unibo */ 
package it.unibo.robot;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.qactors.QActorContext;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.action.ActionReceiveTimed;
import it.unibo.qactors.action.AsynchActionResult;
import it.unibo.qactors.action.IActorAction;
import it.unibo.qactors.action.IActorAction.ActionExecMode;
import it.unibo.iot.configurator.Configurator;
import it.unibo.iot.executors.baseRobot.IBaseRobot; 
import it.unibo.iot.models.sensorData.distance.IDistanceSensorData;
import it.unibo.iot.models.sensorData.impact.IImpactSensorData;
import it.unibo.iot.models.sensorData.line.ILineSensorData;
import it.unibo.iot.models.sensorData.magnetometer.IMagnetometerSensorData;
import it.unibo.iot.sensors.ISensor; 
import it.unibo.iot.sensors.ISensorObserver;
import it.unibo.iot.sensors.distanceSensor.DistanceSensor;
import it.unibo.iot.sensors.impactSensor.ImpactSensor;
import it.unibo.iot.sensors.lineSensor.LineSensor;
import it.unibo.iot.sensors.magnetometerSensor.MagnetometerSensor;
import it.unibo.qactors.action.IMsgQueue;
import it.unibo.qactors.QActorMessage;
import it.unibo.qactors.QActorUtils;


class QaRobotActor extends it.unibo.qactor.robot.RobotActor{
	public QaRobotActor(
		String name, QActorContext ctx, String worldTheoryPath,
			IOutputEnvView outEnvView, String baserobot, String defaultPlan )  throws Exception{
		super(name, ctx, "./srcMore/it/unibo/robot/plans.txt", worldTheoryPath,
		outEnvView, it.unibo.qactor.robot.RobotSysKb.setRobotBase(ctx, baserobot) , defaultPlan);
	}
}

public class AbstractRobot extends QaRobotActor { 
protected AsynchActionResult aar = null;
protected boolean actionResult = true;
protected alice.tuprolog.SolveInfo sol;
//protected IMsgQueue mysupport ;  //defined in QActor
protected String planFilePath    = null;
protected String terminationEvId = "default";
protected String parg="";
protected boolean bres=false;
protected IActorAction  action;

		protected static IOutputEnvView setTheEnv(IOutputEnvView outEnvView ){
			return outEnvView;
		}


	public AbstractRobot(String actorId, QActorContext myCtx, IOutputEnvView outEnvView ,String baserobot)  throws Exception{
		super(actorId, myCtx,  
		"./srcMore/it/unibo/robot/WorldTheory.pl",
		setTheEnv( outEnvView ) ,baserobot , "main");		
		this.planFilePath = "./srcMore/it/unibo/robot/plans.txt";
		//Plan interpretation is done in Prolog
		//if(planFilePath != null) planUtils.buildPlanTable(planFilePath);
 	}
	@Override
	protected void doJob() throws Exception {
		String name  = getName().replace("_ctrl", "");
		mysupport = (IMsgQueue) QActorUtils.getQActor( name );
 		initSensorSystem();
		boolean res = main();
		//println(getName() + " doJob " + res );
	} 
	/* 
	* ------------------------------------------------------------
	* PLANS
	* ------------------------------------------------------------
	*/
    public boolean main() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "main";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = " \"Ready to start!\" ";
    		println( temporaryStr );  
    		if( ! planUtils.switchToPlan("connectToMqtt").getGoon() ) break;
    		if( ! planUtils.switchToPlan("waiting").getGoon() ) break;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=main WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean connectToMqtt() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "connectToMqtt";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = " \"Connecting to MQTT server...\" ";
    		println( temporaryStr );  
    		parg = "actorOp(connectToMqttServer( \"mqtt_robot_system\" , \"tcp://m2m.eclipse.org:1883\" , \"photo\" ))";
    		aar = solveGoalReactive(parg,3600000,"","");
    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
    		if( aar.getInterrupted() ){
    			curPlanInExec   = "connectToMqtt";
    			if( ! aar.getGoon() ) break;
    		} 			
    		if( (guardVars = QActorUtils.evalTheGuard(this, " ??tout(X,Y)" )) != null ){
    		if( ! planUtils.switchToPlan("toutExpired").getGoon() ) break;
    		}
    		returnValue = continueWork;  
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=connectToMqtt WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean waiting() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "waiting";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = " \"Waiting for a command...\" ";
    		println( temporaryStr );  
    		//senseEvent
    		timeoutval = 30000;
    		aar = planUtils.senseEvents( timeoutval,"usercmd","continue",
    		"" , "",ActionExecMode.synch );
    		if( ! aar.getGoon() || aar.getTimeRemained() <= 0 ){
    			//println("			WARNING: sense timeout");
    			addRule("tout(senseevent,"+getName()+")");
    			//break;
    		}
    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?tout(30000,Y)" )) != null ){
    		if( ! planUtils.switchToPlan("toutExpired").getGoon() ) break;
    		}
    		printCurrentEvent(false);
    		//onEvent
    		if( currentEvent.getEventId().equals("usercmd") ){
    		 		String parg = "";
    		 		parg =  updateVars(  Term.createTerm("usercmd(CMD)"), Term.createTerm("usercmd(robotgui(w(S)))"), 
    		 			    		  					Term.createTerm(currentEvent.getMsg()), parg);
    		 			if( parg != null ){
    		 				 if( ! planUtils.switchToPlan("moveForward").getGoon() ) break; 
    		 			}//else println("guard  fails");  //parg is null when there is no guard (onEvent)
    		 }
    		//onEvent
    		if( currentEvent.getEventId().equals("usercmd") ){
    		 		String parg = "";
    		 		parg =  updateVars(  Term.createTerm("usercmd(CMD)"), Term.createTerm("usercmd(robotgui(h(S)))"), 
    		 			    		  					Term.createTerm(currentEvent.getMsg()), parg);
    		 			if( parg != null ){
    		 				 if( ! planUtils.switchToPlan("stop").getGoon() ) break; 
    		 			}//else println("guard  fails");  //parg is null when there is no guard (onEvent)
    		 }
    		if( planUtils.repeatPlan(0).getGoon() ) continue;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=waiting WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean moveForward() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "moveForward";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = " \"Moving forward...\" ";
    		println( temporaryStr );  
    		//forward
    		if( ! execRobotMove("moveForward","forward",40,0,20000, "usercmd,alarm,obstacle,sonarArea" , "checkUserCommand,stop,stop,handlePhotoShoot") ) break;
    		if( planUtils.repeatPlan(0).getGoon() ) continue;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=moveForward WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean checkUserCommand() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "checkUserCommand";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = " \"Checking user command...\" ";
    		println( temporaryStr );  
    		//onEvent
    		if( currentEvent.getEventId().equals("usercmd") ){
    		 		String parg = "";
    		 		parg =  updateVars(  Term.createTerm("usercmd(CMD)"), Term.createTerm("usercmd(robotgui(h(S)))"), 
    		 			    		  					Term.createTerm(currentEvent.getMsg()), parg);
    		 			if( parg != null ){
    		 				 if( ! planUtils.switchToPlan("stop").getGoon() ) break; 
    		 			}//else println("guard  fails");  //parg is null when there is no guard (onEvent)
    		 }
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=checkUserCommand WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean stop() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "stop";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		//stop
    		if( ! execRobotMove("stop","stop",0,0,0, "" , "") ) break;
    		temporaryStr = " \"Robot stopped!\" ";
    		println( temporaryStr );  
    		if( ! planUtils.switchToPlan("waiting").getGoon() ) break;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=stop WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean handlePhotoShoot() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "handlePhotoShoot";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		//stop
    		if( ! execRobotMove("handlePhotoShoot","stop",0,0,0, "" , "") ) break;
    		temporaryStr = " \"The robot is going to take a photo...\" ";
    		println( temporaryStr );  
    		//left
    		if( ! execRobotMove("handlePhotoShoot","left",70,0,2000, "" , "") ) break;
    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?pinLed(PIN)" )) != null ){
    		parg = "actorOp(startLedBlink)";
    		parg = QActorUtils.substituteVars(guardVars,parg);
    		aar = solveGoalReactive(parg,3600000,"","");
    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
    		if( aar.getInterrupted() ){
    			curPlanInExec   = "handlePhotoShoot";
    			if( ! aar.getGoon() ) break;
    		} 			
    		}
    		if( ! planUtils.switchToPlan("sendPhoto").getGoon() ) break;
    		//right
    		if( ! execRobotMove("handlePhotoShoot","right",70,0,2000, "" , "") ) break;
    		parg = "actorOp(stopLedBlink)";
    		aar = solveGoalReactive(parg,3600000,"","");
    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
    		if( aar.getInterrupted() ){
    			curPlanInExec   = "handlePhotoShoot";
    			if( ! aar.getGoon() ) break;
    		} 			
    		temporaryStr = " \"Led stopped blinking\" ";
    		println( temporaryStr );  
    		if( ! planUtils.switchToPlan("checkReachedArea").getGoon() ) break;
    		if( ! planUtils.switchToPlan("moveForward").getGoon() ) break;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=handlePhotoShoot WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean sendPhoto() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "sendPhoto";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?msg(reachedSonarArea, \"event\" ,controller,none,sonarArea(K),N)" )) != null ){
    		parg = "actorOp(sendPhotoToConsole(K))";
    		parg = QActorUtils.substituteVars(guardVars,parg);
    		aar = solveGoalReactive(parg,3600000,"","");
    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
    		if( aar.getInterrupted() ){
    			curPlanInExec   = "sendPhoto";
    			if( ! aar.getGoon() ) break;
    		} 			
    		}
    		temporaryStr = " \"Photo shooted and sent to the user!\" ";
    		println( temporaryStr );  
    		returnValue = continueWork;  
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=sendPhoto WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean checkReachedArea() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "checkReachedArea";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		if( (guardVars = QActorUtils.evalTheGuard(this, " ??msg(reachedSonarArea, \"event\" ,console,none,sonarArea(0),N)" )) != null ){
    		if( ! planUtils.switchToPlan("moveTowardsAreaB").getGoon() ) break;
    		}
    		returnValue = continueWork;  
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=checkReachedArea WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean moveTowardsAreaB() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "moveTowardsAreaB";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		//forward
    		if( ! execRobotMove("moveTowardsAreaB","forward",40,0,3000, "usercmd,obstacle" , "checkUserCommand,stop") ) break;
    		//stop
    		if( ! execRobotMove("moveTowardsAreaB","stop",10,0,0, "" , "") ) break;
    		temporaryStr = " \"The robot has reached the area B!\" ";
    		println( temporaryStr );  
    		if( ! planUtils.switchToPlan("waiting").getGoon() ) break;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=moveTowardsAreaB WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean toutExpired() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "toutExpired";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = "tout(X,Y)";
    		println( temporaryStr );  
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=toutExpired WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    /* 
    * ------------------------------------------------------------
    * SENSORS
    * ------------------------------------------------------------
    */
    protected void initSensorSystem(){		
    	try {
    		String goal = "consult( \"./src/it/unibo/robot/sensorTheory.pl\" )";
    		SolveInfo sol = QActorUtils.solveGoal( goal ,pengine );
    		if( ! sol.isSuccess() ){
    			//println( "avatar initSensorSystem attempt to load sensorTheory "  );
    			goal = "consult( \"./sensorTheory.pl\" )";
    			QActorUtils.solveGoal( pengine, goal  );
    			//println( "avatar initSensorSystem= "  +  aar.getResult() );
    		}
    		addSensorObservers();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /*
    //COMPONENTS
     RobotComponent motorleft 
     RobotComponent motorright 
    Composed component motors
    */
    protected void addSensorObservers(){
    	for (ISensor<?> sensor : Configurator.getInstance().getSensors()) {
    		//println( "robot sensor= "  + sensor.getDefStringRep() );
    		//println( "robot sensor class= "  + sensor.getClass().getName() );
        	if( sensor instanceof DistanceSensor){
        		DistanceSensor sensorDistance  = (DistanceSensor) sensor;
        		ISensorObserver<IDistanceSensorData> obs = new SensorObserver<IDistanceSensorData>(this,outEnvView);
        //		println( "avatar add observer to  "  + sensorDistance.getDefStringRep() );
        		sensorDistance.addObserver(  obs  ) ;
        	}
        	if( sensor instanceof LineSensor){
        		LineSensor sensorLine = (LineSensor) sensor;
         		ISensorObserver<ILineSensorData> obs = new SensorObserver<ILineSensorData>(this,outEnvView);
        //		println( "avatar add observer to  "  + sensorLine.getDefStringRep() );
        		sensorLine.addObserver(  obs  ) ;
        	}
         	if( sensor instanceof MagnetometerSensor){
        		MagnetometerSensor sensorMagneto = (MagnetometerSensor) sensor;
         		ISensorObserver<IMagnetometerSensorData> obs = new SensorObserver<IMagnetometerSensorData>(this,outEnvView);
        //		println( "avatar add observer to  "  + sensorMagneto.getDefStringRep() );
        		sensorMagneto.addObserver(  obs  ) ;
        	}
    		if( sensor instanceof ImpactSensor){
    			ImpactSensor sensorImpact = (ImpactSensor) sensor;
    			ISensorObserver<IImpactSensorData> obs = new SensorObserver<IImpactSensorData>(this,outEnvView);
    	//		println( "avatar add observer to  "  + sensorMagneto.getDefStringRep() );
    			sensorImpact.addObserver(  obs  ) ;
    		}
    	}		
    }	
    
 
	/* 
	* ------------------------------------------------------------
	* APPLICATION ACTIONS
	* ------------------------------------------------------------
	*/
	/* 
	* ------------------------------------------------------------
	* QUEUE  
	* ------------------------------------------------------------
	*/
	    protected void getMsgFromInputQueue(){
//	    	println( " %%%% getMsgFromInputQueue" ); 
	    	QActorMessage msg = mysupport.getMsgFromQueue(); //blocking
//	    	println( " %%%% getMsgFromInputQueue continues with " + msg );
	    	this.currentMessage = msg;
	    }
  }

