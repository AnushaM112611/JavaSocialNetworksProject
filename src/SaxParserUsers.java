import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParserUsers extends DefaultHandler {


	
	static Map<Integer,User> usersMap;
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {


		SaxParserUsers obj = new SaxParserUsers();
		obj.parseXml("users.xml");
		
		for (int key : usersMap.keySet()){
			
			System.out.println("ID : " + key + " " + "Value : " + usersMap.get(key));
		}
		

	}
	
	void parseXml(String fileName) throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(fileName, this);
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		
		if(qName.equals("users")){
			
			usersMap = new HashMap<Integer,User>();
			
		}
		
		// if it is a row create a user object and set its attribute values.
		if(qName.equals("row")){
			
			User userobj = new User(0, null, 0, null);

			for(int i = 0; i < attributes.getLength(); i++ ){
				
				String attrName = attributes.getQName(i);
				
				if(attrName.equals("Id")){
					userobj.id = Integer.parseInt(attributes.getValue(i));
				}
				
				else if(attrName.equals("DisplayName")){
					userobj.displayName = attributes.getValue(i);
				}
				
				else if(attrName.equals("Age")){
					userobj.age = Integer.parseInt(attributes.getValue(i));
				}
				
				else if(attrName.equals("DisplayName")){
					userobj.displayName = attributes.getValue(i);
				}
				
				else if(attrName.equals("Location")){
					userobj.location = attributes.getValue(i);
				}
			}
			
			// Add this to a user map with key as ID and value a user object.
			usersMap.put(userobj.getId(), userobj);
			
		}
		
		

	}
	
	public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName) {
		
		
	}
	
	public void characters(char[] ch, int start, int length) {
		 
	}

}
