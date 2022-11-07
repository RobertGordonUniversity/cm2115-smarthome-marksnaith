package uk.ac.rgu.cm2115.devices;

public abstract class Device<T> {
    
    protected final String name;
    protected T status;

    public Device(String name){
        this.name = name;
    }

    public final T getStatus(){
        return this.status;
    }

    @Override
    public final String toString(){
        return this.name + " " + this.getClass().getSimpleName();
    }
}
