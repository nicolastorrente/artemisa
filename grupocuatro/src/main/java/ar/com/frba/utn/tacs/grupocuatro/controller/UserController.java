package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserCreationException;
import ar.com.frba.utn.tacs.grupocuatro.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	/**
	 * CREATE USER OK
	 * 
	 * @param user
	 * @return User_G4
	 * @HTTP status: 400 Si el usuario enviado era inválido
	 * @HTTP status: 406 Si ya existe el nombre de usuario enviado
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<User_G4> login(@RequestBody User_G4 user, @RequestHeader String accessToken) {
		try{
			return new ResponseEntity<User_G4>(this.service.login(user, accessToken), HttpStatus.CREATED);
		}catch(UserCreationException e){
			return new ResponseEntity<User_G4>(HttpStatus.BAD_REQUEST);
		}catch(UserAlreadyExistsException e){
			return new ResponseEntity<User_G4>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * GET USER
	 * 
	 * @param id
	 * @return User_G4
	 * @HTTP status: 404 Si el id enviado no pertenece a ningún usuario
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody ResponseEntity<User_G4> getUser(@PathVariable Long id) {
		try{
			return new ResponseEntity<User_G4>(this.service.getById(id), HttpStatus.OK);
		}catch(ObjectNotFoundException e){
			return new ResponseEntity<User_G4>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * GET USER FRIENDS
	 * 
	 * @param id
	 * @return List<User_G4>
	 * @HTTP status: 404 Si el id enviado no pertenece a ningún usuario
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/friends")
	public @ResponseBody ResponseEntity<List<User_G4>> getUserFriends(@PathVariable Long id) {
		try{
			return new ResponseEntity<List<User_G4>>(this.service.getFriends(), HttpStatus.OK);
		}catch(ObjectNotFoundException e){
			return new ResponseEntity<List<User_G4>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * GET ALL USERS
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<User_G4>> getAllUsers() {
		return new ResponseEntity<List<User_G4>>(this.service.getAll(), HttpStatus.OK);
	}

}
