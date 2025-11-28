void updateFearLevel()
{/*ALCODESTART::1762151469016*/
// Get list of neighbors
ArrayList<Person> neighbors = getSurroundingNeighbors();

// Count how many neighbors are infective
int nearbyInfective = 0;
for (Person p : neighbors) {
    if (p.isInfective()) {
        nearbyInfective++;
    }
}

// Get the number of previous and current neighbors that have died
for (int pIdx: neighborHistory) {
	if (main.deadAgentIDs.contains(pIdx) && !deadNeighbors.contains(pIdx)) {
		deadNeighbors.add(pIdx);	
	}
}



// Get the total number of neighbors
int neighborhoodSize = neighbors.size();

// Calculate the fear level for this agent
if (neighborhoodSize > 0) {
    fearLevel = (((double) nearbyInfective / neighborhoodSize) + (double) deadNeighbors.size() / neighborHistory.size());
} else {
	// If no neighbors, only add the number of previous neighbors that have died
    fearLevel = (double) deadNeighbors.size() / neighborHistory.size();
}
/*ALCODEEND*/}

