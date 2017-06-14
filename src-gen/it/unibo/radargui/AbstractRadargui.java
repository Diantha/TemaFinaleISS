/* Generated by AN DISI Unibo */ 
package it.unibo.radargui;
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
public abstract class AbstractRadargui extends QActor implements IActivity{ 
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
				EnvFrame env = new EnvFrame( "Env_radargui", java.awt.Color.green  , java.awt.Color.black );
				env.init();
				env.setSize(800,400);
				IOutputEnvView newOutEnvView = ((EnvFrame) env).getOutputEnvView();
				return newOutEnvView;
			}
	
	
		public AbstractRadargui(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/radargui/WorldTheory.pl",
			setTheEnv( outEnvView )  , "init");		
			addInputPanel(80);
			addCmdPanels();	
			this.planFilePath = "./srcMore/it/unibo/radargui/plans.txt";
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
	    		temporaryStr = " \"RADAR init the GUI ... \" ";
	    		println( temporaryStr );  
	    		parg = "actorOp(activateGui)";
	    		aar = solveGoalReactive(parg,3600000,"","");
	    		//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
	    		if( aar.getInterrupted() ){
	    			curPlanInExec   = "init";
	    			if( ! aar.getGoon() ) break;
	    		} 			
	    		if( ! planUtils.switchToPlan("doWorkMsgs").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=init WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean doWorkMsgs() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "doWorkMsgs";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = " \"RADAR receive\" ";
	    		println( temporaryStr );  
	    		//ReceiveMsg
	    		 		 aar = planUtils.receiveAMsg(mysupport,30000000, "" , "" ); 	//could block
	    				if( aar.getInterrupted() ){
	    					curPlanInExec   = "playTheGame";
	    					if( ! aar.getGoon() ) break;
	    				} 			
	    				//if( ! aar.getGoon() ){
	    					//System.out.println("			WARNING: receiveMsg in " + getName() + " TOUT " + aar.getTimeRemained() + "/" +  30000000);
	    					//addRule("tout(receive,"+getName()+")");
	    				//} 		 
	    				//println(getName() + " received " + aar.getResult() );
	    		printCurrentMessage(false);
	    		//onMsg
	    		if( currentMessage.msgId().equals("polar") ){
	    			String parg = "actorOp(sendDataToGui(DIST,THETA))";
	    			parg =  updateVars( Term.createTerm("p(Distance,SID)"), Term.createTerm("p(DIST,SID)"), 
	    				    		  					Term.createTerm(currentMessage.msgContent()), parg);
	    			if( parg != null ){
	    					aar = solveGoalReactive(parg,3600000,"","");
	    					//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
	    					if( aar.getInterrupted() ){
	    						curPlanInExec   = "doWorkMsgs";
	    						if( ! aar.getGoon() ) break;
	    					} 			
	    			}
	    		}if( planUtils.repeatPlan(0,0).getGoon() ) continue;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=doWorkMsgs WARNING:" + e.getMessage() );
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
	
