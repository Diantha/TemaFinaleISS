%====================================================================================
% Context ctxConsole  SYSTEM-configuration: file it.unibo.ctxConsole.sonarSensorEmitter.pl 
%====================================================================================
context(ctxsensoremitter, "localhost",  "TCP", "8045" ).  		 
context(ctxconsole, "localhost",  "TCP", "8010" ).  		 
%%% -------------------------------------------
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

