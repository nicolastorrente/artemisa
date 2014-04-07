package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.util.ArrayList;
import java.util.List;

public class List_G4 extends BusinessObject{
	
	private static final long serialVersionUID = 7053015612840694054L;

	private String name;
	private List<Item_G4> items;
	
	public List_G4() {
		super();
		this.items = new ArrayList<Item_G4>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item_G4> getItems() {
		return items;
	}

	public void setItems(List<Item_G4> items) {
		this.items = items;
	}
	
	public void addItem(Item_G4 item){
		this.items.add(item);
	}
	
}
