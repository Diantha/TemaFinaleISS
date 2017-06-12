%====================================================================================
% Context ctxRadar  SYSTEM-configuration: file it.unibo.ctxRadar.sonarSensorEmitter.pl 
%====================================================================================
context(ctxradar, "localhost",  "TCP", "8038" ).  		 
context(ctxsensoremitter, "localhost",  "TCP", "8139" ).  		 
%%% -------------------------------------------
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

