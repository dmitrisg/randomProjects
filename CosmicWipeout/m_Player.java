package CosmicWipeout;

public class m_Player {
    private String name;
    private int totalScore = 0;

    //purpose: construct and initialize m_Player object.
    //inputs: self - the m_Player object being created
    //        name - player's name.
    //outputs: none.
    //assumptions: none.
    public m_Player(String name){
        this.name = name;
    }

    //purpose: gets name String.
    //inputs: self.
    //outputs: returns name String.
    //assumptions: name String exists.
    public String getName(){
        return this.name;
    }

    //purpose: gets totalScore int.
    //inputs: self.
    //outputs: returns totalScore int.
    //assumptions: totalScore int exists.
    public int getTotalScore(){
        return this.totalScore;
    }

    //purpose: replace totalScore.
    //inputs: self.
    //        newScore - int with new score.
    //outputs: none.
    //assumptions: totalScore int exists.
    public void replaceScore(int newScore){
        this.totalScore = newScore;
    }

    //purpose: increase totalScore by specified amount.
    //inputs: self.
    //        newScore - int by which to increase score.
    //outputs: none.
    //assumptions: totalScore int exists.
    public void updateScore(int newScore){
        totalScore += newScore;
    }
}
