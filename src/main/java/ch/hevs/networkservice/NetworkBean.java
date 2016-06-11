package ch.hevs.networkservice;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

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

	public List<Network> getNetworks() {
		return em.createQuery("FROM Network").getResultList();
	}

	public List<Device> getDevices() {
		return em.createQuery("FROM Device").getResultList();
	}

	public List<OperatingSystem> getOperatingSystems() {
		return em.createQuery("FROM OperatingSystem").getResultList();
	}

	public List<User> getUsers() {
		return em.createQuery("FROM User").getResultList();
	}
}
