package ar.com.frba.utn.tacs.grupocuatro.domain;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache
public class List_G4 extends BusinessObject{
	private static final long serialVersionUID = 7053015612840694054L;

	private String name;
	@Index
	private Long userId;
	
	public List_G4(String name) {
		super();
		this.name = name;
	}
	
	public List_G4() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "List_G4 [name=" + name + ", id=" + getId() + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
