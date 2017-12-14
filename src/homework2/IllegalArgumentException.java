package homework2;

public class IllegalArgumentException extends Exception {

    //Exceptions that can happen in a Node method
    public static class ChildAlreadyConnectedException extends IllegalArgumentException {}
    public static class ChildIsOfTheSameColorException extends IllegalArgumentException {}
    public static class ParentAlreadyConnectedException extends IllegalArgumentException {}
    public static class ParentIsOfTheSameColorException extends IllegalArgumentException {}
    public static class ChildDoesntExistException extends IllegalArgumentException {}
    public static class ParentDoesntExistException extends IllegalArgumentException {}

}
