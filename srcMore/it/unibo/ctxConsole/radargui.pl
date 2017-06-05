%====================================================================================
% Context ctxConsole  SYSTEM-configuration: file it.unibo.ctxConsole.radargui.pl 
%====================================================================================
context(ctxconsole, "192.168.251.1",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "192.168.1.103",  "TCP", "8133" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxconsole, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxconsole, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( controller , ctxconsole, "it.unibo.controller.MsgHandle_Controller"   ). %%store msgs 
qactor( controller_ctrl , ctxconsole, "it.unibo.controller.Controller"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

