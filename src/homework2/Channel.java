package homework2;


import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class implements a channel in a graph of payment channels.
 * A Channel has a limit on the sum of payments that can move through it.
 */
public class Channel implements Simulatable<String> {

    private double limit;
    private String channelName;
    List<Transaction> transactions = new ArrayList<Transaction>();

    /**
     * @requires value greater then 0 and name not equals null
     * @modifies this
     * @effects Constructs a new Channel
     */
    public Channel(double limit, String name){
        this.limit = limit;
        this.channelName = name;
        checkRep();
    }

    /**
     * @requires transaction not equals none,
     * @modifies this.transactions
     * @effects adds new transaction to this.transactions if this didn't reach limit
     *   Else throws ChannelReachedLimit
     */
    // Fixme remove package name
    public void addTransaction(Transaction transaction)throws homework2.IllegalArgumentException.ChannelReachedLimit{
        checkRep();
        if (this.limit - transaction.getValue() < 0){
            throw new homework2.IllegalArgumentException.ChannelReachedLimit();
        }
        this.transactions.add(transaction);
        this.limit -= transaction.getValue();
        checkRep();
    }

    /**
     * @requires graph not equals none,
     * @modifies this
     * @effects simulates passing of transactions from this to Participant
     */
    @Override
    public void simulate(BipartiteGraph<String> graph){
        // in case the channel doesn't have transaction do nothing
        if (this.transactions.isEmpty()){
            return;
        }
        // we have at least one transaction
        try {
            // first we get the node of the channel
            Node<String> node = graph.getNodeByNodeLabel(this.channelName);
            // childList can either be empty, or contains only one child
            List<String> childList = node.getChildrenList();
            // if childList is empty clear all transactions
            if (childList.isEmpty()){
                this.transactions.clear();
                return;
            }
            // we have one child
            Node<String> participantNode = graph.getNodeByNodeLabel(childList.get(0));
            Participant participant = (Participant)participantNode.getData();
            for(Transaction t: this.transactions){
                participant.addTransaction(t);
            }
        } catch (homework2.IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    /**
     * @requires nothing
     * @return a space-separated list of the Transaction values currently in the channel
     */
    public List listTransactions() {
        checkRep();
        return Collections.unmodifiableList(this.transactions);
    }

    /**
     * Check to see if the representation invariant is being violated
     * @throw AssertionError if representation invariant is violated
     */
    private void checkRep(){
        assert (this.limit >= 0);
        assert (this.channelName != null);
        assert (this.transactions != null);
        for (Transaction t : this.transactions){
            assert (t != null);
        }
    }
}
