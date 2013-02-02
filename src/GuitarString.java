
import gdp.stdlib.*;
import java.util.*;

public class GuitarString {
    private RingBuffer ringBuffer;
    private RingBuffer frettedBuffer;
    private int count;
    private double frequency ;
    private int SAMPLING_RATE = 44100 ;
    private boolean fretPressed ;

    public GuitarString(double f) {
        frequency = f ; 
        fretPressed = false ;
        if (frequency <= 0 || (int) (SAMPLING_RATE / frequency) < 2) {
            throw new IllegalArgumentException();
        }
        count = 0;
        int capacity = (int) (SAMPLING_RATE / frequency);
        ringBuffer = new RingBuffer(capacity);
        
        for (int i = 0; i < capacity; i++) {
            ringBuffer.enqueue(0.0); // void mute()
        }
    }
    
    // set the buffer to white noise
    void pluck() {
        ringBuffer.clear() ;
        for (int i=0; i<ringBuffer.getCapacity(); i++) {
            ringBuffer.enqueue(Math.random() - 0.5);
        }
    }

    // fill the buffer with zeros
    public void mute() {
        for (int i = 0; i < ringBuffer.getCapacity(); i++) {
            ringBuffer.enqueue(0.0); // void mute()
        }
    }

    // change the length of the buffer according to the number of frets
    void pressFretDown(int fret) {
        fretPressed = true ;
        double newFrequency = frequency * Math.pow(2.0, fret / 12.0) ;
        int capacity = (int) (SAMPLING_RATE / newFrequency);
        ringBuffer = new RingBuffer(capacity) ;
        pluck() ;
    }

    // advance the simulation one time step
    void tic() {
        ringBuffer.dequeue();
        ringBuffer.enqueue(0.996*(0.5*ringBuffer.getNextLastElement())) ;
        count++;
    }

    // return the current sample
    public double sample() {
        return ringBuffer.peek();
    }
    // return number of tics
    public int time() {
        return count;
    }
}
