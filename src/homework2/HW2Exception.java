package homework2;

/**
 * This class stores all the exceptions that we would like to throw.
 * Each exception name should be clear enough to understand what went wrong. No exceptions supplies any behavior other
 * than being an exception.
 */
public class HW2Exception extends Exception {

    public static class SameColorException extends HW2Exception {}
    public static class EdgeLabelAlreadyExists extends HW2Exception {}
    public static class EdgeWithLabelDoesntExistException extends HW2Exception {}
    public static class NodeWithThisLabelAlreadyExistException extends HW2Exception {}
    public static class NodeWithThisLabelDoesntExistException extends HW2Exception {}
    public static class ChannelReachedLimit extends HW2Exception {}

}
