package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;



public class PersistenciaMemoria implements Persistencia {
	
	ArrayList<List_G4> listasEnMemoria = new ArrayList<List_G4>();
	
	public ArrayList<List_G4> ReturnLists(){
		return listasEnMemoria;
	}

	public boolean AddListToMemory(List_G4 list){
		boolean repetido = false;
		
		for(List_G4 elemento : listasEnMemoria){
			if(elemento.getName().equals(list.getName()))
				repetido = true;
		}
		
		if(repetido){
			return false;
		}
		else{
			listasEnMemoria.add(list);
			return true;
		}
	}
}
