onRaspberry.
simulate.
//physicSonar.
mysonar( s1, coloryellow, "192.168.251.118" ).	 
//mysonar( s2, colorred, "192.168.251.121" ).		 
//mysonar( s3, colorgreen, "192.168.251.120" ).	 		
/* SIMULATION DATA */	   
p(80,1).p(70,1).p(60,1).p(50,3).p(40,3).p(30,3).p(20,2).p(40,2).p(60,2).p(80,2).
/* RULES */
sonar(s1,1).
sonar(s2,2).
sonar(s3,3).
numOfSonars(N) :- bagof(sonar(S,P), sonar(S,P), SonarList), length(SonarList,N).	
setmyposition  :-  
	numOfSonars(N),
	assert( numSonars( N ) ),
	mysonar( SONAR, _, _ ), sonar(SONAR,SID), 
	assert( position( SID ) ).		
obstacledata( p(D,SID) ) :- 
	//numSonars( N ),
	actorOpDone( _,d(D) ), 	//set by actorOp getDistanceFromSonar (1)
	position(SID).