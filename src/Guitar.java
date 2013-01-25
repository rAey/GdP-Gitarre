
public class Guitar {

	/**
	Constructor. Tune every string to its correct frequency.
	 
	Pluck. Call pluck() on a GuitarString object.
	 
	Mute. Call mute() on every GuitarString object.
	 
	PressFretDown. Call pressFretDown(fret) on a GuitarString object.
	 
	Tic. Call tic() on every string.
	 
	Sample. By the superposition principle, you can get a sample of your guitar by adding the samples of all strings
	**/
	
    Guitar(){
    	
    }
    void pluckString(int string){
    	// pluck the corresponding string
    }
    void mute(){
    	// mute every string
    }
    void pressFretDown(int string, int fret){
    	// press a fret on a string down
    }
    void tic(){
    	// advance the simulation one every string
    }
    double sample(){
		return 0;
	  // compute a sample of the guitar output
    }
	
}
