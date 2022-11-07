package uk.ac.rgu.cm2115;

import uk.ac.rgu.cm2115.devices.Device;

/**
 * Class to act as a hub for the smarthome
 * @author Mark Snaith
 */
public class Home {

    private Device<?>[] devices;

    public Home(){
        this.devices = new Device[5];
    }

    public void addDevice(Device<?> device){
        for(int i=0;i<this.devices.length;i++){
            if(devices[i] == null){
                devices[i] = device;
                break;
            }
        }
    }

    public Device<?>[] getDevices(){
        return this.devices;
    }
}
