%====================================================================================
% Context ctxRadar  SYSTEM-configuration: file it.unibo.ctxRadar.robot.pl 
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8070" ).  		 
context(ctxradar, "localhost",  "TCP", "8033" ).  		 
%%% -------------------------------------------
qactor( robotsonar , ctxrobot, "it.unibo.robotsonar.MsgHandle_Robotsonar"   ). %%store msgs 
qactor( robotsonar_ctrl , ctxrobot, "it.unibo.robotsonar.Robotsonar"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxradar,"it.unibo.ctxRadar.Evh","obstacle,alarm").  
eventhandler(evhinput,ctxradar,"it.unibo.ctxRadar.Evhinput","usercmd,sonarArea").  
%%% -------------------------------------------
qactor( robot , ctxrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxrobot, "it.unibo.robot.Robot" ). 

