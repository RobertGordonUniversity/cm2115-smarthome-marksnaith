package uk.ac.rgu.cm2115;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import uk.ac.rgu.cm2115.devices.Device;
import uk.ac.rgu.cm2115.devices.DeviceFactory;

public class HomeData {

    public static Home load(String filename, DeviceFactory factory) throws IOException{

        Home home = new Home(factory);

        try(FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)){
            String input = br.readLine();

            while(input != null){
                String[] elements = input.split(",");

                switch(elements[0].toLowerCase().trim()){
                    case "light":
                        home.addLight(elements[1].trim());
                        break;
                    case "smartplug":
                        home.addSmartPlug(elements[1].trim());
                        break;
                    case "thermostat":
                        home.addThermostat(elements[1].trim());
                        break;
                }
            }

            br.close();
            fr.close();

        }catch(IOException ex){
            throw ex;
        }

        return home;

    }

    public static void save(Home home, String filename) throws IOException{
        try(FileWriter fw = new FileWriter(filename); BufferedWriter bw = new BufferedWriter(fw)){
            for(Device<?> d : home.getDevices()){
                    bw.write(d.getType() + "," + d.getName());
            }
        }catch(IOException ex){
            throw ex;
        }
    }
    
}
