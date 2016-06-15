import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomParserPosts {

	static Map<String,Integer> questionsMap = new HashMap<String, Integer>();
	static Map<String,Integer> answersMap = new HashMap<String, Integer>();
	static CustomParserUsers obj;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		obj = new CustomParserUsers();
		File file = new File("users.xml");
		obj.parseUsers(file);
		
		CustomParserPosts obj1 = new CustomParserPosts();
		File file1 = new File("posts.xml");
		obj1.parsePosts(file1);
		
		Map<String,Integer> sortedQuestionsMap = obj1.sortMapByValue(questionsMap);
		Map<String,Integer> sortedAnswersMap = obj1.sortMapByValue(answersMap);
		
		obj1.printOutMap(sortedQuestionsMap);
		System.out.println("||************************************************||");
		obj1.printOutMap(sortedAnswersMap);

	}
	
	public void parsePosts(File file) throws FileNotFoundException, IOException{
		
		// Take the input file. Create a buffered reader to read line by line
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		
		while ((line = br.readLine()) != null) {
			
			//post type
			int postType = 0;
			String postID_pattern = "PostTypeId=\"\\d+\"";
			Pattern postpattern = Pattern.compile(postID_pattern);
		    Matcher m_post = postpattern.matcher(line);
		    String post_id;
		    
		    if (m_post.find( )) {
		    	   int len = m_post.group(0).length();
		    	   post_id = (m_post.group(0).substring(12,len-1)).trim();
		    	   
		    	   if(post_id.equals("1")){
		    		   postType = 1;  
		    	   }
		    	   else if(post_id.equals("2")){
		    		   postType = 2;
		    	   }
		   }
		    
		    String id_pattern = "OwnerUserId=\"\\d+\"";
			Pattern ipattern = Pattern.compile(id_pattern);
		    Matcher m_id = ipattern.matcher(line);
		    String user_id = null;
		    
		    if (m_id.find( )) {
		    	   int len = m_id.group(0).length();
		    	   user_id = (m_id.group(0).substring(13,len-1)).trim();
		    }
		    
		    if(postType == 1){
				
				// add this userId only if it is not present in map, else increment count of value
				if(questionsMap.containsKey(user_id)){
					
					int numOfQuestions = questionsMap.get(user_id);
					// increment questions count
					questionsMap.put(user_id, numOfQuestions + 1);
				}
				else {
					questionsMap.put(user_id, 1);
				}
			}
			
			// if it is answer then update answers map
			else if (postType == 2) {
				
				// add this userId only if it is not present in map, else increment count of value
				if(answersMap.containsKey(user_id)){
					
					int numOfAnswers = answersMap.get(user_id);
					// increment answers count
					answersMap.put(user_id, numOfAnswers + 1);
				}
				else {
					answersMap.put(user_id, 1);
				}
			}
			
		}
		
		br.close();

	}
	
	/**
	 * This method sorts hashMap by values
	 * @param map, this is a unsorted HashMap
	 * @return this method returns new HashMap with sorted by values
	 */
	public Map<String, Integer> sortMapByValue(Map<String,Integer> map){
		 List<Map.Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());
		

	        // Sorting the list based on values
	        Collections.sort(list, new Comparator<Entry<String, Integer>>()
	        {
	          
				@Override
				public int compare(Entry<String, Integer> o1,
						Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
	        });
	        
	        // Maintaining insertion order with the help of LinkedList
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Entry<String, Integer> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	        
	        return sortedMap;
	        	        
	}
	
	/* THis method is to print out elements of sorted map */
	public void printOutMap(Map<String,Integer> map){
		
		int count = 0;
		for(String key : map.keySet()){			
			System.out.println("User Name : " + obj.usersMap.get(key) + " , " + map.get(key));
			// to print only 10 elements
			count++;
			if(count == 10){
				break;
			}
		}
	}

}
