package homework2;

import static org.junit.Assert.*;
import org.junit.Test;
import static homework2.IllegalArgumentException.*;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() throws IllegalArgumentException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

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

    @Test
    public void addNodesRegularTest() throws NodeWithThisLabelAlreadyExistException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g1");

        driver.addBlackNode("g1","c");
        driver.addBlackNode("g1","a");
        driver.addBlackNode("g1","b");
        driver.addWhiteNode("g1","C");
        driver.addWhiteNode("g1","A");
        driver.addWhiteNode("g1","B");

        //Verifying all the kids we wanted were added
        assertEquals("g1 black children weren't added properly", "a b c", driver.listBlackNodes("g1"));
        assertEquals("g1 white children weren't added properly", "A B C", driver.listWhiteNodes("g1"));

    }

    @Test(expected = NodeWithThisLabelAlreadyExistException.class)
    public void addNodesExceptionTest() throws NodeWithThisLabelAlreadyExistException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g");

        //Adding a few nodes then trying to add a node with the same label
        driver.addBlackNode("g","a");
        driver.addBlackNode("g","b");
        driver.addWhiteNode("g","A");
        driver.addWhiteNode("g","B");
        driver.addBlackNode("g","a");
    }

    @Test
    public void listNodesTest() throws NodeWithThisLabelAlreadyExistException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g");

        //Adding a couple of children and then verifying we get them sorted in a space-separated string
        driver.addBlackNode("g","amir");
        driver.addBlackNode("g","eitan");
        driver.addBlackNode("g","ben");
        driver.addBlackNode("g","felix");
        driver.addBlackNode("g","david");
        driver.addBlackNode("g","candy");
        assertEquals("listBlackNodes for g didn't work correctly", "amir ben candy david eitan felix",
                driver.listBlackNodes("g"));

        driver.addWhiteNode("g","10");
        driver.addWhiteNode("g","12");
        driver.addWhiteNode("g","23");
        driver.addWhiteNode("g","22");
        driver.addWhiteNode("g","44");
        driver.addWhiteNode("g","15");
        assertEquals("listWhiteNodes for g didn't work correctly", "10 12 15 22 23 44",
                driver.listWhiteNodes("g"));
    }

    @Test
    public void addEdgeRegularTest() throws IllegalArgumentException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g");

        //Adding nodes
        driver.addBlackNode("g","b1");
        driver.addBlackNode("g","b2");
        driver.addBlackNode("g","b3");
        driver.addWhiteNode("g","w1");
        driver.addWhiteNode("g","w2");
        driver.addWhiteNode("g","w3");

        // Trying to connect vertices that we should be to connect
        driver.addEdge("g","b1","w1", "a");
        driver.addEdge("g","w2","b2", "b");
        driver.addEdge("g","b3","w3", "c");

        //Trying to connect vertices with a label used before in the graph but not with the vertices
        driver.addEdge("g","b3","w2", "a");
        driver.addEdge("g","w1","b3", "b");

        //Verifying that the connections we expect really exist
        assertEquals("b1 parents incorrect","",driver.listParents("g","b1"));
        assertEquals("b1 children incorrect","w1",driver.listChildren("g","b1"));
        assertEquals("b2 parents incorrect","w2",driver.listParents("g","b2"));
        assertEquals("b2 children incorrect","",driver.listChildren("g","b2"));
        assertEquals("b3 parents incorrect","w1",driver.listParents("g","b3"));
        assertEquals("b3 children incorrect","w2 w3",driver.listChildren("g","b3"));

        assertEquals("w1 parents incorrect","b1",driver.listParents("g","w1"));
        assertEquals("w1 children incorrect","b3",driver.listChildren("g","w1"));
        assertEquals("w2 parents incorrect","b3",driver.listParents("g","w2"));
        assertEquals("w2 children incorrect","b2",driver.listChildren("g","w2"));
        assertEquals("w3 parents incorrect","b3",driver.listParents("g","w3"));
        assertEquals("w3 children incorrect","",driver.listChildren("g","w3"));
    }

    @Test(expected = ParentIsOfTheSameColorException.class)
    public void addEdgeBetweenSameColorTest() throws IllegalArgumentException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g");

        //Adding nodes
        driver.addBlackNode("g","b1");
        driver.addBlackNode("g","b2");
        driver.addWhiteNode("g","w1");
        driver.addWhiteNode("g","w2");

        // Trying to connect vertices that we shouldn't be to connect. We can choose which one to check with comment
        driver.addEdge("g","b1","b2", "a");
        driver.addEdge("g","w1","w2", "a");
    }

    @Test(expected = NodeWithThisLabelDoesntExistException.class)
    public void addEdgeWithNodeThatDoesntExistTest() throws IllegalArgumentException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g");

        //Adding nodes
        driver.addBlackNode("g","b1");
        driver.addWhiteNode("g","w1");

        // Trying to connect vertices that we shouldn't be to connect.
        driver.addEdge("g","b1","w3", "a");
    }

    @Test(expected = LabelAlreadyInUseException.class)
    public void addEdgeWithLabelThatAlreadyExistTest() throws IllegalArgumentException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g");

        //Adding nodes
        driver.addBlackNode("g","b1");
        driver.addWhiteNode("g","w1");

        // Using the same label for edges of the same vertex. Should throw an exception
        driver.addEdge("g","b1","w1", "x");
        driver.addEdge("g","w1","b1", "x");
    }

    @Test
    public void getNodesByEdgeLabelTest() throws IllegalArgumentException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("g");

        //Adding nodes
        driver.addBlackNode("g","b1");
        driver.addBlackNode("g","b2");
        driver.addWhiteNode("g","w1");
        driver.addWhiteNode("g","w2");

        // Adding some edges
        driver.addEdge("g","b1","w1","b1w1");
        driver.addEdge("g","b2","w1","b2w1");
        driver.addEdge("g","b2","w2","b2w2");

        //Testing the methods
        assertEquals("Black nodes incorrect","b1 b2", driver.listBlackNodes("g"));
        assertEquals("White nodes incorrect","w1 w2", driver.listWhiteNodes("g"));
        assertEquals("b1w1 incorrect","w1", driver.getChildByEdgeLabel("g","b1","b1w1"));
        assertEquals("b1w1 incorrect","b1", driver.getParentByEdgeLabel("g","w1","b1w1"));

        assertEquals("b2w1 incorrect","w1", driver.getChildByEdgeLabel("g","b2","b2w1"));
        assertEquals("b2w1 incorrect","b2", driver.getParentByEdgeLabel("g","w1","b2w1"));

        assertEquals("b2w2 incorrect","w2", driver.getChildByEdgeLabel("g","b2","b2w2"));
        assertEquals("b2w2 incorrect","b2", driver.getParentByEdgeLabel("g","w2","b2w2"));

    }



    
    
    //  TODO: Add black-box tests
    
  
}
