void updateFearLevel()
{/*ALCODESTART::1762151469016*/
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

