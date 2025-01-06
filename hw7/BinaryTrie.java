import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {
    private Node root;

    /**
     * Given a frequency table which maps symbols of type V to their relative frequencies,
     * the constructor should build a Huffman decoding trie according to the procedure discussed in class.
     * A tries mean a node should hava both children, or have no children.
     */
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        root = buildTrie(frequencyTable);
    }

    /**
     * Finds the longest prefix that matches the given querySequence and returns a Match object for that Match.
     */
    public Match longestPrefixMatch(BitSequence querySequence) {
        if (root == null) {
            return null;
        }

        Node current = root;
        int bitIndex = 0;
        String bitSequence = "";

        // Traverse down the trie until a leaf node is reached or the query sequence ends
        while (!current.isLeaf()) {
            // there is no match, return null
            if (bitIndex >= querySequence.length()) {
                return null;
            }
            int bit = querySequence.bitAt(bitIndex);
            bitSequence += bit;
            current = (bit == 0) ? current.left : current.right;
            bitIndex += 1;
        }

        return new Match(new BitSequence(bitSequence), current.ch);
    }

    public Map<Character, BitSequence> buildLookupTable() {
        HashMap<Character, BitSequence> lookupTable = new HashMap<Character, BitSequence>();
        travelTrie(root, "", lookupTable);
        return lookupTable;
    }

    /**
     * build the Huffman trie given frequencies.
     * the less common use 0 mean let it been left
     */
    private Node buildTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> minQueue = new MinPQ<>();

        // Insert each character with its frequency into the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            minQueue.insert(node);
        }

        // Build the tree by repeatedly combining the two nodes with the lowest frequency
        while (minQueue.size() > 1) {
            Node left = minQueue.delMin();
            Node right = minQueue.delMin();
            Node combineNode = new Node('\0', left.freq + right.freq, left, right);
            minQueue.insert(combineNode);
        }

        return minQueue.delMin();
    }

    /**
     * Helper function for travel the tree to get the left node's ch and bitSequence.
     */
    private void travelTrie(Node node, String bitSequence, HashMap<Character, BitSequence> expected) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            expected.put(node.ch, new BitSequence(bitSequence));
            return;
        }
        travelTrie(node.left, bitSequence + '0', expected);
        travelTrie(node.right, bitSequence + '1', expected);
    }


    /**
     * assume 0 stand left, 1 stand right.
     */
    private static class Node implements Comparable<Node>, Serializable {
        private char ch;
        private int freq;
        private Node left;
        private Node right;

        public Node(char ch, int freq) {
            this(ch, freq, null, null);
        }

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        /**
         * A tries has special structure.
         */
        public boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }
}
