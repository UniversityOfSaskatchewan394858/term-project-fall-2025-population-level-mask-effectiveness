/**
 * InfectionRecord
 */	
public class InfectionRecord {
	double time;
	
	int susceptibleID;
	boolean susceptibleMasked;
	
	int infectiveID;
	boolean infectiveMasked;

    /**
     * Default constructor
     */
    public InfectionRecord(double t, Person s, Person i) {    	
    	time = t;
    	susceptibleID = s.ID;
    	infectiveID = i.ID;
    	
    	susceptibleMasked = s.isMasked();
    	infectiveMasked = i.isMasked();
    }

	@Override
	public String toString() {
		return time + "," + 
			   susceptibleID + "," + susceptibleMasked + "," +
			   infectiveID + "," + infectiveMasked;
	}
}