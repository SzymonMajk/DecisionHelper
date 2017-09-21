package pl.edu.agh.kis.creator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * trr
 * 
 * @author Szymon Majkut
 * @version %I%, %G% 
 * 
 */
public class RelationArchitect 
{
	private Hierarchy hierarchy;

	public boolean couldSetRelatives(Integer Layer, int index)
	{
		return Layer > 0 && Layer < hierarchy.criteria.size() && index >= 0 &&
			index < hierarchy.criteria.get(Layer).size();
	}
	
	public void setRelatives(Integer Layer, int index, Scanner userDecisonGetter)
	{
		Block currentBlock = hierarchy.criteria.get(Layer).get(index);
		Double userDecision = 0.0;
		ArrayList<Double> column = new ArrayList<Double>();
		
		for(int i = 1; i < currentBlock.getLowerLayerBlocksNumber(); ++i)
		{
			for(int j = 0; j < i; ++j)
			{				
				System.out.print("Add ratio(" + (j+1)  + "," + (i+1) +"): ");
				try
				{
					userDecision = 
							Double.parseDouble(userDecisonGetter.nextLine());
					if(userDecision < 0)
						throw new NumberFormatException("Negative value");
					column.add(userDecision);
				} catch (NumberFormatException e) {
					System.err.printf("Number must be positive real number.\n");
					--j;
				} 
				
			}
			System.out.print("Check inconcistency Index for so far ratios:");
			if(hierarchy.criteria.get(Layer).get(index).setPairRelatives(column))
				column.clear();
			else
			{
				--i;
				column.clear();
			}
				
		}
		
		System.out.println("All subcriteria relations for " + 
				currentBlock.getBlockName() + " have been set.");
	}
	
	public boolean checkRelativesSet()
	{
		for(int i = 0; i < hierarchy.criteria.size(); ++i)
		{
			for(Block b : hierarchy.criteria.get(i))
				if(b.getlowerLayerWeights().equals("\r"))
					return false;
		}
		
		return true;
	}
	
	/**
	 * trr
	 * 
	 * @param hierarchy
	 */
	public RelationArchitect(Hierarchy hierarchy)
	{
		this.hierarchy = hierarchy;
	}
}
