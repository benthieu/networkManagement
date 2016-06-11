package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Device")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String description;

	/** ASSOCIATIONS**/

	// association avec User
	@ManyToMany
	private List<User> owners;

	// association avec Operating System
	@ManyToOne
	private OperatingSystem os;

	// association avec Network
	@ManyToMany
	private List<Network> networks;

	// integration de Brand
	@Embedded
	private Brand brand;

	

	/** CONSRUCTORS **/
	
	public Device()
	{
		networks = new ArrayList<Network>();
		owners = new ArrayList<User>();
	}

	/** GETTERS AND SETTERS **/	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getOwners() {
		return owners;
	}

	public void setOwners(List<User> owners) {
		this.owners = owners;
		for (User u : owners) {
			u.addDevice(this);
		}
	}

	public OperatingSystem getOs() {
		return os;
	}

	public void setOs(OperatingSystem os) {
		this.os = os;
	}

	public List<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(List<Network> networks) {
		this.networks = networks;
		for (Network n : networks) {
			n.addDevice(this);
		}
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	

	/** HELPER METHODS **/
	
	public void addNetwork(Network n) {
		networks.add(n);
		n.addDevice(this);
	}
	
	public void addOwner(User u) {
		owners.add(u);
		u.addDevice(this);
	}

}
