package uk.ac.rgu.cm2115.devices.diagnostics;

import uk.ac.rgu.cm2115.devices.Light;
import uk.ac.rgu.cm2115.devices.SmartPlug;
import uk.ac.rgu.cm2115.devices.Thermostat;

public class SimpleDiagnosticVisitor implements DeviceVisitor{

    @Override
    public void visit(Light light) {
        System.out.println("Performing a simple diagnostic on " + light.toString());
        
    }

    @Override
    public void visit(SmartPlug plug) {
        System.out.println("Performing a simple diagnostic on " + plug.toString());
    }

    @Override
    public void visit(Thermostat thrm) {
        System.out.println("Performing a simple diagnostic on " + thrm.toString());
    }
    
}
