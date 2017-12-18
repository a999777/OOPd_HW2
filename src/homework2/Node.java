package homework2;

import java.util.*;
import java.util.List;

import static homework2.IllegalArgumentException.*;
import static homework2.Node.Color.BLACK;
import static homework2.Node.Color.WHITE;


/**
 * A Node is an abstraction of a Bipartite Graph Node Element.
 * As such, each Node has a color that can be either Black or White and it stores the information we want to represent
 * using the bipartite graph. It also contains information about its parents and its children.
 * Thus, a typical Node has the properties {label, data, color, children, parents, childrenList, parentsList}
 * A Node is immutable
 */
public class Node<L, D> {

    //Abs. Function:
    //  Represents a bipartite graph node that is labeled this.label, contains this.data and is of color this.color.
    //  It also saves all its children nodes in this.children and the all its parents nodes in this.parents.
    //  In addition, the labels of its children and parent nodes are accessible via this.childrenList and via
    //  this.parentsList.

    //Rep. Invariant:
    //  this.label cannot be null.
    //  this.color has to be either Black or White.
    //  this.children and this.parents cannot be null.
    //  this.childrenList and this.parentsList cannot be null.


    public enum Color{BLACK, WHITE};

    private final L label;
    private D data;
    private final Color color;
    private final Map<L,Node> children;
    private final Map<L,Node> parents;
    private String childrenList;
    private String parentsList;


    /**
     * @requires label is not null, data is not null, color is BLACK or WHITE
     * @effects Initializes this with the given label, data and color.
     */
    public Node(L label, D data, Color color) {
        this.label = label;
        //TODO should we create a new D?
        this.data = data;
        this.color = color;
        this.children = new HashMap<>();
        this.parents = new HashMap<>();
        this.childrenList = "";
        this.parentsList = "";
        checkRep();
    }


    /**
     * Copy Constructor
     * @requires toCopy is not null
     * @effects Initializes this as a copy of toCopy
     */
    public Node(Node toCopy) {
        this.label = (L)toCopy.getLabel();
        if(toCopy.data == null) {
            this.data = null;
        } else {
            this.data = (D)toCopy.getData();
        }
        this.parents = new HashMap<>(toCopy.parents);
        this.children = new HashMap<>(toCopy.children);
        this.parentsList = toCopy.getChildrenList();
        this.childrenList = toCopy.getParentsList();
        this.color = toCopy.getColor();
        checkRep();
    }


    /**
     * @requires label is not null, child is not null
     * @modifies this
     * @effects Adds the node child to the children and children list if the child's color is different from this.color
     *              and child is not already a child of this.
     *          Throws EdgeLabelAlreadyExists if child is already in this.children
     *          Otherwise throws SameColorException
     */
    public void appendToChildren(L label, Node child) throws EdgeLabelAlreadyExists, SameColorException  {
        checkRep();

        //Check if color is okay
        if(this.color == child.color) {
            throw new SameColorException();
        }
        //Verify we don't have this label yet
        if(this.children.containsKey(child.label)) {
            throw new EdgeLabelAlreadyExists();
        }

        //Add to children map
        //TODO can we assume toString of 'label'?
        this.children.put(label, child);
        //Use a helper list to get all the names of children and then sort them
        List<String> tempChildrenList = new ArrayList<>();
        for(Node currNode : this.children.values()) {
            tempChildrenList.add(currNode.getLabel().toString());
        }
        Collections.sort(tempChildrenList);
        //Create the updated string representing all children
        this.childrenList = "";
        for(String childLabel: tempChildrenList) {
            this.childrenList +=  childLabel;
            this.childrenList +=  " ";
        }
        this.childrenList = this.childrenList.trim();
        checkRep();
    }


