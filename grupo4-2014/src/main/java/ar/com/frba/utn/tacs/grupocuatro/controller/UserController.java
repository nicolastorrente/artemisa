package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	/**
	 * CREATE USER
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<User_G4> createUser(@RequestBody User_G4 user) {
		if (service.create(user)) {
			return new ResponseEntity<User_G4>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<User_G4>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * GET USER
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody
	ResponseEntity<User_G4> getUser(@PathVariable Long id) {
		User_G4 user = service.getById(id);
		if (user != null) {
			return new ResponseEntity<User_G4>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User_G4>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * GET ALL USERS
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<User_G4> getAllUsers() {
		return service.getAll();
	}

	/**
	 * DELETE LIST
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public @ResponseBody
	ResponseEntity<User_G4> deleteList(@RequestBody Long id) {
		if (service.delete(id)) {
			return new ResponseEntity<User_G4>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<User_G4>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * UPDATE USER
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public @ResponseBody
	ResponseEntity<User_G4> updateUser(@PathVariable Long id,
			@RequestBody User_G4 user) {
		if (service.update(id, user)) {
			return new ResponseEntity<User_G4>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<User_G4>(HttpStatus.NOT_FOUND);
		}
	}
}
