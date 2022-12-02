package uk.ac.rgu.cm2115.devices.Apple;

import uk.ac.rgu.cm2115.devices.DeviceFactory;
import uk.ac.rgu.cm2115.devices.Light;
import uk.ac.rgu.cm2115.devices.SmartPlug;
import uk.ac.rgu.cm2115.devices.Thermostat;

public class AppleDeviceFactory implements DeviceFactory {

    @Override
    public Light getLight(String name) {
        return new AppleLight(name);
    }

    @Override
    public SmartPlug getSmartPlug(String name) {
        return new AppleSmartPlug(name);
    }

    @Override
    public Thermostat getThermostat(String name) {
        return new AppleThermostat(name);
    }
    
    @Override
    public String toString(){
        return "Apple";
    }
}
