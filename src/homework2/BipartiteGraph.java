package homework2;

import java.util.*;

import static homework2.HW2Exception.*;


/**
 * A BipartiteGraph is an abstraction of a graph that has two groups of nodes: black nodes and white nodes. In a
 * bipartite graph there can only be edges from white nodes to black nodes and vice versa (i.e. no edges between nodes
 * that are of the same color).
 * Thus, a typical BipartiteGraph has the properties {whiteNodes, blackNodes}.
 */
public class BipartiteGraph<L> {

    //Abs. Function:
    //  Represents a bipartite graph whose all white nodes are stored in this.whiteNodes and all black nodes are stored
    //  in this.blackNodes. Other than that, the graph also stores a set of all the edges it has in this.edgesLabels.

    //Rep. Invariant:
    //  this.whiteNodes and this.blackNodes cannot be null.
    //  this.edgesLabel cannot be null.


    private final Map<L, Node<L>> whiteNodes;
    private final Map<L, Node<L>> blackNodes;
    private final Set<L> edgesLabels;


    /**
     * @requires name is not null
     * @effects Constructs a BipartiteGraph named name.
     */
    public BipartiteGraph() {
        this.whiteNodes = new HashMap<>();
        this.blackNodes = new HashMap<>();
        this.edgesLabels = new HashSet<>();
        checkRep();
    }

    
    /**
     * @requires nodeLabel is not null
     * @modifies this
     * @effects Creates a new black node labeled nodeLabel and adds it to the graph if there is no node with nodeName that
     *          is already in the graph, throws NodeWithThisLabelAlreadyExistException otherwise
     *
     */
    public void addBlackNode(L nodeLabel, Object data) throws NodeWithThisLabelAlreadyExistException {
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
    public void addWhiteNode(L nodeLabel, Object data) throws NodeWithThisLabelAlreadyExistException {
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
     *              the label edgeLabel throws EdgeLabelAlreadyExists
     *          Else if parent and child is of the same color throws SameColorException
     *          Else creates an edge from parentName Node to childName Node labeled edgeLabel
     */
    public void addEdge(L parentName, L childName, L edgeLabel) throws HW2Exception {
        checkRep();

        //Getting the nodes that we want to connect. These lines might throw an exception, we'll just pass it on
        Node child = getNodeByNodeLabel(childName);
        Node parent = getNodeByNodeLabel(parentName);
        //Connecting the edge. These lines also might throw an exception that we'll pass on
        child.appendToParents(edgeLabel, parent);
        parent.appendToChildren(edgeLabel, child);
        this.edgesLabels.add(edgeLabel);
        checkRep();
    }

    
    /**
     * @return a Set of the Edges of this (only unique labels)
     */
    public Set<L> getEdgesLabels() {
        checkRep();
        return Collections.unmodifiableSet(this.edgesLabels);
    }


    /**
     * @return a list of the names of all the black nodes in this
     */
    public List<L> listBlackNodes() {
        checkRep();
        //Creating an array list with the labels of all the black nodes
        List<L> blackNodesList = new ArrayList<>(this.blackNodes.keySet());
        return Collections.unmodifiableList(blackNodesList);
    }


    /**
     * @return a list of the names of all the white nodes in this
     */
    public List<L> listWhiteNodes() {
        checkRep();
        //Creating an array list with the labels of all the white nodes
        List<L> whiteNodesList = new ArrayList<>(this.whiteNodes.keySet());
        return Collections.unmodifiableList(whiteNodesList);
    }

    
    /**
     * @requires parentName is not null
     * @effects If this doesn't contain a node named parentName throws NodeWithThisLabelDoesntExistException
     *         Otherwise returns a list of the labels of the children of parentName in this.
     */
    public List<L> listChildren(L parentName) throws NodeWithThisLabelDoesntExistException {
        checkRep();
        Node parent = getNodeByNodeLabel(parentName);
        return parent.getChildrenList();
    }

    
    /**
     * @requires childName is not null
     * @effects If this doesn't contain a node named parentName throws NodeWithThisLabelDoesntExistException
     *          Otherwise returns a list of the labels of the parents of childName in this.
     */
    public List<L> listParents(L childName) throws NodeWithThisLabelDoesntExistException {
        checkRep();
        Node child = getNodeByNodeLabel(childName);
        return child.getParentsList();
    }

    
    /**
     * @requires parentName and edgeLabel is not null
     * @effects If this doesn't contain a node named parentName throws NodeWithThisLabelDoesntExistException
     *          Else if parentName doesn't have an outgoing edge with the label edgeLabel throws EdgeWithLabelDoesntExistException
     * 		    Otherwise the name of the child of parentName that is connected by the edge labeled edgeLabel in this.
     */
    public L getChildByEdgeLabel(L parentName, L edgeLabel) throws NodeWithThisLabelDoesntExistException,
        EdgeWithLabelDoesntExistException {
        checkRep();
        Node parent = getNodeByNodeLabel(parentName);
        Node child = parent.findChildByEdgeLabel(edgeLabel);
        checkRep();
    	return (L)child.getLabel();
    }


    /**
     * @requires childName and edgeLabel is not null
     * @effects If this doesn't contain a node named childName throws NodeWithThisLabelDoesntExistException
     *          Else if childName doesn't have an ingoing edge with the label edgeLabel throws EdgeWithLabelDoesntExistException
     * 		    Otherwise the name of the parent of childName that is connected by the edge labeled edgeLabel in this.
     */
    public L getParentByEdgeLabel(L childName, L edgeLabel) throws NodeWithThisLabelDoesntExistException,
            EdgeWithLabelDoesntExistException {
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
    public Node getNodeByNodeLabel(L nodeLabel) throws NodeWithThisLabelDoesntExistException {
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
        assert(this.edgesLabels != null):"A graph's edgges labels cannot be null!";
    }

}
