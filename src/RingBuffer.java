
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class RingBuffer {

    private double buffer[];
    private int first, last, size;

// constructor to create an empty buffer, with given max capacity
    RingBuffer(int capacity) {
        buffer = new double[capacity];
        first = last = size = 0;
    }

// return number of items currently in the buffer
    public int size() {
        return size;
    }
    
    public int getCapacity() {
        return buffer.length;
    }

// is the buffer empty (size equals zero)?
    public boolean isEmpty() {
        if (size <= 0) {
            return true;
        }
        return false;
    }

// is the buffer full  (size equals capacity)?
    public boolean isFull() {
        if (size >= buffer.length) {
            return true;
        }
        return false;
    }

// add item x to the end
    public void enqueue(double x) {
        if (isFull()) {
            throw new BufferOverflowException();
        }

        buffer[last] = x;
        size++;
        if (++last >= buffer.length) {
            last = 0;
        }
    }

// delete and return item from the front
    public double dequeue() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }

        double x = buffer[first];
        size--;
        if (++first >= buffer.length) {
            first = 0;
        }
        return x;
    }

// return (but do not delete) item from the front
    public double peek() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }

        return buffer[first];
    }

// clear the buffer, so it can be filled with new elements
    public void clear() {
        first = last = size = 0;
    }

    public double getNextLastElement() {
        if (first == buffer.length - 1) {
            return buffer[first] + buffer[0] ;
        } else {
            return  buffer[first] + buffer[first+1] ;
        }

    }
}
