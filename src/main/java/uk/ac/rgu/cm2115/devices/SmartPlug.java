package uk.ac.rgu.cm2115.devices;

public class SmartPlug extends Device<SmartPlugStatus> implements Switchable{

    public SmartPlug(String name) {
        super(name);
        this.status = SmartPlugStatus.OFF;
    }

    public void switchOn(){
        System.out.println(this.name + " is switched on");
        this.status = SmartPlugStatus.ON;
    }

    public void switchOff(){
        System.out.println(this.name + " is switched off");
        this.status = SmartPlugStatus.OFF;
    }
    
}
