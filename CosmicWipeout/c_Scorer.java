package CosmicWipeout;

public class c_Scorer {
    private c_ComboScorer comboScorer;
    private c_SingleCubeScorer singleCubeScorer;
    private c_RollInfo rollInfo;

    //purpose: construct and initialize c_Scorer object.
    //inputs: self - the c_Scorer object being created
    //        rollInfo - c_RollInfo object
    //outputs: none.
    //assumptions: none.
    public c_Scorer(c_RollInfo rollInfo) {
        this.rollInfo = rollInfo;
        this.comboScorer = new c_ComboScorer(rollInfo);
        this.singleCubeScorer = new c_SingleCubeScorer(rollInfo);
    }

    //purpose: gets valid score or that roll is invalid.
    //inputs: rolledValues - m_Collection<Integer> containing values that were rolled,
    //        flashMatchValue - value that cannot be rolled because of flash.
    //outputs: returns calculated score or that roll is invalid.
    //assumptions: none.
    public int calculateScore(m_Collection<Integer> rolledValues, int flashMatchValue) {
        int tempScore = 0;
        if (!rolledValues.isEmpty()) {
            if (invalidRoll(rolledValues, flashMatchValue))
                return -1;
            else tempScore+=regularScoring(rolledValues);
        }
        return tempScore;
    }

    //purpose: calculate total score for current roll.
    //inputs: rolledValues - collection of rolledValues.
    //outputs: returns calculated score.
    //assumptions: comboScorer and singleCubeScorer.
    private int regularScoring(m_Collection<Integer> rolledValues){
        int tempScore = 0;
        tempScore += comboScorer.calculateScore(rolledValues);
        tempScore += singleCubeScorer.calculateScore(rolledValues, "2a");
        if (tempScore == 0){
            tempScore += singleCubeScorer.calculateScore(rolledValues, "2d");
        }
        return tempScore;
    }

    //purpose: checks if roll is valid.
    //inputs: rolledValues - values that were rolled,
    //        flashMatchValue - value that cannot be rolled because of flash.
    //outputs: returns whether roll is valid.
    //assumptions: rollInfo exists.
    private boolean invalidRoll(m_Collection<Integer> rolledValues, int flashMatchValue){
        if (flashMatchValue != -1) {
            boolean flashMatch = false;
            for (Integer rolledValue : rolledValues) {
                flashMatch = flashMatch || rolledValue == flashMatchValue;
            }
            if (flashMatch) {
                rollInfo.setFlashMatch();
                return true;
            }
        }
        return false;
    }

}