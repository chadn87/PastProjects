///////////////////////////////////////////////////////////////////////
//Author:Chad Nelson
//Assignment:Project 4
//This program simulates two mammals interacting with each other, a human and a dog
//////////////////////////////////////////////////////////////////////

//import for ArrayList object
import java.util.ArrayList;
//import for user input
import java.util.Scanner;

public class PetSimulator {

	public static void main(String[] args) {
		//Main Variables
		int choice, 
			Num_of_Humans,
			opt,
			stop;
		String name_choice;
		Scanner input = new Scanner(System.in);
		ArrayList<Human> human_List = new ArrayList<Human>();
		
////ask user how man humans and dogs to create
		System.out.println("How many humans do you want to create?");
		Num_of_Humans=input.nextInt();

//Loop that creates the humans and asks user to assign names(to dogs and humans)
//number of dogs per human is randomly generated
			for(int i=0;i<Num_of_Humans;i++){
				System.out.println("What do you want human "+i+" to be named?");
				name_choice = input.next();
				Human temp_human = new Human();
				temp_human.setName(name_choice);
				human_List.add(i, temp_human);
				human_List.get(i).setDogName();
			}
			Num_of_Humans=Num_of_Humans-1;
			
		//Loop to bring up menu and manipulate the dog and human class
		do{
			for(int i=0;i<human_List.size();i++){
				int j =human_List.get(i).getNum_of_Dogs();
				System.out.println(human_List.get(i).toString());
					for(int x=0;x<j;x++){
					System.out.println(human_List.get(i).getDogs(x).toString());
					}
			}
				if(human_List.size() > 1){
				System.out.println("Which Human would you like to interact with?\n"
								+"Pick a number between 0 and "+Num_of_Humans);
				opt=input.nextInt();}
				else{opt=0;}
				
		
			Human setHuman= human_List.get(opt);
			
			do{
				System.out.println("How would you like to do with the dogs?(type in corresponding number)");
				System.out.println("1. Do Nothing \n" +
								"2. Walk the dogs around the block.\n" +
								"3. Feed the dogs\n"+
								"4. Give the dogs a bath.\n"+
								"5. Buy more dogs food.\n"+
								"6. Pass the time(get more money)\n"+
								"7. Do something that should never be done.\n"+
								"8. Exit program.");
				choice=input.nextInt();
				switch(choice){
				case 1: System.out.println("Nothing happend...");
							break;
							
				case 2: for(int i =0;i < setHuman.getNum_of_Dogs();i++){
						setHuman.walkDog(setHuman.getDogs(i));}
							break;
							
				case 3: System.out.println("how much food when you like to give him?\n");
						int dogfood_given=input.nextInt();
						for(int i =0;i < setHuman.getNum_of_Dogs();i++){
							setHuman.feedDog(setHuman.getDogs(i), dogfood_given);}
							break;
							
				case 4: for(int i =0;i < setHuman.getNum_of_Dogs();i++){
						setHuman.cleanDog(setHuman.getDogs(i));}
							break;
							
				case 5: for(int i =0;i < setHuman.getNum_of_Dogs();i++){
						setHuman.buyDogfood(setHuman.getDogs(i));}
							break;
					
				case 6: System.out.println(setHuman.getName()+" passes the time.....\n");
						System.out.println(setHuman.getName()+" makes MONEY!"+setHuman.getName()+ "dogs are getting hungry and are not having fun\n");
						for(int i =0;i < setHuman.getNum_of_Dogs();i++){
							setHuman.passTheTime(setHuman.getDogs(i));}
							break; 
				case 7: System.out.println("Welp...I would check "+setHuman.getName()+"'s gender.....");
						setHuman.changeSex();
							break;
							
				case 8: System.out.println("Goodbye");
							break;		
							
				default: System.out.println("Please enter a valid choice using the corresponding number.");
								break;}
		
				if(choice!=8){
					System.out.println("All humans pass the time.....\n");
					for(int i =0;i < human_List.size();i++){
						int j =human_List.get(i).getNum_of_Dogs();
						for(int x=0;x<j;x++){
							human_List.get(i).passTheTime(human_List.get(i).getDogs(x));
							}
					}
				}
			if(choice==8){break;}
			System.out.println("Press 1 to keep interacting with this Human.Press 2 then enter to choose a new human. ");
			stop=input.nextInt();
			}while(stop !=2);
		}while(choice !=8);
		
	input.close();
	}
	

}
