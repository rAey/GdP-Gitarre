
public class Guitar {

    GuitarString strE;
    GuitarString strA;
    GuitarString strD;
    GuitarString strG;
    GuitarString strB;
    GuitarString stre;
    
    double volume ;

    Guitar() {
        strE = new GuitarString(82.41);
        strA = new GuitarString(110.00);
        strD = new GuitarString(146.83);
        strG = new GuitarString(196.00);
        strB = new GuitarString(246.94);
        stre = new GuitarString(329.63);
        volume = 1.0 ;
    }

    void pluckString(int string) {

        switch (string) {
            case 0:
                stre.pluck();
                break;

            case 1:
                strB.pluck();
                break;

            case 2:
                strG.pluck();
                break;

            case 3:
                strD.pluck();
                break;

            case 4:
                strA.pluck();
                break;

            case 5:
                strE.pluck();
                break;

            default:
                break;
        }

        // pluck the corresponding string
    }

    // mute every string
    void mute() {
        strE.mute();
        strA.mute();
        strD.mute();
        strG.mute();
        strB.mute();
        stre.mute();
    }

    void pressFretDown(int string, int fret) {
        switch (string) {
            case 0:
                stre.pressFretDown(fret);
                break;

            case 1:
                strB.pressFretDown(fret);
                break;

            case 2:
                strG.pressFretDown(fret);
                break;

            case 3:
                strD.pressFretDown(fret);
                break;

            case 4:
                strA.pressFretDown(fret);
                break;

            case 5:
                strE.pressFretDown(fret);
                break;

            default:
                break;
        }
    }

    public void tic() {
        strE.tic();
        strA.tic();
        strD.tic();
        strG.tic();
        strB.tic();
        stre.tic();
    }
    
    public void setVolume(double newVolume) {
        volume = newVolume ;
    }
    
    double sample(){
        // No average: Addition of the "waves" of all the current sounds
        return volume * (strE.sample() + strA.sample() + strD.sample() +
                strG.sample() + strB.sample() + stre.sample()) ;
    }
}
