package uk.ac.rgu.cm2115.devices;

import uk.ac.rgu.cm2115.devices.diagnostics.DeviceVisitor;

public abstract class Device<T> {
    
    protected final String name;
    protected T status;

    public Device(String name){
        this.name = name;
    }

    public final T getStatus(){
        return this.status;
    }

    public final String getName(){
        return this.name;
    }

    @Override
    public final String toString(){
        return this.name + " " + this.getClass().getSimpleName();
    }

    public abstract String getType();

    public abstract void accept(DeviceVisitor visitor);
}
