package homework2;

public class IllegalArgumentException extends Exception {

    public static class SameColorException extends IllegalArgumentException {}
    public static class EdgeLabelAlreadyExists extends IllegalArgumentException {}
    public static class EdgeWithLabelDoesntExistException extends IllegalArgumentException {}
    public static class NodeWithThisLabelAlreadyExistException extends IllegalArgumentException {}
    public static class NodeWithThisLabelDoesntExistException extends IllegalArgumentException {}

}
