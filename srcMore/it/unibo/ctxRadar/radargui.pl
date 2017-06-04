%====================================================================================
% Context ctxRadar  SYSTEM-configuration: file it.unibo.ctxRadar.radargui.pl 
%====================================================================================
context(ctxradar, "192.168.251.1",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "192.168.1.103",  "TCP", "8133" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxradar, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxradar, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( controller , ctxradar, "it.unibo.controller.MsgHandle_Controller"   ). %%store msgs 
qactor( controller_ctrl , ctxradar, "it.unibo.controller.Controller"   ). %%control-driven 
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

