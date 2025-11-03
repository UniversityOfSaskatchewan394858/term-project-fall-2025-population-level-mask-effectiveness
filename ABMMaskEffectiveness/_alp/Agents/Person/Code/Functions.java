boolean infectionTransmitted(Person infector)
{/*ALCODESTART::1762022249895*/
if (inState(Masked)) {
	infectionRate *= (1.0+main.maskEffectiveness);
}

if (inState(VaccinatedRecovered)) { 
	infectionRate = infectionRate * (naturalImmunity * vaccineImmunity);
}
else if (inState(Vaccinated)) {
	infectionRate *= (vaccineImmunity);
}
else if (inState(Recovered)) {
	infectionRate *= (naturalImmunity);
}

if (infector.inState(Person.Masked)) {
	infectionRate *= (main.maskEffectiveness);
}


if (uniform(0.0, 1.0) >= infectionRate) {
	send("InfectionTransmitted", this);
	infectionRate = 1.0;
	return true;
}
return false;
/*ALCODEEND*/}

double getVaccination()
{/*ALCODESTART::1762029287107*/
if (getDayOfYear(date()) >= (main.modelStartDay+main.vaccineAvailabilityDay)) {
	if (getDayOfYear(date()) >= main.vaccineMandateStartDay) {
		if (vaccineMandateAffected == false) {
			vaccinationRate += main.vaccineMandateInducedAdoptionRateIncrease;
			vaccineMandateAffected = true;
		}
	}

	if (main.availableVaccinations > 0) {
		if (uniform(0, 1.0) <= vaccinationRate) {
			//System.out.println("vaccinate");
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

