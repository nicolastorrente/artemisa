package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;

@Service
public class UserServiceMemoria implements UserService {
	
	private long lastId = 0;
	private List<User_G4> users = new ArrayList<User_G4>();
	
	public UserServiceMemoria() {
		this.create(new User_G4("Yo"));
		this.create(new User_G4("Moe"));
		this.create(new User_G4("Chad"));
		this.create(new User_G4("John"));
	}

	@Override
	public User_G4 getById(Long id) {
		for(User_G4 elemento : users){
			if(elemento.getId().equals(id))
				return elemento;
		}
		return null;
	}
	
	@Override
	public List<User_G4> getAll() {
		return users;
	}
	
	@Override
	public boolean create(User_G4 user) {
		boolean exists = this.exists(user);
		if(!exists){
			lastId++;
			user.setId(lastId);
			users.add(user);
		}
		return !exists;
	}
	
	public boolean exists(User_G4 user){
		for(User_G4 elemento : users){
			if(elemento.getUsername().equals(user.getUsername()))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(long id) {
		User_G4 user = this.getById(id);
		if(user!=null){
			users.remove(user);
		}
		//Duevuelve verdadero si la borró
		return (user!=null);
	}
	
	@Override
	public boolean update(long id, User_G4 user) {
		User_G4 u = this.getById(id);
		if(u!=null){
			//No debería cambiar el ID
			user.setId(id);
			users.set(users.indexOf(u), user);
		}
		//Duevuelve verdadero si la actualizó
		return (u!=null);
	}
}