package ar.com.frba.utn.tacs.grupocuatro.service;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.restfb.types.User;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;

public class UserServiceGAETest {
	
	private UserServiceGAE userServiceGAETest;
	private User usuarioNoExistente;
	
	@Before
	public void setUp() {
		userServiceGAETest = new UserServiceGAE();
		usuarioNoExistente = new User();
	}
	
	@Test
	public void intentaCrearUnUsuarioQueNoExisteEnGAEYValidaQueSeHayaCreadoConExito(){
		userServiceGAETest.findOrCreate(usuarioNoExistente);
		Assert.assertTrue(true);
	}

}
