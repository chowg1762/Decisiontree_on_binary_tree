package Assignment5;
/**
 *  @author Wonguen Cho
 *	109461283
 * 
 * DecisionTreeClassifier class will use this class to construct, delete, search, move cursor.
 * In this class, user can get the classification of input text which user inserted.
 * You can track the path which leaf has passed through. 
 * In this class, you can move cursor, left child, right child, and root of the tree. 
 * In addition, through text file, this method generates tree by using the information in the text file.
 * 
 *  
 * Datafield
 * -root:	A reference to the root TreeNode of this tree
 * -cursor : A reference to the currently selected TreeNode in the tree
 * 			The cursor should select the root node by default
 * -deleteChild : A reference to a node to remove delete child
 * 
 * Constructor 
 * TreeNavigator(String[] keywords) : This constructor initialize root and cursor. Cursor will be initialized same as root in this constructor. 
 * TreeNavigator() : This constructor initializes root and cursor as null.

 *
 * Method:
 * classify 
 * 		-Classifies the text with the given tree and returns the classification as a String
 * classification
 * 		-This method is helper method of classify. This method will execute as recursion
 *  getPath
 *  	-Gets the current path of the cursor 
 *  trackingPath
 *  	-This method is for executing 'P' in the main method
 *  trackingPath(TreeNode root, String text)
 *  	-This method is helper method of trackingPath
 *  resetCursor
 *  	-Resets the cursor to the root node
 *  cursorLeft
 *  	-Moves cursor to its left child
 *  cursorRight
 *  	-Moves cursor to its right child
 *  getCursor
 *  	-This gets the cursor so you can modifiy the keywords or the left or the right child links
 *  editCursor
 *  	- This method will edit the cursor's keywords.
 *  searchNode
 *  	-This method is helper method for deleteChild method. This method will search specific node which keywords are correlated with a node which user wants to delete
 *  deleteChild
 *  	-This method is for deleting the children node which user want to delete
 *  addChildren
 *  	-This method add children nodes no child will be added on left side, yes node will be added on right side.
 *  buildTree
 *  	-Reads in a text file describing a TreeNavigator.
 */
public class TreeNavigator {
	private TreeNode root;
	private TreeNode cursor;
	private TreeNode deleteChild;
	
	/**
	 * This constructor initializes root and cursor as null
	 */
	public TreeNavigator(){
		this.root = null;
		this.cursor = root;
		
	}
	
	/**
	 * This constructor initializes root and cursor. Root get specific string array.
	 * @param keywords
	 * 	this parameter is string array which will save keywords array into node
	 */
	public TreeNavigator(String[] keywords){
		this.root = new TreeNode(keywords, null, null);
		this.cursor = root;
	
	}
	
	
	/**
	 * Classifies the text with the given tree and returns the classification as a String
	 * @param text
	 * 	This parameter will be used to make classification.  
	 * @return
	 * 	A classified string which is based on parameter text
	 */
	public String classify(String text){
		String result = classification(root, text);
		return result;
	}
	
