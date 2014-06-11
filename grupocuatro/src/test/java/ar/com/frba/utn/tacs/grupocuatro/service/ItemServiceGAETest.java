package ar.com.frba.utn.tacs.grupocuatro.service;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ar.com.frba.utn.tacs.grupocuatro.domain.Item_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

public class ItemServiceGAETest {
	private ItemServiceGAE itemServiceGAETest;
	private Item_G4 itemNuevo;
	private List_G4 listaNueva;
	private Long idList;
	
	@Before
	public void setUp() {
		itemServiceGAETest = new ItemServiceGAE();
		listaNueva = new List_G4("Mi Lista");
		itemNuevo = new Item_G4("mi Item Nuevo");
		idList = listaNueva.getId();
	}
	
	@Test
	public void creoUnItemNuevoParaLaListaYValidoQueSeHayaGuardado(){
		
		itemServiceGAETest.create(idList, itemNuevo);
		assertEquals("chequeo que el label del item nuevo que guarde sea igual al persistido", itemNuevo.getLabel(),itemServiceGAETest.getItemById(itemNuevo.getId()).getLabel());

	}
}