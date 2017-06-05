/* Generated by AN DISI Unibo */ 
package it.unibo.sensorsonar;
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
import it.unibo.baseEnv.basicFrame.EnvFrame;
import alice.tuprolog.SolveInfo;
import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IIntent;


//REGENERATE AKKA: QActor instead QActorPlanned
public abstract class AbstractSensorsonar extends QActor implements IActivity{ 
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
				EnvFrame env = new EnvFrame( "Env_sensorsonar", java.awt.Color.cyan  , java.awt.Color.black );
				env.init();
				env.setSize(800,400);
				IOutputEnvView newOutEnvView = ((EnvFrame) env).getOutputEnvView();
				return newOutEnvView;
			}
	
	
		public AbstractSensorsonar(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/sensorsonar/WorldTheory.pl",
			setTheEnv( outEnvView )  , "init");		
			addInputPanel(80);
			addCmdPanels();	
			this.planFilePath = "./srcMore/it/unibo/sensorsonar/plans.txt";
			//Plan interpretation is done in Prolog
			//if(planFilePath != null) planUtils.buildPlanTable(planFilePath);
	 	}
	protected void addInputPanel(int size){
		((EnvFrame) env).addInputPanel(size);			
	}
	protected void addCmdPanels(){
		((EnvFrame) env).addCmdPanel("input", new String[]{"INPUT"}, this);
		((EnvFrame) env).addCmdPanel("alarm", new String[]{"FIRE"}, this);
		((EnvFrame) env).addCmdPanel("help",  new String[]{"HELP"}, this);				
	}
		@Override
		protected void doJob() throws Exception {
			String name  = getName().replace("_ctrl", "");
			mysupport = (IMsgQueue) QActorUtils.getQActor( name );
	 		initSensorSystem();
			boolean res = init();
			//println(getName() + " doJob " + res );
		} 
		/* 
		* ------------------------------------------------------------
		* PLANS
		* ------------------------------------------------------------
		*/
	    public boolean init() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "init";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"sensorsonar STARTS\"";
	    		println( temporaryStr );  
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?onRaspberry" )) != null ){
	    		parg = "actorOp(startSonarC)";
	    		parg = QActorUtils.substituteVars(guardVars,parg);
	    		//aar = solveGoalReactive(parg,3600000,"","");
	    		//genCheckAar(m.name)»
	    		QActorUtils.solveGoal(parg,pengine );
	    		}
	    		parg = "setmyposition";
	    		//tout=1 day (24 h)
	    		//aar = solveGoalReactive(parg,86400000,"","");
	    		//genCheckAar(m.name)»		
	    		QActorUtils.solveGoal(parg,pengine );
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?position(POS)" )) != null ){
	    		temporaryStr = "position(POS)";
	    		temporaryStr = QActorUtils.substituteVars(guardVars,temporaryStr);
	    		println( temporaryStr );  
	    		}
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?numSonars(N)" )) != null ){
	    		temporaryStr = "numSonars(N)";
	    		temporaryStr = QActorUtils.substituteVars(guardVars,temporaryStr);
	    		println( temporaryStr );  
	    		}
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?numSonars(N)" )) != null ){
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "numOfSonar(N)","numOfSonar(N)", guardVars ).toString();
	    		emit( "numOfSonar", temporaryStr );
	    		}
	    		if( ! planUtils.switchToPlan("workSimulate").getGoon() ) break;
	    		temporaryStr = "\"sensorsonar workReal\"";
	    		println( temporaryStr );  
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?onRaspberry" )) != null ){
	    		if( ! planUtils.switchToPlan("workReal").getGoon() ) break;
	    		}
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=init WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean workSimulate() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "workSimulate";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?p(DIST,SID)" )) != null ){
	    		temporaryStr = "p(DIST,SID)";
	    		temporaryStr = QActorUtils.substituteVars(guardVars,temporaryStr);
	    		println( temporaryStr );  
	    		}
	    		else{ println( "bye" );
	    		returnValue = continueWork;
	    		//QActorContext.terminateQActorSystem(this);
	    		break;
	    		}if( (guardVars = QActorUtils.evalTheGuard(this, " ??p(DIST,SID)" )) != null ){
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "p(Distance,Angle)","p(DIST,SID)", guardVars ).toString();
	    		emit( "sonar", temporaryStr );
	    		}
	    		//delay
	    		aar = delayReactive(500,"" , "");
	    		if( aar.getInterrupted() ) curPlanInExec   = "workSimulate";
	    		if( ! aar.getGoon() ) break;
	    		if( planUtils.repeatPlan(9).getGoon() ) continue;
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=workSimulate WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean workReal() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "workReal";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		parg = "actorOp(getDistanceFromSonar)";
	    		//aar = solveGoalReactive(parg,3600000,"","");
	    		//genCheckAar(m.name)»
	    		QActorUtils.solveGoal(parg,pengine );
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?obstacledata(VAL)" )) != null ){
	    		temporaryStr = "uuuuu(VAL)";
	    		temporaryStr = QActorUtils.substituteVars(guardVars,temporaryStr);
	    		println( temporaryStr );  
	    		}
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?obstacledata(VAL)" )) != null ){
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "p(Distance,Angle)","VAL", guardVars ).toString();
	    		emit( "sonar", temporaryStr );
	    		}
	    		if( planUtils.repeatPlan(0).getGoon() ) continue;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=workReal WARNING:" + e.getMessage() );
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
		/* 
		* ------------------------------------------------------------
		* IACTIVITY (aactor with GUI)
		* ------------------------------------------------------------
		*/
		private String[] actions = new String[]{
		    	"println( STRING | TERM )", 
		    	"play('./audio/music_interlude20.wav'),20000,'alarm,obstacle', 'handleAlarm,handleObstacle'",
		"emit(EVID,EVCONTENT)  ",
		"move(MOVE,DURATION,ANGLE)  with MOVE=mf|mb|ml|mr|ms",
		"forward( DEST, MSGID, MSGCONTENTTERM)"
		    };
		    protected void doHelp(){
				println("  GOAL ");
				println("[ GUARD ], ACTION  ");
				println("[ GUARD ], ACTION, DURATION ");
				println("[ GUARD ], ACTION, DURATION, ENDEVENT");
				println("[ GUARD ], ACTION, DURATION, EVENTS, PLANS");
				println("Actions:");
				for( int i=0; i<actions.length; i++){
					println(" " + actions[i] );
				}
		    }
		@Override
		public void execAction(String cmd) {
			if( cmd.equals("HELP") ){
				doHelp();
				return;
			}
			if( cmd.equals("FIRE") ){
				emit("alarm", "alarm(fire)");
				return;
			}
			String input = env.readln();
			//input = "\""+input+"\"";
			input = it.unibo.qactors.web.GuiUiKb.buildCorrectPrologString(input);
			//println("input=" + input);
			try {
				Term.createTerm(input);
	 			String eventMsg=it.unibo.qactors.web.QActorHttpServer.inputToEventMsg(input);
				//println("QActor eventMsg " + eventMsg);
				emit("local_"+it.unibo.qactors.web.GuiUiKb.inputCmd, eventMsg);
	  		} catch (Exception e) {
		 		println("QActor input error " + e.getMessage());
			}
		}
	 	
		@Override
		public void execAction() {}
		@Override
		public void execAction(IIntent input) {}
		@Override
		public String execActionWithAnswer(String cmd) {return null;}
	  }
	
