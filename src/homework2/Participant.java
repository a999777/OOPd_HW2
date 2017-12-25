package homework2;


import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a Participant in a graph of participants and channels.
 * A Participant has a fee for every payment that goes through him and he also has storage buffer with fees.
 */
public class Participant implements Simulatable<String>{

    private double fee;
    private String participantName;
    List<Transaction> storageBuffer = new ArrayList<>();
    List<Transaction> outgoingTransactions = new ArrayList<>();

    /**
     * @requires fee greater or equal then 0 and name not equals null
     * @modifies this
     * @effects Constructs a new  Participant
     */
    public Participant(double fee, String name){
        this.fee = fee;
        this.participantName = name;
        checkRep();
    }

    /**
     * @requires transaction not equals none,
     * @modifies this.storageBuffer
     * @effects adds new transaction with this.fee to this.storageBuffer and if there is value left, adds new Transaction
     *   with value that equals oldTransaction.value minus this.fee to this.outgoingTransactions
     */
    // Fixme remove package name
    public void addTransaction(Transaction transaction){
        checkRep();
        // in case transactions' value is bigger then fee, we need to store transaction in this.storageBuffer
        // and create new transaction to this.outgoingTransactions
        if (transaction.getValue() > this.fee){
            Transaction feeTransaction = new Transaction(this.participantName, this.fee);
            this.storageBuffer.add(feeTransaction);
            Transaction outgoingTransaction = new
                    Transaction(transaction.getDest(), transaction.getValue() - this.fee);
            this.outgoingTransactions.add(outgoingTransaction);
            checkRep();
            return;
        }
        // in case transactions' value is lower or equal to fee, we need to add transaction to this.storageBuffer
        Transaction feeTransaction = new Transaction(this.participantName, transaction.getValue());
        this.storageBuffer.add(feeTransaction);
        checkRep();
    }

    /**
     * @requires nothing
     * @modifies none
     * @effects returns sum of all transactions in this.storageBuffer
     */
    public double getParticipantBalance(){
        double sum = 0;
        for (Transaction t : this.storageBuffer){
            sum += t.getValue();
        }
        return sum;
    }

    /**
     * @requires graph not equals none,
     * @modifies this
     * @effects simulates passing of transactions from this to Channel
     */
    @Override
    public void simulate(BipartiteGraph<String> graph){
        // in case the Participant doesn't have any transaction do nothing
        if (this.outgoingTransactions.isEmpty()){
            return;
        }
        // we have at least one transaction
        try {
            // first we get the node of the channel
            Node<String> node = graph.getNodeByNodeLabel(this.participantName);
            // childList must contain only one child
            List<String> childList = node.getChildrenList();
            // we have one child-Channel, need to pass the transaction to him if possible
            Node<String> channelNode = graph.getNodeByNodeLabel(childList.get(0));
            Channel channel = (Channel)channelNode.getData();
            for(Transaction t : this.outgoingTransactions){
                try {
                    channel.addTransaction(t);
                // if we can't pass the transaction to the channel we need to store it in our storageBuffer
                } catch (IllegalArgumentException.ChannelReachedLimit e){
                    Transaction newTransaction = new Transaction(this.participantName, t.getValue());
                    this.storageBuffer.add(newTransaction);
                }
            }
        } catch (homework2.IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    /**
     * Check to see if the representation invariant is being violated
     * @throw AssertionError if representation invariant is violated
     */
    private void checkRep(){
        assert (this.fee >= 0);
        assert (this.participantName != null);
        assert (this.storageBuffer != null);
        assert (this.outgoingTransactions != null);
        for(Transaction t: this.outgoingTransactions){
            assert (t != null);
        }
        for(Transaction t: this.storageBuffer){
            assert (t != null);
        }
    }
}
