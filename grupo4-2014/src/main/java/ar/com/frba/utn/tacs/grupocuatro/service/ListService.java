package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

@Service
public interface ListService {
	public List_G4 getById(String id);
	public List<List_G4> getAll();
	public List_G4 create(List_G4 list);
	public List_G4 update(List_G4 list);
	public List_G4 delete(List_G4 list);
}
