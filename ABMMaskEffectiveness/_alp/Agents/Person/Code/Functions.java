boolean infectionTransmitted(Person infector)
{/*ALCODESTART::1762022249895*/
if (inState(Masked)) {
	infectionProbability *= (1.0+main.maskEffectiveness);
}

if (inState(VaccinatedRecovered)) { 
	infectionProbability = infectionProbability * (1.0+(naturalImmunity * (1.0+vaccineImmunity)));
}
else if (inState(Vaccinated)) {
	infectionProbability *= (1.0+vaccineImmunity);
}
else if (inState(Recovered)) {
	infectionProbability *= (1.0+naturalImmunity);
}

if (infector.inState(Person.Masked)) {
	infectionProbability *= (1.0+main.maskEffectiveness);
}


if (uniform((1.0-infectionProbability), 1.0) >= (1.0-infectionProbability)) {
	send("InfectionTransmitted", this);
	infectionProbability = 1.0;
	return true;
}
return false;
/*ALCODEEND*/}

double getVaccination()
{/*ALCODESTART::1762029287107*/
if (main.availableVaccinations > 0) {
	if (uniform(vaccinationRate, 1.0) < vaccinationRate) {
		main.availableVaccinations -= 1;
		send("Vaccinate", this);
	}
}
/*ALCODEEND*/}

