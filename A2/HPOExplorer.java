package assignment.pkg2;
import java.io.*;
import java.util.*;

/*
- Anirudha Verma (20128760)
- HPOExplorer Class: Loads the HPO file and sorts through, loads the query file
                     and calculates the query results and finally finds the max path
*/

public class HPOExplorer {
	
	//key = term id; value = term
    
        // A hash map of all terms loaded from the HPO.txt file:
        private Map<String, Term> termHashMap = new HashMap<String, Term>();
        
        // Finds a string with the term contents that contain a specific ID in the parameter
	private String termString(Map<String, Term> terms, String ID) 
        {
		String concept = "";
		
		Term term = terms.get(ID);
		String altID = term.getAltID();
		concept = term.getConcept();
                
		if (altID != null) 
                {
			String altIDTermContent = termString(terms, altID);
			concept = concept + altIDTermContent;
		}
                
		return concept;
	}
	
        
        // Finds the number to a term path of a given ID
	private int termPath(Map<String, Term> terms, String ID, int path) 
        {
		Term term = terms.get(ID);
		String altID = term.getAltID();
                
		if (altID != null) 
                {
			return termPath(terms, altID, path) + 1;
		}
                
                else 
                {
			return path + 1;
		}
	}
        
        // Open and loads the HPO.text file and stores all the term objects into a hash map
	public void loadTerms() throws FileNotFoundException 
        {
            // Throws FileNotFoundException when HPO.text file is not found
            
		File file = new File("HPO.txt");
		
		Scanner sc = new Scanner(file);
		
		Term term = null;
                String ID = null;
		String altID = null;
		String concept = null;
		
		while (sc.hasNextLine()) 
                {
			String nextLine = sc.nextLine();
			if (nextLine.equals("[Term]")) 
                        {
				if (term != null) 
                                {
                                        term.setID(ID);
                                        term.setAltID(altID);
					term.setConcept(concept);
					
					if (!concept.contains("is_obsolete: true")) 
                                        {
						termHashMap.put(ID, term);
					}
				}
                                
				term = new Term();
				ID = null;
				altID = null;
                                concept = "";
                                
			} 
                        
                        else if (term != null)
                        {
				concept = concept + nextLine + "\r\n";
				if (nextLine.startsWith("ID: ") && ID == null) 
                                {
					String[] split = nextLine.split(": ");
					ID = split[1];
				} 
                                
				if (nextLine.startsWith("altID: ") && altID == null) {
					String[] split = nextLine.split(" ");
					altID = split[1];
				}
			}
		}
                
		if (term != null) 
                {
			term.setConcept(concept);
		}
	
	}
	
        
        // Reads the queries.txt file and returns it as seperate Query Objects 
	public Query queries() throws FileNotFoundException 
        {
            //Throws FileNotFoundException when the queries.txt file is not found
            
		File file =  new File("queries.txt");
		Query query = null;
		Scanner sc = new Scanner(file);
		
		List<String> ids = new ArrayList<>();
		
		while(sc.hasNextLine()) 
                {
			String nextLine = sc.nextLine();
			String[] split = nextLine.split(": ");
			String queryId = split[1];
			
			ids.add(queryId);
		}
		if(ids.size() != 0) 
                {
			query = new Query();
			query.setIDs(ids);
		}
		
		sc.close();
			
		return query;
	}
        
        // Creates a text file called results.txt
        // Puts the answers to each query into this text file
	public void queryResult(Query query) throws IOException 
        {
            // Throws IOException if the file cannot be written successfully
            
		FileWriter fw = new FileWriter("results.txt");
		List<String> queryIds = query.getIDs();
                
		for(String ID : queryIds) 
                { 
			fw.write("[query answer]\r\n");
			String termString = termString(termHashMap, ID);
			fw.write(termString);
		}
                
		fw.close();
	}
        
        // Calculates the maximimum path
        // Puts all the terms in that path into maxpath.txt
	public void maxPath() throws IOException 
        {
		FileWriter fw = new FileWriter("maxpath.txt");
		int maxPath = 0;
		String termIdWithMaxPath = null;
                
		for(String id : termHashMap.keySet()) 
                { 
			int path = termPath(termHashMap, id, 0);
			if (path > maxPath) {
				maxPath = path;
				termIdWithMaxPath = id;
			}
		}
                
		fw.write("[maxPath="+ maxPath + "]\r\n");
		fw.write(termString(termHashMap, termIdWithMaxPath));
		fw.close();
	}
	
	// Main function that runs the program
	public static void main(String[] args) throws Exception {
		HPOExplorer hpoExplorer = new HPOExplorer();
		
                // Calls necessary methods
                System.out.println("Starting");
		hpoExplorer.loadTerms();
		Query query = hpoExplorer.queries();
		hpoExplorer.queryResult(query);
		hpoExplorer.maxPath();
                System.out.println("\nEnd");
	}

}