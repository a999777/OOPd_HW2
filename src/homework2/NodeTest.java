package homework2;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void testCreation() {
        //Regular constructor
        Node n1 = new Node("n1", "n1_data", Node.Color.WHITE);
        Node n2 = new Node("n2", "n2_data", Node.Color.BLACK);
        assertNotNull(n1);
        assertNotNull(n2);

        //Copy constructor
        Node c1 = new Node(n1);
        Node c2 = new Node(n2);
        assertNotNull(c1);
        assertNotNull(c2);
        assertEquals(n1.getLabel(),c1.getLabel());
        assertEquals(n2.getLabel(),c2.getLabel());
        assertEquals(n1.getData(),c1.getData());
        assertEquals(n2.getData(),c2.getData());
        assertEquals(n1.getColor(),c1.getColor());
        assertEquals(n2.getColor(),c2.getColor());
    }

    @Test
    public void appendToChildren() {
        Node b1 = new Node("b1", "b1_data", Node.Color.BLACK);
        Node b2 = new Node("b2", "b2_data", Node.Color.BLACK);
        Node w1 = new Node("w1", "w1_data", Node.Color.WHITE);
        Node w2 = new Node("w2", "w2_data", Node.Color.WHITE);

        //Adding correctly (b1 is the father of w1)

    }

    @Test
    public void appendToParents() {
    }

    @Test
    public void getColor() {
    }

    @Test
    public void getLabel() {
    }

    @Test
    public void getData() {
    }

    @Test
    public void getChildrenList() {
    }

    @Test
    public void getParentsList() {
    }

    @Test
    public void findChildByEdgeLabel() {
    }

    @Test
    public void findParentByEdgeLabel() {
    }
}