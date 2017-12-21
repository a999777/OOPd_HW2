package homework2;

import java.util.*;
import static homework2.IllegalArgumentException.*;


/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String, String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
        this.graphs = new HashMap<>();
    }

    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
        BipartiteGraph<String, String> newGraph = new BipartiteGraph();
        this.graphs.put(graphName, newGraph);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName) throws NodeWithThisLabelAlreadyExistException {
    	BipartiteGraph currentGraph = this.graphs.get(graphName);
    	currentGraph.addBlackNode(nodeName, null);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName) throws NodeWithThisLabelAlreadyExistException {
        BipartiteGraph currentGraph = this.graphs.get(graphName);
        currentGraph.addWhiteNode(nodeName, null);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel) throws IllegalArgumentException{
        BipartiteGraph currentGraph = this.graphs.get(graphName);
        currentGraph.addEdge(parentName, childName, edgeLabel);
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
        BipartiteGraph currentGraph = this.graphs.get(graphName);
        return convertListToAlphabeticSpacedString(currentGraph.listBlackNodes());
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
        BipartiteGraph currentGraph = this.graphs.get(graphName);
        return convertListToAlphabeticSpacedString(currentGraph.listWhiteNodes());
    }

    
    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName) throws NodeWithThisLabelDoesntExistException {
        BipartiteGraph currentGraph = this.graphs.get(graphName);
        return convertListToAlphabeticSpacedString(currentGraph.listChildren(parentName));
    }

    
    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName) throws NodeWithThisLabelDoesntExistException {
        BipartiteGraph currentGraph = this.graphs.get(graphName);
            return convertListToAlphabeticSpacedString(currentGraph.listParents(childName));
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel) throws NodeWithThisLabelDoesntExistException,
                                        EdgeWithLabelDoesntExistException{
        BipartiteGraph currentGraph = this.graphs.get(graphName);
        return (String)currentGraph.getChildByEdgeLabel(parentName, edgeLabel);
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) throws NodeWithThisLabelDoesntExistException,
                                         EdgeWithLabelDoesntExistException {
        BipartiteGraph currentGraph = this.graphs.get(graphName);
        return (String)currentGraph.getParentByEdgeLabel(childName, edgeLabel);
    }


    /**
     * @requires list is not null
     * @return returns a space separated string constructed from all the elements of list, sorted in alphabetical order
     */
    private String convertListToAlphabeticSpacedString(List<String> list) {
        //Using a new list because the one we get is unmodifiable
        List<String> currentList = new ArrayList<>(list);
        Collections.sort(currentList);
        //Create the updated string
        String toRet = "";
        for(String label: currentList) {
            toRet +=  label;
            toRet +=  " ";
        }
        toRet = toRet.trim();
        return toRet;
    }

}
