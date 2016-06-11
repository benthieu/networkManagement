package ch.hevs.networkservice;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.security.auth.spi.Users;

import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.Network;
import ch.hevs.businessobject.OperatingSystem;
import ch.hevs.businessobject.User;

@Stateful
public class NetworkBean implements NetworkInterface {
	@Resource
	private SessionContext ctx;
	@PersistenceContext(name = "BankPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	private List<Network> networks;
	private List<User> users;
	private List<OperatingSystem> operatingSystems;
	private List<Device> devices;

	public List<Network> getNetworks() {
		networks = em.createQuery("FROM Network").getResultList();
		return networks;
	}

	public List<Device> getDevices() {
		devices = em.createQuery("FROM Device").getResultList();
		return devices;
	}

	public List<OperatingSystem> getOperatingSystems() {
		operatingSystems = em.createQuery("FROM OperatingSystem").getResultList();
		return operatingSystems;
	}

	public List<User> getUsers() {
		users = em.createQuery("FROM User").getResultList();
		return users;
	}
	
	public Network getNetworkById(long id) {
		return em.getReference(Network.class, id);
	}
	
	public User getUserById(long id) {
		return em.getReference(User.class, id);
	}
	
	public OperatingSystem getOperatingSystemById(long id) {
		return em.getReference(OperatingSystem.class, id);
	}
	
	public Device getDeviceById(long id) {
		return em.getReference(Device.class, id);
	}
	
	public void addDevice(Device new_device) {
		devices.add(new_device);
		em.persist(new_device);
	}
}
