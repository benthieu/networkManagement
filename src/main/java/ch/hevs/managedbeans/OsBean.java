package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedProperty;
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
//@RolesAllowed(value = { "admin" })
public class OsBean
{
    private List<OperatingSystem> osList;
    private NetworkInterface networking;
    private String osName;
    private String operation;
    private int operation_state;
    private long mod_os_id;
    private String selectedBrand;
    private List<Brand> brandNames;
    
    
    @PostConstruct
    public void initialize() throws NamingException {
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
    	networking = (NetworkInterface) ctx.lookup("java:global/network-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.NetworkInterface");  
		
    	// set operation
    	this.setOperation("Add Operating System");
    	operation_state = 0;
		this.mod_os_id = -1;
    	
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
	
	// reset form
	public void abord() {
		this.operation_state = 0;
		this.mod_os_id = -1;
		this.setOperation("Add Operating System");
		this.setOsName("");
		this.setSelectedBrand("Microsoft");	
	}
	
	// get operating system and update form (for edit)
	public void modifyOs(long id) {
		this.mod_os_id = id;
		OperatingSystem mod_os = networking.getOperatingSystemById(id);
		this.operation_state = 1;
		this.setOperation("Modify Operating System");
		this.setSelectedBrand(mod_os.getBrand().getBrand_name());
		
		this.setOsName(mod_os.getName());
	}
	
	public void handleOs() {

		int found = 0;
		int c = 0;
		for (Brand b : brandNames) {
			if (b.getBrand_name().equals(this.getSelectedBrand())) {
				found = c;
			}
			c++;
		}
		Brand brand = this.brandNames.get(found);
		

		if (operation_state == 0) {
			networking.addOs(this.getOsName(),brand);
		}
		if (operation_state == 1) {
			networking.modifyOs(mod_os_id,
									this.getOsName(), brand);
		}
		// reset form
		this.abord();
	}
	
	public void deleteOs(long id) {
		networking.deleteOsById(id);
	}

	// getters and setters
    public List<OperatingSystem> getOsList() {
		return osList;
    }
    
	public void setOsList(List<OperatingSystem> osList) {
		this.osList = osList;
	}
	
    public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
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


	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}