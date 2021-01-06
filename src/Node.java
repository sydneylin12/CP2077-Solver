/**
 * Represents a node in the breach protocol grid.
 */
public class Node {

    // First and second letter/number of the node
    public char first;
    public char second;

    // For in-place search instead of using an additional DS
    public boolean visited;

    /**
     * Constructor for a breach protocol grid node.
     * @param a the first part of the node
     * @param b the second part of the node
     */
    public Node(char a, char b){
        this.first = Character.toUpperCase(a);
        this.second = Character.toUpperCase(b);
        this.visited = false;
    }

    /**
     * Checks equality of 2 nodes.
     * @param other the other node
     * @return true if nodes are equal
     */
    public boolean equals(Node other){
        return first == other.first && second == other.second;
    }

    /**
     * Print string representation.
     * @return the string
     */
    @Override
    public String toString(){
        return first + "" + second;
    }
}
