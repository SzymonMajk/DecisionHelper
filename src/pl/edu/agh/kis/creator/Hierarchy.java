package pl.edu.agh.kis.creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Collect criteria and alternatives for single hierarchy, providing methods
 * for simple display, increase and decrease state, with is private Byte
 * value, which helps objects using hierarchy to determine is it possible
 * for them to change hierarchy with their possible methods. For example
 * if hierarchy is in state of building, then it should be forbiden to
 * add some pair comparisons. Another two methods provide possibility to
 * save and load hierarchy in xml format.
 * 
 * @author Szymon Majkut
 * @version %I%, %G% 
 *
 */
public class Hierarchy 
{
	private String hierarchyName;
	
	private Byte currentState;
	
	private void displayHierarchyCreate()
	{
		System.out.println("\nCurrent Hierarchy:");
		for(int i = 0; i < criteria.size(); ++i)
		{
			ArrayList<Block> blocks = criteria.get(new Integer(i));
			
				System.out.print("Layer" + i +": ");
				for(Block b : blocks)
					System.out.print(b.getBlockName() + " ");
				System.out.println();
		}
		
		System.out.println("\nCurrent Alternatives: ");
		for(Block b : alternatives)
			System.out.print(b.getBlockName() + " ");
		System.out.println("\n!!!------------------------------------!!!");
	}
	
	private void displayHierarchyConnect()
	{
		System.out.println("\nCurrent Hierarchy:");
		
		ArrayList<Block> blocks = criteria.get(new Integer(0));
		System.out.print("Layer" + 0 +": ");
		for(Block b : blocks)
			System.out.println(b.getBlockName());
		for(int i = 1; i < criteria.size(); ++i)
		{
			blocks = criteria.get(new Integer(i));
			
				System.out.print("Layer" + i +": ");
				for(Block b : blocks)
					System.out.print(b.getBlockName() + "{parent-" + 
				b.getParentName() + "} ");
				
			System.out.println();
		}
		
		System.out.println("\nCurrent Alternatives: ");
		for(Block b : alternatives)
			System.out.print(b.getBlockName() + " ");
		System.out.println("\n!!!------------------------------------!!!");
	}
	
	private void displayHierarchyWithIndexes()
	{
		System.out.println("\nCurrent Hierarchy:");
		for(int i = 0; i < criteria.size(); ++i)
		{
			ArrayList<Block> blocks = criteria.get(new Integer(i));
			
				System.out.print("Layer" + i +": ");
				for(Block b : blocks)
				{
					System.out.print(b.getBlockName() + " ");
					if(b.getlowerLayerWeights().equals("\r"))
						System.out.print("[setRelatives] ");
				}
				
			System.out.println();
		}
		
		System.out.println("\nCurrent Alternatives: ");
		for(Block b : alternatives)
			System.out.print(b.getBlockName() + " ");
		System.out.println("\n!!!------------------------------------!!!");
	}
	
	public HashMap<Integer,ArrayList<Block>> criteria 
								= new HashMap<Integer,ArrayList<Block>>();
	
	public ArrayList<Block> alternatives = new ArrayList<Block>();
	
	public HashSet<String> names = new HashSet<String>();
	
	public void display()
	{
		switch(currentState)
		{
		case 1:
			displayHierarchyCreate();
            break;
		case 2:
			displayHierarchyConnect();
            break;
		case 3:
			displayHierarchyWithIndexes();
			break;
		}
	}
	
	/**
	 * trr
	 * 
	 * @return
	 */
	public Byte getCurrentState()
	{
		return currentState;
	}
	
	//TODORozbudujemy to, o dodatkowe funkcje zwi¹zane z tym co siê dzieje przy przechodzniu
	public void increaseState()
	{
		if(currentState < 3)
			++currentState;
	}
	
	public void decreaseState()
	{
		if(currentState > 1)
			--currentState;
	}
	
	public void loadHierarchy()
	{
		
	}
	
	public void saveHierarchy()
	{
		//new XmlFormatFileCreator.writeData();
	}
	
	public Hierarchy(String name)
	{
		hierarchyName = name;
		currentState = new Byte((byte) 1);
	}
}
