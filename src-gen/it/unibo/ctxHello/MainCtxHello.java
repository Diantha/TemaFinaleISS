/* Generated by AN DISI Unibo */ 
package it.unibo.ctxHello;
import it.unibo.qactors.QActorContext;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedSysKb;
public class MainCtxHello  {
  
//MAIN
public static QActorContext initTheContext() throws Exception{
	IOutputEnvView outEnvView = SituatedSysKb.standardOutEnvView;
	String webDir = null;
	return QActorContext.initQActorSystem(
		"ctxhello", "./srcMore/it/unibo/ctxHello/hellosystem.pl", 
		"./srcMore/it/unibo/ctxHello/sysRules.pl", outEnvView,webDir,false);
}
public static void main(String[] args) throws Exception{
	initTheContext();
} 	
}