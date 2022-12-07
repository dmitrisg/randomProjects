package CosmicWipeout;

public class c_SingleCubeScorer {
    private c_RollInfo rollInfo;

    //purpose: construct and initialize c_SingleCubeScorer object.
    //inputs: self - the c_SingleCubeScorer object being created
    //        rollInfo - c_RollInfo object
    //outputs: none.
    //assumptions: none.
    public c_SingleCubeScorer(c_RollInfo rollInfo){
        this.rollInfo = rollInfo;
    }

    //purpose: scores according to any rules related to scoring individual cubes.
    //inputs:  rolledValues - the m_Collection<Integer> of rolled values.
    //         scoreType - which rule to apply.
    //outputs: returns determined score based on those rules.
    //assumptions: none.
    public int calculateScore(m_Collection<Integer> rolledValues, String scoreType) {
        switch (scoreType){
            case "2a":
                return calculate2aScore(rolledValues);
            case "2d":
                return calculate2dScore(rolledValues);
            default:
                return 0;
        }
    }

    //purpose: scores according to rule 2d
    //inputs:  rolledValues - the m_Collection<Integer> of rolled values.
    //outputs: returns determined score based on 2d.
    //assumptions: none.
    private int calculate2dScore(m_Collection<Integer> rolledValues){
        for (Integer rolledValue: rolledValues){
            if (rolledValue == 0) {
                rollInfo.incCubesScoredCount(1);
                return 10;//don't like iterating twice //but better sep of concerns tho
                //other option is 2a scorer tracking the flaming sun
            }//rules say flamingSun can be scored as 5 or 10 but i dont think a user will ever choose 5
        }
        return 0;
    }

    //purpose: scores according to rule 2a.
    //inputs:  rolledValues - the m_Collection<Integer> of rolled values.
    //outputs: returns determined score based on 2a.
    //assumptions: none.
    private int calculate2aScore(m_Collection<Integer> rolledValues){
        int twoAScore = 0;
        int fivesToSkip = rollInfo.getValueScoredCount(5);
        int tensToSkip = rollInfo.getValueScoredCount(10);
        for (Integer rolledValue: rolledValues){
            if (rolledValue ==  5){
                if (fivesToSkip <= 0){
                    twoAScore += rolledValue;
                    rollInfo.incCubesScoredCount(1);
                }
                else fivesToSkip--;
            }
            else if (rolledValue ==  10){
                if (tensToSkip <= 0){
                    twoAScore += rolledValue;
                    rollInfo.incCubesScoredCount(1);
                }
                else tensToSkip--;
            }
        }
        return twoAScore; //need to generalize the above //loop messing everythign up
    }//bro u gotta like refactor some core part of this process

}
