package ar.com.frba.utn.tacs.grupocuatro.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ListAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ListNotYoursException;

public class ListServiceGAETest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private ListServiceGAE listService = new ListServiceGAE();
	private UserServiceGAE uService = new UserServiceGAE();
	
	Long idListInexistente = 3L;
	Long idListExistente = 11L;
	List_G4 listExistente;
	List_G4 listInexistente;
	List<List_G4> listExistentes = new ArrayList<List_G4>();
	
	User_G4 usuarioExistente, otroUsuario;

	@Before
	public void setUp() {
		
		usuarioExistente = new User_G4();
		usuarioExistente.setUsername("Manteca Martinez");
		usuarioExistente.setId(3l);
		
		otroUsuario = new User_G4();
		otroUsuario.setUsername("Pepe");
		otroUsuario.setId(5l);
		
		OfyService mockOfiServ = mock(OfyService.class);
		//cuando intente buscar el el Usuario con id 3 retornara El usuario con id 3
		when(mockOfiServ.find(User_G4.class, usuarioExistente.getId())).thenReturn(usuarioExistente);
		when(mockOfiServ.find(User_G4.class, otroUsuario.getId())).thenReturn(otroUsuario);
		uService.ofyService = mockOfiServ;
		
		uService.login( usuarioExistente, "miToken");
		
		listExistente = new List_G4();
		listExistente.setId(idListExistente);
		listExistente.setUserId(usuarioExistente.getId());
		listExistente.setName("soy una lista que existe");
		
		listInexistente = new List_G4();
		listInexistente.setId(idListInexistente);
		listInexistente.setUserId(usuarioExistente.getId());
		listInexistente.setName("soy una lista que no existe");
		
		listExistentes.add(listExistente);
		when(mockOfiServ.find(List_G4.class, listExistente.getId())).thenReturn(listExistente);
		when(mockOfiServ.filter(List_G4.class, "userId", listExistente.getUserId())).thenReturn(listExistentes);
		listService.ofyService = mockOfiServ;
	}
	
	@Test
	public void createUnaListaqueNoExiste(){
		UserServiceGAE.setLoggedUser(usuarioExistente);
		assertEquals(listService.create(usuarioExistente.getId(), listInexistente), listInexistente);
	}
	
	@Test
	public void noPodesCrearUnaListaAOtroUsuario(){
		exception.expect(ListNotYoursException.class);
		UserServiceGAE.setLoggedUser(otroUsuario);
		assertEquals(listService.create(usuarioExistente.getId(), listInexistente), listInexistente);
	}	
	
	@Test
	public void createUnaListaqueExisteYRetornaExcepcion(){
		exception.expect(ListAlreadyExistsException.class);
		listService.create(usuarioExistente.getId(), listExistente);
	}
	
	@Test
	public void eliminarUnaListaInexistenteDeUnUsuario(){
		int cantListas = listService.getListsFromUser(usuarioExistente.getId()).size();
		listService.delete(listInexistente.getId());
		assertEquals(cantListas, listService.getListsFromUser(usuarioExistente.getId()).size());
	}
	
	@Test
	public void traerUnaListaPorSuId(){
		List_G4 lista = listService.getListById(listExistente.getId());
		assertEquals(lista, listExistente);
	}
	
	@Test
	public void traerLasListasDeUnUsuario(){
		List<List_G4> listaDelUsuario = listService.getListsFromUser(listExistente.getUserId());
		assertEquals(listaDelUsuario, listExistentes);
	}
}
