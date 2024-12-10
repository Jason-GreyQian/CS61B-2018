package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        int targetX = maze.toX(t);
        int targetY = maze.toY(t);
        return Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        // create the priority queue
        NodeComparator cmp = new NodeComparator();
        PriorityQueue<Node> pq = new PriorityQueue<>(cmp);
        Node source = new Node(s);
        pq.add(source);

        while (!pq.isEmpty()) {
            Node curr = pq.remove();
            int v = curr.getV();

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    Node neighbour = new Node(w);
                    edgeTo[neighbour.getV()] = v;
                    distTo[neighbour.getV()] = distTo[v] + 1;
                    announce();
                    if (w == t) {
                        targetFound = true;
                        return ;
                    }
                    pq.add(neighbour);
                }
            }
        }

    }

    @Override
    public void solve() {
        astar(s);
    }

    private class Node {
        private int v;
        private int priority;

        Node(int v) {
            this.v = v;
            this.priority = distTo[v] + h(v);
        }

        int getV() {
            return v;
        }
    }

    private class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node node, Node t1) {
            return Integer.compare(node.priority, t1.priority);
        }
    }

}

