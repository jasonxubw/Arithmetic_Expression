import java.util.NoSuchElementException;

public class DynamicArrayStack<AnyType> implements Stack<AnyType> {
    public static final int DEFAULT_CAPACITY = 256;
    AnyType[] data;
    int topOfStack;
    int theSize;

    public DynamicArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArrayStack(int capacity) { //stack constructor
        topOfStack = -1;
        theSize = 0;
        data = (AnyType[]) new Object[capacity];
    }

    public int size() {
        return theSize;
    } //returns the size of the stack

    public boolean isEmpty() {
        return (theSize == 0);
    } //checks if stack is empty

    public void push(AnyType newValue) { //adds a new value on top of the stack
        int n = size();
        if (n==data.length){
            resize(2 * data.length);
        }
        topOfStack++;
        data[topOfStack] = newValue;
        theSize++;
    }

    public AnyType top() {
        return data[topOfStack];
    } //returns the data at the top of the stack

    public AnyType pop() { //removes the data at the top of the stack, then returns that value
        int n = size();
        if (n <= (data.length / 4)){
            resize(data.length / 2);
        }
        if (theSize == 0){
            throw new NoSuchElementException();
        }
        AnyType temp = data[topOfStack];
        data[topOfStack] = null;
        topOfStack--;
        theSize--;
        return temp;
    }

    protected void resize(int newCapacity) { //creates a new stack with a larger capacity, and copies everything over
        int n = size();
        AnyType[] temp = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < n; i++)
            temp[i] = data[i];
        data = temp;
    }
}