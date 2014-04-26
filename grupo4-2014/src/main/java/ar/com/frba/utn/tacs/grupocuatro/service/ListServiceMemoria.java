package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

@Service
public class ListServiceMemoria implements ListService {
	
	long lastId = 0;
	ArrayList<List_G4> listasEnMemoria = new ArrayList<List_G4>();

	public ListServiceMemoria() {
		this.create(new List_G4("Lista 1",
								new ArrayList<Item_G4>(Arrays.asList(
										new Item_G4("Item 1"),
										new Item_G4("Item 2")))));
		this.create(new List_G4("Lista 2",
				new ArrayList<Item_G4>(Arrays.asList(
						new Item_G4("Item a"),
						new Item_G4("Item b")))));
		this.create(new List_G4("Lista 3",
				new ArrayList<Item_G4>(Arrays.asList(
						new Item_G4("Item")))));		
	}

	@Override
	public List_G4 getById(Long id) {
		for(List_G4 elemento : listasEnMemoria){
			if(elemento.getId().equals(id))
				return elemento;
		}
		return null;
	}

	@Override
	public ArrayList<List_G4> getAll() {
		return listasEnMemoria;
	}
	
	@Override
	public boolean create(List_G4 list) {
		boolean exists = this.exists(list);
		if(!exists){
			lastId++;
			list.setId(lastId);
			listasEnMemoria.add(list);
		}
		return !exists;
	}
	
	public boolean exists(List_G4 list){
		for(List_G4 elemento : listasEnMemoria){
			if(elemento.getName().equals(list.getName()))
				return true;
		}
		return false;
	}

	@Override
	public boolean update(long id, List_G4 list) {
		List_G4 l = this.getById(id);
		if(l!=null){
			//No debería cambiar el ID
			list.setId(id);
			listasEnMemoria.set(listasEnMemoria.indexOf(l), list);
		}
		//Duevuelve verdadero si la actualizó
		return (l!=null);
	}

	@Override
	public boolean delete(long id) {
		List_G4 l = this.getById(id);
		if(l!=null){
			listasEnMemoria.remove(l);
		}
		//Duevuelve verdadero si la borró
		return (l!=null);
	}
}
