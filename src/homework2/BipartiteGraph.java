package homework2;

import java.util.*;

import static homework2.IllegalArgumentException.*;


/**
 * A BipartiteGraph is an abstraction of a graph that has two groups of nodes: black nodes and white nodes. In a
 * bipartite graph there can only be edges from white nodes to black nodes and vice versa (i.e. no edges between nodes
 * that are of the same color).
 * Thus, a typical BipartiteGraph has the properties {whiteNodes, blackNodes}.
 */
public class BipartiteGraph {

    //Abs. Function:
    //  Represents a bipartite graph whose all white nodes are stored in this.whiteNodes and all black nodes are stored
    //  in this.blackNodes. The name of this bipartite graph is this.name.

    //Rep. Invariant:
    //  this.whiteNodes and this.blackNodes cannot be null.


    private final Map<String, Node> whiteNodes;
    private final Map<String, Node> blackNodes;

    /**
     * @requires name is not null
     * @effects Constructs a BipartiteGraph named name.
     */
    public BipartiteGraph() {
    	// TODO: Implement this constructor
        this.whiteNodes = new HashMap<>();
        this.blackNodes = new HashMap<>();
        checkRep();
    }

    
    /**
     * @requires nodeLabel is not null
     * @modifies this
     * @effects Creates a new black node labeled nodeLabel and adds it to the graph if there is no node with nodeName that
     *          is already in the graph, throws NodeWithThisLabelAlreadyExistException otherwise
     *
     */
    public void addBlackNode(String nodeLabel, String data) throws NodeWithThisLabelAlreadyExistException {
    	// TODO: Implement this method
        checkRep();
        if(isNodeWithNameExists(nodeLabel)) {
            throw new NodeWithThisLabelAlreadyExistException();
        }
        Node toAdd = new Node(nodeLabel, data, Node.Color.BLACK);
        this.blackNodes.put(nodeLabel, toAdd);
        checkRep();
    }


    /**
     * @requires nodeLabel is not null
     * @modifies this
     * @effects Creates a new white node labeled nodeLabel and adds it to the graph if there is no node with nodeName that
     *          is already in the graph, throws NodeWithThisLabelAlreadyExistException otherwise
     *
     */
    public void addWhiteNode(String nodeLabel, String data) throws NodeWithThisLabelAlreadyExistException {
    	//TODO: Implement this method
        checkRep();
        if(isNodeWithNameExists(nodeLabel)) {
            throw new NodeWithThisLabelAlreadyExistException();
        }
        Node toAdd = new Node(nodeLabel, data, Node.Color.WHITE);
        this.whiteNodes.put(nodeLabel, toAdd);
        checkRep();
    }


