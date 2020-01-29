import java.io.File;
 
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
 

public class PlayMIDI {

  public void setup() {
    try {
        Sequence sequence = MidiSystem.getSequence(new File("LT.mid"));
 
      // Create a sequencer for the sequence
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
    // Start playing
      sequencer.start();
    }
    catch (Exception e)
    {
      // a special way i'm handling logging in this application
      System.out.println(e);
    }
  }
  
}