package CosmicWipeout;

public class v_Display {
    String crlf = "\r\n";

    //purpose: display a string.
    //inputs: text - a String
    //        color - String with code for color to display in.
    //outputs: prints the string.
    //assumptions: none.
    public void printText(String text) {
        System.out.println(text);
    }

    //purpose: display the winner.
    //inputs: winner - String with the winner's name.
    //outputs: prints who won.
    //assumptions: none.
    public void printWin(String winner) {
        printText("--" + winner + " won this game.");
    }
    //purpose: display sides that were rolled.
    //inputs: sideNames - string containing side names.
    //outputs: prints rolled sides.
    //assumptions: none.
    public void printSides(String sideNames){
        printText(sideNames);
    }

    //purpose: display whose turn it is.
    //inputs: playerName - player whose turn it is.
    //outputs: prints whose turn it is.
    //assumptions: none.
    public void printTurn(String playerName){
        printText(crlf + "--" + playerName + "'s turn:");
    }

    //purpose: display updated score.
    //inputs: playerName - player whose score it is.
    //        newScore - updated score.
    //outputs: prints updated score.
    //assumptions: none.
    public void printNewScore(String playerName, String newScore){
        printText(playerName + "'s updated score: " + newScore);
    }

    //purpose: display roll score.
    //inputs: rollScore - updated score.
    //outputs: prints updated score.
    //assumptions: none.
    public void printRollScore(String rollScore){
        printText("Points scored in this roll: " + rollScore);
    }

    //purpose: notify that name entered is invalid.
    //inputs: computerName - computer's name.
    //outputs: prints that name is invalid.
    //assumptions: none.
    public void printInvalidName(String computerName){
        printText(computerName + " is the computer's name, pick something different.");
    }

    //purpose: display error message.
    //inputs: errorMessage - String with error message.
    //outputs: prints error message.
    //assumptions: none.
    public void printError(String errorMessage){
        printText(errorMessage);
    }

    //purpose: notify that rule 3ci applies.
    //inputs: none.
    //outputs: prints that rule 3ci applies.
    //assumptions: none.
    public void printReroll3ci(){
        printText(">>All 5 cubes scored - must reroll" + crlf);
    }

    //purpose: notify that rule 3cii applies.
    //inputs: none.
    //outputs: prints that rule 3cii applies.
    //assumptions: none.
    public void printReroll3cii(){
        printText(">>Total score under 35 points overall - must reroll" + crlf);
    }

    //purpose: notify that the score was cleared.
    //inputs: none.
    //outputs: prints that the score was cleared.
    //assumptions: none.
    public void printScoreCleared(){
        printText(">>Roll did not produce scoring cubes - all points for the turn lost." + crlf);
    }

    //purpose: notify that flash must be cleared.
    //inputs: flashSide - value that caused a flash.
    //outputs: prints that flash must be cleared.
    //assumptions: none.
    public void printClearFlash(String flashSide){
        printText(">>Flash scored with " + flashSide + " - roll until no cubes match that number." + crlf);
    }

    //purpose: notify that some cube matched the flash.
    //inputs: none.
    //outputs: prints that some cube matched the flash.
    //assumptions: none.
    public void printFlashMatch(){
        printText(">>Cubes cannot match flash, rerolling." + crlf);
    }

    //purpose: notify that some cubes were removed.
    //inputs: none.
    //outputs: prints that some cubes were removed.
    //assumptions: none.
    public void printCubesRemoved(String cubesRemoved){
        int cubesRemovedInt = Integer.parseInt(cubesRemoved);
        if (cubesRemovedInt > 0) {
            printText("Set aside " + cubesRemoved + " cube(s).");
        }
    }

    //purpose: display intro to game.
    //inputs: none.
    //outputs: prints intro to game.
    //assumptions: none.
    public void printIntro(){
        printText("Cosmic Wipeout: A Pol Germakovski Production\r\n");
        //printText("IF UNFAMILIAR WITH THE RULES, CLICK HERE FOR A QUICK EXPLANATION: https://youtu.be/dQw4w9WgXcQ?t=43\r\n", defaultColor);
    }
}
