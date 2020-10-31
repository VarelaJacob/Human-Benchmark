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
    private int aimScore, chimpScore, visualScore;
    private int customScore, typingScore, numMemScore, verbMemScore;
    private long reactionScore;

    // This method initializes each score to have a starting value of zero.
    public void initializeScores(){
        this.reactionScore = 0;
        this.aimScore      = 0;
        this.chimpScore    = 0;
        this.visualScore   = 0;
        this.customScore   = 0;
        this.typingScore   = 0;
        this.numMemScore   = 0;
        this.verbMemScore  = 0;
    }

    public long getReactionScore(){
        return reactionScore;
    }

    public void setReactionScore(long newScore){
        this.reactionScore = newScore;
    }

    public int getAimScore(){
        return aimScore;
    }

    public void setAimScore(int newScore){
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

    public int getCustomScore(){
        return customScore;
    }

    public void setCustomScore(int newScore){
        this.customScore = newScore;
    }

    public int getTypingScore(){
        return typingScore;
    }

    public void setTypingScore(int newScore){
        this.typingScore = newScore;
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
