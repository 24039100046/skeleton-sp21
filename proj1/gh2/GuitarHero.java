package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        MusicString[] string = new GuitarString[37];
//        MusicString[] string = new _String[37];
        Double[] concert = new Double[37];
        for (int i = 0; i < 37; i++) {
            concert[i] = 440 * Math.pow(2, (double) (i - 24) / 12);
            string[i] = new GuitarString(concert[i]);
//            string[i] = new _String(concert[i]);
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0 && index < 37) {
                    string[index].pluck();
                }
            }
            double sample = 0;
            for (MusicString s : string) {
                sample += s.sample();
            }
            StdAudio.play(sample);
            for (MusicString s : string) {
                s.tic();
            }
        }
    }
}
