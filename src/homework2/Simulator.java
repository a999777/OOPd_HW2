package homework2;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static homework2.IllegalArgumentException.*;
/**
 *Simulator class contains filters and pipes in a bipartite graph
 */
public class Simulator<L, T> {
    //Abs. Function:
    //  Represents a Simulator with a bipartite graph whose all white nodes represent filters and all black nodes
    //  represent pipes.
    //  An edge from a pipe (black node) to a filter (white node) represents an input stream from the pipe
    //  An edge from a filter (white node) to a pipe (black node) re[resents an output stream from the pipe

    //Rep. Invariant:
    //  this.graph is not null, every node in this.graph is not null and this.round > 0

    private BipartiteGraph<L> graph;
    private int round;

    /**
     * @effects Initializes this with new bipartite graph and round equals zero
     */
    public Simulator(){
        this.graph = new BipartiteGraph<>();
        this.round = 0;
        checkRep();
    }

    /**
     * @requires filter and filterLabel not equal null and no filter with filterLabel exists already
     * @modifies graph.
     * @effects add a white node to the graph, associated with filter.
     * @throws NodeWithThisLabelAlreadyExistException if a filter with filterLabel already exists.
     */
    public void addFilter(L filterLabel, Object filter)
            throws IllegalArgumentException.NodeWithThisLabelAlreadyExistException {
        checkRep();
        graph.addWhiteNode(filterLabel, filter);
        checkRep();
    }

    /**
     * @requires pipe and pipeLabel not equal null and no pipe with pipeLabel exists already
     * @modifies graph.
     * @effects add a black node to the graph, associated with filter.
     * @throws NodeWithThisLabelAlreadyExistException if a filter with filterLabel already exists.
     */
    public void addPipe(L pipeLabel, Object pipe)
            throws IllegalArgumentException.NodeWithThisLabelAlreadyExistException {
        checkRep();
        graph.addBlackNode(pipeLabel, pipe);
        checkRep();
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
    public void addEdge(L parentName, L childName, L edgeLabel) throws IllegalArgumentException{
        checkRep();
        this.graph.addEdge(parentName, childName, edgeLabel);
        checkRep();
    }

    /**
     * @requires Nothing
     * @modifies this.graph
     * @effects performs a simulation for one round
     */
    public void simulate(){
        checkRep();
        // get all keys of nodes
        List<L> filters = this.graph.listWhiteNodes();
        List<L> pipes = this.graph.listBlackNodes();
        // iterate over pipes and do simulation
        for (L key: pipes){
            try {
                 Node<L> pipeNode = this.graph.getNodeByNodeLabel(key);
                 Simulatable<L> pipe = (Simulatable<L>) pipeNode.getData();
                 pipe.simulate(this.graph);
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            }

        }
        // iterate over filters and do simulation
        for (L key: filters){
            try {
                Node<L> filterNode = this.graph.getNodeByNodeLabel(key);
                Simulatable<L> filter = (Simulatable<L>) filterNode.getData();
                filter.simulate(this.graph);
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        this.round += 1;
        checkRep();
    }

    /**
     * @requires nodeLabel is not null
     * @effects If this doesn't contain a node named nodeLabel throws NodeWithThisLabelDoesntExistException
     *          Else returns the Node connected to nodeLabel label
     */
    public Node getSimulatorElementByLabel(L nodeLabel) throws NodeWithThisLabelDoesntExistException {
        Node<L> node = this.graph.getNodeByNodeLabel(nodeLabel);
        return node;
    }

    /**
     * @return a Set of the Edges of this.graph
     */
    public Set<L> getEdgesLabels() {
        checkRep();
        return this.graph.getEdgesLabels();
    }

    /**
     * Check to see if the representation invariant is being violated
     * @throw AssertionError if representation invariant is violated
     */
    private void checkRep() {
        assert (this.graph != null);
        assert (this.round >= 0);
        List<L> filters = this.graph.listWhiteNodes();
        List<L> pipes = this.graph.listBlackNodes();
        for (L key: pipes){
            try {
                Simulatable<L> pipe = (Simulatable<L>) this.graph.getNodeByNodeLabel(key);
                assert (key != null);
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        // iterate over filters and do simulation
        for (L key: filters){
            try {
                Simulatable<L> filter = (Simulatable<L>) this.graph.getNodeByNodeLabel(key);
                assert (key != null);
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }
}
