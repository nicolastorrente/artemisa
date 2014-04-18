package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.ArrayList;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

public interface Persistencia {
	ArrayList<List_G4> ReturnLists();
	boolean AddListToMemory(List_G4 list);
}
