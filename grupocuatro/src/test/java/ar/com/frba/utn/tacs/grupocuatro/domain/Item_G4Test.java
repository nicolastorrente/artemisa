package ar.com.frba.utn.tacs.grupocuatro.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyVoteException;

public class Item_G4Test {
	Item_G4 itemTest;
	Long idUsuario;
	Long uno = 1L;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
    public void setUp() {
		itemTest = new Item_G4("mi Item");
		idUsuario = 3L;
    }
	
	@Test
	public void votoElItemNuevoYChequeoQueTengaUnVoto() {
	    // voto el item "mi Item" recien creado
		itemTest.incVotes(idUsuario);
		
		// chequeo que al pedirle la cantidad de votos sea igual a 1
		assertEquals("la cantidad de votos del item debe ser 1", uno, itemTest.getVotes());
	 }
	
	@Test
	public void votoElItemNuevoYChequeoQueElUsuarioHayaVotadoElItem() {
		// voto el item "mi Item" recien creado
		itemTest.incVotes(idUsuario);
				
		// chequeo que al pedirle los usuarios que votaron el item se encuentre el usuario con id 3
		assertTrue(itemTest.getVoters().contains(idUsuario));
	}
	
	@Test
	public void votoElItemConElMismoUsuarioYChequeoQueDevuelvaUnaExcepcion() {
		// voto el item "mi Item" recien creado
		itemTest.incVotes(idUsuario);
		
		exception.expect( UserAlreadyVoteException.class );
		exception.expectMessage("El usuario ya voto por este item");
		
		// vuelvo a votar el item "mi Item" con el mismo usuario y valida que me devuelva una excepcion
		itemTest.incVotes(idUsuario);
	}
}
	