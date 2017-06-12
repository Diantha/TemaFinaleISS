/* Generated by AN DISI Unibo */ 
package it.unibo.ctxRadar;
import it.unibo.qactors.QActorContext;
import java.io.InputStream;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
 
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IIntent;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedSysKb;
<<<<<<< HEAD
public class MainCtxRadar  {
  
//MAIN
public static QActorContext initTheContext() throws Exception{
	IOutputEnvView outEnvView = SituatedSysKb.standardOutEnvView;
	it.unibo.is.interfaces.IBasicEnvAwt env=new it.unibo.baseEnv.basicFrame.EnvFrame( 
		"Env_ctxRadar",java.awt.Color.yellow , java.awt.Color.black );
	env.init();
	outEnvView = env.getOutputEnvView();
	String webDir = "./srcMore/it/unibo/ctxRadar";
	return QActorContext.initQActorSystem(
		"ctxradar", "./srcMore/it/unibo/ctxRadar/radargui.pl", 
		"./srcMore/it/unibo/ctxRadar/sysRules.pl", outEnvView,webDir,false);
}
public static void main(String[] args) throws Exception{
	initTheContext();
} 	
=======

public class MainCtxRadar   {
private IBasicEnvAwt env; 
private it.unibo.qactor.robot.RobotActor robot; 
 
 	
/*
* ----------------------------------------------
* MAIN
* ----------------------------------------------
*/
 
	public static void main(String[] args) throws Exception{
			IOutputEnvView outEnvView = SituatedSysKb.standardOutEnvView;
			it.unibo.qactors.QActorUtils.setRobotBase("robot0" );  
		    String webDir = "./srcMore/it/unibo/ctxRadar";
			QActorContext.initQActorSystem(
				"ctxradar", "./srcMore/it/unibo/ctxRadar/robot.pl", 
				"./srcMore/it/unibo/ctxRadar/sysRules.pl", outEnvView,webDir, false);
 	}
 	
>>>>>>> branch 'master' of https://github.com/Diantha/TemaFinaleISS.git
}
