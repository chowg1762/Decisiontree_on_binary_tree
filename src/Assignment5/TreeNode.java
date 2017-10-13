package Assignment5;
/**
 * 
 * 
 * @author Wonguen Cho
 * 109461283
 * 
 * Your tree node is constructed in this class
 * you can set or get the data from this class and make links among the data by this class.
 * 
 * Data Field 
 * -keywords : This field holds the message only if it is a leaf, otherwise this is a list of words to trigger going down this path.
 * 				These keywords are joined as if 'OR'ed together
 * -left, right : These two fields hold the left and right subtrees respectively
 * 
 * Constructor
 * 	-TreeNode : this constructor get three parameters, and these parameters initialize three data fields
 * 
 * Method
 * -setKeywords
 * 	This method is mutator method of data field 'keywords'
 * -setLeft
 * 	This method is mutator method of data field 'left'
 * -setRight
 * 	This method is mutator method of data field 'right'
 * 
 * -getKeywords
 * 	This method is accessor method of data field 'keywords'
 * -getLeft
 * 	This method is accessor method of data field 'left'
 * -getRight
 * 	This method is accessor method of data field 'right'
 * -isLeaf
 * 	This method checks tree node is leaf or not.
 * 
 *
 */
public class TreeNode {
	private String[] keywords = new String[100];
	private TreeNode left;
	private TreeNode right;
	/**
	 * this constructor get three parameters, and these parameters initialize three data fields
	 * @param keywords
	 * 	it initializes the array 'keywords'
	 * @param left
	 * 	it initializes the left reference of current node.
	 * @param right
	 * 	it initializes the right reference of current node.
	 */
	public TreeNode(String[] keywords, TreeNode left, TreeNode right){
		this.keywords = keywords;
		this.left = left;
		this.right = right;
	}
	/**
	 * This method is mutator method of data field 'keywords'
	 * @param keywords
	 * 	The parameter will be saved(change) the keyword value.
	 */
	public void setKeywords(String[] keywords){
		this.keywords = keywords;
	}
	/**
	 * This method is mutator method of data field 'left'
	 * @param left
	 * 	The parameter will be saved as new reference of node's left
	 */
	public void setLeft(TreeNode left){
		this.left = left;
	}
	
	/**
	 * This method is mutator method of data field 'right'
	 * @param right
	 * The parameter will be saved as new reference of node's right
	 */
	public void setRight(TreeNode right){
		this.right = right;
	}
	/**
	 * This method is accessor method of data field 'keywords'
	 * @return
	 * 	This method will return keywords in current Node.
	 */
	public String[] getKeywords(){
		String[] realKeywords;
		int count=0;
		for(int i=0; i<keywords.length; i++){
			if(keywords[i]=="")
				break;
			count++;
		}
		realKeywords = new String[count];
		for(int i=0; i<realKeywords.length; i++){
			realKeywords[i] = keywords[i];
		}
		return realKeywords;
	}
	/**
	 * This method is accessor method of data field 'left'
	 * @return
	 * This method will return left reference in current Node.
	 */
	public TreeNode getLeft(){
		return left;
	}
	/**
	 * This method is accessor method of data field 'right'
	 * @return
	 *  This method will return right reference in current Node.
	 */
	public TreeNode getRight(){
		return right;
	}
	/**
	 * This method checks tree node is leaf or not.
	 * @return
	 * 	If there are no children node, this method will return true.
	 */
	public boolean isLeaf(){
		return left==null&&right==null;
	}
	
}
