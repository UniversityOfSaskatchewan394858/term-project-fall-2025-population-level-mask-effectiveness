void updateNetworkEvent()
{/*ALCODESTART::1762017198541*/
applyNetwork();
/*ALCODEEND*/}

void initialInfectionEvent()
{/*ALCODESTART::1762017198552*/
sendToRandomAgentInside("InitialInfect");
/*ALCODEEND*/}

void checkInfectionNumber()
{/*ALCODESTART::1762121326766*/
traceln("Infected surpassed susceptibles on day: " + time());
/*ALCODEEND*/}

