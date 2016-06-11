package ch.hevs.networkservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.Network;
import ch.hevs.businessobject.OperatingSystem;
import ch.hevs.businessobject.User;

@Local
public interface NetworkInterface {
	
	public List<Device> getDevices();
	
	
	public List<Network> getNetworks();


	public List<OperatingSystem> getOperatingSystems();

	public List<User> getUsers();
}
