boolean infectionTransmitted(Person infector)
{/*ALCODESTART::1762022249895*/
double baseInfectionRate = infectionRate;
String immunityState;

// Checks if agent is masked
if (inState(Masked)) {
	infectionRate *= (1.0+main.maskEffectiveness);
}

// Checks if agent has any form of immunity
if (inState(VaccinatedRecovered)) { 
	infectionRate = infectionRate * (naturalImmunity * vaccineImmunity);
	immunityState = new String("VaccinatedRecovered");
}
else if (inState(Vaccinated)) {
	infectionRate *= (vaccineImmunity);
	immunityState = new String("Vaccinated");
}
else if (inState(Recovered)) {
	infectionRate *= (naturalImmunity);
	immunityState = new String("Recovered");
}

// Checks if infector is masked
if (infector.inState(Person.Masked)) {
	infectionRate *= (main.maskEffectiveness);
}


if (randomTrue(infectionRate)) {
	send("InfectionTransmitted", this);
	infectionRate = baseInfectionRate;
	return true;
}
else {
	// Updates charts in Main if infection has been prevented
	if (inState(Masked) || infector.inState(Person.Masked)) {
		main.maskingPreventedPotentialExposureCount += 1;
	}
	
	if (inState(Vaccinated) || inState(VaccinatedRecovered)) {
		main.vaccinePreventedPotentialExposureCount += 1;
	}
	return false;
}
/*ALCODEEND*/}

double getVaccination()
{/*ALCODESTART::1762029287107*/
// If vaccinations are available at current day
if (main.vaccineMandateInEffect || (time() >= main.vaccineAvailabilityDay)) {
	// If vaccine mandate is in effect, increases vaccination rate
	if (time() >= main.vaccineMandateStartDay) {
		if (vaccineMandateAffected == false) {
			vaccinationRate += main.vaccineMandateInducedAdoptionRateIncrease;
			vaccineMandateAffected = true;
			main.vaccineMandateInEffect = true;
		}
	}

	if (main.availableVaccinations > 0) {
		//if (uniform(0, 1.0) <= vaccinationRate) {
		if (randomTrue(vaccinationRate)) {
			main.availableVaccinations -= 1;
			send("Vaccinate", this);
		}
	}
}
/*ALCODEEND*/}

boolean isSusceptible()
{/*ALCODESTART::1762072407997*/
return this.inState(Susceptible);
/*ALCODEEND*/}

boolean isExposed()
{/*ALCODESTART::1762072577219*/
return this.inState(Exposed);
/*ALCODEEND*/}

boolean isInfective()
{/*ALCODESTART::1762072590127*/
return this.inState(Infective);
/*ALCODEEND*/}

boolean isRecovered()
{/*ALCODESTART::1762072607157*/
return this.inState(Recovered);
/*ALCODEEND*/}

boolean isDead()
{/*ALCODESTART::1762072626723*/
return this.inState(Dead);
/*ALCODEEND*/}

boolean isVaccinatedRecovered()
{/*ALCODESTART::1762072638988*/
return this.inState(VaccinatedRecovered);
/*ALCODEEND*/}

boolean isVaccinated()
{/*ALCODESTART::1762072666650*/
return this.inState(Vaccinated);
/*ALCODEEND*/}

double getMaskEffectiveness()
{/*ALCODESTART::1762074225605*/
if(this.inState(Masked))
	return main.maskEffectiveness;
else
	return 0.0;
/*ALCODEEND*/}

boolean isUnmasked()
{/*ALCODESTART::1762077271194*/
return this.inState(Unmasked);
/*ALCODEEND*/}

boolean isMasked()
{/*ALCODESTART::1762077323326*/
return this.inState(Masked);
/*ALCODEEND*/}

double adoptMask()
{/*ALCODESTART::1762215634275*/
if (inState(Dead) == false) {
	// If mask mandate is in effect, increases mask adotpion rate
	if (main.maskMandateInEffect || (time() >= main.maskMandateStartDay)) {
		if (maskMandateAffected == false) {
			maskAdoptionRate += main.maskMandateInducedAdoptionRateIncrease;
			maskMandateAffected = true;
			main.maskMandateInEffect = true;
		}
	}
	
	if (randomTrue(maskAdoptionRate)) {
		send("Mask", this);
	}
}
/*ALCODEEND*/}

