package Dev_Course.DB_Project1;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ChainsManager {
	Scanner scan = new Scanner(System.in);
	Dao dao =new Dao();
	
	

	
	public void init() throws SQLException{
		
		while(true){
			System.out.println("please choose the following DB actions or type q for quit");
			System.out.println("1.Add new chain");
			System.out.println("2.Add a shop to a chain");
			System.out.println("3.Add employee to shop/group managment");
			System.out.println("4.Present all shops that are in a certain Shopping Mall");
			System.out.println("5.Present all shops that are in a certain Shopping Mall Group");
			System.out.println("6.Present all Employees of a certain Chain");
			System.out.println("7.Present all details of a Shop");
			String input=scan.nextLine();
				if(input.equals("1")){
					addNewChain();
				}
				else if(input.equals("2")){
					addShopToChain();
				}
				else if(input.equals("3")){
					System.out.println("1.Add employee to shop");
					System.out.println("2.Add employee to group managment");
					String employeeInput= scan.nextLine();
					if(employeeInput.equals("1")){
						addEmployeeToChain(true);
					}
					if(employeeInput.equals("2")){
						addEmployeeToChain(false);
					}
					
				}
				else if(input.equals("4")){
					selectShopsinMall();
				}
				else if(input.equals("5")){
					selectShopsInMallGroup();
				}
				else if(input.equals("6")){
					selectEmployeeInChain();
				}
				else if(input.equals("7")){
					selectShopDetails();
				}
				else if(input.equals("q")){
					System.out.println("Quiting..");
					break;
				}

		}
	}




	private void selectShopDetails() throws SQLException {
		System.out.println("list of all shops");
		List<Shop> shops = dao.getAllShops();
		for(Shop sh : shops){
			System.out.println(sh.getName() + "," +sh.getChain().getName() + "," + sh.getMall().getName());
		}
		
	}




	private void selectEmployeeInChain() throws SQLException {
		System.out.println("Please enter the chain name");
		String name = scan.nextLine();
		int chainId = dao.getChainByName(name);
		List<Employee> employees = dao.getAllEmployeeInChain(chainId);
		for(Employee emp : employees){
			System.out.println(emp.getName());
		}
		
	}




	private void selectShopsInMallGroup() throws SQLException {
		System.out.println("Please enter the Mall Group name");
		String name= scan.nextLine();
		int mallGroupId = dao.getMallGroupByName(name);
		int mallId = dao.getMallIDByGroupMall(mallGroupId);
		List<Shop> shops = dao.getAllShopsInMall(mallId);
		for(Shop sh : shops){
			System.out.println(sh.getName());
		}
		
		
		
	}




	private void selectShopsinMall() throws SQLException {
		String mallName;
		System.out.println("Please enter mall name");
		mallName=scan.nextLine();
		int mallId=dao.getMallByName(mallName);
		List<Shop> shops = dao.getAllShopsInMall(mallId);
		System.out.println("The shops in mall "+mallName + "are:");
		for(Shop sh : shops){
			System.out.println(sh.getName());
		}
		
	}




	private void addEmployeeToChain(boolean isShopEmployee) throws SQLException {
		String employeeName;
		Chain employeeChain=null;
		Shop EmployeeShop=null;
		System.out.println("Please enter the employee name");
		employeeName = scan.nextLine();
		List<Chain> chains =dao.getAllChains();
		for(Chain c : chains){
			System.out.println(c.getName());			
		}
		System.out.println("Please choose the chain name");		
		String chainName=scan.nextLine();
		for(Chain c : chains){
			if(c.getName().equals(chainName)){
				employeeChain =c;
			}
		}
		if(isShopEmployee){
			List<Shop> shops = dao.getAllShopsInChain(employeeChain.getId());
			for(Shop sh:shops){
				System.out.println(sh.getName());
			}
			System.out.println("Please choose the shop name");
			
			String shopName=scan.next();
			for(Shop sh:shops){
				if(sh.getName().equals(shopName)){
					EmployeeShop=sh;
				}
			}
			dao.createEmployee(employeeName, employeeChain, EmployeeShop);
		}
		else{
			dao.createEmployee(employeeName, employeeChain, null);
		}
		
		
		
	}




	private void addShopToChain() throws SQLException {
		String chainName,mallName;
		int chainId,mallId;
		//assume we have shops only in malls
		System.out.println("please enter the chain name");
		chainName = scan.next();
		chainId=dao.getChainByName(chainName);
		System.out.println("please enter the mall name");
		mallName = scan.next();
		mallId=dao.getMallByName(mallName);
		System.out.println("please enter the shop number in the mall");
		int shopNumberInMall = scan.nextInt();				
		System.out.println("Please enter shop name");
		String shopName = scan.next();
		dao.createShopInMall(shopName,chainId,mallId,shopNumberInMall);
		
	}




	private void addNewChain() throws SQLException {
		String chainName;
		System.out.println("please enter the chain name");
		if(scan.hasNext()){
			chainName = scan.nextLine();
			Chain ch = new Chain(chainName);
			dao.createChain(ch);
			
		}
		
	}
	
}
