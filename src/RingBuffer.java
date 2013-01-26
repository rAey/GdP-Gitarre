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
int size() {
	return size;
}

// is the buffer empty (size equals zero)?
boolean isEmpty() {
	if (size <= 0)
		return true;
	return false;
}

// is the buffer full  (size equals capacity)?
boolean isFull() {
	if (size >= buffer.length)
		return true;
	return false;
}

// add item x to the end
void enqueue(double x) {
	if (isFull()) {
		throw new BufferOverflowException();
	}
	
	buffer[last] = x;
	
	last++;
	size++;
	if (last >= buffer.length)
		last = 0;
}

// delete and return item from the front
double dequeue() {
	if (isEmpty()) {
		throw new BufferUnderflowException();
	}
	
	double x = buffer[first];
	
	first++;
	size--;
	if (first >= buffer.length)
		first = 0;
	
	return x;
}

// return (but do not delete) item from the front
double peek() {
	return buffer[first];
}

}
