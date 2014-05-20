package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.io.Serializable;

public class BusinessObject implements Serializable{
	
	private static final long serialVersionUID = -1312614820613521796L;
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
