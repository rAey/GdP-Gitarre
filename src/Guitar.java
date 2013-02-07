public class Guitar {

private GuitarString strE;
private GuitarString strA;
private GuitarString strD;
private GuitarString strG;
private GuitarString strB;
private GuitarString stre;

private double volume;

/*
 * Guitar
 *	Wrapper around GuitarString to build a Guitar.
 */
Guitar()
{
	strE = new GuitarString(82.41);
	strA = new GuitarString(110.00);
	strD = new GuitarString(146.83);
	strG = new GuitarString(196.00);
	strB = new GuitarString(246.94);
	stre = new GuitarString(329.63);
	volume = 1.0;
}

/*
 * pluckString()
 *	Call pluck() on a GuitarString object.
 */
void pluckString(int string)
{
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
	}
}

/*
 * mute()
 *	Call mute() on every GuitarString object.
 */
void mute()
{
	strE.mute();
	strA.mute();
	strD.mute();
	strG.mute();
	strB.mute();
	stre.mute();
}

/*
 * pressFretDown()
 *	Call pressFretDown() on a GuitarString object.
 */
void pressFretDown(int string, int fret)
{
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
	}
}

/*
 * tic()
 *	Call tic() on every string.
 */
void tic()
{
	strE.tic();
	strA.tic();
	strD.tic();
	strG.tic();
	strB.tic();
	stre.tic();
}

/*
 * setVolume()
 *	Set the volume of the Guitar.
 *	1.0 is full volume and 0.0 is silence.
 */
void setVolume(double vol)
{
	volume = vol;
}

/*
 * sample()
 *	Sample the guitar.
 * Return:
 *	The added samples of all GuitarString objects.
 */
double sample()
{
	return volume * (strE.sample() + strA.sample() + strD.sample()
		+ strG.sample() + strB.sample() + stre.sample());
}

}
