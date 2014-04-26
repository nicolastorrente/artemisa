package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;

@Service
public interface UserService {

	/**
	 * CREATE
	 * 
	 * Recibe un usuario y retorna un boolean con el resultado.
	 * 
	 * @param user: Datos de usuario.
	 * @return TRUE o FALSE dependiendo del resultado de la operacion.
	 */
	public boolean create(User_G4 user);

	/**
	 * GET BY ID
	 * 
	 * Recibe un ID y retorna un usuario.
	 * 
	 * @param id: ID de usuario.
	 * @return Datos de usuario.
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
	 * UPDATE 
	 * 
	 * Recibe un ID de un usuario y sus nuevos datos, y los actualiza en
	 * la lista de usuarios.
	 * 
	 * @param id
	 *            : ID de usuario.
	 * @param user
	 *            : Datos de usuario.
	 * @return TRUE o FALSE dependiendo del resultado de la operacion.
	 */
	public boolean update(long id, User_G4 user);

	/**
	 * DELETE
	 * 
	 * Elimina un usuario por ID
	 * 
	 * @param id: ID de usuario.
	 * @return TRUE o FALSE dependiendo del resultado de la operacion.
	 */
	public boolean delete(long id);

}
