package ar.com.frba.utn.tacs.grupocuatro.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import ar.com.frba.utn.tacs.grupocuatro.domain.List_G4;
import ar.com.frba.utn.tacs.grupocuatro.domain.User_G4;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/spring-config/application-context.xml" })
public class OfyServiceTest {

	@Autowired
	private OfyService ofyService;
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }
	
	@Test
	public void testSaveAndFindUser(){
		User_G4 user = new User_G4();
		user.setUsername("pepillo");
		user.setId(12345678l);
		ofyService.save(user);
		User_G4 user2 = ofyService.find(User_G4.class, 12345678l);
		Assert.assertNotNull(user2);
		
	}
	
	@Test
	public void testSaveAndFilterList(){
		List_G4 list = new List_G4();
		list.setName("JAjaJAjaJa");
		list.setUserId(123456l);
		ofyService.save(list);
		list = new List_G4();
		list.setName("JOJOJO");
		list.setUserId(123456l);
		ofyService.save(list);
		List<List_G4> lists = ofyService.filter(List_G4.class, "userId", 123456l);
		Assert.assertTrue(!lists.isEmpty());
		
	}

}