    /**
     * @requires nodeLabel is not null
     * @modifies Nothing
     * @effects Returns True if a node with nodeLabel already exists
     *          False otherwise
     */
    private boolean isNodeWithNameExists(String nodeLabel) {
        // TODO: Implement this method
        checkRep();
        return (this.whiteNodes.containsKey(nodeLabel) || this.blackNodes.containsKey(nodeLabel));
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
     * @effects If parentName or childName doesn't exist as nodes in this throws NodeWithThisLabelDoesntExistException
     *          Else if parentName has an outgoing edge with the label edgeLabel or childName has an incoming edge with
     *              the label edgeLabel throws EdgeWithThisLabelAlreadyExistException
     *          Else if parent and child is of the same color throw
     *          Else creates an edge from parentName Node to childName Node labeled edgeLabel
     */
    public void addEdge(String parentName, String childName, String edgeLabel) throws IllegalArgumentException{
    	//TODO: Implement this method
        checkRep();
        // These lines might throw an exception, we'll just pass it on
        Node child = getNodeByNodeLabel(childName);
        Node parent = getNodeByNodeLabel(parentName);

        // These line also might throw an exception that we'll pass on
        child.appendToParents(edgeLabel, parent);
        parent.appendToChildren(edgeLabel, child);
        checkRep();
    }

    
    /**
     * @requires nothing
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes() {
    	//TODO: Implement this method
        checkRep();
        List<String> blackNodesList = new ArrayList<String>();
        Iterator keysIterator = this.blackNodes.keySet().iterator();
        while (keysIterator.hasNext()) {
            blackNodesList.add(keysIterator.next().toString());
        }
        Collections.sort(blackNodesList);
        String blackNodesString = "";
        for(String currNode: blackNodesList) {
            blackNodesString += currNode;
            blackNodesString += " ";
        }
        checkRep();
        return blackNodesString.trim();
    }

    
    /**
     * @requires nothing
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes() {
    	//TODO: Implement this method
        checkRep();
        List<String> whiteNodesList = new ArrayList<String>();
        Iterator keysIterator = this.whiteNodes.keySet().iterator();
        while (keysIterator.hasNext()) {
            whiteNodesList.add(keysIterator.next().toString());
        }
        Collections.sort(whiteNodesList);
        String whiteNodesString = "";
        for(String currNode: whiteNodesList) {
            whiteNodesString += currNode;
            whiteNodesString += " ";
        }
        checkRep();
        return whiteNodesString.trim();
    }

    
    /**
     * @requires parentName is not null
     * @effects If this doesn't contain a node named parentName throws NodeWithThisLabelDoesntExistException
     *         Otherwise a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String parentName) throws NodeWithThisLabelDoesntExistException {
    	//TODO: Implement this method
        checkRep();
        Node parent = getNodeByNodeLabel(parentName);
        return parent.getChildrenList();
        //We need to sort alphabetically it somehow fixme
    }

    
    /**
     * @requires childName is not null
     * @effects If this doesn't contain a node named parentName throws NodeWithThisLabelDoesntExistException
     *         Otherwise a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listParents(String childName) throws NodeWithThisLabelDoesntExistException {
    	//TODO: Implement this method
        checkRep();
        Node child = getNodeByNodeLabel(childName);
        return child.getParentsList();
        //We need to sort alphabetically it somehow fixme
    }

    
    /**
     * @requires parentName and edgeLabel is not null
     * @effects If this doesn't contain a node named parentName throws NodeWithThisLabelDoesntExistException
     *          Else if parentName doesn't have an outgoing edge with the label edgeLabel throws ChildDoesntExistException
     * 		    Otherwise the name of the child of parentName that is connected by the edge labeled edgeLabel in this.
     */
    public String getChildByEdgeLabel(String parentName, String edgeLabel) throws NodeWithThisLabelDoesntExistException,
        ChildDoesntExistException {
    	//TODO: Implement this method
        checkRep();
        Node parent = getNodeByNodeLabel(parentName);
        Node child = parent.findChildByEdgeLabel(edgeLabel);
        checkRep();
    	return child.getLabel();
    }


    /**
     * @requires childName and edgeLabel is not null
     * @effects If this doesn't contain a node named childName throws NodeWithThisLabelDoesntExistException
     *          Else if childName doesn't have an ingoing edge with the label edgeLabel throws ParentDoesntExistException
     * 		    Otherwise the name of the parent of childName that is connected by the edge labeled edgeLabel in this.
     */
    public String getParentByEdgeLabel(String childName, String edgeLabel) throws NodeWithThisLabelDoesntExistException,
            ParentDoesntExistException {
        //TODO: Implement this method
        checkRep();
        Node child = getNodeByNodeLabel(childName);
        Node parent = child.findParentByEdgeLabel(edgeLabel);
        checkRep();
        return parent.getLabel();
    }


    /**
     * @requires nodeLabel is not null
     * @effects If this doesn't contain a node named nodeLabel throws NodeWithThisLabelDoesntExistException
     *          Else returns the Node connected to nodeLabel label
     */
    private Node getNodeByNodeLabel(String nodeLabel) throws NodeWithThisLabelDoesntExistException {
        if(!isNodeWithNameExists(nodeLabel)) {
            throw new NodeWithThisLabelDoesntExistException();
        }
        if(this.whiteNodes.containsKey(nodeLabel)) {
            return whiteNodes.get(nodeLabel);
        }
        return blackNodes.get(nodeLabel);
    }


    /**
     * Check to see if the representation invariant is being violated
     * @throw AssertionError if representation invariant is violated
     */
    private void checkRep() {
        assert(this.whiteNodes != null):"A graph's white nodes cannot be null!";
        assert(this.blackNodes != null):"A graph's black nodes cannot be null!";
    }

}
