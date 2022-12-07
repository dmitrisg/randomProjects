package CosmicWipeout;
public class View {
    private v_Display display;
    private v_Obtain obtain;

    //purpose: construct and initialize View object.
    //inputs: self - the View object being created
    //outputs: none.
    //assumptions: none.
    public View() {
        this.display = new v_Display();
        this.obtain = new v_Obtain(display);
    }

    //purpose: prompt user about resetting.
    //inputs: none.
    //outputs: returns whether user wants to reset.
    //assumptions: none.
    public boolean resetPrompt() {
        return obtain.resetPrompt();
    }

    //purpose: prompt user about rerolling.
    //inputs: none.
    //outputs: returns char with rerolling preference.
    //assumptions: obtain exists.
    public boolean rerollPrompt(){
        return obtain.rerollPrompt();
    }

    //purpose: prompt user for name.
    //inputs: none.
    //outputs: returns String with entered name.
    //assumptions: obtain exists.
    public String userNamePrompt() {
        return obtain.userNamePrompt();
    }

    //purpose: prompt user about ending game.
    //inputs: none.
    //outputs: returns char with game end preference.
    //assumptions: obtain exists.
    public char endGamePrompt(){
        return obtain.endGamePrompt();
    }

    //purpose: display based on one parameter.
    //inputs: displayType - String representing which type of message to display
    //        displayInfo - String containing parameter.
    //outputs: displays message specified.
    //assumptions: display exists.
    public void display(v_DisplayType displayType, String displayInfo) { //repetitive
        switch (displayType) {
            case TURN:
                display.printTurn(displayInfo);
                break;
            case SIDES:
                display.printSides(displayInfo);
                break;
            case INVALID_NAME:
                display.printInvalidName(displayInfo);
                break;
            case ERROR:
                display.printError(displayInfo);
                break;
            case WIN:
                display.printWin(displayInfo);
                break;
            case CUBES_REMOVED:
                display.printCubesRemoved(displayInfo);
                break;
            case ROLL_SCORE:
                display.printRollScore(displayInfo);
                break;
            case CLEAR_FLASH:
                display.printClearFlash(displayInfo);
                break;
        }
    }

    //purpose: display based on no parameters.
    //inputs: displayType - String representing which type of message to display.
    //outputs: displays message specified.
    //assumptions: display exists.
    public void display(v_DisplayType displayType) {
        switch (displayType) {
            case REROLL_3cii:
                display.printReroll3cii();
                break;
            case REROLL_3ci:
                display.printReroll3ci();
                break;
            case INTRO:
                display.printIntro();
                break;
            case SCORE_CLEARED:
                display.printScoreCleared();
                break;
            case FLASH_MATCH:
                display.printFlashMatch();
                 break;
        }
    }

        //purpose: display based on multiple parameters.
        //inputs: displayType - String representing which type of message to display
        //        displayInfo - String array containing parameters.
        //outputs: displays message specified.
        //assumptions: display exists.
        public void display(v_DisplayType displayType, String[]displayInfo){ //might cause bad info hiding?
            switch (displayType) { //is it a bad idea to have a generic collection?
                case NEW_SCORE: //deal with parameter type issue?
                    display.printNewScore(displayInfo[0], displayInfo[1]);//casting a good idea?
                    break;
            }
        }
    //rest in peace v_Colors. the world wasn't ready
}