import edu.princeton.cs.algs4.Huffman;

public class HuffmanDecoder {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java HuffmanDecoder <file>");
            return;
        }

        String zippedFileName = args[0];
        String decodedFileName = args[1];

        // 1: Read the Huffman coding trie.
        ObjectReader reader = new ObjectReader(zippedFileName);
        BinaryTrie huffmanTrie = (BinaryTrie) reader.readObject();
        // 2: If applicable, read the number of symbols.
        int fileSize = (Integer) reader.readObject();
        // 3: Read the massive bit sequence corresponding to the original txt.
        BitSequence bitSequence = (BitSequence) reader.readObject();
        // 4: Repeat until there are no more symbols:
        //4a: Perform a longest prefix match on the massive sequence.
        //4b: Record the symbol in some data structure.
        //4c: Create a new bit sequence containing the remaining unmatched bits.
        char[] symbols = new char[fileSize];
        int i = 0;
        while (bitSequence.length() > 0) {
            Match match = huffmanTrie.longestPrefixMatch(bitSequence);
            symbols[i++] = match.getSymbol();
            bitSequence = bitSequence.allButFirstNBits(match.getSequence().length());
        }
        //5: Write the symbols in some data structure to the specified file.
        FileUtils.writeCharArray(decodedFileName, symbols);
    }
}
