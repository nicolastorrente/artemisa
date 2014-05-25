package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ListAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;

@Service
public class ListServiceMemory implements ListService {
	
	private Long lastId = 0l;
	@Autowired
	private UserService userService;

	public boolean exists(final List<List_G4> lists, final List_G4 list){
		return CollectionUtils.exists(lists, new Predicate() {
			@Override
			public boolean evaluate(Object item) {
				return ((List_G4) item).getName().equals(list.getName());
			}
		});
	}

	@Override
	public List_G4 create(String id_user, List_G4 list) {
		List<List_G4> lists = this.getListsFromUser(id_user);
		if(this.exists(lists, list)){
			throw new ListAlreadyExistsException("En la lista ya existe una con ese nombre");
		}
		list.setId((++lastId).toString());
		lists.add(list);
		return list;
	}
	

	@Override
	public List_G4 getListByUserId(String id_user, String id_list) {
		User_G4 user = this.userService.getById(id_user);
		if (user == null) {
			throw new ObjectNotFoundException("No existe el usuario buscado");
		}
		return this.getListById(user.getLists(), id_list);
	}

	@Override
	public List<List_G4> getListsFromUser(String id_user) {
		User_G4 user = this.userService.getById(id_user);
		if (user == null) {
			throw new ObjectNotFoundException("No existe el usuario buscado");
		}
		return user.getLists();
	}

	@Override
	public void delete(String id_user, String id) {
		List<List_G4> lists = this.getListsFromUser(id_user);
		List_G4 list = this.getListById(lists, id);
		lists.remove(list);
	}
	
	@Override
	public List_G4 getListById(List<List_G4> lists, final String id) {
		List_G4 list = (List_G4) CollectionUtils.find(lists, new Predicate() {
							@Override
							public boolean evaluate(Object list) {
								return ((List_G4) list).getId().equals(id);
							}
						});
		if(list == null){
			throw new ObjectNotFoundException("No existe la lista buscada");
		}
		return list;
	}

}
