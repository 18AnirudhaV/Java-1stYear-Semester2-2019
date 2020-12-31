package assignment.pkg2;

/*
- Anirudha Verma (20128760)
- Term Class: keeps track of each of the concepts defined in ontology
*/

public class Term {
	private String ID; 
	private String altID;
	private String concept;

	// Get Method for: ID
	public String getID() 
        {
		return ID;
	}

	// Set Method for: ID
	public void setID(String ID) 
        {
		this.ID = ID;
	}

	// Get Method for: altID
	public String getAltID() 
        {
		return altID;
	}

	// Set Method for: altID
	public void setAltID(String altID) 
        {
		this.altID = altID;
	}

	// Get Method for: concept
	public String getConcept() 
        {
		return concept;
	}

	// Set Method for: concept
	public void setConcept(String concept) {
		this.concept = concept;
	}

	// method that converts all the data into a single string called: Term[]
	@Override
	public String toString() {
		return "Term [id=" + ID + ", altID=" + altID + ", concept=" + concept + "]";
	}
        
        //constructor
	public Term() 
        {
	}
	
}