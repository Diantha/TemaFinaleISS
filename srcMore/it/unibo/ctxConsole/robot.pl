%====================================================================================
% Context ctxConsole  SYSTEM-configuration: file it.unibo.ctxConsole.robot.pl 
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8021" ).  		 
context(ctxconsole, "localhost",  "TCP", "8010" ).  		 
%%% -------------------------------------------
qactor( robotsonar , ctxrobot, "it.unibo.robotsonar.MsgHandle_Robotsonar"   ). %%store msgs 
qactor( robotsonar_ctrl , ctxrobot, "it.unibo.robotsonar.Robotsonar"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxconsole,"it.unibo.ctxConsole.Evh","obstacle,alarm").  
eventhandler(evhinput,ctxconsole,"it.unibo.ctxConsole.Evhinput","usercmd,sonarArea").  
%%% -------------------------------------------
qactor( robot , ctxrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxrobot, "it.unibo.robot.Robot" ). 

