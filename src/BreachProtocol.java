import java.util.*;

/**
 * Class for the brach protocol minigame.
 */
class BreachProtocol {
    public Node[][] mat;
    public boolean valid;

    /**
     * Constructor for the minigame.
     * @param gridSize N value of cells in the N x N grid.
     */
    public BreachProtocol(int gridSize){
        mat = new Node[gridSize][gridSize];
        valid = false;
    }

    /**
     * Parse board configuration for the grid.
     * @param s the input string, formatted with spaces and newlines
     */
    public void parseBoard(String s){
        String[] rows = s.split("\n");
        if(rows.length != mat.length) {
            System.out.println("Parsing error!");
            return;
        }

        // Parse each entry in the rows
        for(int i = 0; i < rows.length; i++){
            String[] arr = rows[i].split(" ");
            if(arr.length != mat[0].length){
                System.out.println("Parsing error!");
                return;
            }

            for(int j = 0; j < arr.length; j++){
                String toParse = arr[j];
                if(toParse.length() != 2){ // Check for length 2 node size
                    System.out.println("Parsing error!");
                    return;
                }
                Node temp = new Node(toParse.charAt(0), toParse.charAt(1));
                mat[i][j] = temp;
            }
        }
    }

    /**
     * Parse path string into list of nodes.
     * @param s the path.
     * @return a list of nodes representing the path.
     */
    public List<Node> parsePath(String s){
        String[] arr = s.split(" ");
        List<Node> res = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            String toParse = arr[i];
            if(toParse.length() != 2){ // Check for length 2 node size
                System.out.println("Parsing error!");
                return null;
            }
            Node temp = new Node(toParse.charAt(0), toParse.charAt(1));
            res.add(temp);
        }
        return res;
    }

    /**
     * Display the visual of the board.
     */
    public void displayBoard(){
        for(Node[] a : mat) System.out.println(Arrays.toString(a));
    }

    /**
     * Displays a visual of the solution to the board.
     */
    public void displaySolution(List<int[]> solution){
        String[][] temp = new String[mat.length][mat[0].length];

        for(String[] arr : temp) Arrays.fill(arr, "_");

        for(int[] node : solution) temp[node[0]][node[1]] = ("" + node[2]);

        for(String[] arr : temp) System.out.println(Arrays.toString(arr));
    }

    /**
     * Backtracking algo to solve the puzzle.
     * Always starts in the first row, then goes to cols, then rows, etc.
     * Prints the final configuration/order of the solution
     * @param s The path configuration of the hack
     */
    public void search(String s){
        List<int[]> res = new ArrayList<>();
        List<Node> path = parsePath(s);
        System.out.println("SEARCHING FOR : " + path.toString());
        for(int i = 0; i < mat[0].length; i++){
            boolean b = backtrack(res, path, false, 0, i, 0);
            if(b){
                displayBoard();
                displaySolution(res);
                break;
            }
        }
    }

    /**
     * Backtracking algo to solve a breach protocol.
     * @param res Solution of int[] {i, j, step.no}
     * @param path target path
     * @param isRow indicates to traverse row or column
     * @param r the i index
     * @param c the j index
     * @param idx the index of the path node we are on
     * @return true if a solution is found
     */
    public boolean backtrack(List<int[]> res, List<Node> path, boolean isRow, int r, int c, int idx){
        // Done case
        if(idx == path.size()) return true;

        // Out of bounds case
        else if(r < 0 || c < 0 || r >= mat.length || c >= mat[0].length) return false;

        // Already visited
        else if(mat[r][c].visited) return false;

        // Needs to be equal at the index we are searching for
        else if(!mat[r][c].equals(path.get(idx))) return false;

        // IT MUST BE EQUAL HERE
        mat[r][c].visited = true;

        // Add the (i, j) coordinates as well as step no.
        res.add(new int[]{r, c, idx + 1});

        if(isRow){ // Iterate through the ROW (j index)
            for(int j = 0; j < mat[0].length; j++){
                boolean b = backtrack(res, path, false, r, j, idx + 1);
                if(b) return true;
            }
        }
        else{ // Iterate through COLUMN (i index)
            for(int i = 0; i < mat.length; i++){
                boolean b = backtrack(res, path, true, i, c, idx + 1);
                if(b) return true;
            }
        }

        mat[r][c].visited = false;
        res.remove(res.size()-1);
        return false;
    }
}
