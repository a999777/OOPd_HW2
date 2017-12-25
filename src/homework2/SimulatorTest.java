package homework2;

import static org.junit.Assert.*;
import org.junit.Test;
import static homework2.IllegalArgumentException.*;

public class SimulatorTest {
    @Test
    public void testExample() throws IllegalArgumentException {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a graph
        driver.createSimulator("sim1");

        //add a pair of nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");


        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");

        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));

    }
}
