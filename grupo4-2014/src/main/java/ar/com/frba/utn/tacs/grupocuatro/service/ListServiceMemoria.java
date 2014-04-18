package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

@Service
public class ListServiceMemoria implements ListService {
	
	ArrayList<List_G4> listasEnMemoria = new ArrayList<List_G4>();

	@Override
	public List_G4 getById(String id) {
		return this.createMockList(id, "jack", 3);
	}

	@Override
	public ArrayList<List_G4> getAll() {
		return listasEnMemoria;
	}

	@Override
	public ResponseEntity<String> create(List_G4 list) {
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

	@Override
	public List_G4 update(List_G4 list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List_G4 delete(List_G4 list) {
		// TODO Auto-generated method stub
		return null;
	}

	public List_G4 createMockList(String id, String name, int count){
		List_G4 list = new List_G4();
		list.setName(name);
		list.setMockStatus("got");
		list.setId(Long.parseLong(id));
		list.setItems(this.createMockListItem(count));
		return list;
	}	
	
	private List<Item_G4> createMockListItem(int count){
		List<Item_G4> list = new ArrayList<Item_G4>();
		for(int i = 0; i < count; i++)
			list.add(this.createMockItem(""+(count+1), "labelItem"+(count+1)));
		return list;
	}
	
	public Item_G4 createMockItem(String id) {
		return this.createMockItem(id, "labelItem");
	}
	
	private Item_G4 createMockItem(String id, String label) {
		Item_G4 item = new Item_G4();
		item.setId(Long.parseLong(id));
		item.setMockStatus("got");
		item.setLabel(label);
		item.incVotes();
		item.incVotes();
		return item;
	}
}
