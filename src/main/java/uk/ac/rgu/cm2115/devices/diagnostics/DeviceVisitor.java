package uk.ac.rgu.cm2115.devices.diagnostics;

import uk.ac.rgu.cm2115.devices.Light;
import uk.ac.rgu.cm2115.devices.SmartPlug;
import uk.ac.rgu.cm2115.devices.Thermostat;

public interface DeviceVisitor {
    public void visit(Light light);
    public void visit(SmartPlug plug);
    public void visit(Thermostat thrm);
}
