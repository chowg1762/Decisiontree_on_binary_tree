package Assignment5;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Wonguen Cho
 * 109461283
 * 
 * 
 * This class will drive the program and present a menu.
 * You can execute tree program through this class.
 * This class can get a external file to execute for replying your question.
 * Or you can construct your tree logic by this class.
 */
public class DecisionTreeClassifier {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner input = new Scanner(System.in);
		TreeNavigator tree1 = new TreeNavigator();
		TreeNavigator tree2;
		TreeNavigator currentTree = tree1;
		 //= new TreeNavigator();
		String option;
		String secondOption;
		System.out.println("<<<Welcome to the DecisionTree Classifier>>>>");
		do{
		System.out.println("---------Main Menu--------");
		System.out.println("Menu");
		System.out.println("I) Import a tree from a file");
		System.out.println("E) Edit current tree");
		System.out.println("C) Classify a Description");
		System.out.println("P) Show decision path for a description ");
		System.out.println("Q) Quit");
		
		do{
		System.out.print("Please enter an option : ");
		option = input.nextLine();
		if(option.length()>1)
			System.out.println("Your input have to be one character");
		}while(option.length()>1);
		
		switch(option.toUpperCase().charAt(0)){
		case 'I' :
			System.out.print("Please enter a filename : ");
			String fileName = input.nextLine();
			try{
				java.io.File file = new java.io.File(fileName);
				Scanner fromFile = new Scanner(file);
				String booleanExpression="";
				while(fromFile.hasNextLine()){
					booleanExpression = booleanExpression+fromFile.nextLine()+"\n";
				}
				tree2 = TreeNavigator.buildTree(booleanExpression);
				System.out.println("Tree Loaded");
				currentTree=tree2;
				}
			catch(FileNotFoundException ex){
				System.out.println("File doesn't exist");
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
			}
			
			break;
		case 'E' :
			do{
				System.out.println("\n--------Edit-------");
				System.out.println("E) Edit Keywords");
				System.out.println("C) Add Children");
				System.out.println("D) Delete Children, and make Leaf");
				System.out.println("N) Cursor to No Child");
				System.out.println("Y) Cursor to Yes Child");
				System.out.println("R) Cursor to Root ");
				System.out.println("P) Cursor to Parent///I didn't make for extra credit!" );
				System.out.println("M) Main Menu");
				do{
					System.out.print("Please select an option : ");
					secondOption = input.nextLine();
					if(secondOption.length()>1)
						System.out.println("Your input have to be one character");
				}while(secondOption.length()>1);
			
				switch(secondOption.toUpperCase().charAt(0)){
				case 'E' : 
					System.out.print("Please enter keywords for this node, separated by a comma : ");
					try{
					 String keywords = input.nextLine();
					 currentTree.editCursor(keywords);
					}
					catch(IllegalArgumentException ex){
						System.out.println(ex);
					}
					
					break;
				case 'C' : 
					System.out.print("Please enter terminal text for the no leaf : ");
					String noLeaf = input.nextLine();
					String[] leftLeaf = noLeaf.split(",");  
					TreeNode noLeafNode = new TreeNode(leftLeaf, null, null);
					
					System.out.print("Please enter terminal text for the yes leaf :");
					String yesLeaf = input.nextLine();
					String[] rightLeaf = yesLeaf.split(",");
					TreeNode yesLeafNode= new TreeNode(rightLeaf, null, null);
					try{
						currentTree.addChildren(noLeafNode,yesLeafNode);
					}
					catch(IllegalArgumentException ex){
						System.out.println(ex);
					}
					break;
				case 'D' :
					String deleteOne;
					boolean comma = false;
					do{
					System.out.print("Pleas enter a terminal text for this node(You can only insert just one word, don't use comma : ");
					deleteOne = input.nextLine();
					
					for(int i=0; i<deleteOne.length(); i++){
						if(deleteOne.charAt(i)==',')
							comma = true;
					}
					if(comma)
						System.out.println("You have to check your input again. Don't use comma");
					}while(comma);
					try{
						currentTree.deleteChild(deleteOne);
					}
					catch(NullPointerException ex){
						System.out.println(ex);
					}
					break;
				case 'N' :
					try{
						currentTree.cursorLeft();
					}
					catch(NullPointerException ex){
						System.out.println(ex);
					}
					break;
				case 'Y' :
					try{
						currentTree.cursorRight();
					}
						catch(NullPointerException ex){
							System.out.println(ex);
					}
					break;
				case 'R' :
					try{
						currentTree.resetCursor();
					}
					catch(NullPointerException ex){
						System.out.println(ex);
					}
					break;
				//case 'P' :
					//break;
				case 'M':
					break;
				default:
					System.out.println("Your input is wrong.");
				}
			}while(secondOption.toUpperCase().charAt(0)!='M');
			break;
			
		case 'C' : 
			System.out.print("Please enter some text : ");
			String classifiedText = input.nextLine();
			System.out.println("Your request is classified as : "+currentTree.classify(classifiedText));
			break;
		case 'P' : 
			System.out.print("Please enter some text : ");
			String TrackingText = input.nextLine();
			System.out.println("Decision Path : "+currentTree.trackingPath(TrackingText));
			break;
		case 'Q' : 
			System.out.println("ByeBye!");
			break;
		}
		}while(option.toUpperCase().charAt(0)!='Q');
	}

	
}
