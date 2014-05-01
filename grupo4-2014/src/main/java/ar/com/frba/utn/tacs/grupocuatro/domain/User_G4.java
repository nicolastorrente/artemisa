package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.util.ArrayList;
import java.util.List;

public class User_G4 extends BusinessObject{
	
	private static final long serialVersionUID = -5224827520355279040L;

	private String username;
	private List<List_G4> lists;
	
	public User_G4() {
		super();
		this.lists = new ArrayList<List_G4>();
	}

	public User_G4(String username) {
		super();
		this.lists = new ArrayList<List_G4>();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<List_G4> getLists() {
		return lists;
	}

	public void setLists(List<List_G4> lists) {
		this.lists = lists;
	}
	
	public void addList(List_G4 list){
		this.lists.add(list);
	}

}
