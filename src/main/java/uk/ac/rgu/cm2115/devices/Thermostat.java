package uk.ac.rgu.cm2115.devices;

public class Thermostat extends Device<ThermostatStatus> implements Adjustable{

    public Thermostat(String name) {
        super(name);
        this.status = ThermostatStatus.OFF;
    }

    public void turnUp(){
        System.out.println(this.name + " is turned up");
        this.status = ThermostatStatus.HIGH;
    }

    public void turnDown(){
        System.out.println(this.name + " is turned down");
        this.status = ThermostatStatus.MEDIUM;
    }
    
}
