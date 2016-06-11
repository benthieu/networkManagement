package ch.hevs.businessobject;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Brand {

	private String brand_name;
	private String brand_description;
	private String brand_headquarters;


	/** CONSTRUCTORS **/

	public Brand ()
	{
	}
	

	/** GETTERS AND SETTERS **/

	public String getBrand_name() {
		return brand_name;
	}


	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}


	public String getBrand_description() {
		return brand_description;
	}


	public void setBrand_description(String brand_description) {
		this.brand_description = brand_description;
	}


	public String getBrand_headquarters() {
		return brand_headquarters;
	}


	public void setBrand_headquarters(String brand_headquarters) {
		this.brand_headquarters = brand_headquarters;
	}
}
