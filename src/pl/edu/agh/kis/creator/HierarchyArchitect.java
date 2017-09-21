package pl.edu.agh.kis.creator;

import java.util.ArrayList;

/**
 * trr
 * 
 * @author Szymon Majkut
 * @version %I%, %G% 
 * 
 */
public class HierarchyArchitect 
{
	private Hierarchy hierarchy;
	
	private boolean couldDeleteLayer()
	{
		return hierarchy.criteria != null && 
				hierarchy.criteria.get(new Integer(hierarchy.criteria.size()-1)) != null &&
				hierarchy.criteria.get(new Integer(hierarchy.criteria.size()-1)).isEmpty() &&
				hierarchy.criteria.size() > 1;
	}
	
	private boolean couldDeleteCriterion(Integer layer, int index)
	{
		return layer >= 0 && layer < hierarchy.criteria.size() &&
				index >= 0 && index < hierarchy.criteria.get(layer).size();
	}
	
	private boolean checkLayers()
	{
		if(hierarchy.criteria.size() < 2)
		{
			System.err.printf("You need at least one criterion layer.\n");
			return false;
		}
		
		for(ArrayList<Block> blocks : hierarchy.criteria.values())
			if(blocks == null || blocks.isEmpty())
			{
				System.err.printf("There cannot be empty layers.\n");
				return false;
			}
		return true;
	}
	
	private boolean checkCriteria()
	{
		ArrayList<Block> blocks = null;
		
		for(int i = 1; i < hierarchy.criteria.size()-1; ++i)
		{
			blocks = hierarchy.criteria.get(i);
			if(blocks.size() < 2)
			{
				System.err.printf("Every criterion must have no subcriterion "
						+ "or more than one.\n");
				return false;
			}
		}
		
		blocks = hierarchy.criteria.get(0);
		if(blocks.size() != 1)
		{
			System.err.printf("Top layer must have one criterion.\n");
			return false;
		}
			
		return true;
	}
	
	private boolean checkAlternatives()
	{
		if(hierarchy.alternatives.size() < 2)
		{
			System.out.printf("You need at least two alternatives.\n");
			return false;
		}
			
		return true;
	}
	
	/**
	 * trr
	 */
	public void addLayer()
	{
		hierarchy.criteria.put(new Integer(hierarchy.criteria.size()), new ArrayList<Block>());
	}
	
	/**
	 * trr
	 */
	public void deleteLayer()
	{
		if(!couldDeleteLayer())
		{
			System.err.printf("Lowest layer must be empty\n");
			return;
		}
		
		hierarchy.criteria.remove(hierarchy.criteria.size()-1);
	}
	
	/**
	 * trr
	 * 
	 * @param layer
	 * @param blockName
	 */
	public void addCriterion(Integer layer, String blockName)
	{
		if(hierarchy.names.contains(blockName))
		{
			System.err.printf("Criterion name must be unique.\n");
			return;
		}
		
		if(layer < 0 || layer >= hierarchy.criteria.size())
		{
			System.err.printf("Provided layer is out of range.\n");
			return;
		}
		
		hierarchy.names.add(blockName);
		hierarchy.criteria.get(layer).add(new Block(layer,blockName));
	}
	
	/**
	 * trr
	 * 
	 * @param layer
	 * @param index
	 */
	public void deleteCriterion(Integer layer, int index)
	{
		if(couldDeleteCriterion(layer,index))
			hierarchy.criteria.get(layer).remove(index);
	}
	
	/**
	 * trr
	 * 
	 * @param blockName
	 */
	public void addAlternative(String blockName)
	{
		if(hierarchy.names.contains(blockName))
		{
			System.err.printf("Alternative name must be unique.\n");
			return;
		}
		
		hierarchy.names.add(blockName);
		hierarchy.alternatives.add(new Block(0,blockName));
	}
	
	/**
	 * trr
	 * 
	 * @param blockIndex
	 */
	public void deleteAlternative(Integer blockIndex)
	{
		if(blockIndex >= 0 && blockIndex < hierarchy.alternatives.size())
			hierarchy.alternatives.remove(blockIndex);
	}
	
	/**
	 * trr
	 * 
	 * @return
	 */
	public boolean checkHierarchy()
	{
		return checkLayers() && checkCriteria() && checkAlternatives();
	}
	
	/**
	 * trr
	 * 
	 * @param hierarchy
	 */
	public HierarchyArchitect(Hierarchy hierarchy)
	{
		this.hierarchy = hierarchy;
	}
}
