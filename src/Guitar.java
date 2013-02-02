public class Guitar {

	/**
	Constructor. Tune every string to its correct frequency.
	 
	Pluck. Call pluck() on a GuitarString object.
	 
	Mute. Call mute() on every GuitarString object.
	 
	PressFretDown. Call pressFretDown(fret) on a GuitarString object.
	 
	Tic. Call tic() on every string.
	 
	Sample. By the superposition principle, you can get a sample of your guitar by adding the samples of all strings
	**/
	
	GuitarString strE;
	GuitarString strA;
	GuitarString strD;
	GuitarString strG;
	GuitarString strB;
	GuitarString stre;
	
    Guitar(){
    	strE = new GuitarString(82.41);
    	strA = new GuitarString(110.00);
    	strD = new GuitarString(146.83);
    	strG = new GuitarString(196.00);
    	strB = new GuitarString(246.94);
    	stre = new GuitarString(329.63);
    }
    
    void pluckString(int string){

    	switch( string){
    	case 1:
    		stre.pluck();
    		break;
    		
    	case 2:
    		strB.pluck();
    		break;
    		
    	case 3:
    		strG.pluck();
    		break;
    		
    	case 4:
    		strD.pluck();
    		break;
    		
    	case 5:
    		strA.pluck();
    		break;
    	
    	case 6:
    		strE.pluck();
    		break;
    		
    	default:
    		break;
    	}
    		
    	// pluck the corresponding string
    }
	
    // mute every string
    void mute(){
    	strE.mute();
    	strA.mute();
    	strD.mute();
    	strG.mute();
    	strB.mute();
    	stre.mute();
    }
    
    void pressFretDown(int string, int fret){
    	switch( string){
    	case 1:
    		stre.pressFretDown(fret);
    		break;
    		
    	case 2:
    		strB.pressFretDown(fret);
    		break;
    		
    	case 3:
    		strG.pressFretDown(fret);
    		break;
    		
    	case 4:
    		strD.pressFretDown(fret);
    		break;
    		
    	case 5:
    		strA.pressFretDown(fret);
    		break;
    	
    	case 6:
    		strE.pressFretDown(fret);
    		break;
    		
    	default:
    		break;
    	}
    }
    
    void tic(){
    	strE.tic();
    	strA.tic();
    	strD.tic();
    	strG.tic();
    	strB.tic();
    	stre.tic();
    }
    
    double sample(){
		//by superposition priciple it just adds all frequences to a sum
		return(strE+strA+strD+strB+strE+stre);
		//by logic it should be the average, shouldnt it?
		/*
			double ave = 0;
			int count = 0;
			if(strE != 0) 
			{
				ave += strE; 
				count++;
			}
				if(strA != 0) 
			{
				ave += strA; 
				count++;
			}
				if(strD != 0) 
			{
				ave += strD; 
				count++;
			}
				if(strG != 0) 
			{
				ave += strG; 
				count++;
			}
				if(strB != 0) 
			{
				ave += strB; 
				count++;
			}
				if(stre != 0) 
			{
				ave += stre; 
				count++;
			}
			return(ave/count);
	  // compute a sample of the guitar output
    }
	
}
