void updateFearLevel()
{/*ALCODESTART::1762151469016*/
//System.out.println("Pop:" + main.startingPopulation);
//System.out.println("Infective:" + main.People.Infective() + " | Dead: " + main.People.Dead());
//System.out.println("Infective%: " + ((double) main.People.Infective() / main.startingPopulation));
//System.out.println("Dead: " + ((double) main.People.Dead() / main.startingPopulation));

//fearLevel = ((double) main.People.Infective() / (main.startingPopulation) + ((double) main.People.Dead() / (main.startingPopulation)));

double fearRadius = main.spaceSideLength * 0.1; // radius of 10% of the box

// Get list of neighbors
List<Person> neighbors = getNeighbors(fearRadius);

// Count how many neighbors are infective
int nearbyInfective = 0;
for (Person p : neighbors) {
    if (p.isInfective()) {
        nearbyInfective++;
    }
}

// Count how many neighbors are dead
int nearbyDead = 0;
for (Person p : neighbors) {
    if (p.isDead()) {
        nearbyDead++;
    }
}

// Get the total number of neighbors
int neighborhoodSize = neighbors.size();

// Calculate the fear level for this agent
if (neighborhoodSize > 0) {
    fearLevel = (((double) nearbyInfective / neighborhoodSize) + ((double) nearbyDead / neighborhoodSize));
} else {
    fearLevel = 0; // No neighbors no fear
}
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

