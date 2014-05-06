package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;

public interface ItemService {

	/**
	 * 
	 * @param id_user
	 * @param id_list
	 * @param item
	 * @return Item_G4
	 * throws ObjectNotFoundException si no existe el usuario o la lista enviada por parámetro
	 */
	public Item_G4 create(Long id_user, Long id_list, Item_G4 item);

	/**
	 * 
	 * @param id_user
	 * @param id_list
	 * @param id
	 * @return Item_G4
	 * throws ObjectNotFoundException si no existe el usuario, la lista o el item enviada por parámetro
	 */
	public Item_G4 voteItem(Long id_user, Long id_list, Long id);
	
	/**
	 * 
	 * @param list
	 * @param id
	 * @return Item_G4
	 * throws ObjectNotFoundException si no existe el item en la lista
	 */
	public Item_G4 getItemById(List<Item_G4> list, final Long id);

	/**
	 * 
	 * @param id_user
	 * @param id_list
	 * @param id
	 * @return
	 */
	public void delete(Long id_user, Long id_list, Long id);

}
