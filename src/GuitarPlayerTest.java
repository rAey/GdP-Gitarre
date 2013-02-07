/******************************************************************************
 *  Compilation:  javac GuitarPlayer.java
 *  Execution:    java  GuitarPlayer
 *
 ******************************************************************************/
import java.awt.Font;

public class GuitarPlayerTest {
	
    public static void main(String[] args) {
		double volume = 1.0 ;
        StdDraw.text(.5, .5, "Click this window and type m, k, o, comma, l or p");

        // big font for displaying the currently pressed key
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 120));

        Guitar guitar = new Guitar();

        // keys to pluck a string
        char[] pluckKeys = {'m', 'k', 'o', ',', 'l', 'p'};

        // keys to press a fret down, only the lower strings can be accessed
        char[][] changeFretKeys = {{'<', 'y', 'x', 'c', 'v', 'b'},
            {'a', 's', 'd', 'f', 'g', 'h'}, {'q', 'w', 'e', 'r', 't', 'z'},
            {'2', '3', '4', '5', '6', '7'}, {},
            {}};

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            while (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // draw the character to standard draw (for debugging)
                StdDraw.clear();
                StdDraw.text(.5, .5, "" + key);

                // pluck the corresponding string
                for (int i = 0; i < pluckKeys.length; i++) {
                    if (key == pluckKeys[i]) {
                        guitar.pluckString(i);
                    }
                }

                // or change the frequency of a string
                for (int i = 0; i < changeFretKeys.length; i++) {
                    for (int j = 0; j < changeFretKeys[i].length; j++) {
                        if (key == changeFretKeys[i][j]) {
                            guitar.pressFretDown(i, j);
                        }
                    }
                }
                
				// volume change
                if (key == '+') {
                    volume += 0.2 ;
                } else if (key == '-' && volume > 0.0) {
                    volume -= 0.2 ;
                }
				guitar.setVolume(volume);

            }

            // send the result to the sound card
            StdAudio.play(guitar.sample());

            // advance the simulation of each guitar string by one step
            guitar.tic();
        }
    }
}
