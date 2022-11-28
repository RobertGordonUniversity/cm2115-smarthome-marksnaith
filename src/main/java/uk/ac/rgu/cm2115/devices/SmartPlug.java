package uk.ac.rgu.cm2115.devices;

import uk.ac.rgu.cm2115.devices.diagnostics.DeviceVisitor;

public abstract class SmartPlug extends Device<SmartPlugStatus> implements Switchable{

    public SmartPlug(String name) {
        super(name);
        this.status = SmartPlugStatus.OFF;
    }

    @Override
    public final void accept(DeviceVisitor visitor){
        visitor.visit(this);
    }

    public void switchOn(){
        System.out.println(this.name + " is switched on");
        this.status = SmartPlugStatus.ON;
    }

    public void switchOff(){
        System.out.println(this.name + " is switched off");
        this.status = SmartPlugStatus.OFF;
    }

    public final String getType(){
        return "smartplug";
    }
    
}
