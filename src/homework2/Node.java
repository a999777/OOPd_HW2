package homework2;

import java.util.List;
import java.util.Map;

/**
 * A Node is a TODO
 */
public class Node {

    //Rep. Invariant:

    //Abs. Function:


    public enum Color{BLACK, WHITE};

    private final String label; //FIXME to template
    private String data; //FIXME to template
    private final Color color;
    private final Map<String,Node> children;
    private final Map<String,Node> parents;
    private String childrenList;
    private String parentsList;


    /**
     * @requires label is not null, data is not null, color is BLACK or WHITE
     * @effects Initializes this with the given label, data and color.
     */
    public Node(String label, String data, Color color) {
        this.label = label;
        this.data = data;
        this.color = color;
        checkRep();
    }


    /**
     * Copy Constructor
     * @requires toCopy is not null
     * @effects Initializes this as a copy of toCopy
     */
    public Node(Node toCopy) {
        String copiedLabel = new String(toCopy.label);
        String copiedData = new String(toCopy.data);
        //How do we copy maps?
    }


    /**
     * @requires label is not null, child is not null
     * @modifies this
     * @effects Adds the node child to the children list if the child's color is different from this.color
     *          Otherwise throws ChildIsOfTheSameColor
     */
    public void appendToChildren(String label, Node child) {
        checkRep();
    }


    /**
     * @requires label is not null, parent is not null
     * @modifies this
     * @effects Adds the node parent to the parents list if the parent's color is different from this.color
     *          Otherwise throws ParentIsOfTheSameColor
     */
    public void appendToParents(String label, Node parent);


    /**
     * @requires None
     * @modifies Nothing
     * @effects Returns the color of this
     */
    public Color getColor();


    /**
     * @requires None
     * @modifies Nothing
     * @effects Returns the label of this
     */
    public String getLabel();


    /**
     * @requires None
     * @modifies Nothing
     * @effects Returns the data of this
     */
    public String getData();


    /**
     * @requires None
     * @modifies Nothing
     * @effects Returns a list containing the children of this
     */
    public String getChildrenList();


    /**
     * @requires None
     * @modifies Nothing
     * @effects Returns a list containing the parents of this
     */
    public String getParentsList();


    /**
     * @requires label is not null
     * @modifies Nothing
     * @effects Returns a child that is connected to this with an edge labeled label
     */
    public Node findChildByEdgeLabel(String label);


    /**
     * @requires label is not null
     * @modifies Nothing
     * @effects Returns a parent that is connected to this with an edge labeled label
     */
    public Node findParentByEdgeLabel(String label);


    /**
     * Check to see if the representation invariant is being violated
     * @throw AssertionError if representation invariant is violated
     */
    private void checkRep();

}