package uk.ac.rgu.cm2115.devices.amazon;

import uk.ac.rgu.cm2115.devices.DeviceFactory;
import uk.ac.rgu.cm2115.devices.Light;
import uk.ac.rgu.cm2115.devices.SmartPlug;
import uk.ac.rgu.cm2115.devices.Thermostat;

public class AmazonDeviceFactory implements DeviceFactory{

    @Override
    public Light getLight(String name) {
        return new AmazonLight(name);
    }

    @Override
    public SmartPlug getSmartPlug(String name) {
        return new AmazonSmartPlug(name);
    }

    @Override
    public Thermostat getThermostat(String name) {
        return new AmazonThermostat(name);
    }

    public String toString(){
        return "Amazon";
    }
}
