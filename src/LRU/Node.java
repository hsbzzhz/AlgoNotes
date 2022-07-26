package LRU;
/*
 * 非使用 linkedHashmap实现（不要看这个）
 */
public class Node {
    public int key, val;
    public Node next, prev; //双向链表
    public Node(int k, int v){
        this.key = k;
        this.val = v;
    }
}
