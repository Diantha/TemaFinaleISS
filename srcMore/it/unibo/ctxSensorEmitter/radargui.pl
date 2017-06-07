%====================================================================================
% Context ctxSensorEmitter standalone= SYSTEM-configuration: file it.unibo.ctxSensorEmitter.radargui.pl 
%====================================================================================
context(ctxsensoremitter, "192.168.1.103",  "TCP", "8133" ).  		 
%%% -------------------------------------------
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
