package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Brand;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.Network;
import ch.hevs.businessobject.OperatingSystem;
import ch.hevs.businessobject.User;
import ch.hevs.networkservice.NetworkInterface;

/**
 * TransferBean.java
 * 
 */
public class DeviceBean
{
    private List<Device> deviceList;
    private List<User> userList;
    private List<OperatingSystem> osList;
    private List<Network> networkList;
    private NetworkInterface networking;
    private String deviceName;
    private String deviceDescription;
    private String selectedBrand;
    private long[] selectedNetworks;
    private long[] selectedUsers;
    private String operation;
    private int operation_state;
    private long mod_device_id;

	private long selectedOS;
    private List<Brand> brandNames;

	@PostConstruct
    public void initialize() throws NamingException {
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
    	networking = (NetworkInterface) ctx.lookup("java:global/network-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.NetworkInterface");  
		
    	// set operation
    	this.setOperation("Add device");
    	operation_state = 0;
		this.mod_device_id = -1;

    	// get devices
    	deviceList = networking.getDevices();
    	// get networks
    	networkList = networking.getNetworks();
    	// get users
    	userList = networking.getUsers();
    	// get os
    	osList = networking.getOperatingSystems();
    	
    	
    	brandNames = new ArrayList<Brand>();
    	Brand lenovo = new Brand();
		lenovo.setBrand_description("electronics manufacturing company");
		lenovo.setBrand_name("Lenovo");
		lenovo.setBrand_headquarters("Chicago"); 
		brandNames.add(lenovo);
		
		Brand apple = new Brand();
		apple.setBrand_description("electronics manufacturing company");
		apple.setBrand_name("Apple");
		apple.setBrand_headquarters("Cupertino, California");
		brandNames.add(apple);
		
		Brand samsung = new Brand();
		samsung.setBrand_description("electronics manufacturing company");
		samsung.setBrand_name("Samsung");
		samsung.setBrand_headquarters("Seoul");
		brandNames.add(samsung);
		
		Brand microsoft = new Brand();
		microsoft.setBrand_description("software company");
		microsoft.setBrand_name("Microsoft");
		microsoft.setBrand_headquarters("Redmond");
		brandNames.add(microsoft);
		
		Brand google = new Brand();
		google.setBrand_description("software company /search engine");
		google.setBrand_name("Google");
		google.setBrand_headquarters("Mountain View, California");
		brandNames.add(google);
    }
	
	public void refreshLists() {
    	// get devices
    	deviceList = networking.getDevices();
    	// get networks
    	networkList = networking.getNetworks();
    	// get users
    	userList = networking.getUsers();
    	// get os
    	osList = networking.getOperatingSystems();
	}
	
	// reset form
	public void abord() {
		this.operation_state = 0;
		this.mod_device_id = -1;
		this.setOperation("Add device");
		this.setSelectedBrand("Lenovo");
		this.setSelectedNetworks(new long[0]);
		this.setSelectedUsers(new long[0]);
		this.setSelectedOS(0);
		this.setDeviceName("");
		this.setDeviceDescription("");
	}
	
	// get device and update form (for edit)
	public void modifyDevice(long id) {
		this.mod_device_id = id;
		Device mod_device = networking.getDeviceById(id);
		this.operation_state = 1;
		this.setOperation("Modify device");
		this.setSelectedBrand(mod_device.getBrand().getBrand_name());
		List<Network> nList = mod_device.getNetworks();
		long[] selNetworks = new long[nList.size()];
		int i = 0;
		for (Network n : nList) {
			selNetworks[i] = n.getId();
			i++;
		}
		this.setSelectedNetworks(selNetworks);

		List<User> uList = mod_device.getOwners();
		long[] selUsers = new long[uList.size()];
		i = 0;
		for (User u : uList) {
			selUsers[i] = u.getId();
			i++;
		}
		this.setSelectedUsers(selUsers);
		this.setSelectedOS(mod_device.getOs().getId());
		this.setDeviceName(mod_device.getName());
		this.setDeviceDescription(mod_device.getDescription());
	}
	
	public void handleDevice() {
		List<Network> networks = new ArrayList<Network>();
		List<User> owners = new ArrayList<User>();
		
		int found = 0;
		int c = 0;
		for (Brand b : brandNames) {
			if (b.getBrand_name().equals(this.getSelectedBrand())) {
				found = c;
			}
			c++;
		}
		Brand brand = this.brandNames.get(found);
		
		for (int i = 0; i < selectedNetworks.length; i++) {
			networks.add(networking.getNetworkById(selectedNetworks[i]));
		}
		for (int i = 0; i < selectedUsers.length; i++) {
			owners.add(networking.getUserById(selectedUsers[i]));
		}
		
		OperatingSystem os = networking.getOperatingSystemById((long)this.getSelectedOS());

		if (operation_state == 0) {
			networking.addDevice(this.getDeviceName(),
									this.getDeviceDescription(),
									brand, networks, owners, os);
		}
		if (operation_state == 1) {
			networking.modifyDevice(mod_device_id,
									this.getDeviceName(),
									this.getDeviceDescription(),
									brand, networks, owners, os);
		}
		// reset form
		this.abord();
	}
	
	public void deleteDevice(long id) {
		networking.deleteDeviceById(id);
	}

	// getters and setters
    public List<Device> getDeviceList() {
		return deviceList;
    }
    public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceDescription() {
		return deviceDescription;
	}

	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}

	public String getSelectedBrand() {
		return selectedBrand;
	}

	public void setSelectedBrand(String selectedBrand) {
		this.selectedBrand = selectedBrand;
	}
    
    public List<Brand> getBrandNames() {
		return brandNames;
	}

	public void setBrandNames(List<Brand> brandNames) {
		this.brandNames = brandNames;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<OperatingSystem> getOsList() {
		return osList;
	}

	public void setOsList(List<OperatingSystem> osList) {
		this.osList = osList;
	}

	public long getSelectedOS() {
		return selectedOS;
	}

	public void setSelectedOS(long selectedOS) {
		this.selectedOS = selectedOS;
	}

	public List<Network> getNetworkList() {
		return networkList;
	}

	public void setNetworkList(List<Network> networkList) {
		this.networkList = networkList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}
    public long[] getSelectedNetworks() {
		return selectedNetworks;
	}

	public void setSelectedNetworks(long[] selectedNetworks) {
		this.selectedNetworks = selectedNetworks;
	}

	public long[] getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(long[] selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}