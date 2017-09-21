package pl.edu.agh.kis.creator;

import java.util.ArrayList;

/**
 * trr
 * 
 * @author Szymon Majkut
 * @version %I%, %G% 
 * 
 */
public class ConnectionArchitect 
{
	private Hierarchy hierarchy;
	
	private boolean couldAddConnection(Integer aboveLayer, Integer aboveIndex,
			Integer belowLayer, Integer belowIndex)
	{
		return aboveIndex*belowIndex >= 0 && aboveLayer*belowLayer >= 0 &&
				belowLayer < hierarchy.criteria.size() && aboveLayer < belowLayer && 
				hierarchy.criteria.get(aboveLayer).size() > aboveIndex &&
				hierarchy.criteria.get(belowLayer).size() > belowIndex;
	}
	
	private boolean couldDeleteConnection(Integer aboveLayer, Integer aboveIndex,
			Integer belowLayer, Integer belowIndex)
	{
		return aboveIndex*belowIndex >= 0 && aboveLayer*belowLayer > 0 &&
				aboveLayer.equals(belowLayer + 1) && 
				hierarchy.criteria.get(aboveLayer).size() > aboveIndex &&
				hierarchy.criteria.get(belowLayer).size() > belowIndex;
	}
	
	/**
	 * trr
	 */
	public void addGoalConnections()
	{
		ArrayList<Block> blocks = hierarchy.criteria.get(new Integer(1));
		Block goal = hierarchy.criteria.get(new Integer(0)).get(0);
		
		for(Block b : blocks)
		{
			goal.addLowerLayerBlock(b);
		}
		
		//TODO tymczasowo te¿ alternative, dopóki nie usuniemy
		ArrayList<Block> upper = hierarchy.criteria.get(new Integer(hierarchy.criteria.size()-1));
		
		for(Block b : upper)
		{
			for(Block alt : hierarchy.alternatives)
			{
				b.addLowerLayerBlock(alt);
			}
		}
	}
	
	/**
	 * trr
	 * 
	 * @param aboveLayer
	 * @param aboveIndex
	 * @param belowLayer
	 * @param belowIndex
	 */
	public void addConnection(Integer aboveLayer, Integer aboveIndex,
			Integer belowLayer, Integer belowIndex)
	{
		if(couldAddConnection(aboveLayer, aboveIndex,belowLayer, belowIndex))
			{
				Block upper = hierarchy.criteria.get(aboveLayer).get(aboveIndex);
				Block below = hierarchy.criteria.get(belowLayer).get(belowIndex);
				
				upper.addLowerLayerBlock(below);
			}
	}
	
	/**
	 * trr
	 * 
	 * @param aboveLayer
	 * @param aboveIndex
	 * @param belowLayer
	 * @param belowIndex
	 */
	public void deleteConnection(Integer aboveLayer, Integer aboveIndex,
			Integer belowLayer, Integer belowIndex)
	{
		if(couldDeleteConnection(aboveLayer, aboveIndex,belowLayer, belowIndex))
			{
				Block upper = hierarchy.criteria.get(aboveLayer).get(aboveIndex);
				Block below = hierarchy.criteria.get(belowLayer).get(belowIndex);
				
				upper.deleteLowerLayerBlock(below);
			}
	}
	
	/**
	 * trr
	 * 
	 * @return
	 */
	public boolean checkConnections()
	{
		for(int i = 1; i < hierarchy.criteria.size(); ++i)
		{
			for(Block b : hierarchy.criteria.get(i))
				if(b.getParentName().equals(""))
					return false;
		}
		return true;
	}
	
	/**
	 * trr
	 * 
	 * @param hierarchy
	 */
	public ConnectionArchitect(Hierarchy hierarchy)
	{
		this.hierarchy = hierarchy;
	}
}
