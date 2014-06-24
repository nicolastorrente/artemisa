package ar.com.frba.utn.tacs.grupocuatro.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class List_G4Test {
	
	List_G4 listaTest;
	Long idUsuario;
	String nombreLista;
	
	@Before
    public void setUp() {
		listaTest = new List_G4();
		idUsuario = 4L;
		nombreLista = "carlitos bala bala";
    }
	
	@Test
	public void seteoElNombreDeLaListaYLoValido() {
	   // seteo a la lista el nombre "carlitos bala bala"
		listaTest.setName(nombreLista);
	   
	   // chequeo que al pedirle el nombre a la lista me devuelva el nombre que le indique
	   assertEquals("el nombre de la lista debe ser carlitos bala bala", nombreLista , listaTest.getName());
	 }
	
	@Test
	public void seteoElUsuarioDeLaListaYLoValido() {
		// seteo a la lista el usuario con id 4
		listaTest.setUserId(idUsuario);
		
	   // chequeo que al pedirle el usuario a la lista me devuelva el usuario que le indique
	   assertEquals("el id de usuario de la lista debe ser 4", idUsuario , listaTest.getUserId());
	 } 
}