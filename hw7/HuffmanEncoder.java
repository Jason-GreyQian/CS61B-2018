import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> frequencyTable = new HashMap<Character, Integer>();
        for (char symbol : inputSymbols) {
            frequencyTable.put(symbol, frequencyTable.getOrDefault(symbol, 0) + 1);
        }
        return frequencyTable;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java HuffmanEncoder <symbol>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = inputFileName + ".huf";

        // Read the file as 8 bit symbols. && Build frequency table.
        char[] inputSymbols = FileUtils.readFile(inputFileName);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);
        // Use frequency table to construct a binary decoding trie.
        BinaryTrie huffmanTrie = new BinaryTrie(frequencyTable);
        // Write the binary decoding trie to the .huf file.
        ObjectWriter writer = new ObjectWriter(outputFileName);
        writer.writeObject(huffmanTrie);
        // (optional: write the number of symbols to the .huf file)
        writer.writeObject(inputSymbols.length);
        // Use binary trie to create lookup table for encoding.
        Map<Character, BitSequence> lookupTable = huffmanTrie.buildLookupTable();
        // Create a list of bitsequences.
        List<BitSequence> bitSequenceList = new ArrayList<>();
        // Add the appropriate bit sequence to the list of bitsequences.
        for (char symbol : inputSymbols) {
            bitSequenceList.add(lookupTable.get(symbol));
        }
        // Assemble all bit sequences into one huge bit sequence.
        BitSequence bitSequence = BitSequence.assemble(bitSequenceList);
        // Write the huge bit sequence to the .huf file.
        writer.writeObject(bitSequence);
    }
}
