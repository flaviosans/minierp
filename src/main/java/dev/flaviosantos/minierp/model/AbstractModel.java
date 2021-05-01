package dev.flaviosantos.minierp.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AbstractModel {
	@Id
	@GeneratedValue
	private UUID id;
	
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}
