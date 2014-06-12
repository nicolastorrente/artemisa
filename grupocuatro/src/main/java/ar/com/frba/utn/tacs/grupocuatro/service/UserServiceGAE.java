package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserCreationException;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

@Service
public class UserServiceGAE implements UserService {
	
	@Autowired
	public OfyService ofyService;
	private List<User_G4> friends = new ArrayList<User_G4>();
	private static User_G4 loggedUser;
	public FacebookClient facebookClient;
	private final static String APP_SECRET = "30aacedb87868e4f4fa7728fe4e27e2d";
	
	public UserServiceGAE() {
		
	}

	@Override
	public User_G4 getById(Long id) {
		User_G4 user = this.ofyService.find(User_G4.class, id);
		if (user == null) {
			throw new ObjectNotFoundException("No se encontro el usuario");
		}
		return user;
	}
	
	public static User_G4 getLoggedUser(){
		return loggedUser;
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
		try{
			User_G4 user = this.getById(loggedU.getId());
			loggedUser = user;
		}catch(ObjectNotFoundException e){
			loggedUser = this.verify(loggedU);
			ofyService.save(loggedU);
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
				friends.add(findOrCreate((User)fbUser));
			}
		});
		return this.friends;
	}
	
	public User_G4 findOrCreate(User fbUser) {
		User_G4 user = null;
		try{
			user = this.getById(Long.parseLong(fbUser.getId()));
		}catch(ObjectNotFoundException e){
			user = new User_G4(fbUser);
			this.ofyService.save(user);
		}
		return user;
	}
	
}