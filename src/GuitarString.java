public class GuitarString {

static final int SAMPLING_RATE = 44100;

private RingBuffer buffer;
private int        count;
private double     frequency, volume;

/*
 * GuitarString
 *	Model of a vibrating guitar string of a given frequency.
 */
GuitarString(double freq)
{
	frequency = freq;
	count = 0;
	volume = 1.0;
	int capacity = (int)Math.round(SAMPLING_RATE / frequency);
	buffer = new RingBuffer(capacity);
	mute();
}

/*
 * pluck()
 *	Replace the N elements in the buffer with N random values
 *	between -0.5 and +0.5.
 */
void pluck()
{
	buffer.clear();
	while (!buffer.isFull())
		buffer.enqueue(Math.random() - 0.5);
}

/*
 * mute()
 *	Replace the N elements in the buffer with zeros.
 */
void mute()
{
	buffer.clear();
	while (!buffer.isFull())
		buffer.enqueue(0.0);
}

/*
 * pressFretDown()
 *	Change the buffer length as if the string frequency would have
 *	changed.
 */
void pressFretDown(int fret)
{
	double newFrequency = frequency * Math.pow(2.0, fret / 12.0);
	int capacity = (int)Math.round(SAMPLING_RATE / newFrequency);
	buffer = new RingBuffer(capacity);
	mute();
}

/*
 * tic()
 *	Apply the Karplus-Strong update: delete the sample at the front of
 *	the buffer and add to the end of the buffer the average of the first
 *	two samples, multiplied by the energy decay factor.
 */
void tic()
{
	double a = buffer.dequeue();
	double b = buffer.peek();
	a = (a+b) / 2;
	a *= 0.996;
	buffer.enqueue(a);
	count++;
}

/*
 * setVolume()
 *	Set the volume of the guitar string.
 */
void setVolume(double vol)
{
	volume = vol;
}

/*
 * sample()
 *	Sample the guitar string.
 * Return:
 *	The item at the front of the buffer.
 */
double sample()
{
	return buffer.peek() * volume;
}

/*
 * time()
 * Return:
 *	The total number of times tic() was called.
 */
int time()
{
	return count;
}

}
