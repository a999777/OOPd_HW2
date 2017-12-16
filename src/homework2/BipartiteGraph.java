package homework2;

import java.util.*;

import static homework2.IllegalArgumentException.*;


/**
 * A BipartiteGraph is an abstraction of a graph that has two groups of nodes: black nodes and white nodes. In a
 * bipartite graph there can only be edges from white nodes to black nodes and vice versa (i.e. no edges between nodes
 * that are of the same color).
 * Thus, a typical BipartiteGraph has the properties {whiteNodes, blackNodes}.
 */
public class BipartiteGraph<L, D> {

    //Abs. Function:
    //  Represents a bipartite graph whose all white nodes are stored in this.whiteNodes and all black nodes are stored
    //  in this.blackNodes. The name of this bipartite graph is this.name.

    //Rep. Invariant:
    //  this.whiteNodes and this.blackNodes cannot be null.


    private final Map<L, Node<L, D>> whiteNodes;
    private final Map<L, Node<L, D>> blackNodes;

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
    public void addBlackNode(L nodeLabel, D data) throws NodeWithThisLabelAlreadyExistException {
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
    public void addWhiteNode(L nodeLabel, D data) throws NodeWithThisLabelAlreadyExistException {
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
    private boolean isNodeWithNameExists(L nodeLabel) {
        checkRep();
        return (this.whiteNodes.containsKey(nodeLabel) || this.blackNodes.containsKey(nodeLabel));
    }


    /**
     * @requires parentName, childName, edgeLabel is not null
     * @modifies this
     * @effects If parentName or childName doesn't exist as nodes in this throws NodeWithThisLabelDoesntExistException
     *          Else if parentName has an outgoing edge with the label edgeLabel or childName has an incoming edge with
     *              the label edgeLabel throws EdgeWithThisLabelAlreadyExistException
     *          Else if parent and child is of the same color throw
     *          Else creates an edge from parentName Node to childName Node labeled edgeLabel
     */
    public void addEdge(L parentName, L childName, L edgeLabel) throws IllegalArgumentException{
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
    public String listChildren(L parentName) throws NodeWithThisLabelDoesntExistException {
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
    public String listParents(L childName) throws NodeWithThisLabelDoesntExistException {
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
    public L getChildByEdgeLabel(L parentName, L edgeLabel) throws NodeWithThisLabelDoesntExistException,
        ChildDoesntExistException {
    	//TODO: Implement this method
        checkRep();
        Node parent = getNodeByNodeLabel(parentName);
        Node child = parent.findChildByEdgeLabel(edgeLabel);
        checkRep();
    	return (L)child.getLabel();
    }


    /**
     * @requires childName and edgeLabel is not null
     * @effects If this doesn't contain a node named childName throws NodeWithThisLabelDoesntExistException
     *          Else if childName doesn't have an ingoing edge with the label edgeLabel throws ParentDoesntExistException
     * 		    Otherwise the name of the parent of childName that is connected by the edge labeled edgeLabel in this.
     */
    public L getParentByEdgeLabel(L childName, L edgeLabel) throws NodeWithThisLabelDoesntExistException,
            ParentDoesntExistException {
        //TODO: Implement this method
        checkRep();
        Node child = getNodeByNodeLabel(childName);
        Node parent = child.findParentByEdgeLabel(edgeLabel);
        checkRep();
        return (L)parent.getLabel();
    }


    /**
     * @requires nodeLabel is not null
     * @effects If this doesn't contain a node named nodeLabel throws NodeWithThisLabelDoesntExistException
     *          Else returns the Node connected to nodeLabel label
     */
    private Node getNodeByNodeLabel(L nodeLabel) throws NodeWithThisLabelDoesntExistException {
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
