//Super class that extends to Human and Dog

public class Mammal {
	
	//Class Members
	private int weight;
	private String name;
	private char sex;

	//Default constructor
	public Mammal(){
		weight = 100;
		name = "NoNamie";
		sex = 'M';
	}
	//Additional constructor
	public Mammal(int w,String n, char s){
		setWeight(w);
		setName(n);
		setSex(s);
	}
	
	
	
	//Methods to adjust and get mammal weight
	public void setWeight(int set){
			weight=set;
	}
	public int getWeight(){
		return weight;
	}	
	public void reduceWeight(int change){
		weight= weight-change;
	
	}
	public void increaseWeight(int change){
	weight=weight+change;
	
	}
	
	
	//Methods to set and get mammal sex
	public void setSex(char set) {
		if (set == 'M' || set == 'F' || set == 'm' || set == 'f' ){
		sex= set;}
		else{ 
			System.out.println("Not a valid choice for gender,please try again.");}
		}
		
	public void changeSex() {
		if (sex == 'M' ||  sex == 'm'){sex = 'F';}
			else{ sex='M';}	
	}
	
	public char getSex() {
		return sex;
		
	}
	
	
	//Methods to set and get mammal name
	public void setName(String set){
		this.name=set;
	}
	public String getName(){
		return name;
	}

}

