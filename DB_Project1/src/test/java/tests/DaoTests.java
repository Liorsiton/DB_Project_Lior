package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Dev_Course.DB_Project1.Chain;
import Dev_Course.DB_Project1.Dao;
import Dev_Course.DB_Project1.Shop;

public class DaoTests {
   
	@Before
    public void cleanUp() throws SQLException {
		Dao dao = new Dao();
		int id = dao.getChainByName("LivePerson"); // get current Chain Id (precondition)
		dao.cleanUp("LivePerson",id);
    }
	
	@Test
	public void good_addChain() throws SQLException {
    	Dao dao = new Dao();
    	String chainName = "LivePerson";
    	Chain chain = new Chain(chainName);
    	assertEquals(1,dao.createChain(chain));
    	int id = dao.getChainByName(chainName);    	
		assertNotNull(id);
		
   }
	
	@Test
	public void good_addShop() throws SQLException {
		Dao dao = new Dao();
		Chain chain = new Chain("LivePerson");
		assertEquals(1, dao.createChain(chain));
		int chainId = dao.getChainByName("LivePerson");
		int mallId= dao.getMallByName("Azrieli_TA");
		assertEquals(1, dao.createShopInMall("LivePerson_AZ", mallId, 1, 101));
		int shopID = dao.getShopByName("LivePerson_AZ");
		assertNotNull(shopID);
   }
	
	@Test
	public void good_getMallByName() throws SQLException {
		Dao dao = new Dao();
		assertEquals(2,dao.getMallByName("Malcha"));
   }
   
   @Test
   public void good_getChainByName() throws SQLException {
	   Dao dao = new Dao();
	   assertEquals(1,dao.getChainByName("Fox"));
   }
   
   @Test
   public void good_getMallGroupByName() throws SQLException {
	   Dao dao = new Dao();
	   assertEquals(1,dao.getMallGroupByName("Azrieli"));
   }
   @Test
   public void good_getMallIDByGroupMall() throws SQLException {
	   Dao dao = new Dao();
	   assertEquals(2,dao.getMallIDByGroupMall(2));
   }
   
   @Test
   public void good_getAllShopsInMall() throws SQLException {
	   List<Shop> expected = new ArrayList<>();
	   Dao dao = new Dao();
	   Shop shop1 = new Shop();
	   shop1.setId(1);
	   expected.add(shop1);
	   assertEquals(expected.get(0).getId(),dao.getAllShopsInMall(1).get(0).getId());
   }
}