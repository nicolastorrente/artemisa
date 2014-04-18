package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

@Service
public class ListServiceMemoria implements ListService {
	
	ArrayList<List_G4> listasEnMemoria = new ArrayList<List_G4>();

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
	public List_G4 update(List_G4 list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List_G4 delete(List_G4 list) {
		// TODO Auto-generated method stub
		return null;
	}
}
