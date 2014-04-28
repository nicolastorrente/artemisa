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
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.service.ListService;

@Controller
@RequestMapping("/user/{ID_USER}/lists")
public class ListController {

	@Autowired
	private ListService service;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody
	ResponseEntity<List_G4> getList(@PathVariable Long id) {
		List_G4 list = service.getById(id);
		if (list != null) {
			return new ResponseEntity<List_G4>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List_G4>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<List_G4> getAllLists() {
		return service.getAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<List_G4> createList(@RequestBody List_G4 list) {
		if (service.create(list)) {
			return new ResponseEntity<List_G4>(list, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<List_G4>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public @ResponseBody
	ResponseEntity<List_G4> updateList(@PathVariable Long id,
			@RequestBody List_G4 list) {
		if (service.update(id, list)) {
			return new ResponseEntity<List_G4>(list, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<List_G4>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public @ResponseBody
	ResponseEntity<List_G4> deleteList(@PathVariable Long id) {
		if (service.delete(id)) {
			return new ResponseEntity<List_G4>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List_G4>(HttpStatus.NOT_FOUND);
		}
	}
}
