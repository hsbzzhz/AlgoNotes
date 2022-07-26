package LRU;
/*
 * 非使用 linkedHashmap实现（不要看这个）
 */
public class DoubleList {
    // 头尾节点
    private Node head, tail;
    // 链表元素个数
    private int size;

    public DoubleList() {
        // 初始化链表
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表尾部添加节点 node（相当于在tail前插入节点）， 时间 O(1)
    public void addLast(Node node){
        // 处理node 的前后指针
        node.prev = tail.prev;
        node.next = tail;
        // 处理node前后节点的指向
        tail.prev.next = node; // 把前一个节点的next指针，指向node
        tail.prev = node;
        size++;
    }

    // 删除 node 节点
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    // 删除链表中的第一个节点
    public Node removeFirst(){
        if (head.next == tail){
            // 没有可以删除的节点了
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    // 返回当前链表的使用长度
    public int getSize(){
        return size;
    }
}
