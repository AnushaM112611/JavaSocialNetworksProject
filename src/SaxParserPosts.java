import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserPosts extends DefaultHandler {
	
	static Map<Integer,Integer> questionsMap;
	static Map<Integer,Integer> answersMap;
	static Queue<Map.Entry<Integer, Integer>> questionsQueue;
	static Queue<Map.Entry<Integer, Integer>> answersQueue;
	User userObj;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		
		long starttime = System.currentTimeMillis();
		
		// Parse posts.xml
		SaxParserPosts obj = new SaxParserPosts();
		obj.parseXml("posts.xml");
		
		// Parse users.xml
		SaxParserUsers obj1 = new SaxParserUsers();
		obj1.parseXml("users.xml");
		
		Map<Integer,Integer> sortedQuestionsMap = obj.sortMapByValue(questionsMap);
		Map<Integer,Integer> sortedAnswersMap = obj.sortMapByValue(answersMap);
		
		
		System.out.println("|**** TOP 10 USERS BY QUESTIONS ASKED****|");
		obj.printOutMap(sortedQuestionsMap);
		System.out.println("|**** TOP 10 USERS BY QUESTIONS ANSWERED****|");
		obj.printOutMap(sortedAnswersMap);
		
		
		long endtime = System.currentTimeMillis();
		System.out.println(endtime - starttime);
		
	}
	
	private void parseXml(String fileName) throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(fileName, this);
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		 
		// if tag is 'posts' initialize both maps to Hash map
		if(qName.equals("posts")){
			
			questionsMap = new HashMap<>();
			answersMap = new HashMap<>();
			
		}
		
		// if it is a row
		if(qName.equals("row")){
			
			
			//boolean isQuestionType = false;
			int postType = 0;
			
			for(int i = 0; i < attributes.getLength(); i++ ){
				
				String attrName = attributes.getQName(i);
				
				if(attrName.equals("PostTypeId")){
					
					// post is Question type
					if(attributes.getValue(i).equals("1")){
						//isQuestionType = true;
						postType = 1;
					}
					// post is answer Type
					else if(attributes.getValue(i).equals("2")){
						//isQuestionType = false;
						postType = 2;
					}
					
				}
				
				else if (attrName.equals("OwnerUserId")){
					
					int userId = Integer.parseInt(attributes.getValue(i));
					
					// if it is a question then update question map
					if(postType == 1){
						
						// add this userId only if it is not present in map, else increment count of value
						if(questionsMap.containsKey(userId)){
							
							int numOfQuestions = questionsMap.get(userId);
							// increment questions count
							questionsMap.put(userId, numOfQuestions + 1);
						}
						else {
							questionsMap.put(userId, 1);
						}
					}
					
					// if it is answer then update answers map
					else if (postType == 2) {
						
						// add this userId only if it is not present in map, else increment count of value
						if(answersMap.containsKey(userId)){
							
							int numOfAnswers = answersMap.get(userId);
							// increment answers count
							answersMap.put(userId, numOfAnswers + 1);
						}
						else {
							answersMap.put(userId, 1);
						}
					}
				} 				
			}
		}
	}
	
	// method to put map objects into priority que
	public void putIntoQue (Map<Integer, Integer> questionsmap, Map<Integer, Integer> answersmap){
		
		Comparator<Entry<Integer, Integer>> comparator = new ValueComparator();
		questionsQueue = new PriorityQueue<>(100,comparator);
		answersQueue = new PriorityQueue<>(100,comparator);
		
		int max = Integer.MIN_VALUE;
		for(int key : questionsMap.keySet()){
			
			if(questionsMap.get(key) >= max){
				max = questionsMap.get(key);
			}
		}
		
		System.out.println(max);

		questionsQueue.addAll(questionsmap.entrySet());
		answersQueue.addAll(answersmap.entrySet());
		
		System.out.println("queue size : " + questionsQueue.size());
		System.out.println("map size : " + questionsmap.size());
	
	}
	
	/**
	 * This method sorts hashMap by values
	 * @param map, this is a unsorted HashMap
	 * @return this method returns new HashMap with sorted by values
	 */
	public Map<Integer, Integer> sortMapByValue(Map<Integer,Integer> map){
		 List<Map.Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(map.entrySet());
		

	        // Sorting the list based on values
	        Collections.sort(list, new Comparator<Entry<Integer, Integer>>()
	        {
	          
				@Override
				public int compare(Entry<Integer, Integer> o1,
						Entry<Integer, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
	        });
	        
	        // Maintaining insertion order with the help of LinkedList
	        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
	        for (Entry<Integer, Integer> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	        
	        return sortedMap;
	        	        
	}
	
	/* THis method prints out elements of sorted map  */
	public void printOutMap(Map<Integer,Integer> map){
		
		int count = 0;
		for(int key : map.keySet()){
			userObj = SaxParserUsers.usersMap.get(key);
			String displayName = userObj.getDispalyName();
			System.out.println(displayName + " : " + map.get(key) );
			count++;
			// to print only 10 elements
			if(count == 10){
				break;
			}
		}
	}
	

}
