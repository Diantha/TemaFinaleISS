%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.robot.pl 
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8071" ).  		 
context(ctxradar, "localhost",  "TCP", "8032" ).  		 
%%% -------------------------------------------
qactor( robotsonar , ctxrobot, "it.unibo.robotsonar.MsgHandle_Robotsonar"   ). %%store msgs 
qactor( robotsonar_ctrl , ctxrobot, "it.unibo.robotsonar.Robotsonar"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------
qactor( robot , ctxrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxrobot, "it.unibo.robot.Robot" ). 

