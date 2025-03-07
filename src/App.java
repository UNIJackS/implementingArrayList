import java.lang.String;

class customArrayList<E> {
    public static final int INITAL_CAPACITY = 8;

    private E[] internalArray; // Internal array to store all values
    private int size;

    // -------------------------- Constructors --------------------------
    public customArrayList(int size) {
        if (size <= 0) {throw new IllegalArgumentException();}
        internalArray = createGenericArray(size);
        //internalArray = new E[size];
        size = 0;
    }

    public customArrayList() {this(INITAL_CAPACITY);}

    // ------------------------- Private Methods -------------------------
    private boolean checkInArray(int index) {return index < internalArray.length;}

    @SuppressWarnings("unchecked")
    private E[] createGenericArray(int size){
        return (E[]) new Object[size];
    }

    // ----------------------------- Getters -----------------------------
    public int size() {return size;}

    public int capacity() {return internalArray.length;}

    // ------------------------- Public Methods --------------------------
    public String toString(){
        String output = "capacity : " + capacity() + " | size : " + size() + " | Contenets -> ";
        for(int i =0; i < internalArray.length; i +=1){
            output += "[" + i + "] : " +internalArray[i] +" | ";
        }
        return output;
    }

    public void ensureCapacity(int desiredCapacity) {
        if (desiredCapacity <= 0) {throw new IllegalArgumentException("Ensured capacity must be greater than 0");}
        if (internalArray.length >= desiredCapacity) {return;} // Already enough space exit early

        if(desiredCapacity < internalArray.length*2){desiredCapacity = internalArray.length*2;}
        E[] newInternalArray = createGenericArray(desiredCapacity);
        //Copys over the current contents to the new array
        for (int i = 0; i < size; i += 1) {
            newInternalArray[i] = internalArray[i];
        }
        internalArray = newInternalArray;
    }

    public void trimToSize(){
        if (internalArray.length == size) {return;} // Already size early return

        E[] newInternalArray = createGenericArray(size);
        //Copys over the current contents to the new array. Cuts off any elements past the size
        for (int i = 0; i < size; i += 1) {
            newInternalArray[i] = internalArray[i];
        }
        internalArray = newInternalArray;
    }

    public E get(int index) {
        // If attempting to acess past the end of the array throw error.
        if (!checkInArray(index)) {throw new IllegalArgumentException("Attempting to acess index past end of List");}
        // Else return the value in the array.
        return internalArray[index];
    }

    // Adds at index of list
    public void add(E newItem, int index) {
        ensureCapacity(size+1); // Makes sure there is enough capacity for the new item

        // If attempting to acess past the end of the array throw error.
        if (!checkInArray(index) || index > size+1) {throw new IllegalArgumentException("Attempting to assign past end of list | Item :" + newItem);}

        //Shifts up items in array starting from the end of the elements in the array down till the index of the item to be added.
        for (int i = size-1; i > index; i -= 1) {
            internalArray[i+1] = internalArray[i];
        }
        //Sets the new item at the index.
        internalArray[index] = newItem;
        size +=1;
    }

    // Adds to end of list
    public void add(E newItem) {
        add(newItem, size);
    }

    public E remove(int index) {
        if (!checkInArray(index)) {throw new IllegalArgumentException("Attempting to remove item at index past end of list | index :" + index);}
        E removedValue = internalArray[index];
        //Shift all the items down from the position of the item
        for (int i = index; i < size - 1; i += 1) {
            internalArray[i] = internalArray[i + 1];
        }
        internalArray[size-1] = null; //Sets the last element in the array to null as it has been copyed down
        size -= 1;
        return removedValue;
    }

    public E remove(E toRemove) {
        for (int i = 0; i < size; i += 1) {
            if (toRemove.equals(internalArray[i])) {
                return remove(i);   // Early exit so only first copy of string is removed.

            }
        }
        throw new IllegalArgumentException("Attempting to remove string that is not in list | String :" + toRemove);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // assert false : "always false assert";

        customArrayList<String> testArrayList = new customArrayList<String>();
        System.out.println(testArrayList);

        testArrayList.add("1 Item");
        testArrayList.add("2 Item");
        testArrayList.add("3 Item");
        testArrayList.add("4 Item");
        testArrayList.add("5 Item");
        testArrayList.add("6 Item");
        testArrayList.add("7 Item");
        testArrayList.add("8 Item");
        testArrayList.add("9 Item");
        testArrayList.add("10 Item");
        System.out.println(testArrayList);

        

        assert testArrayList.size() == 10 : "size does not return 10";
        assert testArrayList.get(3) == "4 Item" : "Forth item does not match";

        testArrayList.remove(7);
        assert testArrayList.size() == 9 : "size does not return 9 after removing item";
        assert testArrayList.get(3) == "4 Item" : "fourth item does not match";

        testArrayList.add("new first",0);

        testArrayList.add("new item",5);
    
        testArrayList.remove(9);

        testArrayList.add("test");


        testArrayList.trimToSize();
        testArrayList.add("test 2");




    }
}
