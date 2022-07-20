public class ArrayDeque<T>{
    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;
    /*empty set*/
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }
    private void givespaceforaddfirst(int capacity){
        T[] newarray = (T []) new Object[capacity];
        System.arraycopy(items,0,newarray,0,nextfirst);
        System.arraycopy(items,nextfirst,newarray,nextfirst+1,size-nextfirst);
        items = newarray;
    }
    private void givespaceforaddlast(int capacity){
        T[] newarray = (T []) new Object[capacity];
        System.arraycopy(items,0,newarray,0,size);
        items = newarray;
    }

    private int minusone(int one){
        one = one - 1;
        if (one < 0){
            one = items.length - Math.abs(one);
        }
        return one;
    }
    private int addone(int one){
        one = (one + 1) % items.length;
        return one;
//        if (nextlast >= items.length){
//            nextlast = nextlast - items.length;
//        }
    }
    private void resize(int cap) {
        T[] newarray = (T []) new Object[cap];
        int oldindex = addone(nextfirst);
        for (int i = 0; i < size;i++){
                newarray[i] = items[oldindex];
                oldindex = addone(oldindex);
        }
        items = newarray;
        nextfirst = items.length - 1;
        nextlast = size;
    }
    public void addFirst(T item){
        if (size == items.length){
            resize(size + 5);
        }
//        if (nextfirst == nextlast) {
//            resize(item);
//        }
        items[nextfirst] = item;
        /*update the nextfirst*/
        nextfirst = minusone(nextfirst);
        size = size + 1;
    }

    public void addLast(T item){
        if (size == items.length){
            resize(size + 5);
        }
        items[nextlast] = item;
        nextlast = addone(nextlast);
        size = size + 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for (int i =  addone(nextfirst); i != nextfirst; i=(i+1) % items.length){
            System.out.println(items[i] + " ");
        }
    }
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        nextfirst = addone(nextfirst);
        T oldfirst = items[nextfirst];
        items[nextfirst] = null;
        size = size - 1;
        if (items.length >= 16 && size < (items.length / 4)) {
            resize(size - 5);
        }
        return oldfirst;
    }
    public T removeLast(){
        if (size == 0){
            return null;
        }
        nextlast = minusone(nextlast);
        T oldlast = items[nextlast];
        items[nextlast] = null;
        size = size - 1;
        if (items.length >= 16 && size < (items.length / 4)) {
            resize(size - 5);
        }
        return oldlast;
    }
    public T get(int index){
        if (index >= size) {
            return null;
        }
        int p = addone(nextfirst);
        return items[(index + p) % items.length];
    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addLast(0);
        ad1.removeLast();
        ad1.addFirst(2);
        ad1.removeFirst();
        ad1.addFirst(4);
        ad1.removeFirst();
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.addLast(10);
        ad1.addFirst(11);
        ad1.addLast(12);
        ad1.addLast(13);
        ad1.addLast(14);
        ad1.removeLast();
        ad1.addLast(16);
        ad1.addFirst(17);
        ad1.addFirst(18);
        ad1.addFirst(19);
        ad1.addLast(20);
        ad1.addFirst(21);
        System.out.println(ad1.get(1));


    }
}