	/**
	 * This method is helper method of classify. This method will execute as recursion
	 * @param root
	 * 	The parameter get root information from classify method
	 * @param text
	 * 	This parameter get string from classify method the values is same as classifiy method
	 * @return
	 * 	The result string of the classify method.  
	 */
	public String classification(TreeNode root, String text){
		if(root.isLeaf()){
			return root.getKeywords()[0];
		}
		String[] textArr = text.split("\\s+");
		boolean find=false;

		for(int i=0; i<root.getKeywords().length; i++){
			for(int j=0; j<textArr.length; j++){
				if(textArr[j].toUpperCase().charAt(textArr[j].length()-1)<65||(textArr[j].toUpperCase().charAt(textArr[j].length()-1))>90)
						textArr[j]=textArr[j].substring(0, textArr[j].length()-1);
				if(textArr[j].toUpperCase().equals(root.getKeywords()[i].toUpperCase())){
					find = true;
				}
			}
		}
		
		if(find){
			return classification(root.getRight(),text);
		}
		else{
			return classification(root.getLeft(),text);
		
		}
	}
	/**
	 * Gets the current path of the cursor 
	 * @return
	 * current path of the cursor
	 * 
	 */
	public String getPath(){
		String answer="";
		for(int i=0; i<cursor.getKeywords().length;i++)
			answer=answer+root.getKeywords()[i]+", ";
		return answer;
	}
	/**
	 * This method is for executing 'P' in the main method
	 * @param text
	 * This parameter is from user input
	 * @return
	 * The return value is decision path of the leaf.
	 */
	public String trackingPath(String text){
		return trackingPath(root, text);
	}
	/**
	 * This method is helper method of trackingPath
	 * @param root
	 * 		This parameter is root of the Tree
	 * @param text
	 * 		This text is user input text to track path of the leaf
	 * @return
	 * 		Process of the decision string
	 */
	public String trackingPath(TreeNode root, String text){
		String answer ="";
		if(root.isLeaf()){
			return answer+"DECSION : "+root.getKeywords()[0];
		}
		String[] textArr = text.split("\\s+");
		boolean find=false;
		
		for(int i=0; i<root.getKeywords().length; i++){
			for(int j=0; j<textArr.length; j++){
				
				if(textArr[j].toUpperCase().charAt(textArr[j].length()-1)<65||(textArr[j].toUpperCase().charAt(textArr[j].length()-1))>90)
					textArr[j]=textArr[j].substring(0, textArr[j].length()-1);
				
				if(textArr[j].toUpperCase().equals(root.getKeywords()[i].toUpperCase())){
					find = true;
				}
			}
		}
		
		if(find){
			answer = answer+"IS ";
			for(int i=0; i<root.getKeywords().length;i++)
				answer=answer+root.getKeywords()[i]+", ";
			return answer + trackingPath(root.getRight(),text);
		}
		else{
			answer = answer+"NOT ";
			for(int i=0; i<root.getKeywords().length;i++)
				answer=answer+root.getKeywords()[i]+", ";
			return answer + trackingPath(root.getLeft(),text);
		}
	}
	
	/**
	 * Resets the cursor to the root node
	 *<dt><b>PostCondition : </b><dd>
	 *Cursor references root node. Cursor contents are printed 
	 *@throw
	 *NullPointerException:
	 *	if root is empty, exception will be executed. 
	 */
	public void resetCursor(){
		if(root==null)
			throw new NullPointerException("Root is already empty!");
		
		cursor = root;
		
		System.out.println("cursor is at root");
		System.out.print("Current node keywords : ");
		
		if(root==null)
			System.out.println("Tree is empty");
		
		else
			for(int i=0; i<cursor.getKeywords().length; i++){
				if(i==cursor.getKeywords().length-1)
					System.out.println(cursor.getKeywords()[i]+".");
				else
					System.out.print(cursor.getKeywords()[i]+", ");
				
			}
	}
	

	/**
	 * Moves cursor to its left child
	 * @throw
	 * NullPointerException : 
	 * 	if root is empty, or 'no' node is empty, exception will be executed.
	 */
	public void cursorLeft(){
		if(root==null)
			throw new NullPointerException("Root is empty!!");
		if(cursor.getLeft()==null)
			throw new NullPointerException("The No Node is empty!!");
		cursor = cursor.getLeft();
		System.out.print("Cursor is moved"+(cursor.isLeaf()?"cusor is at leaf":""));
		System.out.print(", message is ");
		for(int i=0; i<cursor.getKeywords().length; i++){
			if(i<cursor.getKeywords().length-1)
				System.out.print(cursor.getKeywords()[i]+",");
			else
				System.out.println(cursor.getKeywords()[i]+".");
		}
	}

