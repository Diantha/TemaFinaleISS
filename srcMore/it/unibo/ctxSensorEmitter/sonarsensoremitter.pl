%====================================================================================
% Context ctxSensorEmitter  SYSTEM-configuration: file it.unibo.ctxSensorEmitter.sonarSensorEmitter.pl 
%====================================================================================
<<<<<<< HEAD
context(ctxsensoremitter, "localhost",  "TCP", "8045" ).  		 
context(ctxradar, "localhost",  "TCP", "8010" ).  		 
=======
context(ctxradar, "localhost",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "localhost",  "TCP", "8133" ).  		 
>>>>>>> branch 'master' of https://github.com/Diantha/TemaFinaleISS.git
%%% -------------------------------------------
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

