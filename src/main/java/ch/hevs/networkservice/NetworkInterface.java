package ch.hevs.networkservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.Account;

@Local
public interface NetworkInterface {
	
	public List<Device> getDevices();
}
