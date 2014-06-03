package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ListAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;

@Service
public class ListServiceGAE implements ListService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private OfyService ofyService;

	public boolean exists(final List<List_G4> lists, final List_G4 list){
		return CollectionUtils.exists(lists, new Predicate() {
			@Override
			public boolean evaluate(Object item) {
				return ((List_G4) item).getName().equals(list.getName());
			}
		});
	}

	@Override
	public List_G4 create(Long id_user, List_G4 list) {
		List<List_G4> listsUser = this.getListsFromUser(id_user);
		if(this.exists(listsUser, list)){
			throw new ListAlreadyExistsException("En la lista ya existe una con ese nombre");
		}
		list.setUserId(id_user);
		ofyService.save(list);
		return list;
	}
	

	@Override
	public List_G4 getListById(Long id_list) {
		List_G4 list = ofyService.find(List_G4.class, id_list);
		if (list == null) {
			throw new ObjectNotFoundException("No existe la lista buscada");
		}
		return list;
	}

	@Override
	public void delete(Long id) {
		List_G4 list = ofyService.find(List_G4.class, id);
		this.ofyService.remove(list);
	}
	
	@Override
	public List<List_G4> getListsFromUser(Long id_user) {
		return ofyService.filter(List_G4.class, "userId", id_user);
	}

}
