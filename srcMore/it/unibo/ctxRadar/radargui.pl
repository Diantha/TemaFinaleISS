%====================================================================================
% Context ctxRadar  SYSTEM-configuration: file it.unibo.ctxRadar.radargui.pl 
%====================================================================================
context(ctxradar, "192.168.251.1",  "TCP", "8033" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxradar, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxradar, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( qacmdexecutor , ctxradar, "it.unibo.qacmdexecutor.MsgHandle_Qacmdexecutor"   ). %%store msgs 
qactor( qacmdexecutor_ctrl , ctxradar, "it.unibo.qacmdexecutor.Qacmdexecutor"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxradar,"it.unibo.ctxRadar.Evh","sonar").  
eventhandler(evh2,ctxradar,"it.unibo.ctxRadar.Evh2","alarm,local_inputcmd,inputcmd,endplay").  
%%% -------------------------------------------

