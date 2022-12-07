package CosmicWipeout;

public class c_RoundHandler {
    private c_CubeHandler cubeHandler;
    private View view;
    private Model model;
    public c_RoundHandler(Model model, View view){
        this.view = view;
        this.model = model; //is the coupling worth it for better cohesion?
        this.cubeHandler = new c_CubeHandler(view);
    }
    public void round(m_Player computer, m_Player human){
        turn(true, computer); //should a cond be checking for isComputer instead?
        turn(false, human); //might be a costlier and messier computation to do that
    }

    //purpose: Perform a turn for the human player.
    //inputs: self.
    //outputs: none.
    //assumptions: model and view exist.
    private void turn(boolean isComputer, m_Player player){
        String name = model.getName(player);
        view.display(v_DisplayType.TURN, name);
        int finalScore = turnScore(isComputer, player);
        resetTurn();
        model.updateScore(finalScore, player);
        view.display(v_DisplayType.NEW_SCORE, new String[]{name, Integer.toString(model.getTotalScore(player))});
    }

    private void resetTurn(){
        cubeHandler.resetRollInfo();
        model.resetCubes();
    }

    //purpose: get a valid roll.
    //inputs: flashMatchValue - value that cannot be rolled because of flash.
    //outputs: return a valid roll, display rollScore or that roll is invalid.
    //assumptions: view exists.
    private int validRoll(int flashMatchValue){
        int tempRollScore;
        boolean invalidRoll;
        do {
            tempRollScore = roll(flashMatchValue);
            invalidRoll = (tempRollScore == -1);
            if (invalidRoll)
                view.display(v_DisplayType.FLASH_MATCH);
        }while (invalidRoll);
        return tempRollScore;
    }

    //purpose: get score for the turn.
    //inputs: human - human player.
    //outputs: returns turn score, displays when score has to be cleared.
    //assumptions: view and model exist.
    private int turnScore(boolean isComputer, m_Player player){
        int turnScore = 0;
        int flashMatchValue = -1;
        int rollScore;
        do{
            rollScore = validRoll(flashMatchValue);
            if (rollScore == 0){
                view.display(v_DisplayType.SCORE_CLEARED);
                return 0;
            }
            view.display(v_DisplayType.ROLL_SCORE, String.valueOf(rollScore));
            turnScore += rollScore;
            flashMatchValue = (isComputer?-1:cubeHandler.getFlashSide());
            removeCubes();
        }while(reroll(((model.getTotalScore(player) + turnScore) >= 35), isComputer));
        return turnScore;
    }

    //purpose: perform a roll and return its result.
    //inputs: flashMatchValue - value that cannot be rolled because of flash.
    //outputs: return roll result.
    //assumptions: cubeHandler and model exist.
    private int roll(int flashMatchValue){
        return cubeHandler.calculateScore(model.getCubes(), flashMatchValue);
    }

    //purpose: check if need to reroll.
    //inputs: over35 - whether player has a total score over 35.
    //outputs: returns whether need to reroll.
    //assumptions: cubeHandler and model exist.
    private boolean reroll(boolean over35, boolean isComputer){
        boolean reroll = false;
        int flashSide = (isComputer?-1:cubeHandler.getFlashSide());
        if (reroll3ciiApplies(over35)){
            reroll = apply3ciiRules();
        }
        else if (clearFlashApplies(flashSide)){
            reroll = applyClearFlash(flashSide);
        }
        else if (reroll3ciApplies()){
            reroll = apply3ciRules();
        }
        else if (!isComputer) {
            reroll = view.rerollPrompt();
        }
        return reroll; //too many conditionals?
    }

    //purpose: check if clearing the flash applies.
    //inputs: flashSide - what formed the flash.
    //outputs: returns boolean of whether clearing the flash applies.
    //assumptions: none.
    private boolean clearFlashApplies(int flashSide){
        return (flashSide != -1);
    }

    //purpose: apply changes based on flash being scored.
    //inputs: flashSide - what formed the flash.
    //outputs: displays that flash must be cleared, returns that must reroll.
    //assumptions: view and model exist.
    private boolean applyClearFlash(int flashSide){
        view.display(v_DisplayType.CLEAR_FLASH, String.valueOf(flashSide));
        if (reroll3ciApplies()){
            model.resetCubes();
        }
        return true;
    }

    //purpose: check if 3ci applies.
    //inputs: none.
    //outputs: returns boolean of whether 3ci applies.
    //assumptions: none.
    private boolean reroll3ciApplies(){
        return model.getCubes().size() == 0;
    }

    //purpose: apply changes based on rule 3ci.
    //inputs: none.
    //outputs: returns that need to reroll, displays that rule 3ci was applied.
    //assumptions: view exists.
    private boolean apply3ciRules(){
        model.resetCubes();
        view.display(v_DisplayType.REROLL_3ci);
        return true;
    }

    //purpose: check if 3cii applies.
    //inputs: over35 - whether player has a total score over 35.
    //outputs: returns boolean of whether 3cii applies.
    //assumptions: none.
    private boolean reroll3ciiApplies(boolean over35){
        return !over35;
    }

    //purpose: apply changes based on rule 3cii.
    //inputs: none.
    //outputs: returns that need to reroll, displays that rule 3cii was applied.
    //assumptions: view exists.
    private boolean apply3ciiRules(){
        view.display(v_DisplayType.REROLL_3cii);
        return true;
    }

    //purpose: remove a number of cubes.
    //inputs: none.
    //outputs: none.
    //assumptions: model, cubeHandler, view exist.
    private void removeCubes(){
        int cubesScoredCount = cubeHandler.getCubesScoredCount();
        model.removeCubesScored(cubesScoredCount);
        view.display(v_DisplayType.CUBES_REMOVED, String.valueOf(cubesScoredCount));
    } //only display cubes removed when reroll prompt
} //better to use player's methods or model's methods?
