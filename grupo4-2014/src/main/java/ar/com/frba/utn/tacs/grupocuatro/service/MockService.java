package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;

@Service
public class MockService {
	
	private Long mockId = 0l;
	
	public List_G4 createMockList(String id){
		return this.createMockList(id, "jack", 3);
	}
	
	private String getMockId(){
		return (++mockId).toString();
	}
	
	public List_G4 createMockList(String id, String name, int count){
		List_G4 list = new List_G4();
		list.setName(name);
		list.setId(id);
		list.setItems(this.createMockListItem(count));
		return list;
	}
	
	private List<Item_G4> createMockListItem(int count){
		List<Item_G4> list = new ArrayList<Item_G4>();
		for(int i = 0; i < count; i++)
			list.add(this.createMockItem(getMockId(), "labelItem"+(i+1)));
		return list;
	}

	public List<List_G4> createMockListOfList() {
		List<List_G4> col = new ArrayList<List_G4>();
		col.add(this.createMockList(getMockId(),"list"+getMockId(),2));
		col.add(this.createMockList(getMockId(),"list"+getMockId(),3));
		col.add(this.createMockList(getMockId(),"list"+getMockId(),1));
		return col;
	}

	public Item_G4 createMockItem(String id) {
		return this.createMockItem(id, "labelItem"+id);
	}
	
	private Item_G4 createMockItem(String id, String label) {
		Item_G4 item = new Item_G4(label);
		item.setId(id);
		item.incVotes();
		item.incVotes();
		return item;
	}

	public List<Item_G4> createMockListOfItem() {
		List<Item_G4> list = new ArrayList<Item_G4>();
		list.add(this.createMockItem(getMockId(), "labelItem"+getMockId()));
		list.add(this.createMockItem(getMockId(), "labelItem"+getMockId()));
		list.add(this.createMockItem(getMockId(), "labelItem"+getMockId()));
		return list;
	}

	public User_G4 createMockUser(String id) {
		return this.createMockUser(id, "Angus");
	}
	
	public User_G4 createMockUser(String id, String name) {
		User_G4 user = new User_G4(name);
		user.setId(id);
		user.setLists(this.createMockListOfList());
		return user;
	}

	public List<User_G4> createMockListOfUsers() {
		List<User_G4> list = new ArrayList<User_G4>();
		list.add(this.createMockUser(getMockId(), "Moe"));
		list.add(this.createMockUser(getMockId(), "Larry"));
		list.add(this.createMockUser(getMockId(), "Shemp"));
		list.add(this.createMockUser(getMockId(), "Curly"));
		return list;
	}
}
