package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.ReadPolicy.Consistency;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

@Service
public class OfyService implements InitializingBean{
	
	@Autowired 
	private ObjectifyFactory objectifyFactory;
	
	private Objectify ofy(){
		return ObjectifyService.ofy();
	}
	
	public void save(Object o){
		this.ofy().consistency(Consistency.STRONG).save().entity(o).now();
	}
	
	public void remove(Object o){
		this.ofy().consistency(Consistency.STRONG).delete().entity(o).now();
	}
	
	public <T> T find(Class<T> clazz, Long id){
		return this.ofy().consistency(Consistency.STRONG).load().type(clazz).id(id).now();
	}
	
	public <T> List<T> filter(Class<T> clazz, String field, Object value){
		return (List<T>) this.ofy().consistency(Consistency.STRONG).load().type(clazz).filter(field, value).list();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ObjectifyService.setFactory(objectifyFactory);
	}

}
