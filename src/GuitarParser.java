/*
 * GuitarParser.java -
 *     Play a song: "java GuitarParser < path_to_song.gp"
 *     Save a song to a wav file: "java GuitarParser song.wav < path_to_song.gp"
 *     Save a song to a au file: "java GuitarParser song.au < path_to_song.gp"
 *
 *     Look at the examples for the song format...
 */
import java.util.ArrayList;

public class GuitarParser {


private static final String STR_PLAYERNAME = "Super cool and funky guitar player by Wurstfachverkäuferin";
private static final String STR_GENERATING = "Generating waveform, please stand by...";
private static final String STR_PLAYING    = "Now playing, please enjoy... :)";

private static final String HDR_ARTIST  = "artist: ";
private static final String HDR_TITLE   = "title: ";
private static final String HDR_BPM     = "bpm: ";
private static final String HDR_SPEED   = "speed: ";
private static final String HDR_TRACKS  = "tracks: ";
private static final String COMMENT     = "//";

private static final int BASE_STEP = 60 * 44100;    // 60sec * SAMPLING_RATE

private static String artist = "$artist";     // Artist name
private static String title  = "$title";      // Song title
private static int    bpm    = 120;           // Song BPM
private static int    speed  = 4;             // Song speed (length of one line)
private static int    tracks = 1;             // # of Tracks

private static GuitarString strings[];


// Super cool and funky guitar player.
public static void main(String args[])
{
	parseHeader();
	printLogo();
	printSongInfo();

	// Initialize guitar strings
	strings = new GuitarString[tracks];
	for (int i = 0; i < tracks; ++i)
		strings[i] = new GuitarString(440);

	// Generate waveform for output
	System.out.println(STR_GENERATING);
	ArrayList<Double> wave = new ArrayList<Double>();
	int step = (int)((BASE_STEP/bpm) * (4.0/speed));
	while (parseNotes()) {
		for (int i = 0; i < step; ++i)
			wave.add(playNotes());
	}
	double wave_array[] = new double[wave.size()];
	for (int i = 0; i < wave.size(); ++i)
		wave_array[i] = wave.get(i).doubleValue();

	if (args.length == 0) {
		// Play waveform
		System.out.println(STR_PLAYING);
		StdAudio.play(wave_array);
	} else {
		// Save waveform to file
		System.out.println("Saving to " + args[0] + "...");
		StdAudio.save(args[0], wave_array);
	}

	System.exit(0);
}

// Parse the metadata of the song.
private static void parseHeader()
{
	String ln;
	while (!(ln = StdIn.readLine()).equals("")) {
		if (ln.startsWith(HDR_ARTIST))
			artist = ln.substring(HDR_ARTIST.length());
		else if (ln.startsWith(HDR_TITLE))
			title = ln.substring(HDR_TITLE.length());
		else if (ln.startsWith(HDR_BPM))
			bpm = Integer.parseInt(ln.substring(HDR_BPM.length()));
		else if (ln.startsWith(HDR_SPEED))
			speed = Integer.parseInt(ln.substring(HDR_SPEED.length()));
		else if (ln.startsWith(HDR_TRACKS))
			tracks = Integer.parseInt(ln.substring(HDR_TRACKS.length()));
	}
}

// Parse one line of notes.
// Return false, if there is no more note data.
private static boolean parseNotes()
{
	if (StdIn.isEmpty())
		return false;

	// Strip comments
	String ln = StdIn.readLine();
	while (ln.startsWith(COMMENT))
		ln = StdIn.readLine();

	String notes[] = ln.split("\t", tracks);                 // Split each track
	for (int i = 0; i < notes.length; ++i) {
		String note_vol[] = notes[i].split(" ", 2);          // Split one track in note and volume
		char note[] = note_vol[0].toCharArray();             // Split note data in 3 parts

		if (!note_vol[1].equals("--")) {
			double vol = Integer.parseInt(note_vol[1]) / 100.0;
			strings[i].setVolume(vol);
		}
		if (note[1] != '-') {
			// Transpose relative to A
			int trans = 0;
			switch (Character.toUpperCase(note[1])) {
				case 'C': trans -= 9; break;
				case 'D': trans -= 7; break;
				case 'E': trans -= 5; break;
				case 'F': trans -= 4; break;
				case 'G': trans -= 2; break;
				case 'B': trans += 2; break;
			}
			switch (note[0]) {
				case '#': ++trans; break;
				case 'b': --trans; break;
			}
			trans += (Character.getNumericValue(note[2])-3) * 12;

			strings[i].pressFretDown(trans);
			strings[i].pluck();
		}
	}

	return true;
}

// Play the parsed notes and return a single sample.
private static Double playNotes()
{
	double sample = 0;
	for (int i = 0; i < tracks; ++i) {
		sample += strings[i].sample();
		strings[i].tic();
	}
	return new Double(sample);
}

// Print the metadata of the song.
private static void printSongInfo()
{
	System.out.println(artist + " - " + title
		+ " // " + bpm + " BPM, " + speed + " // " + tracks + " Tracks");
	System.out.println();
}

// Print a little logo.
private static void printLogo()
{
	for (int i = 0; i < STR_PLAYERNAME.length(); ++i)
		System.out.print("-");
	System.out.println();
	System.out.println(STR_PLAYERNAME);
	for (int i = 0; i < STR_PLAYERNAME.length(); ++i)
		System.out.print("-");
	System.out.println();
}

}
