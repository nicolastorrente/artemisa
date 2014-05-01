package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserCreationException;

@Service
public class UserServiceMemory implements UserService, InitializingBean {
	
	@Autowired
	private MockService mockService;
	
	private long lastId = 0;
	private List<User_G4> users = new ArrayList<User_G4>();
	
	public UserServiceMemory() {
		
	}

	@Override
	public User_G4 getById(final Long id) {
		User_G4 user = (User_G4) CollectionUtils.find(this.users, new Predicate() {
							@Override
							public boolean evaluate(Object user) {
								return ((User_G4) user).getId().equals(id);
							}
						});
		if (user == null) {
			throw new ObjectNotFoundException("No se encontro el usuario");
		}
		return user;
	}
	
	@Override
	public List<User_G4> getAll() {
		return this.users;
	}
	
	@Override
	public void create(User_G4 user) {
		if(user.getUsername() == null || "".equals(user.getUsername())){
			throw new UserCreationException("No se pudo crear el Usuario");
		}
		if(this.exists(user)){
			throw new UserAlreadyExistsException("Ya existe un usuario con ese username");
		}
		lastId++;
		user.setId(lastId);
		users.add(user);
	}
	
	public boolean exists(final User_G4 user){
		return CollectionUtils.exists(this.users, new Predicate() {
							@Override
							public boolean evaluate(Object u) {
								return ((User_G4) u).getUsername().equals(user.getUsername());
							}
						});
	}
	
	@Override
	public void delete(long id) {
		User_G4 user = this.getById(id);
		this.users.remove(user);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.create(mockService.createMockUser(++lastId,"Yo"));
		this.create(mockService.createMockUser(++lastId,"Moe"));
		this.create(mockService.createMockUser(++lastId,"Chad"));
		this.create(mockService.createMockUser(++lastId,"John"));
	}
}