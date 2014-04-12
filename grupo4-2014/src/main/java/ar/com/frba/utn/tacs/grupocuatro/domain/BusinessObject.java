package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.io.Serializable;

public class BusinessObject implements Serializable{
	
	private static final long serialVersionUID = -1312614820613521796L;
	
	private Long id;
	
	private String mockStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getMockStatus() {
		return mockStatus;
	}

	public void setMockStatus(String mockStatus) {
		this.mockStatus = mockStatus;
	}
	
}
