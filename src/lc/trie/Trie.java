package lc.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
//        trie.insert("apple");
//        System.out.println("apple is found: " + trie.search("apple"));
//        System.out.println("app is found: " + trie.search("app"));
//        System.out.println("Prefix app is found: " + trie.startsWith("app"));
//        trie.insert("app");
        System.out.println("app is found: " + trie.search("app"));
    }

    private static final class TrieNode {
        private Character key;
        private Set<String> values;
        private Map<Character, TrieNode> childrenHandle;

        public TrieNode() {
            key = null;
            values = new HashSet<>();
            childrenHandle = new HashMap<>();
        }

        public TrieNode(Character key) {
            this.key = key;
            values = new HashSet<>();
            childrenHandle = new HashMap<>();
        }

        public boolean isRoot() {
            return null == key;
        }

        public Character getKey() {
            return key;
        }

        public void addValue(String value) {
            values.add(value);
        }

        public Set<String> getValues() {
            return values;
        }

        public void addChild(TrieNode node) {
            this.childrenHandle.put(node.getKey(), node);
        }

        public Set<TrieNode> getChildren() {
            return childrenHandle.values().stream().collect(Collectors.toSet());
        }

        public Map<Character, TrieNode> getChildrenHandle() {
            return childrenHandle;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode ref = root;
        for (Character ch: word.toCharArray()) {
            if (null != ref.getChildrenHandle().get(ch)) {
                ref = ref.getChildrenHandle().get(ch);
            } else {
                TrieNode temp = new TrieNode(ch);
                ref.addChild(temp);
                ref = temp;
            }
        }
        ref.addValue(word);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode ref = root;
        for (Character ch: word.toCharArray()) {
            if (null != ref.getChildrenHandle().get(ch)) {
                ref = ref.getChildrenHandle().get(ch);
            } else {
                break;
            }
        }
        return null != ref ? ref.getValues().contains(word) : false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode ref = root;
        boolean notFound = false;
        for (Character ch: prefix.toCharArray()) {
            if (null != ref.getChildrenHandle().get(ch)) {
                ref = ref.getChildrenHandle().get(ch);
            } else {
                notFound = true;
                break;
            }
        }
        return !notFound;
    }
}
