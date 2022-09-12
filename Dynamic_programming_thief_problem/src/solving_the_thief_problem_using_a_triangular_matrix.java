public class solving_the_thief_problem_using_a_triangular_matrix {
	public static int [] myID= new int[] {3,1,8,8,7,1,2,8,2}; //my ID, the numbers represent weight and i can take maximum 3 items from each item (item is number from my ID)
	public static int row =6; // my row to presentation in the table, will show: several items i chose from specific item (0,1,2 or 3),
	//the value from each one of the row  will show how several items i can insert if i chose this option
	//X will present the best chose (0,1,2 or 3) 
	// F will show the value of the best chose
	// as well i added column "row" for help  
	public static int sum = 40; // sum of my ID


	public static void main(String[] args) {
		int [][][] my_arry=new int [myID.length][row][sum+1];//3D array for the solution 
		BuildFirstPage(my_arry); // function that build my first table 
		runAllOver(my_arry); // the function that calculate all the other table in the cube (3D arry) 
		print(my_arry); // function that print my tables 
		System.out.println("my id is:");
		System.out.println(318871282); //print my ID
		printWay(my_arry); //print the array under my ID and show how match take from every number (products) 
		System.out.println("my answer for the quetion is:"
				+ "defently no, if im dont search for best solution and just take what im thinkink"
				+ "im will never know what im loss from the options and i cant never be sure "
				+ "that my chuse is the best chose");
	}

	public static void BuildFirstPage(int[][][] my_arry) { //function that build the first table in the cube.
		for (int k=0;k<=40;k++)
		{
			int ArryPlace=0; // value for my Xi
			int MaxValue=0;	// value for my Fi 
			for (int j=0; j<4;j++)
			{
				if(k>=j*myID[8]) // check if the value in the bug its enough
					my_arry [8][j][k]=j; //if the weight in our back have the cost of that items 
				else
					my_arry [8][j][k]=0; 
				if(ArryPlace<my_arry [8][j][k])
				{
					MaxValue=my_arry [8][j][k]; //keep the value and the place
					ArryPlace=j;
				}

			}
			my_arry [8][4][k]=ArryPlace;
			my_arry [8][5][k]=MaxValue;
		}	
	}

	public static void runAllOver(int[][][] my_arry)
	{
		for (int i=row+1; i>=0;i--)
		{
			for(int k=0;k<=40;k++)
			{
				int ArryPlace=0; // cheek the value for my Xi
				int MaxValue=0;	// cheek the value in the options 
				int optimomValue=0;// select from all the MaxValue 
				for(int j=0;j<4;j++)
				{
					if(k>=j*myID[i]) 
					{	
						if(my_arry[i][j][k]<j+my_arry[i+1][5][k-(j*myID[i])]) //will check each option are beter, with or without
						{
							my_arry[i][j][k]=j+my_arry[i+1][5][k-(j*myID[i])]; //save the place
							if(MaxValue<j+my_arry[i+1][5][k-(j*myID[i])])
								MaxValue=j+my_arry[i+1][5][k-(j*myID[i])]; //keep the value 
						}
						else // alternative if the wave can take but it's still not the best offer
						{
							my_arry[i][j][k]=0; // its mean i don't take from this object
							MaxValue=my_arry[i+1][5][k];
						}
					}					
					if(MaxValue>optimomValue) //search the place and the value in every row for the max
					{
						optimomValue=MaxValue; 
						ArryPlace=j;
					}
				}
				my_arry [i][4][k]=ArryPlace; //save xi 
				my_arry [i][5][k]=MaxValue; // save Fi
			}
		}
	}




	public static void printWay (int [][][] my_arry){ //print my optimum way , how match take from any product 
		int x=0; //counting the value I'm use to memories the next step 
		int best_row=bestRow(my_arry);
		System.out.println("im will take:");
		for (int i=0;i<9;i++)
		{
			System.out.print(my_arry[i][4][best_row-x]);
			x=x+my_arry[i][4][best_row-x]*myID[i]; //calculate the next table 
		}
		System.out.println();
		System.out.println("the optimum value is:" + x);
		System.out.println("Si:"+x+"  " + "Xi="+my_arry[0][4][best_row]+" "+"Fi="+my_arry[0][5][best_row]);
	}
	
	
	public static int bestRow(int [][][] my_arry) {
	int max;
	int place=0;
	for (int i=0; i<40; i++) {
		if (my_arry[0][5][i+1]>my_arry[0][5][i])
			{
				max=my_arry[0][5][i+1];
				place=i+1;
			}
	}
	return place;
	}

	public static void  print(int[][][] my_arry) //function that print the tables
	{ 
		System.out.println("ootimom tables:");
		for (int i=8;i>=0;i--)
		{
			System.out.println("table number:" +i);
			System.out.println(" 0 1 2 3 X F row");

			for (int k=0;k<=40;k++)
			{
				for (int j=0; j<6;j++)
				{
					System.out.print(" "+ my_arry [i][j][k]);
				}
				System.out.print(" "+k+"   ");
				System.out.println();
			}

			System.out.println();
		}
	}
}
