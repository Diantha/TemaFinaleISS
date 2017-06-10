%====================================================================================
% Context ctxSensorEmitter  SYSTEM-configuration: file it.unibo.ctxSensorEmitter.sonarSensorEmitter.pl 
%====================================================================================
context(ctxradar, "localhost",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "localhost",  "TCP", "8133" ).  		 
%%% -------------------------------------------
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

