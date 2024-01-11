package algorithm.structure;


/**
 * 前缀树
 * 森林，字典查询
 */
public class Trie {

    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            //若搜索到了前缀的末尾，就说明字典树中存在该前缀。
            //若前缀末尾对应节点的 isEnd 为真，则说明字典树中存在该字符串
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        System.out.println(obj.search("app"));
        System.out.println(obj.startsWith("app"));
        System.out.println(obj.searchPrefix("app"));
        obj.insert("app");
        System.out.println(obj.search("app"));
    }
}
