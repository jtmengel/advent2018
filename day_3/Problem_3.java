import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoltCalculator{
  // This array list will house all the rows as BoltOfCloth obj's
  ArrayList plannedBolts = new ArrayList<BoltOfCloth>();
  
  /**
   * An object made to contain the elements definine a 'bolt' of cloth (this i
   * will be built from the ingested input data).
   */
  class BoltOfCloth{
    public int id;
    public int xOffset;
    public int yOffset;
    public int boltWidth;
    public int boltHeight;

    BoltOfCloth(String boltDescr){
      String parsedStr[] = boltDescr.replaceAll("[#@: ,x]+", ",").split(",");
      this.id = Integer.parseInt(parsedStr[1]);
      this.xOffset = Integer.parseInt(parsedStr[2]);
      this.yOffset = Integer.parseInt(parsedStr[3]);
      this.boltWidth = Integer.parseInt(parsedStr[4]);
      this.boltHeight = Integer.parseInt(parsedStr[5]);
    }
    
    public String toString(){
      return "#"+this.id+" @ "+
        this.xOffset+","+this.yOffset+": "+
        this.boltWidth+"x"+this.boltHeight;
    }
  }
  
  /**
   * Opens a file and populates/memoizes the data we are going to be using to
   * determine if there are overlaps. 
   * @param fileIn  A String path to a file in open and read in as input data
   */
  BoltCalculator(String fileIn){
    // TODO well, plot the bolts of fabric to cut and see if there are overlaps
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(fileIn));
      String line = reader.readLine();
      while (line != null) {
        plannedBolts.add( new BoltOfCloth(line) );
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Todo now that you have your arraylist populated, check it
    for(BoltOfCloth o: plannedBolts){
      System.out.println( o.toString() );
    }
  }
}

/**
 * For a given file to ingest, this will build a graph and indicate at the
 * coordinates of that graph how many bolts of cloth (per the layout indicated
 * in the ingested input file) overlap. 
 */
public class Problem_3{
  
  /**
   * Main method, passes in the provided argument [0] and kicks off the logic;
   * For this script, we only care about args[0], the path to an input file.
   * @param args  All provided command line arguments 
   */
  public static void main(String args[]) {
    String file = args[0];
    new BoltCalculator(file);
  }
}
