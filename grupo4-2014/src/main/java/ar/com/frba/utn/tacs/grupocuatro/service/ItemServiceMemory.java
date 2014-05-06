package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ItemAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;

@Service
public class ItemServiceMemory implements ItemService{
	
	private long lastId = 0;
	@Autowired
	private ListService listService;

	public ItemServiceMemory() {
	}
	
	private boolean exists(final List<Item_G4> items, final Item_G4 item){
		return CollectionUtils.exists(items, new Predicate() {
							@Override
							public boolean evaluate(Object i) {
								return ((Item_G4) i).getLabel().equals(item.getLabel());
							}
						});
	}

	@Override
	public Item_G4 create(Long id_user, Long id_list, Item_G4 item) {
		List_G4 list = this.listService.getListByUserId(id_user, id_list);
		if(this.exists(list.getItems(), item)){
			throw new ItemAlreadyExistsException("Ya existe un item con ese nombre");
		}
		item.setId(++this.lastId);
		list.getItems().add(item);
		return item;
	}

	@Override
	public Item_G4 voteItem(Long id_user, Long id_list, Long id) {
		List_G4 list = this.listService.getListByUserId(id_user, id_list);
		Item_G4 item = this.getItemById(list.getItems(), id);
		item.incVotes();
		return item;
	}
	
	@Override
	public Item_G4 getItemById(List<Item_G4> list, final Long id) {
		Item_G4 item = (Item_G4) CollectionUtils.find(list, new Predicate() {
							@Override
							public boolean evaluate(Object list) {
								return ((Item_G4) list).getId().equals(id);
							}
						});
		if(item == null){
			throw new ObjectNotFoundException("No existe el item buscado");
		}
		return item;
	}

	@Override
	public void delete(Long id_user, Long id_list, Long id) {
		List_G4 list = this.listService.getListByUserId(id_user, id_list);
		Item_G4 item = this.getItemById(list.getItems(), id);
		list.getItems().remove(item);
	}

}
