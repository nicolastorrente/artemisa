package ar.com.frba.utn.tacs.grupocuatro.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.stubbing.Answer;

import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.*;

public class ItemServiceGAETest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private ItemServiceGAE itemService = new ItemServiceGAE();
	private UserServiceGAE uService = new UserServiceGAE();
	
	Long idItemInexistente = 4L;
	Long idItemExistente = 3L;
	Long idList = 11L;
	Item_G4 itemInexistente;
	Item_G4 itemExistente;
	List<Item_G4> itemsExistentes = new ArrayList<Item_G4>();
	
	User_G4 usuarioExistente;

	@Before
	public void setUp() {
		usuarioExistente = new User_G4();
		usuarioExistente.setUsername("Manteca Martinez");
		
		OfyService mockOfiServ = mock(OfyService.class);
		//cuando intente buscar el el Usuario con id 3 retornara El usuario con id 3
		when(mockOfiServ.find(User_G4.class, usuarioExistente.getId())).thenReturn(usuarioExistente);
		uService.ofyService = mockOfiServ;
		
		uService.login( usuarioExistente, "miToken");
		
		itemInexistente = new Item_G4();
		itemInexistente.setId(idItemInexistente);
		itemInexistente.setListId(idList);
		itemInexistente.setLabel("soy un item que no existe");
		
		itemExistente = new Item_G4();
		itemExistente.setId(idItemExistente);
		itemExistente.setListId(idList);
		itemExistente.setLabel("soy un item que existe!");
		
		itemsExistentes.add(itemExistente);
		
		when(mockOfiServ.filter(Item_G4.class, "listId", idList)).thenReturn(itemsExistentes);
		when(mockOfiServ.find(Item_G4.class, itemExistente.getId())).thenReturn(itemExistente);
		itemService.ofyService = mockOfiServ;
	}
	
	@Test
	public void createUnItemqueNoExiste(){
		assertEquals(itemService.create(idItemInexistente, itemInexistente), itemInexistente);
	}
	
	@Test
	public void createUnItemqueExisteYRetornaExcepcion(){
		exception.expect(ItemAlreadyExistsException.class);
		itemService.create(idList, itemExistente);
	}
	
	@Test
	public void votarItemExistentente(){
		Long cantidadvotos = itemExistente.getVotes();
		Item_G4 itemvotado = itemService.voteItem(itemExistente.getId());
		cantidadvotos++;
		assertEquals(cantidadvotos, itemvotado.getVotes());
	}
	
	@Test
	public void votar2VecesItemExistentente(){
		itemExistente.getVotes();
		itemService.voteItem(itemExistente.getId());
		exception.expect(UserAlreadyVoteException.class);
		itemService.voteItem(itemExistente.getId());
	}
	
	@Test
	public void eliminarUnItemInexistenteDeUnaLista(){
		int cantItems = itemService.getItemsFromList(itemExistente.getListId()).size();
		itemService.delete(itemInexistente.getId());
		assertEquals(cantItems, itemService.getItemsFromList(itemExistente.getListId()).size());
	}
}
