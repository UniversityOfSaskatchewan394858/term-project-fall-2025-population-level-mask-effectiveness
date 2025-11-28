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
maskPopularity = (double) People.Masked() / (startingPopulation - deadAgentCount)
/*ALCODEEND*/}

void updateDataSets()
{/*ALCODESTART::1764298439539*/
int day = (int) Math.floor(time());
int prevDay;

// Updates datasets with previous day's values in the hashmaps
if (day > 0) {
	prevDay = day - 1;
	
	if (incidentInfectionCount.containsKey(prevDay)) {
		incidentInfectionsDS.add(prevDay, 
		incidentInfectionCount.get(prevDay));
	}
	
	if (maskingPreventedPotentialExposureCount.containsKey(prevDay)) {
		maskingPreventedPotentialExposureCountDS.add(prevDay, 
		maskingPreventedPotentialExposureCount.get(prevDay));
	}
	
	if (vaccinePreventedPotentialExposureCount.containsKey(prevDay)) {
		vaccinePreventedPotentialExposureCountDS.add(prevDay,
		vaccinePreventedPotentialExposureCount.get(prevDay));
	}
}
/*ALCODEEND*/}

