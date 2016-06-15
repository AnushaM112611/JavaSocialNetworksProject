
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomParserUsers {
	
	public Map<String, String> usersMap = new HashMap<String, String>();

	public void parseUsers(File file) throws FileNotFoundException, IOException{
		
		// Take the input file. Create a buffered reader to read line by line
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		
		while ((line = br.readLine()) != null) {
			String id_pattern = "Id=\"\\d+\"";
			Pattern ipattern = Pattern.compile(id_pattern);
		    Matcher m_id = ipattern.matcher(line);
		    String user_id = null;
		    
		    if (m_id.find( )) {
		    	   int len = m_id.group(0).length();
		    	   user_id = (m_id.group(0).substring(4,len-1)).trim();
		    	   usersMap.put(user_id, null);
		   }
		    
			String name_pattern = "DisplayName=\"(.*?)\"";
			Pattern pname = Pattern.compile(name_pattern);
		    Matcher m_name = pname.matcher(line);
		    String user_name = null;
		    
		   if (m_name.find( )) {
		    	   
		    	  
		    	   user_name = m_name.group(0);
		    	   int len = m_name.group(0).length();
		    	   user_name = user_name.substring(13,len-1);
		    	   usersMap.put(user_id, user_name);
		   }
		    		
		}
		    
		br.close();	
		System.out.println(usersMap.size());
	}
}
