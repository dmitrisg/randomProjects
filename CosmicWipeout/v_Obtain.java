package CosmicWipeout;

import java.util.Scanner;
public class v_Obtain {
    private v_Display display;
    private Scanner in;
    private final String promptIntro = "\r\nWould you like to";

    //purpose: construct and initialize v_Obtain object.
    //inputs: self - the v_Obtain object being created
    //        display - v_Display object.
    //outputs: none.
    //assumptions: none.
    public v_Obtain(v_Display display){
        this.display = display;
        this.in = new Scanner(System.in);
    }

    //purpose: prompt with multiple options.
    //inputs: options - String array of options.
    //outputs: displays prompt
    //         returns char representing user's choice.
    //assumptions: display exists.
    private char optionPrompt(String[] options){ //options over 26 will be ignored
        char c = 'A';
        String prompt = promptIntro +":\r\n";
        for (String option: options){
            if (c <= 'Z'){
                prompt += "(" + c + ")" + option + "\r\n";
                ++c;
            }
            else break;
        }
        display.printText(prompt);
        return getOptionResponse(c);
    }//return enum type instead of chars?

    //purpose: prompt user about rerolling.
    //inputs: none.
    //outputs: returns char with rerolling preference.
    //assumptions: obtain exists.
    public boolean rerollPrompt() {
        return optionPrompt("try for more points with the unscored cubes");
    }

    //purpose: prompt user about resetting.
    //inputs: none.
    //outputs: returns whether user wants to reset.
    //assumptions: none.
    public boolean resetPrompt(){
        return optionPrompt("start a new game");
    }

    //purpose: prompt with yes/no option.
    //inputs: option - String with option.
    //outputs: returns boolean with user's choice
    //         display prompt.
    //assumptions: display exists.
    private boolean optionPrompt(String option){
        String yes = "Y";
        display.printText(promptIntro + " " + option + "?\r\n(" + yes +") Yes\r\n(Other) No");
        String response = getEntry();
        if (response.isBlank()){
            return false;
        }
        else return (response.trim().equalsIgnoreCase(yes));
    }

    private String getEntry(){
        return in.nextLine();
    }

    //purpose: prompt user about ending game.
    //inputs: none.
    //outputs: returns char with game end preference.
    //assumptions: obtain exists.
    public char endGamePrompt(){
        return optionPrompt(new String[]{"End the game", "Keep playing", "Keep playing and don't ask again"});
    }

    //purpose: prompt user for name.
    //inputs: none.
    //outputs: returns String with entered name.
    //assumptions: display exists.
    public String userNamePrompt(){ //bad security, no way to protect against code injection
        String newName;
        do{
            display.printText("Enter your name:");
            newName = in.nextLine();
        }while (entryBlank(newName));
        return newName.trim();
    }

    //purpose: get a response which is one of the options available.
    //inputs: lastOption - char which defines possible responses.
    //outputs: returns char with user response.
    //assumptions: display exists.
    private char getOptionResponse(char lastOption){
        char response;
        boolean validResponse; //put it in controller? lol
        do{
            response = getValidChar(); //might have unintended consequences
            validResponse = ('A' <= response && response <= lastOption-1);
            if (!validResponse)
                printNotOption();
        } while (!validResponse);
        return response;
    }

    private void printNotOption(){
        display.printText("Response must be one of the options above.");
    }

    //purpose: get response that is not blank.
    //inputs: none.
    //outputs: returns char with response.
    //assumptions: in exists.
    private char getValidChar(){
        String response;
        do {
            response = in.nextLine();
        }while (!entryIsChar(response));
        return Character.toUpperCase(response.charAt(0));
    }

    private boolean entryIsChar(String entry){
        if (entry.length() == 1)
           return true;
        else{
            printNotOption();
            return false;
        }
    }

    //purpose: checks if user entered a blank value.
    //inputs: entry - String with user entry.
    //outputs: returns boolean of whether entry is blank.
    //assumptions: display exists.
    private boolean entryBlank(String entry){ //only used by username?
        if (entry.isBlank()) {
            display.printText("Entry cannot be blank.");
            return true;
        }
        else return false;
    }
}
