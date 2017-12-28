package homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static homework2.HW2Exception.*;


/**
 * This class implements a channel in a graph of payment channels.
 * A Channel has a limit on the sum of payments that can move through it. A channel is a simulatable object and contains
 * the properties{limit, channelName, transactions}.
 */
public class Channel implements Simulatable<String> {

    //Abs. Function:
    //  Represents a Simulatable object that is called a channel. A channel has a limit of transactions total costs that
    //  can pass through it and is stored in this.limit. It also has a name that is stored in this.channelName.
    //  In addition, the channel stores all its current transactions in this.transactions.

    //Rep. Invariant:
    //  this.limit has to be bigger than 0, this.channelName cannot be null, this.transactions cannot be null.

    private double limit;
    private String channelName;
    List<Transaction> transactions = new ArrayList<Transaction>();


    /**
     * @requires limit is greater then 0 and name is not null
     * @modifies this
     * @effects Constructs a new Channel
     */
    public Channel(double limit, String name){
        this.limit = limit;
        this.channelName = name;
        checkRep();
    }


    /**
     * @requires transaction is not null
     * @modifies this.transactions
     * @effects adds a new transaction to this.transactions if this.limit wasn't reached yet
     *          Else throws ChannelReachedLimit
     */
    public void addTransaction(Transaction transaction)throws ChannelReachedLimit{
        checkRep();
        if (this.limit - transaction.getValue() < 0){
            throw new ChannelReachedLimit();
        }
        this.transactions.add(transaction);
        this.limit -= transaction.getValue();
        checkRep();
    }


    /**
     * @requires graph is not null
     * @modifies this
     * @effects simulates passing of transactions from this to a Participant
     */
    @Override
    public void simulate(BipartiteGraph<String> graph){
        // in case the channel doesn't have transaction do nothing
        if (this.transactions.isEmpty()) {
            return;
        }
        // here we can assume we have at least one transaction
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
        } catch (HW2Exception e){
            e.printStackTrace();
        }
        this.transactions.clear();
    }


    /**
     * @return a list of the transactions values currently in the channel
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
        assert (this.limit >= 0):"A channel limit cannot be smaller than 0!";
        assert (this.channelName != null):"A channel name cannot be null!";
        assert (this.transactions != null):"A channel transactions cannot be null!";
        for (Transaction t : this.transactions){
            assert (t != null):"A transaction name that is stored in a channel.transactions cannot be null!";
        }
    }

}
