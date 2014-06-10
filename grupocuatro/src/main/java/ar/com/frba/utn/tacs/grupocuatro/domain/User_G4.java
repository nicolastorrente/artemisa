package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.restfb.types.User;

@Entity
@Cache
public class User_G4  implements Serializable{
	private static final long serialVersionUID = 2595359983700883890L;
	@Id
	private Long id;
	private String username;
	
	public User_G4(User fbUser) {
		this();
		this.setId(Long.parseLong(fbUser.getId()));
		this.username = fbUser.getName();
	}
	
	public User_G4() {
		super();
	}

	public User_G4(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User_G4 [id=" + id + ", username=" + username + "]";
	}

}
