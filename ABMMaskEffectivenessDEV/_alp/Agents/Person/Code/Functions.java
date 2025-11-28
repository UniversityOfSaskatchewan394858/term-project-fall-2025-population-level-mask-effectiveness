boolean infectionTransmitted(Person infector)
{/*ALCODESTART::1762022249895*/
double infectionRate = 1.0;
int day = (int) Math.floor(time());

// Checks if agent is masked
if (inState(Masked)) {
	infectionRate *= (1.0 - main.maskEffectiveness);
}

// Checks if agent has any form of immunity
if (inState(VaccinatedRecovered)) { 
	infectionRate = infectionRate * 
		(1.0 - ((1.0+naturalImmunity) * (1.0+vaccineImmunity)));
}
else if (inState(Vaccinated)) {
	infectionRate *= (1.0 - vaccineImmunity);
}
else if (inState(Recovered)) {
	infectionRate *= (1.0 - naturalImmunity);
}

// Checks if infector is masked
if (infector.inState(Person.Masked)) {
	infectionRate *= (main.maskEffectiveness);
}


if (randomTrue(infectionRate)) {
	send("InfectionTransmitted", this);
	return true;
}
else {
	// Updates charts and hashmaps in Main if infection has been prevented
	if (inState(Masked) || infector.inState(Person.Masked)) {
		main.cumulativeMaskingPreventedPotentialExposureCount += 1;
		main.maskingPreventedPotentialExposureCount.put(day,
		main.maskingPreventedPotentialExposureCount.getOrDefault(day, 0) + 1);
		
	}
	
	if (inState(Vaccinated) || inState(VaccinatedRecovered)) {
		main.cumulativeVaccinePreventedPotentialExposureCount += 1;
		main.vaccinePreventedPotentialExposureCount.put(day,
		main.maskingPreventedPotentialExposureCount.getOrDefault(day, 0) + 1);
	}
	return false;
}
/*ALCODEEND*/}

double considerVaccination()
{/*ALCODESTART::1762029287107*/
// If vaccinations are available at the current day
if (time() >= main.vaccineAvailabilityDay) {
	if (main.availableVaccinations > 0) {
		double vaccinationRate = main.initialVaccinationRate - main.initialVaccineHesitancy; 
		
		
		// Increase with fear level
		vaccinationRate += fearLevel * main.maxFearInducedVaccinationRateIncrease;
	
		// Increase if vaccine mandate is in effect
		if (time() >= main.vaccineMandateStartDay) {
			vaccinationRate += main.vaccineMandateInducedAdoptionRateIncrease;
		}

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

boolean isUnmasked()
{/*ALCODESTART::1762077271194*/
return this.inState(Unmasked);
/*ALCODEEND*/}

boolean isMasked()
{/*ALCODESTART::1762077323326*/
return this.inState(Masked);
/*ALCODEEND*/}

double considerMasking()
{/*ALCODESTART::1762215634275*/
double maskAdoptionRate = main.initialMaskAdoptionRate;

// Increase with fear level
maskAdoptionRate += fearLevel * main.maxFearInducedMaskAdoptionRateIncrease;
// Increase with popularity
maskAdoptionRate += main.maskPopularity * main.maxPopularityInducedMaskAdoptionRateIncrease;

// Increase if mask mandate is in effect
if ((time() >= main.maskMandateStartDay)) {
		maskAdoptionRate += main.maskMandateInducedAdoptionRateIncrease;
}


if (randomTrue(maskAdoptionRate)) {
	send("Mask", this);
}
/*ALCODEEND*/}

boolean isCalm()
{/*ALCODESTART::1763932216147*/
return this.inState(Calm);
/*ALCODEEND*/}

boolean isAware()
{/*ALCODESTART::1763932216149*/
return this.inState(Aware);
/*ALCODEEND*/}

boolean isWorried()
{/*ALCODESTART::1763932216151*/
return this.inState(Worried);
/*ALCODEEND*/}

boolean isAfraid()
{/*ALCODESTART::1763932216153*/
return this.inState(Afraid);
/*ALCODEEND*/}

boolean isPanic()
{/*ALCODESTART::1763932216155*/
return this.inState(Panic);
/*ALCODEEND*/}

double considerUnmasking()
{/*ALCODESTART::1764261026564*/
// This uses randomFalse() instead of randomTrue() to determine
// if the agent should unmask or not

double maskAdoptionRate = main.initialMaskAdoptionRate;

// Increase with fear level
maskAdoptionRate += fearLevel * main.maxFearInducedMaskAdoptionRateIncrease;
// Increase with popularity
maskAdoptionRate += main.maskPopularity * main.maxPopularityInducedMaskAdoptionRateIncrease;

// Increase if mask mandate is in effect
if ((time() >= main.maskMandateStartDay)) {
		maskAdoptionRate += main.maskMandateInducedAdoptionRateIncrease;
}


if (randomFalse(maskAdoptionRate)) {
	send("Unmask", this);
}
/*ALCODEEND*/}

ArrayList<Person> getSurroundingNeighbors()
{/*ALCODESTART::1764289803855*/
ArrayList<Person> neighbors = new ArrayList<Person>();

// Iterate over all agents in the population
for (Person p : main.People) { 
    if (p != this) { // does not include this agent
    	
    	// Computes distance between agents
        double dx = this.getX() - p.getX();
        double dy = this.getY() - p.getY();
        double distance = Math.sqrt(dx*dx + dy*dy);
		
		// If agent is within range add to list
        if (distance <= neighborhoodRadius) {
            neighbors.add(p);
            if (! neighborHistory.contains(p.ID)) {
            	neighborHistory.add(p.ID);
            }
        }
    }
}

return neighbors;
/*ALCODEEND*/}

