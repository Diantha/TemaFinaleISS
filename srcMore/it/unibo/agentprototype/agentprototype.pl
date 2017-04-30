%====================================================================================
% Context agentprototype  SYSTEM-configuration: file it.unibo.agentprototype.agentprototype.pl 
%====================================================================================
context(agentprototype, "localhost",  "TCP", "8060" ).  		 
%%% -------------------------------------------
qactor( agentprototype , agentprototype, "it.unibo.agentprototype.MsgHandle_Agentprototype"   ). %%store msgs 
qactor( agentprototype_ctrl , agentprototype, "it.unibo.agentprototype.Agentprototype"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

