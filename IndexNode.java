// Christopher Nelson
// 20 March 2018
// Assignment_07: Indexing With Trees
package index;

import java.util.LinkedList;
import java.util.List;

public class IndexNode {

    // The word for this entry
    String word;
    // The number of occurrences for this word
    int occurences;
    // A list of line numbers for this word.
    List<Integer> list = new LinkedList<>();;

    IndexNode left;
    IndexNode right;

    public IndexNode(String word, int lineNumber) {
        
        this.word = word;
        this.occurences = 1;
        this.list.add(lineNumber);
    }
    // Constructors
    // Constructor should take in a word and a line number
    // it should initialize the list and set occurrences to 1
    // Complete This
    // return the word, the number of occurrences, and the lines it appears on.
    // string must be one line
    public String toString() {
        return word.toString();
    }
    
}
