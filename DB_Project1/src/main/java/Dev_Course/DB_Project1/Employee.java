package Dev_Course.DB_Project1;

public class Employee {
	
	private int id;
	private String name;
	private Shop shop;
	private Chain chain;
	
	public Employee(){};
	
	
	public Employee(int id, String name, Shop shop, Chain chain) {
		
		this.id = id;
		this.name = name;
		this.shop = shop;
		this.chain = chain;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Shop getShop() {
		return shop;
	}


	public void setShop(Shop shop) {
		this.shop = shop;
	}


	public Chain getChain() {
		return chain;
	}


	public void setChain(Chain chain) {
		this.chain = chain;
	}
	
	
	
	

}
