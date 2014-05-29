
//import for random number generation
import java.util.Random;

public class Dog extends Mammal {
	
	//Random number generator
	Random generate = new Random();
	
		//Class members
		private int hunger,
		 fun,
		 cleanliness;
		private int loyalty;
		
		//default constructor
		public Dog(){
			hunger=generate.nextInt(100)+1;
			fun=generate.nextInt(100)+1;
			cleanliness=generate.nextInt(100)+1;
			setLoyalty();
		}
		
		//Cleaner way to construct object
		public Dog(int f,int h,int c){
			setHunger(h);
			setFun(f);
			setCleanliness(c);
			setLoyalty();
		}
		//Dog toString method
		//At least I think this is what you wanted done
		@Override
		public String toString(){
		return getName()+" stats are:\n"+
				"Hunger:" + getHunger() + " " + "Fun:"+getFun()+ " "+"Loyalty:"+getLoyalty()+" "+
				 "Cleanliness:"+getCleanliness()+" " + "Weight: " +getWeight() + " " +"Gender: "+ getSex() + "\n";
			
		}
		
		//Methods to adjust and get dogs level of fun
		//can only be set or adjust 1-100 and at integer value
		public void setFun(int set){
			if(set >100){fun = 100;}
			else if(set < 1){fun = 1;}
			else{fun=set;}
		}
		public int getFun(){
			return fun;
		}	
		public void reduceFun(int change){
			fun= fun-change;
			if (fun <=0){
				fun= 1;}
		}
		public void increaseFun(int change){
		fun=fun+change;
			if(fun >100){
				fun=100;}
		}
		
		//Methods to adjust and display dogs hunger
		//can only be set or adjust 1-100 and at integer value
		public int getHunger(){
			return hunger;
		}
		public void setHunger(int set){
			if(set >100){hunger = 100;}
			else if(set < 1){hunger = 1;}
			else{hunger=set;}
		}
		public void reduceHunger(int change){
			hunger= hunger-change;
				if (hunger <=0){
					hunger= 1;}
		}
		public void increaseHunger(int change){
			hunger=hunger+change;
				if(hunger >100){
					hunger=100;}
		}
		
		//Methods to adjust and display dogs cleanliness
		//can only be set or adjust 1-100 and at integer value
		public void setCleanliness(int set){
			if(set >100){cleanliness = 100;}
			else if(set < 1){cleanliness = 1;}
			else{cleanliness=set;}
		}
		
		public int getCleanliness(){
			return cleanliness;
		}
		public void reduceCleanliness(int change){
			cleanliness= cleanliness-change;
				if (cleanliness <=0){
					cleanliness= 1;}
		}
		public void increaseCleanliness(int change){
			cleanliness=cleanliness+change;
				if(cleanliness >100){
					cleanliness=100;}
		}
		
		//Methods to adjust and get dogs loyalty
		//can only be set or adjust 1-100 and at integer value
		//These methods will not be used for this project but i wanted to include them just in case I revisit this project
		private void setLoyalty(){
			loyalty=Math.max(fun,100-hunger);
		}
		
		public int getLoyalty(){
			return loyalty;
		}


	}

