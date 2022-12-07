package CosmicWipeout;

public class c_ComboScorer {
    private c_RollInfo rollInfo;

    //purpose: construct and initialize c_ComboScorer object.
    //inputs: self - the c_ComboScorer object being created
    //        rollInfo - c_RollInfo object
    //outputs: none.
    //assumptions: none.
    public c_ComboScorer(c_RollInfo rollInfo){
        this.rollInfo = rollInfo;
    }

    //purpose: gets total score related to any side being rolled multiple times.
    //inputs: rolledValues - the m_Collection<Integer> of rolled values.
    //outputs: returns calculated score.
    //assumptions: none.
    public int calculateScore(m_Collection<Integer> rolledValues){
        int tempScore = 0;
        m_Collection<Integer> uniqueSideValues = rolledValues.getUnique();
        for (Integer uniqueSideValue : uniqueSideValues) {
            tempScore += comboAlgo(uniqueSideValue, rolledValues);
            if (tempScore > 0)
                break;
        }
        return tempScore;
    }

    //purpose: checks if a certain side has been rolled multiple sides.
    //inputs: comboCandidate - potential combo side, rolledValues - the m_Collection<Integer> of rolled values.
    //outputs: returns score.
    //assumptions: none.
    private int comboAlgo(int comboCandidate, m_Collection<Integer> rolledValues){
        boolean flamingSunRolled = false;
        int comboCounter = 0;
        for (Integer rolledValue : rolledValues) {
            flamingSunRolled = flamingSunRolled || rolledValue ==  0;
            if (rolledValue ==  comboCandidate)
                comboCounter++;
        }
        return comboIndividualScore(comboCandidate, comboCounter, flamingSunRolled);
    }

    //purpose: calculate score based on how many times a certain side was rolled.
    //inputs: comboCandidate - side that may have been rolled multiple times
    //        comboCounter - number of times the candidate was rolled
    //        flamingSunRolled - whether a flaming sun was rolled.
    //outputs: returns score.
    //assumptions: none.
    private int comboIndividualScore(int comboCandidate,int comboCounter, boolean flamingSunRolled) {
        if (freightTrainApplies(comboCounter)){
            return applyFreightTrain(comboCandidate);
        }
        boolean flamingSunFlash = (flamingSunRolled && comboCounter == 2);
        if (flashApplies(flamingSunFlash, comboCounter)){
            return applyFlash(comboCandidate, flamingSunFlash);
        }
    else return 0;
    }

    //purpose: check if freight train applies.
    //inputs: comboCounter - how many times a side was rolled.
    //outputs: returns boolean of whether freight train applies.
    //assumptions: none.
    private boolean freightTrainApplies(int comboCounter){
        return comboCounter == 5;
    }

    //purpose: change state based on freight train being rolled.
    //inputs: comboCandidate - number that caused a freight train.
    //outputs: returns score based on a freight train being rolled.
    //assumptions: none.
    private int applyFreightTrain(int comboCandidate){
        setRollInfo(5, comboCandidate,5);
        return freightTrainScore(comboCandidate);
    }

    //purpose: check if flash applies.
    //inputs: flamingSunFlash - whether the flash could be caused by a flaming sun
    //        comboCounter - number of times a side has been rolled.
    //outputs: returns boolean of whether flash applies.
    //assumptions: none.
    private boolean flashApplies(boolean flamingSunFlash, int comboCounter){
        return (flamingSunFlash || (2 < comboCounter) && (comboCounter < 5));
    }

    //purpose: change state based on flash being rolled.
    //inputs: comboCandidate - number that caused a flash,
    //        flamingSunFlash - whether the flash could be caused by a flaming sun
    //outputs: returns score based on a freight train being rolled.
    //assumptions: rollInfo exists.
    private int applyFlash(int comboCandidate, boolean flamingSunFlash){
        if (flamingSunFlash) {
            setRollInfo(2, comboCandidate, 3);
        } else {
            setRollInfo(3, comboCandidate, 3);
        }
        rollInfo.setFlashSide(comboCandidate);
        return flashScore(comboCandidate);
    }

    //purpose: updates rollInfo based on what was scored.
    //inputs: valueIncAmount - how many times the comboCandidate was scored,
    //        comboCandidate - which value formed a combo,
    //        cubesScoredIncAmount - how many cubes were scored.
    //outputs: none.
    //assumptions: rollInfo exists.
    private void setRollInfo(int valueIncAmount, int comboCandidate, int cubesScoredIncAmount){
        rollInfo.incValueScoredCount(valueIncAmount, comboCandidate);
        rollInfo.incCubesScoredCount(cubesScoredIncAmount);
    }

    //purpose: calculate score for flash.
    //inputs: flashSide - the side which caused the flash.
    //outputs: returns calculated score.
    //assumptions: none.
    private int flashScore(int flashSide){
        return flashSide*10;
    }

    //purpose: calculate score for freight train.
    //inputs: freightTrainSide - the side which caused the freight train.
    //outputs: returns calculated score.
    //assumptions: none.
    private int freightTrainScore(int freightTrainSide) {
        return freightTrainSide*100;
    }
}
