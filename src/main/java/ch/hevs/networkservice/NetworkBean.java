package ch.hevs.networkservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.security.auth.spi.Users;

import ch.hevs.businessobject.Brand;
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
	
	public void addDevice(String name, String description, Brand brand, List<Network> networks, List<User> owners, OperatingSystem os) {
		Device new_device = new Device();
		new_device.setName(name);
		new_device.setDescription(description);
		new_device.setBrand(brand);
		new_device.setNetworks(networks);
		new_device.setOwners(owners);
		new_device.setOs(os);
		devices.add(new_device);
		em.persist(new_device);
	}
	public void modifyDevice(long id, String name, String description, Brand brand, List<Network> networks, List<User> owners, OperatingSystem os) {
		Device mod_device = em.getReference(Device.class, id);
		mod_device.setName(name);
		mod_device.setDescription(description);
		mod_device.setBrand(brand);
		mod_device.setNetworks(networks);
		mod_device.setOwners(owners);
		mod_device.setOs(os);
		em.persist(mod_device);
	}
	public void deleteDeviceById(long id) {
		Device to_delete = em.getReference(Device.class, id);
		devices.remove(to_delete);
		em.remove(to_delete);
	}

	public void addNetwork(String name, String description) {
		Network new_network = new Network();
		new_network.setName(name);
		new_network.setDescription(description);
		networks.add(new_network);
		em.persist(new_network);
	}
	public void modifyNetwork(long id, String name, String description) {
		Network mod_network = em.getReference(Network.class, id);
		mod_network.setName(name);
		mod_network.setDescription(description);
		em.persist(mod_network);
	}
	public void deleteNetwork(long id) {
		Network to_delete = em.getReference(Network.class, id);
		networks.remove(to_delete);
		em.remove(to_delete);
	}
	public void addUser(String firstname, String lastname, String email) {
		User new_user = new User();
		new_user.setFirstname(firstname);
		new_user.setLastname(lastname);
		new_user.setEmail(email);
		users.add(new_user);
		em.persist(new_user);
	}
	public void modifyUser(long id, String firstname, String lastname, String email) {
		User mod_user = em.getReference(User.class, id);
		mod_user.setFirstname(firstname);
		mod_user.setLastname(lastname);
		mod_user.setEmail(email);
		em.persist(mod_user);
	}
	
	public void deleteUser(long id) {
		User to_delete = em.getReference(User.class, id);
		users.remove(to_delete);
		em.remove(to_delete);
	}
}
