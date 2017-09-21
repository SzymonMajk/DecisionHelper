package pl.edu.agh.kis.creator;

import java.util.Scanner;

/**
 * Uses XmlFormatFileCreator to write data collected in Block's with lower
 * layer pair weights consistency estimated using geometric mean index and
 * provide methods to put user through completing informations.
 * 
 * @author Szymon Majkut
 * @version %I%, %G% 
 *
 */
public class Proceeder
{
	private Hierarchy hierarchy;
	
	private HierarchyArchitect architect;
	
	private ConnectionArchitect connector;
	
	private RelationArchitect relativer;
	
	private boolean quit = false;
	
	/**
	 * First step of the program, lets user to add or reduce number of layers,
	 * allows to delete only layers from highest level. Looks for empty layers
	 * and inform about it. Allow to add or delete block.
	 * 
	 * @return true if created hierarchy.criteria have no empty layers and top layer
	 * 		has only one block.
	 */
	public void createHierarchy(Scanner userDecision)
	{
		String decisionLine = "";

		hierarchy.display();
		
		System.out.println("l - add layer, L - delete layer, A - add "
				+ "block D - delete block, a - add alternative, "
				+ "d - delete alternative, n - next step with check "
				+ "q - quit, displaying after every decision");
	
		decisionLine = userDecision.nextLine();
		if(decisionLine != null && !decisionLine.equals(""))
		switch (decisionLine.charAt(0))
			{
				case 'q' : quit = true; break;
				case 'n' : 
				{
					if(architect.checkHierarchy())
					{
						hierarchy.increaseState();
						connector.addGoalConnections();
					}
						
					break;
				}
				case 'l' : architect.addLayer(); break;
				case 'L' : architect.deleteLayer(); break;
				case 'D' : 
				{
					System.out.print("Specify layer index: ");
					int layerNumber = -1;
					
					try {
						layerNumber = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					
					System.out.print("\nSpecify criterion index: ");
					int blockIndex = -1;
					
					try {
						blockIndex = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					
					architect.deleteCriterion(layerNumber,blockIndex);
					break;
				}
				case 'A' : 
				{
					System.out.print("Specify layer index: ");
					int layerNumber = -1;
					
					try {
						layerNumber = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					
					System.out.print("\nSpecify criterion name: ");
					String blockName = userDecision.nextLine();
					architect.addCriterion(layerNumber,blockName);
					break;
				}
				case 'a' :
				{
					System.out.print("\nSpecify alternative name: ");
					String blockName = userDecision.nextLine();
					architect.addAlternative(blockName);
					break;
				}
				case 'd' :
				{
					System.out.print("\nSpecify alternative index: ");
					int blockIndex = -1;
					
					try {
						blockIndex = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					
					architect.deleteAlternative(blockIndex);
				}
			}
	}
	
	/**
	 * Second step of the program, lets user to specify connections between
	 * block in center layers. Every block from layer zero is automatically
	 * added to layers from layer one and block from highest layer is also
	 * automatically connect with layers from layer highest minus one. This
	 * connection means adding to the list of lower layer connections.
	 * 
	 * @return true if at the end every block without blocks from layer zero
	 * 		and layer one have connection to block from higher layer, by its
	 * 		parent name.
	 */
	public void createConnections(Scanner userDecision)
	{
		String decisionLine = "";

		hierarchy.display();
		//TODO nie ³¹czy do Goal Connection i nie chce wogle robiæ connection...
		
		System.out.println("a - add connection, d - delete connection, n - next"
				+ " step with check q - quit, displaying after every decision"
				+ ", hierarchy.alternatives are connect automatically");
			
		decisionLine = userDecision.nextLine();
		if(decisionLine != null && !decisionLine.equals(""))
			switch (decisionLine.charAt(0))
			{
				case 'q' : quit = true;
				case 'n' : 
				{
					if(connector.checkConnections()) 
						hierarchy.increaseState();
					break;
				}
				case 'd' : 
				{
					System.out.print("Specify upper layer number: ");
					int upperLayerNumber = -1;
					try {
						upperLayerNumber = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					System.out.print("Specify upper block index: ");
					int upperBlockIndex = -1;
					try {
						upperBlockIndex = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					System.out.print("Specify below layer number: ");
					int belowLayerNumber = -1;
					try {
						belowLayerNumber = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					System.out.print("Specify below block index: ");
					int belowBlockIndex = -1;
					try {
						belowBlockIndex = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					connector.deleteConnection(upperLayerNumber,upperBlockIndex,
							belowLayerNumber,belowBlockIndex);
					break;
				}
				case 'a' : 
				{
					System.out.print("Specify upper layer number: ");
					int upperLayerNumber = -1;
					try {
						upperLayerNumber = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					System.out.print("Specify upper block index: ");
					int upperBlockIndex = -1;
					try {
						upperBlockIndex = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					System.out.print("Specify below layer number: ");
					int belowLayerNumber = -1;
					try {
						belowLayerNumber = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					System.out.print("Specify below block index: ");
					int belowBlockIndex = -1;
					try {
						belowBlockIndex = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					connector.addConnection(upperLayerNumber,upperBlockIndex,
							belowLayerNumber,belowBlockIndex);
				}
			}
	}
	
	/**
	 * Third step of the program, lets user to estimate relatives for every
	 * block, where relatives means the ratio between weights of compare every
	 * lower layer block each other. Before adding next block, calculate
	 * inconsistency index and require to repeat estimation if index is to
	 * high.
	 * 
	 * @return true if all blocks from layer higher than zero have consistent
	 * 		pair weight ratio.
	 */
	public void createRelatives(Scanner userDecision)
	{
		String decisionLine = "";
		hierarchy.display();
		
		System.out.println("s - set relatives for block, c - proceed calculations "
				+ "q - quit, displaying after every decision");

		decisionLine = userDecision.nextLine();
		if(decisionLine != null && !decisionLine.equals(""))
			switch (decisionLine.charAt(0))
			{
				case 'q' : quit = true;
				case 'n' : 
				{
					if(relativer.checkRelativesSet()) 
					{
						System.err.println("Every block must have specified relatives.");
						//quit = true; TODO i wymyœl jak tutaj to AhpSolver pod³¹czyæ..
					}
					
					break;
				}
				case 's' : 
				{
					System.out.print("Specify layer number: ");
					int layerNumber = -1;
					try {
						layerNumber = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					System.out.print("Specify block index: ");
					int blockIndex = -1;
					try {
						blockIndex = 
							Integer.parseInt(userDecision.nextLine());
					} catch (NumberFormatException e) {
						System.err.println(
								"It have to be digit!");
						break;
					}
					if(relativer.couldSetRelatives(layerNumber,blockIndex))
						relativer.setRelatives(layerNumber,blockIndex,userDecision);
				}
			}
	}
	
	/**
	 * trr
	 * 
	 * @param userDecision
	 */
	public void proceedUserDecision(Scanner userDecision)
	{
		while(!quit)
		{
			switch(hierarchy.getCurrentState())
			{
			case 1:
				createHierarchy(userDecision);
	            break;
			case 2:
				createConnections(userDecision);
	            break;
			case 3:
				createRelatives(userDecision);
				break;
			}
		}
	}
	
	/**
	 * trr
	 * 
	 * @param hierarchy
	 */
	public Proceeder(Hierarchy hierarchy)
	{
		if(hierarchy == null)
		{
			System.err.printf("Invalid Hierarchy, check choosen file\n");
			System.exit(1);
		}
		this.hierarchy = hierarchy;
		architect = new HierarchyArchitect(hierarchy);
		architect.addLayer();
		architect.addCriterion(0,"Goal");
		connector = new ConnectionArchitect(hierarchy);
		relativer = new RelationArchitect(hierarchy);
	}
	
	/**
	 * Sample use of Proceeder with terminal communication.
	 * 
	 * @param args in current version do not used
	 */
	public static void main(String[] args)
	{
		Proceeder p = new Proceeder(new Hierarchy("ForTesting"));
		Scanner userDecision = new Scanner(System.in);
		
		p.proceedUserDecision(userDecision);
		//TODO gdzieœ coœ, zapisywanie, nie?
	}
}