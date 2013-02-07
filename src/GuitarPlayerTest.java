import java.awt.Font;

class GuitarPlayerTest {

/*
 * GuitarPlayerTest
 *	A little test class to test the Guitar class.
 */
public static void main(String[] args)
{
	StdDraw.text(.5, .5, "Click this window and type m, k, o, comma, l or p");
	StdDraw.setFont(new Font("SansSerif", Font.BOLD, 120));

	double volume = 1.0;
	Guitar guitar = new Guitar();

	char[] pluckKeys = {'m', 'k', 'o', ',', 'l', 'p'};

	char[][] changeFretKeys = {{'z', 'x', 'c', 'v', 'b', 'n'},
		{'a', 's', 'd', 'f', 'g', 'h'}, {'q', 'w', 'e', 'r', 't', 'y'},
		{'1', '2', '3', '4', '5', '6'}};

	// the main input loop
	while (true) {
		while (StdDraw.hasNextKeyTyped()) {
			char key = StdDraw.nextKeyTyped();

			StdDraw.clear();
			StdDraw.text(.5, .5, "" + key);

			// pluck the corresponding string
			for (int i = 0; i < pluckKeys.length; i++) {
				if (key == pluckKeys[i])
					guitar.pluckString(i);
			}
			// or change the frequency of a string
			for (int i = 0; i < changeFretKeys.length; i++) {
				for (int j = 0; j < changeFretKeys[i].length; j++) {
					if (key == changeFretKeys[i][j])
						guitar.pressFretDown(i, j);
				}
			}
			// volume change
			if (key == '+')
				volume += 0.2;
			else if (key == '-' && volume > 0.0)
				volume -= 0.2;
			guitar.setVolume(volume);
		}
		StdAudio.play(guitar.sample());
		guitar.tic();
	}
}

}
