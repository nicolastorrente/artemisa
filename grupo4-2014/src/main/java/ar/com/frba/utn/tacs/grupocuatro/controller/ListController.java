package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.service.MockListService;

@Controller
@RequestMapping("/lists")
public class ListController {
	
	@Autowired
	private MockListService mockService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody List_G4 getList(@PathVariable String id){
		return mockService.createMockList(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<List_G4> getAllLists(){
		return mockService.createMockListOfList();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody List_G4 createList(@RequestBody List_G4 list){
		list.setMockStatus("List created");
		return list;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody List_G4 updateList(@RequestBody List_G4 list){
		list.setMockStatus("List updated");
		return list;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody List_G4 deleteList(@RequestBody List_G4 list){
		List_G4 deleted = new List_G4();
		deleted.setMockStatus("List deleted");
		return deleted;
	}

}
