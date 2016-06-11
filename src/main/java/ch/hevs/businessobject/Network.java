package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="Network")
public class Network {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String description;

	/** ASSOCIATIONS **/
	
	// association avec Device
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "networks")
	private List<Device> devices;

	

	/** CONSTRUCTORS **/
	public Network() {
		devices = new ArrayList<Device>();
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

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}


	/** HELPER METHODS **/
	// HELPER METHOD
	public void addDevice(Device d) {
		devices.add(d);
	}

}
