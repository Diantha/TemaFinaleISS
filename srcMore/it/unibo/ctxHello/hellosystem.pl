%====================================================================================
% Context ctxHello  SYSTEM-configuration: file it.unibo.ctxHello.helloSystem.pl 
%====================================================================================
context(ctxhello, "localhost",  "TCP", "8079" ).  		 
%%% -------------------------------------------
qactor( qahello , ctxhello, "it.unibo.qahello.MsgHandle_Qahello"   ). %%store msgs 
qactor( qahello_ctrl , ctxhello, "it.unibo.qahello.Qahello"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

