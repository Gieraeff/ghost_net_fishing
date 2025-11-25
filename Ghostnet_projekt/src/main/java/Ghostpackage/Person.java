package Ghostpackage;

public class Person {

	private String name;
	private String handynummer;
	
	public Person()	{}
	
	public Person(String name, String handynummer) 
	{
		this.name = name;
		this.handynummer = handynummer;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getHanynummer()
	{
		return handynummer;
	}
	
	public void setHandynummer(String handynummer)
	{
		this.handynummer = handynummer;
	}
}
