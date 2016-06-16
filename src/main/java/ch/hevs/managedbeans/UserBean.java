package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedProperty;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.User;
import ch.hevs.networkservice.NetworkInterface;

/**
 * TransferBean.java
 * 
 */
public class UserBean
{

	private List<User> userList;
    private NetworkInterface networking;
    private String userFirstname;
    private String userLastname;
    private String userEmail;
    private String operation;
    private int operation_state;
    private long mod_user_id;
    
    @ManagedProperty(value="#{deviceBean}")
    private DeviceBean deviceBean;

	@PostConstruct
    public void initialize() throws NamingException {
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
    	networking = (NetworkInterface) ctx.lookup("java:global/network-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.NetworkInterface");  
		
    	// set operation
    	this.setOperation("Add user");
    	operation_state = 0;
		this.mod_user_id = -1;
    	
    	// get users
    	userList = networking.getUsers();
    }
	
	// reset form
	public void abord() {
		this.operation_state = 0;
		this.mod_user_id = -1;
		this.setOperation("Add User");
		this.setUserFirstname("");
		this.setUserLastname("");
		this.setUserEmail("");
	}
	
	// get user and update form (for edit)
	public void modifyUser(long id) {
		this.mod_user_id = id;
		this.operation_state = 1;
		User mod_user = networking.getUserById(id);
		this.setOperation("Modify User");
		this.setUserFirstname(mod_user.getFirstname());
		this.setUserLastname(mod_user.getLastname());
		this.setUserEmail(mod_user.getEmail());
	}
	
	public void handleUser() {
		if (operation_state == 0) {
			networking.addUser(this.getUserFirstname(), this.getUserLastname(), this.getUserEmail());
		}
		if (operation_state == 1) {
			networking.modifyUser(mod_user_id, this.getUserFirstname(), this.getUserLastname(), this.getUserEmail());
		}
		deviceBean.refreshLists();
		// reset form
		this.abord();
	}
	
	public void deleteUser(int id) {
		networking.deleteUser(id);
		deviceBean.refreshLists();
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public long getMod_user_id() {
		return mod_user_id;
	}

	public void setMod_user_id(long mod_user_id) {
		this.mod_user_id = mod_user_id;
	}
	

    public DeviceBean getDeviceBean() {
		return deviceBean;
	}

	public void setDeviceBean(DeviceBean deviceBean) {
		this.deviceBean = deviceBean;
	}

	
}