%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.robot.pl 
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
%%% -------------------------------------------
eventhandler(evh,ctxrobot,"it.unibo.ctxRobot.Evh","local_inputcmd,alarm,sensordata,androidsensor,click").  
%%% -------------------------------------------
qactor( avatar , ctxrobot, "it.unibo.avatar.MsgHandle_Avatar" ). 
qactor( avatar_ctrl , ctxrobot, "it.unibo.avatar.Avatar" ). 