    /**
     * @requires label is not null, parent is not null
     * @modifies this
     * @effects Adds the node parent to the parents and parents list if the parent's color is different from this.color
     *              and parent is not already a parent of this.
     *          Throws EdgeLabelAlreadyExists if parent is already in this.parent
     *          Otherwise throws SameColorException
     */
    public void appendToParents(L label, Node parent) throws EdgeLabelAlreadyExists, SameColorException {
        checkRep();

        //Check if color is okay
        if(this.color == parent.color) {
            throw new SameColorException();
        }
        //Verify we don't have this label yet
        if(this.parents.containsKey(label) || this.children.containsKey(label)) {
            throw new EdgeLabelAlreadyExists();
        }

        //Add to parents map
        //TODO can we assume toString of 'label'?
        this.parents.put(label, parent);
        //Use a helper list to get all the names of parents and then sort them
        List<String> tempParentsList = new ArrayList<>();
        for(Node currNode: this.parents.values()) {
            tempParentsList.add(currNode.getLabel().toString());
        }
        Collections.sort(tempParentsList);
        //Create the updated string representing all children
        this.parentsList = "";
        for(String parentLabel: tempParentsList) {
            this.parentsList +=  parentLabel;
            this.parentsList +=  " ";
        }
        this.parentsList = this.parentsList.trim();
        checkRep();
    }


    /**
     * @effects Returns the color of this
     */
    public Color getColor() {
        checkRep();
        //We are not worried about returning this.color since it is immutable (it is set as final).
        return this.color;
    }


    /**
     * @effects Returns the label of this
     */
    public L getLabel() {
        checkRep();
        //We are not worried about returning this.label since it is immutable (as said in the pdf).
        return this.label;
    }


    /**
     * @effects Returns the data of this
     */
    public D getData() {
        checkRep();
        //Copying data and returning the copy since we don't know if data is immutable or not
        //D retData = new D(this.data);
        //TODO this causes a problem, so we switched to returning the field itself. we have to make sure it is immutable
        return this.data;
    }


    /**
     * @effects Returns a (string) list containing the children of this
     */
    public String getChildrenList() {
        checkRep();
        //We are not worried about returning this.childrenList since it is immutable (it is a java String).
        return this.childrenList;
    }


    /**
     * @effects Returns a (string )list containing the parents of this
     */
    public String getParentsList() {
        checkRep();
        //We are not worried about returning this.parentsList since it is immutable (it is a java String).
        return this.parentsList;
    }


    /**
     * @requires label is not null
     * @effects Returns a child that is connected to this with an edge labeled label if there is such a child node
     *          Throws EdgeWithLabelDoesntExistException otherwise.
     */
    public Node findChildByEdgeLabel(L label) throws EdgeWithLabelDoesntExistException {
        checkRep();
        //Checking if we have a child connected with label and throwing exception if we don't
        if(!this.children.containsKey(label)) {
            throw new EdgeWithLabelDoesntExistException();
        }
        //Copying the Node just to be on the safe side fixme maybe not needed?
        Node retChild = new Node(this.children.get(label));
        checkRep();
        return retChild;
    }


    /**
     * @requires label is not null
     * @modifies Nothing
     * @effects Returns a parent that is connected to this with an edge labeled label if there is such a parent node
     *          Throws EdgeWithLabelDoesntExistException otherwise.
     */
    public Node findParentByEdgeLabel(L label) throws EdgeWithLabelDoesntExistException {
        checkRep();
        //Checking if we have a parent with this label and throwing exception if we don't
        if(!this.parents.containsKey(label)) {
            throw new EdgeWithLabelDoesntExistException();
        }
        //Copying the Node just to be on the safe side fixme maybe not needed?
        Node retParent = new Node(this.parents.get(label));
        checkRep();
        return retParent;
    }


    /**
     * Check to see if the representation invariant is being violated
     * @throw AssertionError if representation invariant is violated
     */
    private void checkRep() {
        assert(this.label != null):"A label cannot be null!";
        //Note that this.data is not checked since we want to allow it to be null
        assert(this.children != null):"Children Map cannot be null!";
        assert(this.parents != null):"Parents Map cannot be null!";
        assert(this.childrenList != null):"childrenList cannot be null!";
        assert(this.parentsList != null):"parentsList cannot be null!";
        assert(this.color == BLACK || this.color == WHITE ):"A Node color has to be black or white!";
    }


}