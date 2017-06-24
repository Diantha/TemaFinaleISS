%====================================================================================
% Context ctxSensorEmitter  SYSTEM-configuration: file it.unibo.ctxSensorEmitter.console.pl 
%====================================================================================
context(ctxconsole, "localhost",  "TCP", "8010" ).  		 
context(ctxsensoremitter, "localhost",  "TCP", "8045" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxconsole, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxconsole, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( controller , ctxconsole, "it.unibo.controller.MsgHandle_Controller"   ). %%store msgs 
qactor( controller_ctrl , ctxconsole, "it.unibo.controller.Controller"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxsensoremitter,"it.unibo.ctxSensorEmitter.Evh","sonarData").  
%%% -------------------------------------------

