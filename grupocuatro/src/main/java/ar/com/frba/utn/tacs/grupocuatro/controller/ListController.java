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
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ListAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;
import ar.com.frba.utn.tacs.grupocuatro.service.ListService;

@Controller
@RequestMapping("/users/{id_user}/lists")
public class ListController {

	@Autowired
	private ListService listService;

	/**
	 * 
	 * @param id
	 * @return List_G4
	 * @HTTP status 404: si el id enviado no pertenece a ningun usuario o a ninguna lista
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody ResponseEntity<List_G4> getList(@PathVariable Long id) {
		try{
			List_G4 list = this.listService.getListById(id);
			return new ResponseEntity<List_G4>(list, HttpStatus.OK);
		}catch(ObjectNotFoundException e){
			return new ResponseEntity<List_G4>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param id_user
	 * @return List<List_G4>>
	 * @HTTP status 404: si el id enviado no pertenece a ningun usuario
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<List_G4>> getAllListsOfUser(@PathVariable Long id_user) {
		try{
			return new ResponseEntity<List<List_G4>>(this.listService.getListsFromUser(id_user), HttpStatus.OK);
		}catch(ObjectNotFoundException e){
			return new ResponseEntity<List<List_G4>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param list
	 * @return List_G4
	 * @HTTP status 400: cuando no se encuentra el usuario enviado por parámetro
	 * @HTTP status: 406 Si ya existe el nombre de lista enviado 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List_G4> createList(@PathVariable Long id_user, @RequestBody List_G4 list) {
		try{
			return new ResponseEntity<List_G4>(this.listService.create(id_user, list),HttpStatus.OK);
		}catch(ListAlreadyExistsException e){
			return new ResponseEntity<List_G4>(HttpStatus.NOT_ACCEPTABLE);
		}catch(ObjectNotFoundException e){
			return new ResponseEntity<List_G4>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param id_user
	 * @param id
	 * @HTTP status 200: si se borro correctamente la lista
	 * @HTTP status 400: cuando no se encuentra el usuario enviado por parámetro
	 * @HTTP status 404: cuando no se encuentra la lista enviada por parámetro
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public @ResponseBody ResponseEntity<List_G4> deleteList(@PathVariable Long id) {
		try{
			this.listService.delete(id);
			return new ResponseEntity<List_G4>(HttpStatus.OK);
		}catch(ObjectNotFoundException e){
			return new ResponseEntity<List_G4>(HttpStatus.NOT_FOUND);
		}
	}
}
