package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@MappedSuperclass
public class BusinessObject implements Serializable{
	
	private static final long serialVersionUID = -1312614820613521796L;
	
	@Id
	protected Long id;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
