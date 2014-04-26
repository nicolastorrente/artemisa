package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;

@Service
public class MockService {

	public List_G4 createMockList(String id){
		return this.createMockList(id, "jack", 3);
	}
	
	public List_G4 createMockList(String id, String name, int count){
		List_G4 list = new List_G4();
		list.setName(name);
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

	public List<List_G4> createMockListOfList() {
		List<List_G4> col = new ArrayList<List_G4>();
		col.add(this.createMockList("12341","list1",2));
		col.add(this.createMockList("12342","list2",3));
		col.add(this.createMockList("12343","list3",1));
		return col;
	}

	public Item_G4 createMockItem(String id) {
		return this.createMockItem(id, "labelItem");
	}
	
	private Item_G4 createMockItem(String id, String label) {
		Item_G4 item = new Item_G4("");
		item.setId(Long.parseLong(id));
		item.setLabel(label);
		item.incVotes();
		item.incVotes();
		return item;
	}

	public List<Item_G4> createMockListOfItem() {
		List<Item_G4> list = new ArrayList<Item_G4>();
		list.add(this.createMockItem("12341", "labelItem1"));
		list.add(this.createMockItem("12342", "labelItem2"));
		list.add(this.createMockItem("12343", "labelItem3"));
		return list;
	}

	public User_G4 createMockUser(String id) {
		return this.createMockUser(id, "Angus");
	}
	
	private User_G4 createMockUser(String id, String name) {
		User_G4 user = new User_G4("");
		user.setId(Long.parseLong(id));
		user.setUsername(name);
		user.setLists(this.createMockListOfList());
		return user;
	}

	public List<User_G4> createMockListOfUsers() {
		List<User_G4> list = new ArrayList<User_G4>();
		list.add(this.createMockUser("12341", "Moe"));
		list.add(this.createMockUser("12342", "Larry"));
		list.add(this.createMockUser("12343", "Shemp"));
		list.add(this.createMockUser("12344", "Curly"));
		return list;
	}
}
