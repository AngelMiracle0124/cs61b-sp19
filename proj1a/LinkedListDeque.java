/* Deque is double ended queue which means that there exist two ended */
/* <T> is the parameter of type*/
public class LinkedListDeque<T> {

    /* private class for StuffNode*/
    private class StuffNode {
        private StuffNode prev;
        private T item;
        private StuffNode next;
        private StuffNode( StuffNode n1, T i, StuffNode n2) {
            prev = n1;
            item = i;
            next = n2;
        }
    }
    private StuffNode sentinel;
    private int size;


    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new StuffNode( null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x){
        size = size + 1;
        StuffNode a = new StuffNode(sentinel, x,sentinel.next);
        sentinel.next.prev = a;
        sentinel.next = a;
        /* 把a 放在第一位（sentinel.next.prev）与sentinel的next之间*/

    }
    public void addLast(T x){
        size = size + 1;
        StuffNode a = new StuffNode(sentinel.prev,x,sentinel);
        sentinel.prev.next = a;
        sentinel.prev = a;
        /* 把a 放在最后一位（sentinel.prev.next）与sentinel的prev之间*/
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        StuffNode position=sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.println(position.item + " ");
            position = position.next;
        }
    }
    public T removeFirst(){
        if (sentinel.next == sentinel){
            return null;
        }
        size = size - 1;
        T a = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return a;
    }
    public T removeLast(){
        if (sentinel.prev == null){
            return null;
        }
        size = size - 1;
        T a = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return a;
    }
    public T get(int index){
        StuffNode position = sentinel.next;
        if (index > size - 1){
            return null;
        }
        for (int i = 0; i < size; i++) {
            position = position.next;
            if (i == index) {
                return position.item;
            }
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getHelp(sentinel.next, index);
    }

    private T getHelp(StuffNode l, int i) {
        if (i == 0) {
            return l.item;
        }
        return getHelp(l.next, i - 1);
    }

}
