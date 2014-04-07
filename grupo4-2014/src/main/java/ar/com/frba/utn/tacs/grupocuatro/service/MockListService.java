package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

@Service
public class MockListService {

	public List_G4 createMockList(String id){
		List_G4 list = new List_G4();
		list.setName("jack");
		list.setId(Long.getLong(id));
		Item_G4 item = new Item_G4();
		item.setLabel("Item1");
		item.setId(234l);
		list.addItem(item);
		return list;
	}

	public List<List_G4> createMockListOfList() {
		List<List_G4> col = new ArrayList<List_G4>();
		List_G4 list = new List_G4();
		list.setName("jack");
		list.setId(235235l);
		Item_G4 item = new Item_G4();
		item.setLabel("Item1");
		item.setId(234l);
		list.addItem(item);
		col.add(list);
		list = new List_G4();
		list.setName("sparrow");
		list.setId(346767l);
		item = new Item_G4();
		item.setLabel("Item2");
		item.setId(123678l);
		list.addItem(item);
		col.add(list);
		return col;
	}
}
