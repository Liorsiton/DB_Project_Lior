package Dev_Course.DB_Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class Dao {
	
	
	
	public static Connection getCon(){
		 String url = "jdbc:mysql://localhost:3306/chainShops";
		 String user = "root";
		 String password = "Hili2604";
		 
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/chainShops","root","Hili2604");
			//con=DriverManager.getConnection("url,user,password");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	
	

	
	
	public static int createChain(Chain ch){
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into chains(name) values(?)");
			ps.setString(1,ch.getName());		
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	public static int createShopInMall(String shopName,int chainId,int mallId,int numberOfShopInMall){
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into shops(name,chain,mall,shop_number_in_mall) values(?,?,?,?)");
			ps.setString(1,shopName);	
			ps.setInt(2,chainId);
			ps.setInt(3,mallId);
			ps.setInt(4,numberOfShopInMall);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	public static int getChainByName(String name){
		int id=0;		
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select id from chains where name ='"+ name +"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 id=rs.getInt(1);
			}
		   
			con.close();
		}catch(Exception e){System.out.println(e.getMessage());}
		return id;
		
	}
	
	public static int getMallByName(String name){
		int id=0;		
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select id from malls where name ='"+ name +"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 id=rs.getInt(1);
			}
		   
			con.close();
		}catch(Exception e){System.out.println(e);}
		return id;
		
	}
	
	
	public static int getMallGroupByName(String name){
		int id=0;		
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select id from mall_groups where name ='"+ name +"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 id=rs.getInt(1);
			}
		   
			con.close();
		}catch(Exception e){System.out.println(e);}
		return id;
		
	}
	
	public static int getMallIDByGroupMall(int mallGroupID){
		int id=0;		
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select id from malls where mall_group ='"+ mallGroupID +"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 id=rs.getInt(1);
			}
		   
			con.close();
		}catch(Exception e){System.out.println(e);}
		return id;
		
	}
	
	public static int createEmployee(String name,Chain chain ,Shop shop ){
		int status=0;
		PreparedStatement ps;
		try{
			Connection con=getCon();
			if(shop==null){
				ps=con.prepareStatement("insert into employees(name,chain) values(?,?)");
				ps.setString(1,name);
				ps.setInt(2,chain.getId());
			}
			else{
				ps=con.prepareStatement("insert into employees(name,chain,shop) values(?,?,?)");
				ps.setString(1,name);
				ps.setInt(2,chain.getId());
				ps.setInt(3,shop.getId());
			}
			
			
			
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	
	
	public static List<Shop> getAllShopsInMall(int mallId) {

		List<Shop> shops = new ArrayList<>();

		try {
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("select id, name from shops where mall =" + mallId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Shop s1 = new Shop();
				s1.setId(rs.getInt(1));
				s1.setName(rs.getString(2));
				shops.add(s1);

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return shops;
	}
	
	public static List<Shop> getAllShopsInChain(int chainID) {

		List<Shop> shops = new ArrayList<>();

		try {
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("select id,name from shops where chain =" + chainID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Shop s1 = new Shop();
				s1.setId(rs.getInt(1));
				s1.setName(rs.getString(2));
				shops.add(s1);

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return shops;
	}
	
	public static List<Chain> getAllChains(){
		List<Chain> chains = new ArrayList<>();
		try{
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("select * from chains");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Chain c1  = new Chain();
				c1.setId(rs.getInt(1));				
				c1.setName(rs.getString(2));
				chains.add(c1);
				
				}
			con.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return chains;
	}
	
	public static List<Employee> getAllEmployeeInChain(int chainId){
		List<Employee> employees = new ArrayList<>();
		try{
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("select * from employees where chain="+chainId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt(1));				
				emp.setName(rs.getString(2));
				employees.add(emp);
				
				}
			con.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return employees;
	}
	
	public static List<Shop> getAllShops(){
		List<Shop> shops = new ArrayList<>();
		try{
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("select shops.id,shops.name, chains.name,malls.name from shops,chains,malls where shops.chain=chains.id and shops.mall=malls.id");
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
			con.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return shops;
	}
}
	
	
	

