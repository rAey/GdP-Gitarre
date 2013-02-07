import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class RingBuffer {

private double buffer[];
private int first, last, size;

/*
 * RingBuffer
 *	A simple ring buffer of a given capacity containing doubles.
 */
RingBuffer(int capacity)
{
	buffer = new double[capacity];
	first = last = size = 0;
}

/*
 * size()
 * Return:
 *	Number of items currently in the buffer.
 */
int size()
{
	return size;
}

/*
 * isEmpty()
 *	Is the buffer empty?
 * Return:
 *	true, if empty, else false.
 */
boolean isEmpty()
{
	if (size <= 0)
		return true;
	return false;
}

/*
 * isFull()
 *	Is the buffer full?
 * Return:
 *	true, if full, else false.
 */
boolean isFull()
{
	if (size >= buffer.length)
		return true;
	return false;
}

/*
 * enqueue()
 *	Add an item to the end of the buffer.
 */
void enqueue(double x)
{
	if (isFull())
		throw new BufferOverflowException();

	buffer[last] = x;
	size++;
	if (++last >= buffer.length)
		last = 0;
}

/*
 * dequeue()
 *	Delete and return item from the front.
 * Return:
 *	Item from the front of the buffer.
 */
double dequeue()
{
	if (isEmpty())
		throw new BufferUnderflowException();

	double x = buffer[first];
	size--;
	if (++first >= buffer.length)
		first = 0;
	return x;
}

/*
 * peek()
 * Return:
 *	Item from the front of the buffer.
 */
double peek()
{
	if (isEmpty())
		throw new BufferUnderflowException();

	return buffer[first];
}

/*
 * clear()
 *	Reset the buffer to an empty state, so it can be filled with new
 *	values.
 */
void clear()
{
	first = last = size = 0;
}

}
