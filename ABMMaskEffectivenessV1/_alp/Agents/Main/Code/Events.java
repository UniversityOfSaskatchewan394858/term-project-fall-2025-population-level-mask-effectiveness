void updateNetworkEvent()
{/*ALCODESTART::1762017198541*/
applyNetwork();
/*ALCODEEND*/}

void initialInfectionEvent()
{/*ALCODESTART::1762017198552*/
for (int i=0; i < initialInfectiveCount; i++) {
	sendToRandomAgentInside("InitialInfect");
}
/*ALCODEEND*/}

void checkInfectionNumber()
{/*ALCODESTART::1762121326766*/
traceln("Infected surpassed susceptibles on day: " + time());
/*ALCODEEND*/}

void updateMaskPopularity()
{/*ALCODESTART::1762217525125*/
maskPopularity = (double) People.Masked() / (startingPopulation - People.Dead())
/*ALCODEEND*/}

