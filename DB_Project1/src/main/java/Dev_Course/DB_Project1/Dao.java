package Dev_Course.DB_Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;



public class Dao {

	Formatter f = new Formatter();

	private final static String mySQLdriver = "com.mysql.jdbc.Driver";
	private final static String pathToDb = "jdbc:mysql://localhost:3306/chainShops";
	private final static String user = "root";
	private final static String password = "Hili2604";
	private final static String createChain = "insert into chains(name) values(?)";
	private final static String createShopInMall = "insert into shops(name,chain,mall,shop_number_in_mall) values(?,?,?,?)";
	private final static String getMallByName = "select id from malls where name =?";
	private final static String getChainByName = "select id from chains where name = ?";
	private final static String getMallGroupByName = "select id from mall_groups where name =?";
	private final static String getMallIDByGroupMall = "select id from malls where mall_group =?";
	private final static String getAllShopsInMall = "select id, name from shops where mall = ?";
	private final static String getAllShopsInChain = "select id,name from shops where chain = ? ";
	private final static String getAllChains = "select * from chains";
	private final static String getAllEmployeeInChain = "select * from employees where chain= ?";
	private final static String getAllShops = "select shops.id,shops.name, chains.name,malls.name from shops,chains,malls where shops.chain=chains.id and shops.mall=malls.id";
	private final static String deleteFromShops = "delete from shops where chain=?";
	private final static String deleteFromChain = "delete from chains where Name=?";
	private final static String deleteFromEmployee = "delete from employees where chain=?";

	public  Connection getCon() {
		Connection con = null;
		try {
			Class.forName(mySQLdriver);
			con = DriverManager.getConnection(pathToDb, user, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	/**
	 * @param ch
	 * @return
	 * @throws SQLException
	 */
	public int createChain(Chain ch) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(createChain)) {
			ps.setString(1, ch.getName());
			status = ps.executeUpdate();
		}
		return status;
	}

	/**
	 * @param shopName
	 * @param chainId
	 * @param mallId
	 * @param numberOfShopInMall
	 * @return
	 * @throws SQLException
	 */
	public int createShopInMall(String shopName, int chainId, int mallId, int numberOfShopInMall) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(createShopInMall)) {
			ps.setString(1, shopName);
			ps.setInt(2, chainId);
			ps.setInt(3, mallId);
			ps.setInt(4, numberOfShopInMall);
			status = ps.executeUpdate();
		}
		return status;
	}

	/**
	 * @param Name
	 * @return
	 * @throws SQLException
	 */
	public int getChainByName(String Name) throws SQLException {
		int id = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getChainByName)) {
			ps.setString(1, Name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}

		return id;
		
	}

	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int getMallByName(String name) throws SQLException {
		int id = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getMallByName)) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}
		return id;
		
	}

	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int getMallGroupByName(String name) throws SQLException {
		int id = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getMallGroupByName)) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}
		return id;
		
	}

	/**
	 * @param mallGroupID
	 * @return
	 * @throws SQLException
	 */
	public int getMallIDByGroupMall(int mallGroupID) throws SQLException {
		int id = 0;
		Connection con = getCon();
		Formatter f = new Formatter();
		try (PreparedStatement ps = con.prepareStatement(getMallIDByGroupMall)) {
			ps.setInt(1, mallGroupID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}
		return id;
		
	}

	/**
	 * @param name
	 * @param chain
	 * @param shop
	 * @return
	 * @throws SQLException
	 */
	public int createEmployee(String name, Chain chain, Shop shop) throws SQLException {
		int status = 0;
		Connection con = getCon();
		if (shop == null) {
			try (PreparedStatement ps = con.prepareStatement("insert into employees(name,chain) values(?,?)")) {
				ps.setString(1, name);
				ps.setInt(2, chain.getId());
				status = ps.executeUpdate();
			}
		} else {
			try (PreparedStatement ps = con.prepareStatement("insert into employees(name,chain,shop) values(?,?,?)")) {
				ps.setString(1, name);
				ps.setInt(2, chain.getId());
				ps.setInt(3, shop.getId());
				status = ps.executeUpdate();
			}
		}
		return status;
	}

	/**
	 * @param mallId
	 * @return
	 * @throws SQLException
	 */
	public List<Shop> getAllShopsInMall(int mallId) throws SQLException {
		List<Shop> shops = new ArrayList<>();
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getAllShopsInMall)) {
			ps.setInt(1, mallId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Shop s1 = new Shop();
				s1.setId(rs.getInt(1));
				s1.setName(rs.getString(2));
				shops.add(s1);
			}
		}
		return shops;
	}

	/**
	 * @param chainID
	 * @return
	 * @throws SQLException
	 */
	public List<Shop> getAllShopsInChain(int chainID) throws SQLException {
		List<Shop> shops = new ArrayList<>();
		Connection con = getCon();
		// Formatter f = new Formatter();
		try (PreparedStatement ps = con.prepareStatement(getAllShopsInChain)) {
			ps.setInt(1, chainID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Shop s1 = new Shop();
				s1.setId(rs.getInt(1));
				s1.setName(rs.getString(2));
				shops.add(s1);
			}
		}
		return shops;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Chain> getAllChains() throws SQLException {
		List<Chain> chains = new ArrayList<>();
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getAllChains)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Chain c1 = new Chain();
				c1.setId(rs.getInt(1));
				c1.setName(rs.getString(2));
				chains.add(c1);
			}
		}
		return chains;
	}

	/**
	 * @param chainId
	 * @return
	 * @throws SQLException
	 */
	public List<Employee> getAllEmployeeInChain(int chainId) throws SQLException {
		List<Employee> employees = new ArrayList<>();
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getAllEmployeeInChain)) {
			ps.setInt(1, chainId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				employees.add(emp);
			}
		}
		return employees;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Shop> getAllShops() throws SQLException {
		List<Shop> shops = new ArrayList<>();
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getAllShops)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Shop sh = new Shop();
				sh.setId(rs.getInt(1));
				sh.setName(rs.getString(2));
				Chain ch = new Chain();
				ch.setName(rs.getString(3));
				sh.setChain(ch);
				Mall m = new Mall();
				m.setName(rs.getString(4));
				sh.setMall(m);
				shops.add(sh);
			}
		}
		return shops;
	}

	/**
	 * @param ChainName
	 * @param Id
	 * @return
	 * @throws SQLException
	 */
	public int cleanUp(String ChainName , int Id) throws SQLException {
		int status = 0;
		
		Connection con = getCon();
		if (Id !=0) {
			try(PreparedStatement ps = con.prepareStatement(deleteFromEmployee)){
				ps.setInt(1, Id);
				status = ps.executeUpdate();
			}
			try(PreparedStatement ps = con.prepareStatement(deleteFromShops)){
				ps.setInt(1, Id);
				status = ps.executeUpdate();
				}
			try(PreparedStatement ps = con.prepareStatement(deleteFromChain)){
				ps.setString(1, ChainName);
				status = ps.executeUpdate();
		    }
	    }
		return status;
		
	}		
}		
		