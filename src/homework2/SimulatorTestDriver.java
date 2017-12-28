package homework2;

import java.util.*;

import static homework2.HW2Exception.*;

/**
 * This class implements a testing driver for Simulator. The driver manages
 * Simulators for payment channels
 */
public class SimulatorTestDriver {

    private Map<String, Simulator<String, Transaction>> simulators;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public SimulatorTestDriver() {
        this.simulators = new HashMap<>();
    }


    /**
     * @requires simName is not null
     * @modifies this
     * @effects Creates a new simulator named simName. The simulator's graph is
     *          initially empty.
     */
    public void createSimulator(String simName) {
        Simulator<String, Transaction> newSim = new Simulator<>();
        this.simulators.put(simName, newSim);
    }


    /**
     * @requires createSimulator(simName)
     *           and channelName is not null and channelName has
     *           not been used in a previous addChannel()  or
     *           addParticipant() call on this object
     *           limit larger than 0
     * @modifies simulator named simName
     * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
     *          the simulator named simName.
     */
    public void addChannel(String simName, String channelName, double limit)
            throws NodeWithThisLabelAlreadyExistException {
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        Channel newChannel = new Channel(limit, channelName);
        currSim.addPipe(channelName, newChannel);
    }


    /**
     * @requires createSimulator(simName) and participantName is not null
     *           and participantName has not been used in a previous addParticipant(), addChannel()
     *           call on this object
     *           fee larger than 0
     * @modifies simulator named simName
     * @effects Creates a new Participant named by the String participantName and add
     *          it to the simulator named simName.
     */
    public void addParticipant(String simName, String participantName, double fee)
            throws NodeWithThisLabelAlreadyExistException {
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        Participant newParticipant = new Participant(fee, participantName);
        currSim.addFilter(participantName, newParticipant);
    }


    /**
     * @requires createSimulator(simName) and ((addPipe(parentName) and
     *           addFilter(childName)) or (addFilter(parentName) and
     *           addPipe(childName))) and edgeLabel is not null and node named
     *           parentName has no other outgoing edge labeled edgeLabel
     *           and node named childName has no other incoming edge labeled edgeLabel
     * @modifies simulator named simName
     * @effects Adds an edge from the node named parentName to the node named
     *          childName in the simulator named simName. The new edge's label
     *          is the String edgeLabel.
     */
    public void addEdge(String simName, String parentName, String childName, String edgeLabel)
            throws HW2Exception {
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        currSim.addEdge(parentName, childName, edgeLabel);
    }


    /**
     * @requires createSimulator(simName) and addChannel(channelName)
     *           A transaction Transaction is not null
     * @modifies channel named channelName
     * @effects pushes the Transaction into the channel named channelName in the
     *          simulator named simName.
     */
    public void sendTransaction(String simName, String channelName, Transaction tx)
            throws NodeWithThisLabelDoesntExistException, ChannelReachedLimit {
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        Node<String> channelNode = currSim.getSimulatorElementByLabel(channelName);
        Channel channel = (Channel)channelNode.getData();
        channel.addTransaction(tx);
    }


    /**
     * @requires list is not null
     * @return returns a space separated string constructed from all the elements of list, sorted in alphabetical order
     */
    private String convertListToAlphabeticSpacedString(List<Transaction> list) {
        String toRet = "";
        for(Transaction t: list) {
            toRet +=  t.getValue();
            toRet +=  " ";
        }
        toRet = toRet.trim();
        return toRet;
    }


    /**
     * @requires addChannel(channelName)
     * @return a space-separated list of the Transaction values currently in the
     *         channel named channelName in the simulator named simName.
     */
    public String listContents(String simName, String channelName) throws NodeWithThisLabelDoesntExistException{
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        Node<String> channelNode = currSim.getSimulatorElementByLabel(channelName);
        Channel channel = (Channel)channelNode.getData();
        return this.convertListToAlphabeticSpacedString(channel.listTransactions());
    }


    /**
     * @requires addParticipant(participantName)
     * @return The sum of all  Transaction values stored in the storage of the participant participantName in the simulator simName
     */
    public double getParticipantBalace(String simName, String participantName)
            throws NodeWithThisLabelDoesntExistException{
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        Node<String> participantNode = currSim.getSimulatorElementByLabel(participantName);
        Participant participant = (Participant)participantNode.getData();
        return participant.getParticipantBalance();
    }


    /**
     * @requires createSimulator(simName)
     * @modifies simulator named simName
     * @effects runs simulator named simName for a single time slice.
     */
    public void simulate(String simName) {
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        currSim.simulate();
    }


    /**
     * Prints the all edges.
     *
     * @requires simName the sim name
     * @effects Prints the all edges.
     */
    public void printAllEdges(String simName) {
        Simulator<String, Transaction> currSim = this.simulators.get(simName);
        Set<String> edgesSet = currSim.getEdgesLabels();
        String toPrint = "";
        for(String label: edgesSet){
            toPrint += label;
            toPrint += " ";
        }
        System.out.println(toPrint.trim());
    }

}
