package common;
import java.sql.Timestamp;


public class Doc {
	private String ID;
	private String creator;
	private Timestamp timestamp;
	private String description;
	private String filename;
	
	public Doc(String ID,String creator,Timestamp timestamp,String description,String filename){
		super();
		this.ID=ID;
		this.creator=creator;
		this.description=description;
		this.filename=filename;
		this.timestamp=timestamp;
		
	}
	
	public String getID(){
		return ID;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator=creator;
	}
	
	public String getDdescription(){
		return description;
	}
	public void setDdescription(String d){
		this.description=d;
	}
	
	public String getFilename(){
		return filename;
	}
	public void setFilename(String a){
		this.filename=a;
	}

	public Timestamp getTimestamp(){
		return timestamp;
	}
	public void setTimestamp(Timestamp b){
		this.timestamp=b;
	}
}
