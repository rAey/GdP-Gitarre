
public class GuitarString {

	/**
	Constructor. Create a RingBuffer of the desired capacity N (sampling rate divided by frequency, rounded to the nearest integer), and initialize it to represent a string at rest by enqueueing N zeros.
	 
	Pluck. Replace the N elements in the buffer with N random values between -0.5 and +0.5.
	 
	Mute. Replace the N elements in the buffer with zeros.
	 
	PressFretDown. Change the buffer length as if the string frequency would have changed, saving the frequency of the open string. newFrequency = openStringFrequency * Math.pow(2.0, fret / 12.0);
	 
	Tic. Apply the Karplus-Strong update: delete the sample at the front of the buffer and add to the end of the buffer the average of the first two samples, multiplied by the energy decay factor.
	 
	Sample. Return the value of the item at the front of the buffer.
	 
	Time. Return the total number of times tic() was called.
	**/
	
    GuitarString(double frequency){
    	
    }
    
	// set the buffer to white noise
    void pluck(){

    }
    
	// fill the buffer with zeros
    void mute(){

    }
    
	// change the length of the buffer according to the number of frets
    void pressFretDown(int fret){
    
    }
    
	// advance the simulation one time step
    void tic(){

    }
    
    // return the current sample
    double sample(){
		return 0;
    	
    }
    
	// return number of tics
    int time(){
		return 0;

    }

}
