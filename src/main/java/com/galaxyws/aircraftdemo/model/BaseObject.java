package com.galaxyws.aircraftdemo.model;

import java.awt.Point;
import java.util.UUID;

public abstract class BaseObject {

	private String id;
	private Point position;
	private int life;
	private VBO model;

	public BaseObject() {
		super();
		this.setId(UUID.randomUUID().toString());
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public VBO getModel() {
		return model;
	}

	public void setModel(VBO model) {
		this.model = model;
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
		BaseObject other = (BaseObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
