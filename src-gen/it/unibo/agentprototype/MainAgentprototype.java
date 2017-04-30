/* Generated by AN DISI Unibo */ 
package it.unibo.agentprototype;
import it.unibo.qactors.QActorContext;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedSysKb;
public class MainAgentprototype  {
  
//MAIN
public static QActorContext initTheContext() throws Exception{
	IOutputEnvView outEnvView = SituatedSysKb.standardOutEnvView;
	it.unibo.is.interfaces.IBasicEnvAwt env=new it.unibo.baseEnv.basicFrame.EnvFrame( 
		"Env_agentprototype",java.awt.Color.yellow , java.awt.Color.black );
	env.init();
	outEnvView = env.getOutputEnvView();
	String webDir = null;
	return QActorContext.initQActorSystem(
		"agentprototype", "./srcMore/it/unibo/agentprototype/agentprototype.pl", 
		"./srcMore/it/unibo/agentprototype/sysRules.pl", outEnvView,webDir,false);
}
public static void main(String[] args) throws Exception{
	initTheContext();
} 	
}