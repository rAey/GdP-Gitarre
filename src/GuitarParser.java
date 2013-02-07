import java.util.ArrayList;

class GuitarParser {

static final String STR_PLAYERNAME = "Super cool and funky guitar player by Wurstfachverkäuferin";
static final String STR_GENERATING = "Generating waveform, please stand by...";
static final String STR_PLAYING    = "Now playing, please enjoy... :)";
static final String STR_TIME       = "Total play time: ";

static final String HDR_ARTIST = "artist: ";
static final String HDR_TITLE  = "title: ";
static final String HDR_BPM    = "bpm: ";
static final String HDR_SPEED  = "speed: ";
static final String HDR_TRACKS = "tracks: ";
static final String COMMENT    = "//";

static final int SAMPLING_RATE = 44100;

static String artist = "$artist";     // Artist name
static String title  = "$title";      // Song title
static int    bpm    = 120;           // Song BPM
static int    speed  = 4;             // Song speed (length of one line)
static int    tracks = 1;             // # of Tracks

static GuitarString strings[];

/*
 * GuitarParser
 *	Read a song from a .gp file and play or save it to disk.
 *
 *	Play a song: "java GuitarParser < path_to_song.gp"
 *	Save a song to a wav file: "java GuitarParser song.wav < path_to_song.gp"
 *	Save a song to a au file: "java GuitarParser song.au < path_to_song.gp"
 *
 *	Look at the examples for the song format...
 */
public static void main(String args[])
{
	parseHeader();
	printLogo();
	printSongInfo();

	// Initialize guitar strings (one GuitarString per track)
	strings = new GuitarString[tracks];
	for (int i = 0; i < tracks; ++i)
		strings[i] = new GuitarString(440);

	// Generate waveform for output
	System.out.println(STR_GENERATING);
	ArrayList<Double> wave = new ArrayList<Double>();
	int step = (int)((60.0*SAMPLING_RATE/bpm) * (4.0/speed));
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
		printTime(wave_array.length);
		StdAudio.play(wave_array);
	} else {
		// Save waveform to file
		System.out.println("Saving to " + args[0] + "...");
		printTime(wave_array.length);
		StdAudio.save(args[0], wave_array);
	}

	System.exit(0);
}

/*
 * parseHeader()
 *	Parse the metadata of the song.
 */
static void parseHeader()
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

/*
 * parseNotes()
 *	Parse one line of notes and update the volume and frequency of each
 *	GuitarString.
 * Return:
 *	false, if there is are no more lines, else true.
 */
static boolean parseNotes()
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
			case 'C':
				trans -= 9;
				break;
			case 'D':
				trans -= 7;
				break;
			case 'E':
				trans -= 5;
				break;
			case 'F':
				trans -= 4;
				break;
			case 'G':
				trans -= 2;
				break;
			case 'B':
				trans += 2;
			}
			switch (note[0]) {
			case '#':
				++trans;
				break;
			case 'b':
				--trans;
			}
			trans += (Character.getNumericValue(note[2])-3) * 12;

			strings[i].pressFretDown(trans);
			strings[i].pluck();
		}
	}

	return true;
}

/*
 * playNotes()
 *	Play the current line of notes.
 * Return:
 *	Sample of all GuitarStrings.
 */
static Double playNotes()
{
	double sample = 0;
	for (int i = 0; i < tracks; ++i) {
		sample += strings[i].sample();
		strings[i].tic();
	}
	return new Double(sample);
}

/*
 * printSongInfo()
 *	Print the song metadata.
 */
static void printSongInfo()
{
	System.out.println(artist + " - " + title
		+ " // " + bpm + " BPM, " + speed + " // " + tracks + " Tracks");
	System.out.println();
}

/*
 * printLogo()
 *	Print a little header.
 */
static void printLogo()
{
	for (int i = 0; i < STR_PLAYERNAME.length(); ++i)
		System.out.print("-");
	System.out.println();
	System.out.println(STR_PLAYERNAME);
	for (int i = 0; i < STR_PLAYERNAME.length(); ++i)
		System.out.print("-");
	System.out.println();
}

/*
 * printTime()
 *	Print the total length of the song.
 */
static void printTime(int samples)
{
	int sec = samples / SAMPLING_RATE;
	int min = 0;
	while (sec - 60 >= 0) {
		sec -= 60;
		++min;
	}
	System.out.print(STR_TIME);
	System.out.println(min + ":" + sec);
}

}
