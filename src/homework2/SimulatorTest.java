package homework2;

import static org.junit.Assert.*;
import org.junit.Test;

import static homework2.HW2Exception.*;

public class SimulatorTest {

    @Test(expected = NodeWithThisLabelAlreadyExistException.class)
    public void addParticipantWithSameNameExceptionTest() throws NodeWithThisLabelAlreadyExistException{
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a simulator
        driver.createSimulator("sim1");
        driver.addParticipant("sim1", "P1", 10);
        driver.addParticipant("sim1", "P1", 10);
    }

    @Test(expected = NodeWithThisLabelAlreadyExistException.class)
    public void addChannelWithSameNameExceptionTest() throws NodeWithThisLabelAlreadyExistException{
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a simulator
        driver.createSimulator("sim1");
        driver.addChannel("sim1", "CH1", 10);
        driver.addChannel("sim1", "CH1", 10);
    }

    @Test(expected = NodeWithThisLabelAlreadyExistException.class)
    public void addChannelAndParticipantWithSameNameExceptionTest() throws NodeWithThisLabelAlreadyExistException{
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a simulator
        driver.createSimulator("sim1");
        driver.addChannel("sim1", "CH1", 10);
        driver.addParticipant("sim1", "CH1", 1);
    }

    @Test(expected = HW2Exception.class)
    public void testAddEdgeIllegalArgumentException()throws HW2Exception {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a simulator
        driver.createSimulator("sim1");
        driver.addEdge("sim1", "a", "b", "zingale");
    }

    @Test
    public void testSendTransactionSimple() throws HW2Exception {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a simulator
        driver.createSimulator("sim1");
        driver.addChannel("sim1", "CH1", 10);
        Transaction t = new Transaction("moshe", 5);
        driver.sendTransaction("sim1", "CH1", t);
        assertEquals("5.0", driver.listContents("sim1", "CH1"));
    }

    @Test
    public void testSendTransaction() throws HW2Exception {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a new simulator
        driver.createSimulator("sim1");

        //add a few channels and participants
        driver.addChannel("sim1", "c1", 125);
        driver.addChannel("sim1", "c2", 25);
        driver.addParticipant("sim1", "p1", 2);
        driver.addParticipant("sim1", "p2", 3);

        //creating transactions to pass later
        Transaction tp11 = new Transaction("p1", 5);
        Transaction tp12 = new Transaction("p1", 15);
        Transaction tp21 = new Transaction("p2", 53);
        Transaction tp22 = new Transaction("p2", 1.8);
        Transaction tp31 = new Transaction("p2", 1.9);
        Transaction tp32 = new Transaction("p2", 1.1);
        Transaction tp33 = new Transaction("p2", 1.13);

        //add some transactions and make sure it works
        driver.sendTransaction("sim1","c1",tp11);
        driver.sendTransaction("sim1","c1",tp12);
        driver.sendTransaction("sim1","c1",tp21);
        driver.sendTransaction("sim1","c2",tp22);
        driver.sendTransaction("sim1","c2",tp31);
        driver.sendTransaction("sim1","c2",tp32);
        driver.sendTransaction("sim1","c2",tp33);
        assertEquals("c1's transactions incorrect","5.0 15.0 53.0", driver.listContents("sim1","c1"));
        assertEquals("c2's transactions incorrect","1.8 1.9 1.1 1.13", driver.listContents("sim1","c2"));
    }

    @Test
    public void testListContents() throws HW2Exception {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a new simulator
        driver.createSimulator("sim1");

        //add a few channels and participants
        driver.addChannel("sim1", "c1", 125);
        //creating transactions to pass later
        Transaction tp11 = new Transaction("p1", 1.11);
        Transaction tp12 = new Transaction("p1", 2.22);
        Transaction tp21 = new Transaction("p2", 3.33);
        Transaction tp22 = new Transaction("p2", 4.44);
        Transaction tp31 = new Transaction("p2", 5.55);
        Transaction tp32 = new Transaction("p2", 6.66);
        Transaction tp33 = new Transaction("p2", 7.77);
        //add some transactions and make sure it works
        driver.sendTransaction("sim1","c1",tp11);
        driver.sendTransaction("sim1","c1",tp12);
        driver.sendTransaction("sim1","c1",tp21);
        driver.sendTransaction("sim1","c1",tp22);
        driver.sendTransaction("sim1","c1",tp31);
        driver.sendTransaction("sim1","c1",tp32);
        driver.sendTransaction("sim1","c1",tp33);
        assertEquals("c1's transactions incorrect","1.11 2.22 3.33 4.44 5.55 6.66 7.77", driver.listContents("sim1","c1"));
    }

