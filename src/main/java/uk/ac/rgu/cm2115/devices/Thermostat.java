package uk.ac.rgu.cm2115.devices;

import uk.ac.rgu.cm2115.devices.diagnostics.DeviceVisitor;

public abstract class Thermostat extends Device<ThermostatStatus> implements Adjustable{

    public Thermostat(String name) {
        super(name);
        this.status = ThermostatStatus.OFF;
    }

    @Override
    public final void accept(DeviceVisitor visitor){
        visitor.visit(this);
    }

    public void turnUp(){
        System.out.println(this.name + " is turned up");
        this.status = ThermostatStatus.HIGH;
    }

    public void turnDown(){
        System.out.println(this.name + " is turned down");
        this.status = ThermostatStatus.MEDIUM;
    }

    public final String getType(){
        return "thermostat";
    }
    
}
