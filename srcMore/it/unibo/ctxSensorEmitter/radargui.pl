%====================================================================================
% Context ctxSensorEmitter  SYSTEM-configuration: file it.unibo.ctxSensorEmitter.radargui.pl 
%====================================================================================
context(ctxradar, "192.168.251.1",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "192.168.1.103",  "TCP", "8133" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxradar, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxradar, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( qacmdexecutor , ctxradar, "it.unibo.qacmdexecutor.MsgHandle_Qacmdexecutor"   ). %%store msgs 
qactor( qacmdexecutor_ctrl , ctxradar, "it.unibo.qacmdexecutor.Qacmdexecutor"   ). %%control-driven 
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

