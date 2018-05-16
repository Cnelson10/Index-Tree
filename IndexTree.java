// Christopher Nelson
// 20 March 2018
// Assignment_07: Indexing With Trees
package index;
import java.io.*;
import java.util.Scanner;
// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {

    // This is your root 
    // again, your root does not use generics because you know your nodes
    // hold strings, an int, and a list of integers
    private IndexNode root;

    public IndexTree() {
        
        this.root = null;
    }
    // Make your constructor
    // It doesn't need to do anything
    // complete the methods below
    // this is your wrapper method
    // it takes in two pieces of data rather than one
    // call your recursive add method
    public void add(String word, int lineNumber) {
            
        this.root = add(this.root, word, lineNumber);
    }

    // your recursive method for add
    // Think about how this is slightly different the the regular add method
    // When you add the word to the index, if it already exists, 
    // you want to  add it to the IndexNode that already exists
    // otherwise make a new indexNode
    private IndexNode add(IndexNode root, String word, int lineNumber) {
        
        if (root == null) {
            return new IndexNode(word, lineNumber);
        }
        int comparisonResult = word.compareTo(root.word);
        if (comparisonResult == 0) {
            root.occurences++;
            root.list.add(lineNumber);
            return root;
        } else if (comparisonResult < 0) {
            root.left = add(root.left, word, lineNumber);
            return root;
        } else {
            root.right = add(root.right, word, lineNumber);
            return root;
        }
    }

    // returns true if the word is in the index
    public boolean contains(String word) {
        
        return contains(this.root, word);
    }
    
    public boolean contains(IndexNode root, String word) {

        if (root == null) {
            return false;
        }
        if (word.compareTo(root.word) == 0) {
            return true;
        } else if (word.compareTo(root.word) < 0) {
            return contains(root.left, word);
        } else {
            return contains(root.right, word);
        }
    }

    // call your recursive method
    // use book as guide
    public void delete(String word) {
        this.root = delete(this.root, word);
    }

    // your recursive case
    // remove the word and all the entries for the word
    // This should be no different than the regular technique.
    public IndexNode delete(IndexNode root, String word) {
        if (root == null) {
            return null;
        }

        if (word.compareTo(root.word) < 0) {
            root.left = delete(root.left, word);
            return root;
            
        } else if (word.compareTo(root.word) > 0) {
            root.right = delete(root.right, word);
            return root;
            
        } else { // the node holds the item we are deleting

            if (root.left == null && root.right == null) {
                return null;
                
            } else if (root.left != null && root.right == null) {
                return root.left;

            } else if (root.left == null && root.right != null) {
                return root.right;
                
            } else {
                if (root.left.right == null) {
                    IndexNode pred = root.left;
                    pred.right = root.right;
                    return pred;
                    
                } else {

                    IndexNode parent = root.left;
                    IndexNode pred = parent.right;
                    while (pred.right != null) {
                        pred = pred.right;
                        parent = parent.right;
                    }

                    root.word = pred.word;
                    parent.right = pred.left;
                    return root;
                }
            }

        }
    }

    // prints all the words in the index in inorder order
    // To successfully print it out
    // this should print out each word followed by the number of occurrences and the list of all occurrences
    // each word and its data gets its own line
    public void printIndex() {
        printIndex(this.root);
        
    }
    
    private void printIndex(IndexNode root) {
        if (root == null){
            System.out.print("");
            
        } else {
            printIndex(root.left);
            System.out.println('\"' + root.word.toString() + "\", appears (" + root.occurences + ") times, on lines " + root.list);
            printIndex(root.right);
            
        }
    }

    public static void main(String[] args) {
        IndexTree index = new IndexTree();
        int lineNum = 0;
        String fileName = "pg100.txt";

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                //System.out.println(line);
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase();
                    word = word.replaceAll("0", "");
                    word = word.replaceAll("1", "");
                    word = word.replaceAll("2", "");
                    word = word.replaceAll("3", "");
                    word = word.replaceAll("4", "");
                    word = word.replaceAll("5", "");
                    word = word.replaceAll("6", "");
                    word = word.replaceAll("7", "");
                    word = word.replaceAll("8", "");
                    word = word.replaceAll("9", "");
                    word = word.replaceAll(":", "");
                    word = word.replaceAll(",", "");
                    word = word.replaceAll(";", "");
                    word = word.replaceAll("\\.", "");
                    word = word.replaceAll("!", "");
                    word = word.replaceAll("\\?", "");
                    word = word.replaceAll("\"", "");
                    word = word.replaceAll("\\[", "");
                    word = word.replaceAll("\\]", "");
                    word = word.replaceAll("|", "");
                    word = word.replaceAll("_", "");
                    word = word.replaceAll("\\(", "");
                    word = word.replaceAll("\\)", "");
                    word = word.replaceAll("&", "");
                    word = word.replaceAll("\\}", "");
                    word = word.replaceAll(">", "");
                    word = word.replaceAll("<", "");
                    
                    if(word.startsWith("'")){
                        word = word.substring(1);
                        if(word.startsWith("'")){
                            word = word.substring(1);
                        }
                    }
                    
                    if(word.startsWith("-")){
                        word = word.substring(1);
                        if(word.startsWith("-")){
                            word = word.substring(1);
                        }
                    }
                    
                    if(word.endsWith("'")){
                        word = word.substring(0, word.length()-1);
                        if(word.endsWith("'")){
                            word = word.substring(0, word.length()-1);
                        }
                    }
                    
                    if(word.endsWith("-")){
                        word = word.substring(0, word.length()-1);
                        if(word.endsWith("-")){
                            word = word.substring(0, word.length()-1);
                        }
                    }
                    
                    //System.out.println(word);
                    // add all the words to the tree
                    index.add(word, lineNum);
                    
                    
                }
            }
            scanner.close();

        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        index.delete("");
        // test removing a word from the index
        //index.delete("a");
        
        // print out the index
        index.printIndex();
        // test removing a word from the index
        System.out.println("The root of the tree is: \"" + index.root + '\"');
        index.delete("the");
        System.out.println("After deleting it, the new root is: "+ index.root + '\"');
        
        
    }
}
