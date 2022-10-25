
public class Element extends Grabbable {
	
	private String[] names = {"","fire","earth","water","air","ordo"};
	
	private static final int width = 64 ,height = 64;

	private short ID;
	
	public Element(short ID)
	{
		super(width,height);
		this.ID = ID;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return names[ID];
	}
	
	public String getNameFromID(short ID)
	{
		return names[ID];
	}
	
	public int getIDFromName(String name)
	{
		for(int i = 1; i<names.length; i++)
		{
			if(name.equals(names[i]))
			{
				return i;
			}
		}
		return 0;
	}
}
