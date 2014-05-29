
//import for ArrayList abject
import java.util.ArrayList;
//import for random number generation
import java.util.Random;
//import for user input
import java.util.Scanner;


public class Human extends Mammal {
	//Random number generator
	Random generate = new Random();
	
	//Input scanner generator
	Scanner input = new Scanner(System.in);
	
	//human class members
	private double money;
	private int dogfood;

	//Array List to make dogs
	ArrayList<Dog> dogs_owned = new ArrayList<Dog>();

	
	//Default constructor
	public Human(){
		dogfood=generate.nextInt(100)+1;
		money=generate.nextInt(100)+1;
		makeDogs(generate.nextInt(4)+ 1);
	}
	
	//optional constructor
	public Human(int f,double m,int d){
		setDogfood(f);
		setMoney(m);
		makeDogs(d);
	}
	
////////////////////////////////////////////////////////////////////
	
	//Method that creates the dog(s) object 
	//Method is called in the human constructor
	public void makeDogs(int set){
	System.out.println("Creating dogs.....");
		switch (set)
		{
		case 1: dogs_owned.add(0,new Dog());
				break;
		case 2: for (int i=0;i < set;i++){
				dogs_owned.add(i,new Dog());}
				break;
		case 3: for (int i=0;i < set;i++){
				dogs_owned.add(i,new Dog());}
				break;
		case 4: for (int i=0;i < set;i++){
				dogs_owned.add(i,new Dog());}
				break;
		}	
	}
	
	//Method to set the names of the dogs created by the Human class
	public void setDogName(){
		for (int i=0;i < dogs_owned.size();i++){
			System.out.println("Please Name Dog "+i);
			String temp=input.next();
			dogs_owned.get(i).setName(temp);
		}
	}
	
	//Method to get the amount dogs created by each Human
	public int getNum_of_Dogs(){
		return dogs_owned.size();
	}
	//Method to get the names of the dogs created by the Human class
	public Dog getDogs(int i){
		return dogs_owned.get(i);
	}
	
	//Human toString method
	//At least I think this is what you wanted done
	@Override
	public String toString(){
		 return getName()+ " stats are: \n"+ "Money: " + getMoney() + " " + "Weight: "+getWeight()+ " "+"Dog Food: "+getDogfood()+" "+"Gender: "+getSex()+"\n";
	}

//////Methods to adjust and display amount of money Human has//////
	public double getMoney(){
		return money;
	}
	public void setMoney(double set){
		money = set;
	}
	public void increaseMoney(double change){
		money=money+change;
	}
	public void reduceMoney(double change){
		money=money-change;
	}
////////////////////////////////////////////////////////////////////
	
//////Methods to adjust and display dog food quantity/////////
	public int getDogfood(){
		return dogfood;
	}
	public void setDogfood(int set){
		dogfood = set;
	}
	public void increaseDogfood(int change){
		dogfood=dogfood+change;
	}
	public void reduceDogfood(int change){
		dogfood=dogfood-change;
	}
////////////////////////////////////////////////////////////////////
	
	//Method to walk the dog
	public void walkDog(Dog choice){
		choice.increaseFun(1);
		choice.increaseHunger(1);
		choice.reduceCleanliness(1);
		choice.reduceWeight(1);
		reduceWeight(10);
		System.out.println(getName()+" walked "+choice.getName()+ "! "+choice.getName()+" had fun but got more hungry!\n");
	}
	
	//Method to feed the dog by using the human class
	public void feedDog(Dog choice,int dogfood_given){
		if(dogfood > 0 && dogfood_given < dogfood){
			if (dogfood_given >= 10){
				reduceDogfood(dogfood_given);
				choice.increaseWeight(5);
				choice.increaseFun(5);
				choice.increaseHunger(5);
				System.out.println(getName()+" fed " +choice.getName()+"! "+choice.getName()+" had fun and he is less hungry.\n");
			}
			else{System.out.println("That dog needs more food then that!!Try again"+"\n");}
			}
		else{System.out.println("Invalid amount. Either buy more dog food or try to feed the dogs more food then you had.");}
	}
	
	//Method to clean the dog
	public void cleanDog(Dog choice){
		choice.setFun(1);
		choice.setCleanliness(100);
		System.out.println(getName()+" gave "+choice.getName()+ " a bath! "+choice.getName()+" did not like that. But is now clean!\n");
	}
	
	//Method for Human to buy dog food
	public void buyDogfood(Dog choice){
		if(money > 0){
		reduceMoney(25.00);
		increaseDogfood(50);
		choice.reduceFun(2);
		System.out.println(getName()+" goes to the store to buy more dog food. That cost $25.00 and the dogs did not have fun.\n");
		}
		else{System.out.println(getName()+" out of money, go make some money\n");}
	}
	
	//Method for human to increase his money
	public void passTheTime(Dog choice){
		increaseWeight(5);
		increaseMoney(5.00);
		choice.reduceFun(1);
		choice.increaseHunger(1);
		
	}
	
}
