package uk.ac.rgu.cm2115;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import uk.ac.rgu.cm2115.commands.Command;
import uk.ac.rgu.cm2115.commands.CommandNotExistException;
import uk.ac.rgu.cm2115.devices.Device;
import uk.ac.rgu.cm2115.devices.DeviceFactory;
import uk.ac.rgu.cm2115.devices.Light;
import uk.ac.rgu.cm2115.devices.SmartPlug;
import uk.ac.rgu.cm2115.devices.Thermostat;

/**
 * Class to act as a hub for the smarthome
 * 
 * @author Mark Snaith
 */
public class Home {

    private List<Device<?>> devices;

    private Map<String, Command> commands;

    private int numCommands = 0;

    private DeviceFactory factory;

    private List<DeviceFactory> factories;

    public List<DeviceFactory> getFactories() {
        return factories;
    }

    public Home(DeviceFactory factory) {
        this.factory = factory;
        this.devices = new ArrayList<>();
        this.commands = new HashMap<>();
        this.factories = new ArrayList<>();
    }

    public void addFactory(DeviceFactory factory){
        this.factories.add(factory);
    }

    public void setFactory(DeviceFactory factory){
        this.factory = factory;
    }

    public Map<String, Command> getCommands() {
        return this.commands;
    }

    public void addCommand(String name, Command command) {
        this.commands.put(name.toLowerCase(), command);
    }

    public Command getCommand(String name) throws CommandNotExistException {
        Command command = this.commands.get(name.toLowerCase());
        
        if (command == null) {
            throw new CommandNotExistException("Command " + name + " does not exist");
        }

        return command;
    }

    public void runCommand(String name) throws CommandNotExistException {
        Command command = this.getCommand(name.toLowerCase());

        if (command != null) {
            command.execute();
        }
    }

    public Light addLight(String name) {
        Light light = this.factory.getLight(name);
        this.addDevice(light);

        this.addCommand("Switch on " + name, light::switchOn);
        this.addCommand("Switch off " + name, light::switchOff);

        return light;
    }

    public SmartPlug addSmartPlug(String name) {
        SmartPlug plug = this.factory.getSmartPlug(name);
        this.addDevice(plug);

        this.addCommand("Switch on " + name, plug::switchOn);
        this.addCommand("Switch off " + name, plug::switchOff);

        return plug;
    }

    public Thermostat addThermostat(String name) {
        Thermostat thrm = this.factory.getThermostat(name);
        this.addDevice(thrm);

        this.addCommand("Turn up " + name, thrm::turnUp);
        this.addCommand("Turn down " + name, thrm::turnDown);

        return thrm;
    }

    public void addDevice(Device<?> device) {
        this.devices.add(device);
    }

    public List<Device<?>> getDevices() {
        return this.devices;
    }
}
