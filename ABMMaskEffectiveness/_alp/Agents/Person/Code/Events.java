void modifyVaccinationRate()
{/*ALCODESTART::1762027647314*/
if (perceivedFearLevel != fearLevel) {
	vaccinationRate = vaccinationRate + main.fearInducedVaccinationAcceptanceIncreaseRate;
	perceivedFearLevel = fearLevel;
}
/*ALCODEEND*/}

