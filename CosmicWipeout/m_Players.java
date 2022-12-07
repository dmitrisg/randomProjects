package CosmicWipeout;

public class m_Players {
    private m_Player human;
    private m_Player computer;
    private final int winScore = 500;
    private final String nullPlayerName = "No one";
    private final String computerName = "Pol";

    //purpose: construct and initialize m_Players object.
    //inputs: self - the m_Players object being created
    //outputs: none.
    //assumptions: none.
    public m_Players(){
        this.computer = new m_Player(computerName);
    }

    public boolean isComputer(m_Player player){
        return player.getName().equals(computerName);
    }

    //purpose: gets name String.
    //inputs: player - the player whose name you are getting.
    //outputs: returns name String.
    //assumptions: name String exists.
    public String getName(m_Player player){
        return player.getName();
    }

    //purpose: gets computerName String.
    //inputs: self.
    //outputs: returns computerName String.
    //assumptions: computerName String exists.
    public String getComputerName(){
        return this.computer.getName();
    }

    //purpose: gets humanName String.
    //inputs: self.
    //outputs: returns humanName String.
    //assumptions: humanName String exists.
    public String getHumanName(){
        return  this.human.getName();
    }

    //purpose: replace totalScore.
    //inputs: self.
    //        newScore - int with new score.
    //outputs: none.
    //assumptions: totalScore int exists.
    public void replaceScore(int newScore){
        computer.replaceScore(newScore);
        human.replaceScore(newScore);
    }

    //purpose: initialize human player.
    //inputs: humanName - human's name.
    //outputs: none.
    //assumptions: none.
    public void initHumanPlayer(String humanName){
        human = new m_Player(humanName);
    }

    //purpose: check if specified player won.
    //inputs: player - m_Player in question.
    //outputs: returns boolean of whether the player won.
    //assumptions: winScore exists.
    private boolean win(m_Player player){
        return player.getTotalScore() >= winScore;
    }

    //purpose: check if any player won.
    //inputs: none.
    //outputs: returns boolean of whether the player won.
    //assumptions: winScore exists.
    public boolean isWin(){
        return isComputerWin() || isHumanWin();
    }

    //purpose: check if human player won.
    //inputs: none.
    //outputs: returns boolean of whether the human player won.
    //assumptions: human exists.
    public boolean isHumanWin(){ //just pass in which player? then its bad coupling
        return win(human);
    }

    //purpose: check if computer player won.
    //inputs: none.
    //outputs: returns boolean of whether the computer player won.
    //assumptions: computer exists.
    private boolean isComputerWin(){
        return win(computer);
    }

    //purpose: gets winner's name.
    //inputs: self.
    //outputs: returns winner's name String.
    //assumptions: players m_Collection exists.
    public String getWinnerName(){ //if create one win() that returns a player, huge coupling
        if (win(computer))
            return getComputerName();
        else if (win(human)){
            return getHumanName();
        }
        else return nullPlayerName;
    }

    //purpose: gets totalScore int.
    //inputs: self.
    //outputs: returns totalScore int.
    //assumptions: totalScore int exists.
    public int getTotalScore(m_Player player){
        return player.getTotalScore();
    }

    //purpose: increase totalScore by specified amount.
    //inputs: self.
    //        newScore - int by which to increase score.
    //outputs: none.
    //assumptions: totalScore int exists.
    public void updateScore(int newScore, m_Player player){
        player.updateScore(newScore);
    }

    //purpose: gets computer player.
    //inputs: self.
    //outputs: returns computer player.
    //assumptions: computer exists.
    public m_Player getComputerPlayer(){
        return computer;
    }

    //purpose: gets human player.
    //inputs: self.
    //outputs: returns human player.
    //assumptions: human exists.
    public m_Player getHumanPlayer(){
        return human;
    }
}
