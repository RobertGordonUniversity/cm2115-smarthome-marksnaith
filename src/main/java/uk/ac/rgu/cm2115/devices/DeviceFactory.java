package uk.ac.rgu.cm2115.devices;

public interface DeviceFactory {
    public Light getLight(String name);
    public SmartPlug getSmartPlug(String name);
    public Thermostat getThermostat(String name);
}
