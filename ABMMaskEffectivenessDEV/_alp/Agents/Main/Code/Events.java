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

void outputWriter()
{/*ALCODESTART::1764380390480*/
// Writing dailyTimeseries.csv
dailyTimeseriesOutput.println("Day," + 
	"Count of Incident Infections," + 
	"Count of Potential Exposures Prevented by Masking," +
	"Susceptible," +
	"Exposed," +
	"Infective," + 
	"Recovered," +
	"Vaccinated," +
	"VaccinatedRecovered," +
	"Dead");
for (int day = 0; day <= 365; day++) {
	int incidentInfections = incidentInfectionCount.getOrDefault(day, 0);
	int preventedExposures = maskingPreventedPotentialExposureCount.getOrDefault(day, 0);
	int[] stateCounts = dailyStateCounts.get(day);
	
	// Slicing string and getting rid of [ and ]"
	String stateCountsStr = Arrays.toString(stateCounts);
	stateCountsStr = stateCountsStr.substring(1, stateCountsStr.length()-1).replaceAll("\\s+", "");
	dailyTimeseriesOutput.println(day + "," +
		incidentInfections + "," +
		preventedExposures + "," +
		stateCountsStr
	);
}

// Writing infectionChains.csv
infectionChainsOutput.println("Time,Susceptible ID,Susceptible Masking Status,Infective ID,Infective Masking Status");
for (String chain : infectionChains) {
	infectionChainsOutput.println(chain);
}

// Closing each file
dailyTimeseriesOutput.close();
infectionChainsOutput.close();

traceln("Model has finished running. Output files can be found in /output/");
/*ALCODEEND*/}

void updateDailyStateCounts()
{/*ALCODESTART::1764382390757*/
int day = (int) Math.floor(time());
int[] stateCounts = {People.Susceptible(), People.Exposed(), People.Infective(), People.Recovered(), People.Vaccinated(), People.VaccinatedRecovered(), deadAgentCount};
dailyStateCounts.put(day, stateCounts);
/*ALCODEEND*/}

