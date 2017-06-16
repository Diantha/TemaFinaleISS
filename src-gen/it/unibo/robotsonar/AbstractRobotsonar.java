/* Generated by AN DISI Unibo */ 
package it.unibo.robotsonar;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.qactors.QActorContext;
import it.unibo.qactors.ActorTerminationMessage;
import it.unibo.qactors.QActorMessage;
import it.unibo.qactors.QActorUtils;
import it.unibo.contactEvent.interfaces.IEventItem;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.action.ActionReceiveTimed;
import it.unibo.qactors.action.AsynchActionResult;
import it.unibo.qactors.action.IActorAction;
import it.unibo.qactors.action.IActorAction.ActionExecMode;
import it.unibo.qactors.action.IMsgQueue;
import it.unibo.qactors.akka.QActor;


//REGENERATE AKKA: QActor instead QActorPlanned
public abstract class AbstractRobotsonar extends QActor { 
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
	
	
		public AbstractRobotsonar(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/robotsonar/WorldTheory.pl",
			setTheEnv( outEnvView )  , "main");		
			this.planFilePath = "./srcMore/it/unibo/robotsonar/plans.txt";
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
	    		if( ! planUtils.switchToPlan("init").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=main WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean init() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "init";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		parg = "loadTheory( \"./robotSonarKB.pl\" )";
	    		//tout=1 day (24 h)
	    		aar = solveGoalReactive(parg,86400000,"","");
	    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
	    		if( aar.getInterrupted() ){
	    			curPlanInExec   = "init";
	    			if( ! aar.getGoon() ) break;
	    		} 			
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?simulate" )) != null ){
	    		if( ! planUtils.switchToPlan("simulateMode").getGoon() ) break;
	    		}
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?physicSonar" )) != null ){
	    		if( ! planUtils.switchToPlan("realMode").getGoon() ) break;
	    		}
	    		temporaryStr = " \"Sonar on robot starts working.\" ";
	    		println( temporaryStr );  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=init WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean simulateMode() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "simulateMode";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = " \"Simulate mode\" ";
	    		println( temporaryStr );  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=simulateMode WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean realMode() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "realMode";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = " \"Working with physic sonar\" ";
	    		println( temporaryStr );  
	    		parg = "actorOp(startRobotSonarC)";
	    		aar = solveGoalReactive(parg,3600000,"","");
	    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
	    		if( aar.getInterrupted() ){
	    			curPlanInExec   = "realMode";
	    			if( ! aar.getGoon() ) break;
	    		} 			
	    		if( ! planUtils.switchToPlan("working").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=realMode WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean working() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "working";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?obstacle(X)" )) != null ){
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "obstacle(X)","obstacle(X)", guardVars ).toString();
	    		emit( "obstacle", temporaryStr );
	    		}
	    		parg = "actorOp(getDistanceFromSonar)";
	    		aar = solveGoalReactive(parg,3600000,"","");
	    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
	    		if( aar.getInterrupted() ){
	    			curPlanInExec   = "working";
	    			if( ! aar.getGoon() ) break;
	    		} 			
	    		//delay
	    		aar = delayReactive(500,"" , "");
	    		if( aar.getInterrupted() ) curPlanInExec   = "working";
	    		if( ! aar.getGoon() ) break;
	    		if( planUtils.repeatPlan(0).getGoon() ) continue;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=working WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean prologFailure() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "prologFailure";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = " \"Prolog goal fails\" ";
	    		println( temporaryStr );  
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=prologFailure WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    protected void initSensorSystem(){
	    	//doing nothing in a QActor
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
	
