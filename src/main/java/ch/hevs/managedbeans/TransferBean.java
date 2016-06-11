package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
public class TransferBean
{
    private List<Device> deviceList;
    private List<User> userList;
    private List<OperatingSystem> osList;
    private List<Network> networkList;
    private NetworkInterface networking;
    private String deviceName;
    private String deviceDescription;
    private String selectedBrand;
    private int selectedNetwork;
    private int selectedUser;
    private int selectedOS;
    private List<Brand> brandNames;

	@PostConstruct
    public void initialize() throws NamingException {
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
    	networking = (NetworkInterface) ctx.lookup("java:global/network-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.NetworkInterface");  
			
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

	public int getSelectedNetwork() {
		return selectedNetwork;
	}

	public void setSelectedNetwork(int selectedNetwork) {
		this.selectedNetwork = selectedNetwork;
	}

	public int getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(int selectedUser) {
		this.selectedUser = selectedUser;
	}

	public int getSelectedOS() {
		return selectedOS;
	}

	public void setSelectedOS(int selectedOS) {
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
	
}