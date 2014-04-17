package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.service.ListService;

@Controller
@RequestMapping("/lists")
public class ListController {
	
	ArrayList<List_G4> listasEnMemoria = new ArrayList<List_G4>();
	
	@Autowired
	private ListService service;
	
	/**
	 * GET LIST
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody List_G4 getList(@PathVariable String id){
		return service.getById(id);
	}
	
	/**
	 * LIST
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<List_G4> getAllLists(){
		return listasEnMemoria;
	}
	
	/**
	 * CREATE LIST
	 * @param list
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createList(@RequestBody List_G4 list){
		boolean repetido = false;
				
		for(List_G4 elemento : listasEnMemoria){
			if(elemento.getName().equals(list.getName()))
				repetido = true;
		}
		
		if(repetido){
			return new ResponseEntity<String>("Rechazado.", HttpStatus.NOT_FOUND);
		}
		else{
			listasEnMemoria.add(list);
			return new ResponseEntity<String>("Elemento agregado.", HttpStatus.CREATED);
		}
	}

	/**
	 * UPDATE LIST
	 * @param list
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody List_G4 updateList(@RequestBody List_G4 list){
		list.setMockStatus("List updated");
		for(Item_G4 item : list.getItems())
			item.setMockStatus("Item updated");
		return list;
	}
	
	/**
	 * DELETE LIST
	 * @param list
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody List_G4 deleteList(@RequestBody List_G4 list){
		List_G4 deleted = new List_G4();
		deleted.setMockStatus("List deleted");
		return deleted;
	}

}
