package CosmicWipeout;

public class m_ScoreRecord {
    private char player;
    private int turnScore;
    private int totalScore;

    public m_ScoreRecord(char player, int turnScore, int totalScore){
        this.player = player;
        this.turnScore = turnScore;
        this.totalScore = totalScore;
    }

    public char getPlayer(){
        return player;
    }

    public int getTurnScore(){
        return turnScore;
    }

    public int getTotalScore(){
        return totalScore;
    }
}
