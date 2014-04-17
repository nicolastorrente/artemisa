package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;



public class PersistenciaListas {
	
	ArrayList<List_G4> listasEnMemoria = new ArrayList<List_G4>();
	
	/**
	 * RETURN LISTS
	 * @return
	 */
	public ArrayList<List_G4> ReturnLists(){
		return listasEnMemoria;
	}
	
	/**
	 * ADD LIST TO MEMORY
	 * @param list
	 * @return
	 */
	public ResponseEntity<String> AddListToMemory(List_G4 list){
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
}