	/**
	 * Moves cursor to its right child
	 *  @throws
	 *  NullPointerException : 
	 * 	if root is empty, or 'yes' node is empty, exception will be executed.
	 */
	public void cursorRight(){
		if(root==null)
			throw new NullPointerException("Root is empty!!");
		if(cursor.getRight()==null)
			throw new NullPointerException("The Yes Node is empty!!");
		cursor = cursor.getRight();
		System.out.print("Cursor is moved"+(cursor.isLeaf()?"cusor is at leaf":""));
		System.out.print(", message is ");
		for(int i=0; i<cursor.getKeywords().length; i++){
			if(i<cursor.getKeywords().length-1)
				System.out.print(cursor.getKeywords()[i]+",");
			else
				System.out.println(cursor.getKeywords()[i]+".");
		}
	}
	
	/**
	 * This gets the cursor so you can modifiy the keywords or the left or the right child links
	 * <dt><b>Precondition : </b><dd>
	 * Cursor is not null(return null if it is null)
	 * @return
	 * 	Current node which cursor indicates now.
	 */
	public TreeNode getCursor(){
		return cursor;
	}
	/**
	 * This method will edit the cursor's keywords.
	 * After edit the cursor, message will be appear. The message is about changig keywords.
	 * @param text
	 * 	text will be keywords of the cursor. This text must be separated by comma.
	 * 
	 */
	public void editCursor(String text){
		for(int i=0; i<text.length();i++)
			if(text.charAt(i)=='.'||text.charAt(i)=='!'||text.charAt(i)=='`'
					||text.charAt(i)=='@'||text.charAt(i)=='#'||text.charAt(i)=='$'
					||text.charAt(i)=='%'||text.charAt(i)=='^'||text.charAt(i)=='*'
					||text.charAt(i)=='('||text.charAt(i)==')'||text.charAt(i)=='-'
					||text.charAt(i)=='_'||text.charAt(i)=='+'||text.charAt(i)=='='
					||text.charAt(i)=='{'||text.charAt(i)=='['||text.charAt(i)==']'
					||text.charAt(i)=='}'||text.charAt(i)=='|'||text.charAt(i)=='\\'
					||text.charAt(i)=='~'||text.charAt(i)=='`'||text.charAt(i)=='.'
					||text.charAt(i)=='<'||text.charAt(i)=='>'||text.charAt(i)=='/'
					||text.charAt(i)=='?')
				throw new IllegalArgumentException("You have to separate the keywords by a comma.");
		
		String[] keys = text.split(",");
		
		
		if(root==null){
			TreeNode newNode = new TreeNode(keys,null,null);
			root = newNode;
			cursor = root;
		}
		
		else{
			cursor.setKeywords(keys);
		}
		
		System.out.print("Keywords updated to : ");
		for(int i=0; i<keys.length; i++){
			if(i!=keys.length-1)
				System.out.print(keys[i]+", ");
			else
				System.out.println(keys[i]+".");
		}
	}
	/**
	 * This method is helper method for deleteChild method. This method will search specific node which keywords are correlated 
	 * with a node which user wants to delete
	 * @param root
	 * 	This parameter is root of the tree
	 * @param text
	 * 	This text is keywords what user want to delete 
	 * @return
	 * 	This method will return true if the keywords in the tree, or false if the keywords is not in the tree.
	 */
	public boolean searchNode(TreeNode root, String text){
		if(root==null)
			throw new NullPointerException("Root is empty!!");
		for(int i=0; i<root.getKeywords().length; i++)
			if(root.getKeywords()[i].toUpperCase().equals(text.toUpperCase())){
				deleteChild = root;
				return true;
			}
		if(root.getLeft()!=null&&searchNode(root.getLeft(),text)){
			return searchNode(root.getLeft(), text);
		}
		if(root.getRight()!=null&&searchNode(root.getRight(),text)){
			return searchNode(root.getRight(), text);
		}
		return false;
	}
	
	
	/**
	 * This method is for deleting the children node which user want to delete
	 * @param text
	 * 	Text is keyword(terminal word) which user want to delete the children of a node which have same keywords as user input 
	 * @throws
	 * NullPointerException:
	 * 	if root is empty, the exception will throw.
	 */
	public void deleteChild(String text){
		if(root==null)
			throw new NullPointerException("Root is empty!!");
			if(searchNode(root, text)){
				if(deleteChild.getLeft()==null&&deleteChild.getRight()==null)
					throw new NullPointerException("This node doesn't have children");
				deleteChild.setLeft(null);
				deleteChild.setRight(null);
				System.out.print("Current Node is leaf. Text is ");
				for(int i=0; i<deleteChild.getKeywords().length; i++){
					if(i!=deleteChild.getKeywords().length-1)
						System.out.print(deleteChild.getKeywords()[i]+", ");
					else
						System.out.print(deleteChild.getKeywords()[i]);
				}
				System.out.println();
				cursor = deleteChild;
			}
			else
				System.out.println("The node doesn't exist.");
	}
	
