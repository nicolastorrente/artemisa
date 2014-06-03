package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;

public interface ItemService {

	/**
	 * 
	 * @param id_list
	 * @param item
	 * @return Item_G4
	 * throws ObjectNotFoundException si no existe el usuario o la lista enviada por parámetro
	 */
	public Item_G4 create(Long id_list, Item_G4 item);

	/**
	 * 
	 * @param id
	 * @return Item_G4
	 */
	public Item_G4 voteItem(Long id);
	
	/**
	 * 
	 * @param id_list
	 * @return List<Item_G4>
	 */
	public List<Item_G4> getItemsFromList(Long id_list);

	/**
	 * @param id
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Item_G4 getItemById(Long id);

}
