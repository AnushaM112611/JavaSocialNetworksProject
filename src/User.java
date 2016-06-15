
public class User {
	
	public int id;
	public String displayName;
	public int age;
	public String location;
	
	public User(int id, String displayName, int age, String location ){
		
		this.id = id;
		this.displayName = displayName;
		this.age = age;
		this.location = location;
	}
	
	public int getId (){
		return id;
	}
	
	public String getDispalyName (){
		return displayName;
	}
	public int getAge (){
		return age;
	}
	public String getLocation (){
		return location;
	}

}