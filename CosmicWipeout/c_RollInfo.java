package CosmicWipeout;

public class c_RollInfo { //maybe even contain rolledValues
    //only accessed when info is shared between classes
    private int fiveScoredCount = 0;
    private int tenScoredCount = 0;
    private int cubesScoredCount = 0;
    private int flashSide = -1;
    private boolean flashMatch = false;

    //purpose: increment cubesScoredCount by specified amount.
    //inputs: incAmount - amount by which to increment.
    //outputs: none.
    //assumptions: none.
    public void incCubesScoredCount(int incAmount){
        cubesScoredCount+= incAmount;
    }

    //purpose: set value which was part of the flash.
    //inputs: flashSide - value which formed the flash.
    //outputs: none.
    //assumptions: none.
    public void setFlashSide(int flashSide){
        this.flashSide = flashSide;
    }

    //purpose: get side that formed the flash.
    //inputs: none.
    //outputs: return side that formed flash.
    //assumptions: none.
    public int getFlashSide(){
        return flashSide;
    }

    //purpose: get number of cubes scored in the roll.
    //inputs: none.
    //outputs: returns number of cubes scored.
    //assumptions: none.
    public int getCubesScoredCount(){
        return cubesScoredCount;
    }

    //purpose: get number of times a value was scored in the roll.
    //inputs: value - value whose count is being retrieved.
    //outputs: returns number of times a value was scored.
    //assumptions: none.
    public int getValueScoredCount(int value){
        switch (value){
            case 5:
                return fiveScoredCount;
            case 10:
                return tenScoredCount;
            default:
                return 0;
        }
    }

    //purpose: increment fiveScoredCount by specified amount.
    //inputs: incAmount - amount by which to increment.
    //outputs: none.
    //assumptions: none.
    private void incFiveScored(int incAmount){
        fiveScoredCount += incAmount;
    }

    //purpose: increment tenScoredCount by specified amount.
    //inputs: incAmount - amount by which to increment.
    //outputs: none.
    //assumptions: none.
    private void incTenScored(int incAmount){
        tenScoredCount += incAmount;
    }

    //purpose: increment valueScoredCount by specified amount.
    //inputs: incAmount - amount by which to increment.
    //outputs: none.
    //assumptions: none.
    public void incValueScoredCount(int incAmount, int value){
        switch (value){
            case 5:
                incFiveScored(incAmount);
                break;
            case 10:
                incTenScored(incAmount);
        }
    }

    //purpose: reset all variables.
    //inputs: none.
    //outputs: none.
    //assumptions: none.
    public void reset(){
        fiveScoredCount = 0;
        tenScoredCount = 0;
        cubesScoredCount = 0;
        flashSide = -1;
        flashMatch = false;
    }

    //purpose: note that a value matching the flash was rolled.
    //inputs: none.
    //outputs: none.
    //assumptions: none.
    public void setFlashMatch(){
        flashMatch = true;
    }

    //i hate making these get set things bruh
}
