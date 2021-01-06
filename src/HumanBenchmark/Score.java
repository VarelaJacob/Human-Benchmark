package HumanBenchmark;

/**
 * This is the class that stores all the high score
 * values obtained by the user in each game they've played.
 * The only method besides the getters and setters is the 
 * initializeScores() method.
 * 
 * @author Jacob Varela
 */
public class Score {
    
    // Global score variables.
    private int chimpScore, visualScore;
    private int numMemScore, verbMemScore;
    private long aimScore, reactionScore;

    // This method initializes each score to have a starting value of zero.
    public void initializeScores(){
        this.reactionScore = 0;
        this.aimScore      = 0;
        this.chimpScore    = 0;
        this.visualScore   = 0;
        this.numMemScore   = 0;
        this.verbMemScore  = 0;
    }

    public long getReactionScore(){
        return reactionScore;
    }

    public void setReactionScore(long newScore){
        this.reactionScore = newScore;
    }

    public long getAimScore(){
        return aimScore;
    }

    public void setAimScore(long newScore){
        this.aimScore = newScore;
    }

    public int getChimpScore(){
        return chimpScore;
    }

    public void setChimpScore(int newScore){
        this.chimpScore = newScore;
    }

    public int getVisualScore(){
        return visualScore;
    }

    public void setVisualScore(int newScore){
        this.visualScore = newScore;
    }

    public int getNumMemScore(){
        return numMemScore;
    }

    public void setNumMemScore(int newScore){
        this.numMemScore = newScore;
    }

    public int getVerbScore(){
        return verbMemScore;
    }

    public void setVerbScore(int newScore){
        this.verbMemScore = newScore;
    }
}
