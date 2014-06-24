package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyExistsException;
import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserCreationException;

public interface UserService {

	/**
	 * CREATE
	 * 
	 * Recibe un usuario y lo persiste.
	 * 
	 * @param user: Datos de usuario.
	 * @throws UserCreationException: si no tiene un username válido
	 * @throws UserAlreadyExistsException: si ya existe un usuario con ese username
	 */
	public User_G4 verify(User_G4 user);

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
	 * Crea al usuario si no existe o lo obtiene y lo setea como usuario logueado
	 * @param user
	 * @param accessToken 
	 * @return User_G4
	 * Si no existe el usuario y tiene que crearlo
	 * @throws UserCreationException: si no tiene un username válido
	 * @throws UserAlreadyExistsException: si ya existe un usuario con ese username
	 */
	public User_G4 login(User_G4 user, String accessToken);
	
	/**
	 * 
	 * @param id
	 * @return Lista de amigos del usuario logueado
	 */
	public List<User_G4> getFriends();
	
	/**
	 * Notifica al usuario
	 * @param externalUserId
	 * @param message 
	 * @throws ObjectNotFoundException: si no existe el usuario
	 * @throws NotificationException: si el mensaje es inválido
	 */	
	public void sendNotification(String externalUserId, String message);
	
}
