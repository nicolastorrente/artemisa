package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.ObjectNotFoundException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserCreationException;

@Service
public interface UserService {

	/**
	 * CREATE
	 * 
	 * Recibe un usuario, le asigna un id y lo persiste.
	 * 
	 * @param user: Datos de usuario.
	 * @throws UserCreationException: si no tiene un username válido
	 * @throws UserAlreadyExistsException: si ya existe un usuario con ese username
	 */
	public void create(User_G4 user);

	/**
	 * GET BY ID
	 * 
	 * Recibe un ID y retorna un usuario.
	 * 
	 * @param id: ID de usuario.
	 * @return User_G4.
	 */
	public User_G4 getById(Long id);

	/**
	 * GET ALL
	 * 
	 * Retorna una lista con todos los usuarios.
	 * 
	 * @return Lista de usuarios.
	 */
	public List<User_G4> getAll();

	/**
	 * DELETE
	 * 
	 * Elimina un usuario por ID
	 * 
	 * @param id: ID de usuario.
	 * @throws ObjectNotFoundException si no encuentra el usuario con el id enviado.
	 */
	public void delete(long id);

}
