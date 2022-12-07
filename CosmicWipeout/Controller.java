package CosmicWipeout;

public class Controller { //too many instance variables
    private View view;
    private Model model;
    private c_RoundHandler roundHandler;
    private boolean askEnd = true;
    private boolean end = false;

    //purpose: construct and initialize Controller object.
    //inputs: self - the Controller object being created
    //outputs: none.
    //assumptions: none.
    public Controller() {
        this.view = new View();
        this.model = new Model();
        this.roundHandler = new c_RoundHandler(model,view);
    }

    //purpose: initialize model according to validation rules in c_EntryValidator.
    //inputs: none.
    //outputs: none.
    //assumptions: none.
    private void initModel(){
        this.model = new Model();
        model.initHumanPlayer(new c_EntryValidator(view).validUserName(model.getComputerName()));
    }

    //purpose: plays game until player wants to exit.
    //inputs: self.
    //outputs: prints exception message in case of error.
    //assumptions: view and exit exist.
    public void go(){
        try{
            preGame();
            do {
                playToWin();
                reset();
                model.saveGameData();
            }while (view.resetPrompt());
        } catch (Exception e) {
            view.display(v_DisplayType.ERROR,e.getMessage());
        }
    }

    //purpose: perform actions before game's beginning.
    //inputs: none.
    //outputs: displays intro message.
    //assumptions: view exists.
    private void preGame() throws Exception{
        view.display(v_DisplayType.INTRO);
        initModel();
    }

    //purpose: perform turns until a player wins.
    //inputs: self.
    //outputs: displays message when a player wins.
    //assumptions: model and view exist.
    private void playToWin(){
        do{
            roundHandler.round(model.getComputerPlayer(), model.getHumanPlayer());
            if (askEnd)
                end = end();
        }while (!end && !model.isWin());
        view.display(v_DisplayType.WIN, model.getWinnerName());
    }

    //purpose: determines how player wants to proceed in gameplay.
    //inputs: self
    //outputs: displays prompt, returns boolean of whether player wants to exit.
    //assumptions: view and askEnd exists.
    private boolean end(){
        char decision = view.endGamePrompt();
        if (decision == 'A')
            return true;
        if (decision == 'C'){
            askEnd = false;
        }
        return false;
    }

    //purpose: resets game.
    //inputs: self.
    //outputs: none.
    //assumptions: model exists.
    private void reset(){
        model.replaceScore(0);
    }
    //should the controller know what the players are or should the roundHandler be coupled to the model?
}