package ar.com.frba.utn.tacs.grupocuatro.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;


public class UserServiceGAETest {
	
	UserServiceGAE uService = new UserServiceGAE();
	Long idUsuarioInexistente = 4L;
	Long idUsuarioExistente = 3L;
	User_G4 usuarioInexistente;
	User_G4 usuarioExistente;
	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Before
	public void setUp(){
		usuarioInexistente = new User_G4();
		usuarioInexistente.setId(idUsuarioInexistente);
		usuarioInexistente.setUsername("Pepino Cuevas");
		usuarioExistente = new User_G4();
		usuarioExistente.setId(idUsuarioExistente);
		usuarioExistente.setUsername("Manteca Martinez");
		
		
		OfyService mockOfiServ = mock(OfyService.class);
		//cuando intente buscar el el Usuario con id 4 retornara null
		when(mockOfiServ.find(User_G4.class, idUsuarioInexistente)).thenReturn(null);
		//cuando intente buscar el el Usuario con id 3 retornara El usuario con id 3
		when(mockOfiServ.find(User_G4.class, idUsuarioExistente)).thenReturn(usuarioExistente);
		uService.ofyService = mockOfiServ;
		
	}
	
	@Test
	public void getByIdConIdInexistenteDevuelvaUnaExcepcion(){
		exception.expect(ObjectNotFoundException.class);
		uService.getById(idUsuarioInexistente);
	}
	
	@Test
	public void getByIdConIdExistenteDevuelveElUsuario(){
		assertEquals("deberian ser los mismos ids",idUsuarioExistente, uService.getById(idUsuarioExistente).getId());
	}
	
	@Test
	public void loginConUsuarioInexistenteLoCreaYDevuelveElUsuario(){
		User_G4 usuarioRetornado = uService.login( usuarioExistente, "miToken");
		assertEquals("los usuarios deberian ser =es",usuarioExistente, usuarioRetornado);
	}
	
	@Test
	public void loginConUsuarioExistenteDevuelveElUsuario(){
		User_G4 usuarioRetornado = uService.login( usuarioExistente, "miToken");
		assertEquals("los usuarios deberian ser =es",usuarioExistente, usuarioRetornado);
		
	}
		
}
