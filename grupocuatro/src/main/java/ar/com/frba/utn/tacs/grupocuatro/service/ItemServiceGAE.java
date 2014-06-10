package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ItemAlreadyExistsException;

@Service
public class ItemServiceGAE implements ItemService{
	
	@Autowired
	private ListService listService;
	@Autowired
	private OfyService ofyService;

	public ItemServiceGAE() {
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
	public Item_G4 create(Long id_list, Item_G4 item) {
		List<Item_G4> items = this.getItemsFromList(id_list);
		if(this.exists(items, item)){
			throw new ItemAlreadyExistsException("Ya existe un item con ese nombre");
		}
		item.setListId(id_list);
		ofyService.save(item);
		return item;
	}

	@Override
	public Item_G4 voteItem(Long id) {
		Item_G4 item = this.ofyService.find(Item_G4.class, id);
		item.incVotes(UserServiceGAE.getLoggedUser().getId());
		ofyService.save(item);
		return item;
	}
	
	@Override
	public Item_G4 getItemById(Long id) {
		return ofyService.find(Item_G4.class, id);
	}

	@Override
	public void delete(Long id) {
		Item_G4 item = this.getItemById(id);
		ofyService.remove(item);
	}
	
	@Override
	public List<Item_G4> getItemsFromList(Long id_list) {
		return ofyService.filter(Item_G4.class, "listId", id_list);
	}

}
