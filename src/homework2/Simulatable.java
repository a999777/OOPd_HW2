package homework2;

/**
 * The homework2.Simulatable interface is implemented by each of the pipes and filters in
 * a pipe-and-filter simulation.
 */
public interface Simulatable<T> {
//TODO: check if it is ok to change the simulate method signature
	/**
	 * @modifies this, graph
	 * @effects Simulates this pipe or filter in a system modeled by graph
	 */
	public void simulate(BipartiteGraph<T> graph) throws IllegalArgumentException.NodeWithThisLabelDoesntExistException;
}
