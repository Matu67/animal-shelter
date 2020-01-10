package application;

import java.util.List;

public class Dog {
	private String name;
	private int age;
	private String gender;
	private String breed;
	private String color;
	
	public Dog(String[] breeds, List<String> dogMaleNames, List<String> dogFemaleNames)
	{
		// Initialize gender choice
		int genderChoice = (int)(Math.random() * 2);
		
		// If male, choose male name. Same with female
		if (genderChoice == 0){
			gender = "Neutered Male";
			name = dogMaleNames.get((int)(Math.random() * dogMaleNames.size()));
		} else {
			gender = "Spayed Female";
			name = dogFemaleNames.get((int)(Math.random() * dogFemaleNames.size()));
		}
		
		// Loop and try and get a name that hasn't been used. Try this for 50 times, and if it fails, generate a new dog list
		int tryCounter = 0;
		do {
			tryCounter++;
			int breedChoice = (int)(Math.random() * MainController.dogChoices.size());
			breed = breeds[breedChoice];
			int index = (int) (Math.random() * MainController.dogChoices.get(breeds[breedChoice]).size());
			color = MainController.dogChoices.get(breed).set(index, null);
		} while (color == null && tryCounter < 50);
		
		// Make age from 1 to 10
		age = (int)((Math.random() * 10) + 1);
	}
	
	public String getBreed()
	{
		return breed;
	}
	public String getColor()
	{
		return color;
	}
	public String getName()
	{
		return name;
	}
	public int getAge()
	{
		return age;
	}
	public String getGender()
	{
		return gender;
	}
	
	public String toString()
	{
		return name + "\n" + age + "\n" + gender + "\n" + breed + "\n" + color;
	}
}
