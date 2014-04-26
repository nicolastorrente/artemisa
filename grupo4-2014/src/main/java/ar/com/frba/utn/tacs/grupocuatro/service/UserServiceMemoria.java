package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;

@Service
public class UserServiceMemoria implements UserService {
	
	long lastId = 0;
	ArrayList<User_G4> UsuariosEnMemoria = new ArrayList<User_G4>();
	
	public UserServiceMemoria() {
		this.create(new User_G4("Moe"));
		this.create(new User_G4("Chad"));
		this.create(new User_G4("John"));
	}

	@Override
	public User_G4 getById(Long id) {
		for(User_G4 elemento : UsuariosEnMemoria){
			if(elemento.getId().equals(id))
				return elemento;
		}
		return null;
	}
	
	@Override
	public ArrayList<User_G4> getAll() {
		return UsuariosEnMemoria;
	}
	
	@Override
	public boolean create(User_G4 user) {
		boolean exists = this.exists(user);
		if(!exists){
			lastId++;
			user.setId(lastId);
			UsuariosEnMemoria.add(user);
		}
		return !exists;
	}
	
	public boolean exists(User_G4 user){
		for(User_G4 elemento : UsuariosEnMemoria){
			if(elemento.getUsername().equals(user.getUsername()))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(long id) {
		User_G4 user = this.getById(id);
		if(user!=null){
			UsuariosEnMemoria.remove(user);
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
			UsuariosEnMemoria.set(UsuariosEnMemoria.indexOf(u), user);
		}
		//Duevuelve verdadero si la actualizó
		return (u!=null);
	}
}