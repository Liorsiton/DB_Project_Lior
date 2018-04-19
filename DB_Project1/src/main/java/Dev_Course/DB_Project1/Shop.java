package Dev_Course.DB_Project1;

public class Shop  {
	
	private int id;
	private String name;
	private String city;
	private String address;
	private Mall mall;
	private int numberInMall;
	private Chain chain;
	
	
	public Shop(){};
	
	public Shop( int id,String name, String city, String address, Mall mall, int numberInMall) {
		
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.mall = mall;
		this.numberInMall = numberInMall;
	}

 



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Mall getMall() {
		return mall;
	}



	public void setMall(Mall mall) {
		this.mall = mall;
	}



	public int getNumberInMall() {
		return numberInMall;
	}



	public void setNumberInMall(int numberInMall) {
		this.numberInMall = numberInMall;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Chain getChain() {
		return chain;
	}

	public void setChain(Chain chain) {
		this.chain = chain;
	}
	
	
	
	
	
	
	
	

}