    @Test(expected = ChannelReachedLimit.class)
    public void testSendTransactionLimit() throws ChannelReachedLimit, NodeWithThisLabelAlreadyExistException,
            NodeWithThisLabelDoesntExistException{
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a new simulator
        driver.createSimulator("sim1");

        //add a few channels and participants
        driver.addChannel("sim1", "c1", 125);
        Transaction tp11 = new Transaction("p1", 125.1);
        driver.sendTransaction("sim1","c1",tp11);
    }

    @Test
    public void testPrintAllEdges() throws HW2Exception {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a new simulator
        driver.createSimulator("sim1");
        //add a few channels and participants
        driver.addChannel("sim1", "c1", 125);
        driver.addChannel("sim1", "c2", 25);
        driver.addParticipant("sim1", "p1", 2);
        driver.addParticipant("sim1", "p2", 3);
        driver.addEdge("sim1", "p1", "c1", "e1");
        driver.addEdge("sim1", "c1", "p2", "e12");
        driver.addEdge("sim1", "p2", "c2", "e22");
        driver.printAllEdges("sim1");
    }

    @Test
    public void testSimulate() throws HW2Exception {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a new simulator
        driver.createSimulator("sim1");
        //add a few channels and participants
        driver.addChannel("sim1", "c1", 500);
        driver.addChannel("sim1", "c2", 600);
        driver.addChannel("sim1", "c3", 600);
        driver.addParticipant("sim1", "p1", 0);
        driver.addParticipant("sim1", "p2", 1);
        driver.addParticipant("sim1", "p3", 2);
        driver.addEdge("sim1", "p1", "c1", "e1");
        driver.addEdge("sim1", "c1", "p2", "e12");
        driver.addEdge("sim1", "p2", "c2", "e22");
        driver.addEdge("sim1", "c2", "p3", "e23");
        driver.addEdge("sim1", "p3", "c3", "e33");
        Transaction tp11 = new Transaction("p3", 10);
        driver.sendTransaction("sim1", "c1", tp11);
        // check that all nodes besides c1 return zero or empty string
        assertEquals("c2's transactions incorrect","", driver.listContents("sim1","c2"));
        assertEquals("c3's transactions incorrect","", driver.listContents("sim1","c3"));
        assertEquals("p1 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p1"), 0);
        assertEquals("p2 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p2"), 0);
        assertEquals("p3 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p3"), 0);

        assertEquals("c1's transactions incorrect","10.0", driver.listContents("sim1","c1"));
        driver.simulate("sim1");
        // only p2 and c2 should return a number after simulation
        assertEquals("p2 ballance is incorrect", 1.0, driver.getParticipantBalace("sim1", "p2"), 0.1);
        assertEquals("c2 transactions is incorrect", "9.0", driver.listContents("sim1","c2"));
        assertEquals("p1 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p1"), 0);
        assertEquals("p3 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p3"), 0);
        assertEquals("c1 transactions is incorrect", "", driver.listContents("sim1","c1"));
        assertEquals("c3 transactions is incorrect", "", driver.listContents("sim1","c3"));
        driver.simulate("sim1");
        // only p2 and p3 should return a number after simulation
        assertEquals("p2 ballance is incorrect", 1.0, driver.getParticipantBalace("sim1", "p2"), 0.1);
        assertEquals("p3 ballance is incorrect", 9.0, driver.getParticipantBalace("sim1", "p3"), 0.1);
        assertEquals("c2 transactions is incorrect", "", driver.listContents("sim1","c2"));
        assertEquals("p1 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p1"), 0);
        assertEquals("c1 transactions is incorrect", "", driver.listContents("sim1","c1"));
        assertEquals("c3 transactions is incorrect", "", driver.listContents("sim1","c3"));
        driver.simulate("sim1");
        // nothing should happen, so as before, only p2 and p3 should return a number after simulation
        assertEquals("p2 ballance is incorrect", 1.0, driver.getParticipantBalace("sim1", "p2"), 0.1);
        assertEquals("p3 ballance is incorrect", 9.0, driver.getParticipantBalace("sim1", "p3"), 0.1);
        assertEquals("c2 transactions is incorrect", "", driver.listContents("sim1","c2"));
        assertEquals("p1 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p1"), 0);
        assertEquals("c1 transactions is incorrect", "", driver.listContents("sim1","c1"));
        assertEquals("c3 transactions is incorrect", "", driver.listContents("sim1","c3"));
    }

    @Test
    public void testSimulateAgain() throws HW2Exception {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a new simulator
        driver.createSimulator("sim1");
        //add a few channels and participants
        driver.addChannel("sim1", "c11", 500);
        driver.addChannel("sim1", "c22", 500);
        driver.addChannel("sim1", "c3", 500);
        driver.addChannel("sim1", "c4", 1000);
        driver.addParticipant("sim1", "p1", 10);
        driver.addParticipant("sim1", "p2", 20);
        driver.addEdge("sim1", "c11", "p1", "c11p1");
        driver.addEdge("sim1", "c22", "p1", "c22p1");
        driver.addEdge("sim1", "p1", "c3", "p1c3");
        driver.addEdge("sim1", "c3", "p2", "c3p2");
        driver.addEdge("sim1", "p2", "c4", "p2c4");
        Transaction tp11 = new Transaction("p2", 20);
        Transaction tp22 = new Transaction("p2", 30);
        driver.sendTransaction("sim1", "c11", tp11);
        driver.sendTransaction("sim1", "c22", tp22);
        // only c11 and c22 should contain transactions
        assertEquals("c11's transactions incorrect","20.0", driver.listContents("sim1","c11"));
        assertEquals("c22's transactions incorrect","30.0", driver.listContents("sim1","c22"));

        assertEquals("p1 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p1"), 0);
        assertEquals("p2 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p2"), 0);
        assertEquals("c3's transactions incorrect","", driver.listContents("sim1","c3"));
        assertEquals("c4's transactions incorrect","", driver.listContents("sim1","c4"));

        driver.simulate("sim1");
        // p1 should contain 20 and c3 should contain 10 and 20
        assertEquals("p1 ballance is incorrect", 20.0, driver.getParticipantBalace("sim1", "p1"), 0.1);
        assertEquals("c3's transactions incorrect","10.0 20.0", driver.listContents("sim1","c3"));
        assertEquals("c11's transactions incorrect","", driver.listContents("sim1","c11"));
        assertEquals("c22's transactions incorrect","", driver.listContents("sim1","c22"));
        assertEquals("p2 ballance is incorrect", 0.0, driver.getParticipantBalace("sim1", "p2"), 0);
        assertEquals("c4's transactions incorrect","", driver.listContents("sim1","c4"));
        driver.simulate("sim1");
        //p1 should contain 20 and p2 should contain 30
        assertEquals("p1 ballance is incorrect", 20.0, driver.getParticipantBalace("sim1", "p1"), 0.1);
        assertEquals("p2 ballance is incorrect", 30.0, driver.getParticipantBalace("sim1", "p2"), 0.1);
        assertEquals("c3's transactions incorrect","", driver.listContents("sim1","c3"));
        assertEquals("c11's transactions incorrect","", driver.listContents("sim1","c11"));
        assertEquals("c22's transactions incorrect","", driver.listContents("sim1","c22"));
        assertEquals("c4's transactions incorrect","", driver.listContents("sim1","c4"));
        driver.simulate("sim1");
        // nothing should change
        assertEquals("p1 balance is incorrect", 20.0, driver.getParticipantBalace("sim1", "p1"), 0.1);
        assertEquals("p2 balance is incorrect", 30.0, driver.getParticipantBalace("sim1", "p2"), 0.1);
        assertEquals("c3's transactions incorrect","", driver.listContents("sim1","c3"));
        assertEquals("c11's transactions incorrect","", driver.listContents("sim1","c11"));
        assertEquals("c22's transactions incorrect","", driver.listContents("sim1","c22"));
        assertEquals("c4's transactions incorrect","", driver.listContents("sim1","c4"));
    }

}
