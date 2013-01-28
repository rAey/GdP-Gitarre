import gdp.stdlib.*;
import java.util.*;

public class GuitarString {
	
	private Queue<Double> buffer;
        private int count;

	/**
	Constructor. Create a RingBuffer of the desired capacity N (sampling rate divided by frequency, rounded to the nearest integer), and initialize it to represent a string at rest by enqueueing N zeros.
	 
	Pluck. Replace the N elements in the buffer with N random values between -0.5 and +0.5.
	 
	Mute. Replace the N elements in the buffer with zeros.
	 
	PressFretDown. Change the buffer length as if the string frequency would have changed, saving the frequency of the open string. newFrequency = openStringFrequency * Math.pow(2.0, fret / 12.0);
	 
	Tic. Apply the Karplus-Strong update: delete the sample at the front of the buffer and add to the end of the buffer the average of the first two samples, multiplied by the energy decay factor.
	 
	Sample. Return the value of the item at the front of the buffer.
	 
	Time. Return the total number of times tic() was called.
	**/
	
    	public GuitarString(double frequency){
                    if(frequency <= 0 || (int)(StdAudio.SAMPLE_RATE/frequency) < 2){
                            throw new IllegalArgumentException();
                    }
                    count = 0;
                    int capacity = (int)(StdAudio.SAMPLE_RATE/frequency);
                    
            }
           
            // Constructs guitar string of given frequency
            // IllegalArgumentException if < 2  
            public GuitarString(double[] init){
            if(init.length < 2){
                    throw new IllegalArgumentException();
            }
            for(int i = 0; i < init.length; i++){
                    buffer.add(init[i]);
                    }
           
            }
    	
    }
    
	// set the buffer to white noise
    void pluck(){
    	for(double n : buffer){
                            n = Math.random(); 
                            if(n > .5){        
                                    n = n-1;
                            }

    }
    
	// fill the buffer with zeros
    void mute(){
    	buffer = new LinkedList<Double>();
    	for(int i = 0; i < capacity; i++){
    		buffer.add(0.0);
    	}
    }
    
	// change the length of the buffer according to the number of frets
    void pressFretDown(int fret){
    	
    	// ANY IDEA'S ?
    
    }
    
	// advance the simulation one time step
    void tic(){
    	double decay = .996;
                    for(int i = 0; i < buffer.size(); i++){
                            double temp = buffer.remove();
                            double front = buffer.peek();
                            buffer.add(decay*.5*(temp+front));
                    }
                    count++ ;    

    }
    
    // return the current sample
    double sample(){
	return buffer.peek();
    	
    }
    
	// return number of tics
    int time(){
    	return count;

    }

}
