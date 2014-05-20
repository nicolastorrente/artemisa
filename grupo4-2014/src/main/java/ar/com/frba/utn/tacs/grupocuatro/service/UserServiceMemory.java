package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserCreationException;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

@Service
public class UserServiceMemory implements UserService {
	
	private List<User_G4> friends = new ArrayList<User_G4>();
	private static User_G4 loggedUser;
	private FacebookClient facebookClient;
	private final static String APP_SECRET = "30aacedb87868e4f4fa7728fe4e27e2d";
	
	public UserServiceMemory() {
		
	}

	@Override
	public User_G4 getById(String id) {
		User_G4 user = this.getUserById(id);
		if (user == null) {
			throw new ObjectNotFoundException("No se encontro el usuario");
		}
		return user;
	}
	
	public static User_G4 getLoggedUser(){
		return loggedUser;
	}
	
	private User_G4 getUserById(final String id) {
		User_G4 user = (User_G4) CollectionUtils.find(this.friends, new Predicate() {
							@Override
							public boolean evaluate(Object user) {
								return ((User_G4) user).getId().equals(id);
							}
						});
		if(user == null && loggedUser != null && loggedUser.getId().equals(id))
			user = loggedUser;
		return user;
	}
	
	@Override
	public List<User_G4> getAll() {
		return this.friends;
	}
	
	@Override
	public User_G4 verify(User_G4 user) {
		if(user.getUsername() == null || "".equals(user.getUsername())){
			throw new UserCreationException("No se pudo crear el Usuario");
		}
		return user;
	}

	@Override
	public User_G4 login(User_G4 loggedU, String accessToken) {
		this.facebookClient = new DefaultFacebookClient(accessToken, APP_SECRET);
		User_G4 user = this.getUserById(loggedU.getId());
		if(user == null){
			loggedUser = this.verify(loggedU);
		}else{
			loggedUser = user;
		}
		return loggedUser;
	}

	@Override
	public List<User_G4> getFriends() {
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		this.friends.clear();
		CollectionUtils.forAllDo(myFriends.getData(), new Closure() {
			@Override
			public void execute(Object fbUser) {
				friends.add(new User_G4((User)fbUser));
			}
		});
		return this.friends;
	}
}