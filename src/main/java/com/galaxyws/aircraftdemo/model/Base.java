package com.galaxyws.aircraftdemo.model;

import java.io.Serializable;
import java.util.UUID;

public abstract class Base implements Serializable {

	private static final long serialVersionUID = -6014724008537678534L;
	
	private String id;


	public Base() {
		super();
		this.setId(UUID.randomUUID().toString());
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "BaseObject [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Base other = (Base) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
