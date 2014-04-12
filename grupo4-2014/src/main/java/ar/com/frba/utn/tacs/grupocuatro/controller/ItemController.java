package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.service.MockService;

@Controller
@RequestMapping("/lists/{ID_LIST}/items")
public class ItemController {
	
	@Autowired
	private MockService mockService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody Item_G4 getItem(@PathVariable String id){
		return mockService.createMockItem(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Item_G4> getAllLists(){
		return mockService.createMockListOfItem();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Item_G4 createItem(@RequestBody Item_G4 item){
		item.setMockStatus("Item created");
		return item;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Item_G4 updateList(@RequestBody Item_G4 item){
		item.setMockStatus("Item updated");
		return item;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Item_G4 deleteList(@RequestBody Item_G4 item){
		Item_G4 deleted = new Item_G4();
		deleted.setMockStatus("Item deleted");
		return deleted;
	}

}
