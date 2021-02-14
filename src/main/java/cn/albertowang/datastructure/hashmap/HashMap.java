package cn.albertowang.datastructure.hashmap;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/3 21:13
 * @description 实现一个简易的HashMap
 **/

public class HashMap {

    static public class Node {
        String key;
        String value;
        int hashCode;
        Node next;

        public Node(int hashCode, String key, String value, Node node) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
            this.next = node;
        }
    }

    private final int DEFAULT_SIZE = 16;
    private Node[] tab = new Node[this.DEFAULT_SIZE];
    private int size = 0;

    private void addNode(String key, String value, int hashCode, int index) {
        // 扩容
        if (this.size + 1 == tab.length) {
            Node[] newTab = new Node[this.tab.length * 2];
            System.arraycopy(this.tab, 0, newTab, 0, this.tab.length);
            this.tab = newTab;
            this.size++;
        }
        // 头插
        Node node = this.tab[index];
        this.tab[index] = new Node(hashCode, key, value, node);
    }

    public void put(String key, String value) {
        int hashCode = key.hashCode();
        int index = hashCode % this.tab.length;
        Node node = this.tab[index];
        while (node != null) {
            if (node.hashCode == hashCode && node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        addNode(key, value, hashCode, index);
    }

    public String get(String key) {
        int hashCode = key.hashCode();
        int index = hashCode % this.tab.length;
        Node node = this.tab[index];
        while (node != null) {
            if (node.key.equals(key) && hashCode == node.hashCode)
                return node.value;
            node = node.next;
        }
        return null;
    }
}
