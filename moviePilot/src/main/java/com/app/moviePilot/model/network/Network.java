package com.app.moviePilot.model.network;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that represents a watch provider for the VisualContent
 * of our App.
 * @author Arismendy Castillo
 *
 */
@Entity
@Table(name = "NETWORKS")
public class Network {
	
	private Long id;
	private String name;
	private String logoPath;
	
	public Network(Long id, String name, String logoPath) {
		super();
		this.id = id;
		this.name = name;
		this.logoPath = logoPath;
	}

	public Network() {
	}

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

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

}//end class