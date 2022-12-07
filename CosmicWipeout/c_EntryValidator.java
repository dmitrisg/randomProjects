package CosmicWipeout;

public class c_EntryValidator {
    private View view;//coupling increases

    //purpose: construct and initialize c_EntryValidator object.
    //inputs: self - the c_EntryValidator object being created
    //        view - View object
    //outputs: none.
    //assumptions: none.
    public c_EntryValidator(View view){
        this.view = view;
    }

    //purpose: validates user name.
    //inputs: computerName - name of computer player.
    //outputs: displays prompt, returns String with name.
    //assumptions: view and model exist.
    public String validUserName(String computerName){
        String newName;
        do{
            newName = view.userNamePrompt();
        } while (entryIsComputerName(newName, computerName));
        return newName;
    }

    //purpose: compares user name with computer's name.
    //inputs: name - String with user name.
    //        computerName - String with computer's name.
    //outputs: displays error message.
    //assumptions: view exists.
    private boolean entryIsComputerName(String name, String computerName){
        if (name.equalsIgnoreCase(computerName)){
            view.display(v_DisplayType.INVALID_NAME, computerName);
            return true;
        }
        return false;
    }
}
