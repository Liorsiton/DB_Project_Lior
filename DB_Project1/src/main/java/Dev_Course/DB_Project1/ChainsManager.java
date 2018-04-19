package Dev_Course.DB_Project1;


import java.util.List;
import java.util.Scanner;

public class ChainsManager {
	Scanner scan = new Scanner(System.in);
	
	

	
	public void init(){
		
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




	private void selectShopDetails() {
		System.out.println("list of all shops");
		List<Shop> shops = Dao.getAllShops();
		for(Shop sh : shops){
			System.out.println(sh.getName() + "," +sh.getChain().getName() + "," + sh.getMall().getName());
		}
		
	}




	private void selectEmployeeInChain() {
		System.out.println("Please enter the chain name");
		String name = scan.nextLine();
		int chainId = Dao.getChainByName(name);
		List<Employee> employees = Dao.getAllEmployeeInChain(chainId);
		for(Employee emp : employees){
			System.out.println(emp.getName());
		}
		
	}




	private void selectShopsInMallGroup() {
		System.out.println("Please enter the Mall Group name");
		String name= scan.nextLine();
		int mallGroupId = Dao.getMallGroupByName(name);
		int mallId = Dao.getMallIDByGroupMall(mallGroupId);
		List<Shop> shops = Dao.getAllShopsInMall(mallId);
		for(Shop sh : shops){
			System.out.println(sh.getName());
		}
		
		
		
	}




	private void selectShopsinMall() {
		String mallName;
		System.out.println("Please enter mall name");
		mallName=scan.nextLine();
		int mallId=Dao.getMallByName(mallName);
		List<Shop> shops = Dao.getAllShopsInMall(mallId);
		System.out.println("The shops in mall "+mallName + "are:");
		for(Shop sh : shops){
			System.out.println(sh.getName());
		}
		
	}




	private void addEmployeeToChain(boolean isShopEmployee) {
		String employeeName;
		Chain employeeChain=null;
		Shop EmployeeShop=null;
		System.out.println("Please enter the employee name");
		employeeName = scan.nextLine();
		List<Chain> chains =Dao.getAllChains();
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
			List<Shop> shops = Dao.getAllShopsInChain(employeeChain.getId());
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
			Dao.createEmployee(employeeName, employeeChain, EmployeeShop);
		}
		else{
			Dao.createEmployee(employeeName, employeeChain, null);
		}
		
		
		
	}




	private void addShopToChain() {
		String chainName,mallName;
		int chainId,mallId;
		//assume we have shops only in malls
		System.out.println("please enter the chain name");
		chainName = scan.next();
		chainId=Dao.getChainByName(chainName);
		System.out.println("please enter the mall name");
		mallName = scan.next();
		mallId=Dao.getMallByName(mallName);
		System.out.println("please enter the shop number in the mall");
		int shopNumberInMall = scan.nextInt();				
		System.out.println("Please enter shop name");
		String shopName = scan.next();
		Dao.createShopInMall(shopName,chainId,mallId,shopNumberInMall);
		
	}




	private void addNewChain() {
		String chainName;
		System.out.println("please enter the chain name");
		if(scan.hasNext()){
			chainName = scan.nextLine();
			Chain ch = new Chain(chainName);
			Dao.createChain(ch);
			
		}
		
	}
	
}
