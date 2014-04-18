package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.service.MockService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private MockService mockService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody User_G4 getUser(@PathVariable String id){
		return mockService.createMockUser(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User_G4> getAllUsers(){
		return mockService.createMockListOfUsers();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User_G4 createUser(@RequestBody User_G4 user){
		return user;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody User_G4 updateUser(@RequestBody User_G4 user){
		return user;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody User_G4 deleteList(@RequestBody User_G4 user){
		User_G4 deleted = new User_G4();
		return deleted;
	}

}
