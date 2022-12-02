package uk.ac.rgu.cm2115.commands;

public class RoutineCommand implements Command{

    Command[] commands;

    public RoutineCommand(Command[] commands){
        this.commands = commands;
    }

    @Override
    public void execute() {
        for(Command command : this.commands){
            command.execute();
        }
        
    }
    
}
