%====================================================================================
% Context ctxSensorEmitter  SYSTEM-configuration: file it.unibo.ctxSensorEmitter.radargui.pl 
%====================================================================================
context(ctxradar, "localhost",  "TCP", "8010" ).  		 
context(ctxsensoremitter, "localhost",  "TCP", "8045" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxradar, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxradar, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( controller , ctxradar, "it.unibo.controller.MsgHandle_Controller"   ). %%store msgs 
qactor( controller_ctrl , ctxradar, "it.unibo.controller.Controller"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxsensoremitter,"it.unibo.ctxSensorEmitter.Evh","sonar").  
%%% -------------------------------------------

