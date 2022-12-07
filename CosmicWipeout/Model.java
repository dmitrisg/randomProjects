package CosmicWipeout;
import java.util.LinkedList;

public class Model { //too many instance variables?
    private m_Players players;
    private m_Cubes cubes;
	 private m_SqlConnector sql;
	 private LinkedList<m_ScoreRecord> records;

    //purpose: construct and initialize Model object.
    //inputs: self - the Model object being created
    //outputs: none.
    //assumptions: none.
    public Model() {
        players = new m_Players();
        cubes = new m_Cubes();
        sql = new m_SqlConnector();
        records = new LinkedList<m_ScoreRecord>();
    }

    //purpose: initialize model according to validation rules in c_EntryValidator.
    //inputs: none.
    //outputs: none.
    //assumptions: none.
    public void initHumanPlayer(String humanName) {
        players.initHumanPlayer(humanName);
    }

    //purpose: gets computerName String.
    //inputs: self.
    //outputs: returns computerName String.
    //assumptions: computerName String exists.
    public String getComputerName(){
        return players.getComputerName();
    }

    public String getHumanName(){
        return players.getHumanName();
    }

    //purpose: check if human player won.
    //inputs: none.
    //outputs: returns boolean of whether the human player won.
    //assumptions: human exists.
    public boolean isHumanWin(){ //just pass in which player? then its bad coupling
        return players.isHumanWin();
    }

    //purpose: gets winner's name.
    //inputs: self.
    //outputs: returns winner's name String.
    //assumptions: players m_Collection exists.
    public String getWinnerName(){ //if create one win() that returns a player, huge coupling
        return players.getWinnerName();
    }

    //purpose: check if any player won.
    //inputs: none.
    //outputs: returns boolean of whether the player won.
    //assumptions: winScore exists.
    public boolean isWin(){
        return players.isWin();
    }

    //purpose: replace totalScore.
    //inputs: self.
    //        newScore - int with new score.
    //outputs: none.
    //assumptions: totalScore int exists.
    public void replaceScore(int newScore){
        players.replaceScore(newScore);
    }

    //purpose: reset cubes.
    //inputs: none.
    //outputs: none.
    //assumptions: defaultCubes exists.
    public void resetCubes() {
        cubes.resetCubes();
    }

    //purpose: gets name String.
    //inputs: player - the player whose name you are getting.
    //outputs: returns name String.
    //assumptions: name String exists.
    public String getName(m_Player player){
        return players.getName(player);
    }

    //purpose: remove a number of cubes.
    //inputs: removeQuantity - how many cubes to remove.
    //outputs: none.
    //assumptions: cubes exists.
    public void removeCubesScored(int removeQuantity){
        cubes.removeCubesScored(removeQuantity);
    }

    //purpose: increase totalScore by specified amount.
    //inputs: self.
    //        newScore - int by which to increase score.
    //outputs: none.
    //assumptions: totalScore int exists.
    public void updateScore(int newScore, m_Player player){
        players.updateScore(newScore, player);
        addRecord(players.isComputer(player), newScore, getTotalScore(player)); //messy get
    }

    private void addRecord(boolean isComputer, int turnScore, int totalScore){
        records.add(new m_ScoreRecord(isComputer?'c':'h', turnScore, totalScore));
    }

    //purpose: gets cubes m_Collection.
    //inputs: self.
    //outputs: returns cubes m_Collection
    //assumptions: cubes m_Collection exists.
    public m_Collection<m_Cube> getCubes(){
        return cubes.getCubes();
    }

    //purpose: gets totalScore int.
    //inputs: self.
    //outputs: returns totalScore int.
    //assumptions: totalScore int exists.
    public int getTotalScore(m_Player player){
        return players.getTotalScore(player);
    }

    //purpose: gets computer player.
    //inputs: self.
    //outputs: returns computer player.
    //assumptions: computer exists.
    public m_Player getComputerPlayer(){
        return players.getComputerPlayer();
    }

    //purpose: gets human player.
    //inputs: self.
    //outputs: returns human player.
    //assumptions: human exists.
    public m_Player getHumanPlayer(){
        return players.getHumanPlayer();
    }

    public void saveGameData() throws Exception{
        //save records as well
        sql.saveToDB(getHumanName(), getComputerName(), records); //messy
        resetGameData();
    }

    private void resetGameData(){
        records.clear();
    }
}
