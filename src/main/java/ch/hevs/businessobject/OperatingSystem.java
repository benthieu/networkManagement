package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="OperatingSystem")
public class OperatingSystem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	
	/** ASSOCIATIONS **/
	
	// association avec Device
	@OneToMany(mappedBy = "os", cascade = CascadeType.ALL)
	private List<Device> devices;
	
	// A : Integration de Brand
	@Embedded
	private Brand brand;
	
	/** CONSTRUCTORS **/
	public OperatingSystem() {
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

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	/** HELPER METHODS **/

}
