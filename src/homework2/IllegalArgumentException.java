package homework2;

public class IllegalArgumentException extends Exception {

    //Exceptions that can happen in a Node method
    public static class ChildAlreadyConnectedException extends IllegalArgumentException {}
    public static class ChildIsOfTheSameColorException extends IllegalArgumentException {}
    public static class ParentAlreadyConnectedException extends IllegalArgumentException {}
    public static class ParentIsOfTheSameColorException extends IllegalArgumentException {}
    public static class LabelAlreadyInUseException extends IllegalArgumentException {}
    public static class ChildDoesntExistException extends IllegalArgumentException {}
    public static class ParentDoesntExistException extends IllegalArgumentException {}

    //Exceptions that can happen in a BipartiteGraph method
    public static class NodeWithThisLabelAlreadyExistException extends IllegalArgumentException {}
    public static class NodeWithThisLabelDoesntExistException extends IllegalArgumentException {}
    public static class EdgeWithThisLabelAlreadyExistException extends IllegalArgumentException {}

}
