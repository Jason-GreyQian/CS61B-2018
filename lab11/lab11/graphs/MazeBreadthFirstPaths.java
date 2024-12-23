package lab11.graphs;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;  // start position
    private int t;  // target position
    Maze maze;
    private boolean targetFound = false;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int frontier = queue.remove();
            marked[frontier] = true;
            announce();
            if (frontier == t) {
                targetFound = true;
                break;
            }
            for (int w : maze.adj(frontier)) {
                if (!marked[w]) {
                    distTo[w] = distTo[frontier] + 1;
                    edgeTo[w] = frontier;
                    announce();
                    queue.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

