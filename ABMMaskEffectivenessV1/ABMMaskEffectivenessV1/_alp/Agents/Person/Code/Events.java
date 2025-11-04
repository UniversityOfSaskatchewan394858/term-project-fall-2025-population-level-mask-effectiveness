void updateFearLevel()
{/*ALCODESTART::1762151469016*/
//System.out.println("Pop:" + main.startingPopulation);
//System.out.println("Infective:" + main.People.Infective() + " | Dead: " + main.People.Dead());
//System.out.println("Infective%: " + ((double) main.People.Infective() / main.startingPopulation));
//System.out.println("Dead: " + ((double) main.People.Dead() / main.startingPopulation));

fearLevel = ((double) main.People.Infective() / (main.startingPopulation) + ((double) main.People.Dead() / (main.startingPopulation)));
/*ALCODEEND*/}

void updateVaccinationRate()
{/*ALCODESTART::1762152563464*/
vaccinationRate = main.initialVaccinationRate - main.initialVaccineHesitancy + ((fearLevel * 100)*main.fearInducedVaccinationAcceptanceIncreaseRate);
if (vaccineMandateAffected) {
	vaccinationRate += main.vaccineMandateInducedAdoptionRateIncrease;
}
/*ALCODEEND*/}

void updateMaskAdoptionRate()
{/*ALCODESTART::1762217367295*/
maskAdoptionRate = main.initialMaskAdoptionRate + ((fearLevel * 100) * main.fearInducedMaskAdoptionIncreaseRate);;
maskAdoptionRate += ((main.maskPopularity * 100) * main.popularityInducedMaskAdoptionIncreaseRate);

if (maskMandateAffected) {
	maskAdoptionRate += main.vaccineMandateInducedAdoptionRateIncrease;
}
/*ALCODEEND*/}

