package uk.ac.rgu.cm2115.commands;

public class CommandNotExistException extends Exception {
    public CommandNotExistException(String msg){
        super(msg);
    }
}
