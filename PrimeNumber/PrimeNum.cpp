/* Program that reads prime numbers from a file
By: Chad Nelson
cs1250 spring 2013*/ 

#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;



bool prime_test(double number); //Prototype


int main()
{
int count = 0;
int number;

ifstream inputFile;
inputFile.open("numbers.txt");

while ( inputFile >> number)
{
if(prime_test(number)){
count++;
}
}

cout << "There are " << count << " prime numbers in this file." << endl;

inputFile.close();

return 0;
}
// function to check to see if the number is prime
bool prime_test(double number)
{
int number2;
number = sqrt(number);
number2 = number;
if ( number == number2 )
		{
		return true;
		}
	return false;
}