	/**
	 * This method add children nodes no child will be added on left side, yes node will be added on right side.
	 * @param noChild
	 * 		This parameter will be added on left side of the node
	 * @param yesChild
	 * 		This parameter will be added on right side of the node
	 * @throws
	 * 	NullPointerException
	 * 		-if root is empty, this exception will be executed.
	 * IllegalArgumentException
	 * 		-If right or left child is already exist, this Exception will be executed.
	 */
	public void addChildren(TreeNode noChild, TreeNode yesChild){
		if(root==null)
			throw new NullPointerException("Root is empty!!");
		if(cursor.getLeft()!=null)
			throw new IllegalArgumentException("Left child already exist");
		if(cursor.getRight()!=null)
			throw new IllegalArgumentException("Right child already exist");
		cursor.setLeft(noChild);
		cursor.setRight(yesChild);
		System.out.print("Children are : yes - ");
		for(String right:cursor.getRight().getKeywords())
			System.out.print("'"+right+"' ");
		System.out.print(" and no - ");
		for(String left:cursor.getLeft().getKeywords())
			System.out.print("'"+left+"' ");
		System.out.println(".");
	}
	
	

	/**
	 * Reads in a text file describing a TreeNavigator.
	 *<dt><b>Precondition : </b><dd>
	 * treeFile is a non-null, non-empty String that points to a file that exists that is readable and valid
	 * @param treeFile
	 * @return
	 * 	a new TreeNavigator generated by the passed in textfile
	 * @thorws
	 * 	IllegalArgumentException
	 * 		- if the input text file's format is invalid, this exception will be executed
	 */
	
	public static TreeNavigator buildTree(String fileText){
		
		TreeNavigator newTree = new TreeNavigator();
		String[] line = fileText.split("\\n");
		String[] values;
		String[] inputKeys;
		
		for(int i=0; i<line.length; i++){
			values = line[i].split(";");
			if(values.length!=3)
				throw new IllegalArgumentException("The txt format is invalid");
			inputKeys = values[1].split(",");
			String[] path = values[0].split("-");
			newTree.cursor = newTree.root;
		
			for(int j=0; j<path.length; j++){
				if(values[0].length()<=1){
					if(values[0].equals("0")){
						newTree.root = new TreeNode(inputKeys,null,null);
						newTree.cursor = newTree.root;
					}
				}
				
				else{
					if(j!=0&&path[j].equals("0")){
						if(j==path.length-1){
							TreeNode newNode = new TreeNode(inputKeys, null, null);
							newTree.cursor.setLeft(newNode);
						}
						else{
							newTree.cursor = newTree.cursor.getLeft();
						}
						
					}
					
					if(j!=0&&path[j].equals("1")){
						if(j==path.length-1){
							TreeNode newNode = new TreeNode(inputKeys, null, null);
							newTree.cursor.setRight(newNode);
						}
						else{
							newTree.cursor = newTree.cursor.getRight();
						}
					}
					
					
					if(!path[path.length-1].equals("0")&&!path[path.length-1].equals("1"))
						throw new IllegalArgumentException("The txt format is invalid. The Path form should be such as 0-0-0-1 like that");
				}
			}	
		}
		newTree.cursor = newTree.root;
		return newTree;
	}
	
}
