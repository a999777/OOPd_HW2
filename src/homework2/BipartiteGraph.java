package homework2;

import java.util.Map;

/**
 * This class implements a Bipartite Graph. TODO
 *
 */
public class BipartiteGraph {

    //Rep.Invariant:

    //Abs. function:

    private final Map<String, Node> whiteNodes;
    private final Map<String, Node> blackNodes;

    /**
     * @requires name is not null
     * @effects Constructs a BipartiteGraph named name.
     */
    public BipartiteGraph(String name) {
    	// TODO: Implement this constructor
    }

    
    /**
     * @requires nodeName is not null
     * @modifies this
     * @effects Creates a new black node named nodeName and adds it to the graph if there is no node with nodeName that
     *          is already in the graph, throws NodeNameAlreadyExists otherwise
     *
     */
    public void addBlackNode(String nodeName) {
    	// TODO: Implement this method
    }


    /**
     * @requires nodeName is not null
     * @modifies this
     * @effects Creates a new white node named nodeName and adds it to the graph if there is no node with nodeName that
     *          is already in the graph, throws NodeNameAlreadyExists otherwise
     *
     */
    public void addWhiteNode(String nodeName) {
    	//TODO: Implement this method
    }


    /**
     * @requires nodeName is not null
     * @modifies Nothing
     * @effects Returns True if a node with nodeName already exists
     *          False otherwise
     */
    private boolean isNodeWithNameExists(String nodeName) {
        // TODO: Implement this method
    }


    /* TODO: What we must verify when using this function:
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     */
    /**
     * @requires parentName, childName, edgeLabel is not null
     * @modifies this
     * @effects If one of the arguments is null throws NullArgument
     *          Else if parentName or childName doesn't exist as nodes in this throws NodeDoesntExist
     *          Else if parentName has an outgoing edge with the label edgeLabel or childName has an incoming edge with
     *              the label edgeLabel throws LabelNameAlreadyExists
     *          Else creates an edge from parentName Node to childName Node labeled edgeLabel
     */
    public void addEdge(String parentName, String childName, String edgeLabel) {
    	//TODO: Implement this method
    }

    
    /**
     * @requires nothing
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes() {
    	//TODO: Implement this method

    }

    
    /**
     * @requires nothing
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
    	//TODO: Implement this method
    	
    	
    }

    
    /**
     * @requires parentName is not null
     * @effects If this doesn't contain a node named parentName throws NodeDoesntExist
     *         Otherwise a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String parentName) {
    	//TODO: Implement this method
    }

    
    /**
     * @requires childName is not null
     * @effects If this doesn't contain a node named parentName throws NodeDoesntExist
     *         Otherwise a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listParents(String childName) {
    	//TODO: Implement this method
    }

    
    /**
     * @requires parentName and edgeLabel is not null
     * @effects If this doesn't contain a node named parentName throws NodeDoesntExist
     *          Else if parentName doesn't have an outgoing edge with the label edgeLabel throws LabelNameDoesntExists
     * 		    Otherwise the name of the child of parentName that is connected by the edge labeled edgeLabel in this.
     */
    public String getChildByEdgeLabel(String parentName, String edgeLabel) {
    	//TODO: Implement this method
    	
    	
    }


    /**
     * @requires childName and edgeLabel is not null
     * @effects If this doesn't contain a node named childName throws NodeDoesntExist
     *          Else if childName doesn't have an ingoing edge with the label edgeLabel throws LabelNameDoesntExists
     * 		    Otherwise the name of the parent of childName that is connected by the edge labeled edgeLabel in this.
     */
    public String getParentByEdgeLabel(String childName, String edgeLabel) {
    	//TODO: Implement this method
    }
}
