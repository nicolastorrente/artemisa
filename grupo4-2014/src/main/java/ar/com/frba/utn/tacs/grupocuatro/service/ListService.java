package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;

@Service
public interface ListService {
	public List_G4 getById(Long id);

	public List<List_G4> getAll();

	/**
	 * Crea una lista
	 * 
	 * @param list la lista a crear
	 * @return si creó la lista.
	 */
	public boolean create(List_G4 list);

	/**
	 * Actualiza una lista
	 * 
	 * @param id de la lista
	 * @param list nuevos atributos de la lista
	 * @return verdadero si actualizo la lista.
	 */
	public boolean update(long id, List_G4 list);

	/**
	 * Borra una lista
	 * 
	 * @param id de la lista a borrar
	 * @return verdadero si borró la lista.
	 */
	public boolean delete(long id);
}
