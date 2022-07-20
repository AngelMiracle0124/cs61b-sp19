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
        System.arraycopy(items,0,newarray,1,size);
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
    public void addFirst(T item){
        if (size == items.length){
            givespaceforaddfirst(size + 1);
        }
        items[nextfirst] = item;
        /*update the nextfirst*/
        nextfirst = minusone(nextfirst);
        size = size + 1;
    }

    public void addLast(T item){
        if (items.length == size){
            givespaceforaddlast(size + 1);
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
        T oldfirst = items[addone(nextfirst)];
        nextfirst = addone(nextfirst);
        items[nextfirst] = null;
        size = size - 1;
        return oldfirst;
    }
    public T removeLast(){
        T oldlast = items[minusone(nextlast)];
        nextlast = minusone(nextfirst);
        items[nextlast] = null;
        size = size - 1;
        return oldlast;
    }
    public T get(int index){
        if (index >= size) {
            return null;
        }
        int p = addone(nextfirst);
        return items[(index + p) % items.length];
    }
//    public static void main(String[] args) {
//        System.out.println("Running tests.\n");
//        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
//        ad1.addFirst(10);
//        ad1.addLast(20);
//
//    }
}
