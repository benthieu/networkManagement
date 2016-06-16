package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedProperty;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Network;
import ch.hevs.networkservice.NetworkInterface;

/**
 * TransferBean.java
 * 
 */
//@RolesAllowed(value = { "admin" })
public class NetworkBean
{
    private List<Network> networkList;
    private NetworkInterface networking;
    private String networkName;
    private String networkDescription;
    private String operation;
    private int operation_state;
    private long mod_network_id;
    
    @ManagedProperty(value="#{deviceBean}")
    private DeviceBean deviceBean;


	@PostConstruct
    public void initialize() throws NamingException {
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
    	networking = (NetworkInterface) ctx.lookup("java:global/network-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.NetworkInterface");  
		
    	// set operation
    	this.setOperation("Add network");
    	operation_state = 0;
		this.mod_network_id = -1;
    	
    	// get networks
    	networkList = networking.getNetworks();
    }
	
	// reset form
	public void abord() {
		this.operation_state = 0;
		this.mod_network_id = -1;
		this.setOperation("Add Network");
		this.setNetworkName("");
		this.setNetworkDescription("");
	}
	
	// get device and update form (for edit)
	public void modifyNetwork(long id) {
		this.mod_network_id = id;
		this.operation_state = 1;
		Network mod_network = networking.getNetworkById(id);
		this.setOperation("Modify Network");
		this.setNetworkName(mod_network.getName());
		this.setNetworkDescription(mod_network.getDescription());
	}
	
	public void handleNetwork() {
		if (operation_state == 0) {
			networking.addNetwork(this.getNetworkName(), this.getNetworkDescription());
		}
		if (operation_state == 1) {
			networking.modifyNetwork(mod_network_id, this.getNetworkName(), this.getNetworkDescription());
		}
		deviceBean.refreshLists();
		// reset form
		this.abord();
	}
	
	public void deleteNetwork(int id) {
		networking.deleteNetwork(id);
		deviceBean.refreshLists();
	}
	
	public void deleteDevice(long id) {
		networking.deleteDeviceById(id);
	}

	public List<Network> getNetworkList() {
		return networkList;
	}

	public void setNetworkList(List<Network> networkList) {
		this.networkList = networkList;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getNetworkDescription() {
		return networkDescription;
	}

	public void setNetworkDescription(String networkDescription) {
		this.networkDescription = networkDescription;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(int operation_state) {
		this.operation_state = operation_state;
	}

	public long getMod_network_id() {
		return mod_network_id;
	}

	public void setMod_network_id(long mod_network_id) {
		this.mod_network_id = mod_network_id;
	}
	
	public DeviceBean getDeviceBean() {
		return deviceBean;
	}

	public void setDeviceBean(DeviceBean deviceBean) {
		this.deviceBean = deviceBean;
	}

	
}