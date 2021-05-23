
//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr 
//Τσούσης Παναγιώτης 	9605	pitsousis@ece.auth.gr

import java.util.Arrays;
import java.util.Random;
	
	public class Board {
	
		private int N, M;
		private int[][] tiles; 

		private int numOfSnakes;
		private int numOfLadders;
		private int numOfApples;
		
		private Ladder[] ladders;
		private Snake[] snakes;
		private Apple[] apples;
		
		public Board()
		{
			
		}
		public Board (int a, int b, int c, int d, int e)
		{
			N=a;
			M=b;
			numOfSnakes = c;
			numOfLadders = d;
			numOfApples = e;
			
		}
		
		public Board(Board b) {
			
			this.N = b.N;
			this.M=b.M;
			this.tiles=b.tiles; 
			
			this.numOfSnakes=b.numOfSnakes;
			this.numOfLadders=b.numOfLadders;
			this.numOfApples=b.numOfApples;
			
			Ladder[] tempL = new Ladder[this.numOfLadders];
			Snake[] tempS = new Snake[this.numOfSnakes];
			Apple[] tempA = new Apple[this.numOfApples];
			
			for(int i=0;i<b.ladders.length;i++) {
				Ladder l = b.ladders[i];
				if(l != null) {
					tempL[i]=new Ladder(l);
				}
			}
			
			for(int i=0;i<b.snakes.length;i++) {
				Snake s = b.snakes[i];
				if(s != null) {
					tempS[i]=new Snake(s);
				}
			}
			
			for(int i=0;i<b.apples.length;i++) {
				Apple a = b.apples[i];
				if(a != null) {
					tempA[i]=new Apple(a);
				}
			}
			
			this.ladders=tempL;
			this.snakes=tempS;
			this.apples=tempA;
			
		}
		
		public int[] getNandM(int x) //Συνάρτηση που δέχεται τον αριθμό ενός tile και 
		{					   	//επιστρέφει σε ποιά σειρά και στήλη βρίσκεται.
			int a=0,b=0;
			for(int i=0;i<N;i++) 
			{
				for(int j=0;j<M;j++) 
				{
					if(tiles[i][j]==x) 
					{
						a = i;
						b = j;
						break;
					}
				}
			}
			int[] results={a,b};
			return results;
		}
		
		public int getNumOfSnakes() {return numOfSnakes;}
		public int getNumOfLadders() {return numOfLadders;}
		public int getNumOfApples() {return numOfApples;}
		public Snake getSnake(int i) {return snakes[i];}							
		public Ladder getLadder(int i) {return ladders[i];}							
		public Apple getApple(int i) {return apples[i];}
		
		public int getTileId(int x,int y) {return tiles[x][y];}

		public void createTiles() {
			int k =N*M;					//Συνάρτηση που τοποθετεί με τη σωστή σειρά τα ids των tiles
			tiles=new int[N][M];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(N%2==0) 
					{
						if(i%2==0)
						{			//Συνθήκη δημιουργίας id των tiles σε μια σειρά του πίνακα κανονικά ή αντίστροφα, για να υπάρχει μια συνέχεια αριθμών.
							tiles[i][j]= k;
						}
						else 
						{
							tiles[i][M-1-j] = k;
						}
						k--;
					}
					else
					{
						if(i%2==1)
						{			//Συνθήκη δημιουργίας id των tiles σε μια σειρά του πίνακα κανονικά ή αντίστροφα, για να υπάρχει μια συνέχεια αριθμών.
							tiles[i][j]= k;
						}
						else 
						{
							tiles[i][M-1-j] = k;
						}
						k--;
					}
				}
			}
		}
		public void viewTiles() {		//Συνάρτηση που εμφανίζει τα tiles του ταμπλό
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					System.out.print(tiles[i][j] + "\t");
				}
				System.out.println();
			}
		}

		public void createElementBoard()
		{
			String[][] elementBoardSnakes;
			elementBoardSnakes= new String[N][M];
			for (int i=0; i<N; i++)		{Arrays.fill(elementBoardSnakes[i],"___");}//Αρχικοποίηση όλων των θέσεων του πίνακα με "___" (κάτω παύλες)
			for(int i=0;i<numOfSnakes;i++)
			{										//Τοποθέτηση των SH και ST στις σωστές θέσεις του πίνακα elementBoardSnakes
				int N1[]=getNandM(snakes[i].getHeadId());
				elementBoardSnakes[N1[0]][N1[1]]="SH"+i;
				int N2[]=getNandM(snakes[i].getTailId());
				elementBoardSnakes[N2[0]][N2[1]]="ST"+i;
			}
			
			
			System.out.println("SNAKE BOARD");
			
			for(int i=0;i<N;i++) 
			{						//Εμφανίζει τον πίνακα των φιδιών
				for(int j=0;j<M;j++) 
				{
					System.out.print(elementBoardSnakes[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();

			String[][] elementBoardLadders;
			elementBoardLadders= new String[N][M];
			for (int i=0; i<N; i++)		{Arrays.fill(elementBoardLadders[i],"___");} //Αρχικοποίηση όλων των θέσεων του πίνακα με "___" (κάτω παύλες)
			for(int i=0;i<numOfLadders;i++)
			{										//Τοποθέτηση των LU και LD στις σωστές θέσεις του πίνακα elementBoardLadders
				int N1[]=getNandM(ladders[i].getUpStepId());
				elementBoardLadders[N1[0]][N1[1]]="LU"+i;
				int N2[]=getNandM(ladders[i].getDownStepId());
				elementBoardLadders[N2[0]][N2[1]]="LD"+i;
			}

			
			System.out.println("LADDER BOARD");
			
			for(int i=0;i<N;i++) {						//Εμφανίζει τον πίνακα των σκαλών
				for(int j=0;j<M;j++) {
					System.out.print(elementBoardLadders[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			String[][] elementBoardApples;
			elementBoardApples= new String[N][M];
			for (int i=0; i<N; i++)		{Arrays.fill(elementBoardApples[i],"___");}//Αρχικοποίηση όλων των θέσεων του πίνακα με "___" (κάτω παύλες)
			for(int i=0;i<numOfApples;i++)
			{
				if(apples[i].getColor()=="red")	//Έλεγχος χρώματος μήλου.
				{
					int N1[]=getNandM(apples[i].getAppleTileId());
//					int M1=getM(apples[i].getAppleTileId(),N1);
					elementBoardApples[N1[0]][N1[1]]="AR"+i;	//Τοποθέτηση κόκκινου μήλου
				}
				if(apples[i].getColor()=="black")
				{
					int N2[]=getNandM(apples[i].getAppleTileId());
//					int M2=getM(apples[i].getAppleTileId(),N2);
					elementBoardApples[N2[0]][N2[1]]="AB"+i;	//Τοποθέτηση μαύρου μήλου
				}
			}
			
			System.out.println("APPLE BOARD");
			
			for(int i=0;i<N;i++) {						//Εμφανίζει τον πίνακα των μήλων
				for(int j=0;j<M;j++) {
					System.out.print(elementBoardApples[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}		

		public void createBoard() 
		{
			createTiles();		//Επικάλεση της συνάρτησης που τοποθετεί τα id των πλακιδίων στον πίνακα tiles
			int randN1=0,randM1=0,randN2=0,randM2=0;
			Random randA=new Random();
			Random randB=new Random();
			
			String[][] BoardSnakes;
			BoardSnakes = new String [N][M];
			
			snakes= new Snake[getNumOfSnakes()];
			
														//Αρχικοποίηση όλων των στοιχείων του πίνακα φιδιών με κάτω παύλες
			for (int i=0; i<N; i++)		{Arrays.fill(BoardSnakes[i],"___");}
			
			for (int i=0; i<numOfSnakes; i++) 
			{
				snakes[i]=new Snake(i+1,i+1,i+1);
				
				// Δημιουργεί τυχαίο ακέραιο από το 0 έως το N-1 και έναν από το 0 έως το Μ-1
				do
				{
					randN1=randA.nextInt(N);
					randM1=randB.nextInt(M);
				}while(BoardSnakes[randN1][randM1]!="___" || (getTileId(randN1,randM1)==200));
				
				BoardSnakes[randN1][randM1]="SH"+i;  //Τοποθετεί το κεφάλι του φιδιού
				snakes[i].setHeadId(getTileId(randN1,randM1));													
				
				do
				{
					randN2=randN1+randA.nextInt(N-randN1);
					randM2=randB.nextInt(M);
				}while(BoardSnakes[randN2][randM2]!="___"||randN2==randN1);
				BoardSnakes[randN2][randM2]="ST"+i; //Τοποθετεί την ουρά του φιδιού
				snakes[i].setTailId(getTileId(randN2,randM2));	
				
			}
		
			String[][] BoardLadders;
			BoardLadders = new String [N][M];
			
			ladders= new Ladder[getNumOfLadders()];
			
														//Αρχικοποίηση όλων των στοιχείων του πίνακα σκαλών με κάτω παύλες.		
			for (int i=0; i<N; i++)		{Arrays.fill(BoardLadders[i],"___");}
			
			for (int i=0; i<numOfLadders; i++) 
			{
				ladders[i]=new Ladder(i+1,i+1,i+1);															
				
						// Δημιουργεί τυχαίο ακέραιο από το 0 έως το N-1 και έναν από το 0 έως το Μ-1
				do
				{
					randN1=randA.nextInt(N);
					randM1=randB.nextInt(M);
				}while(BoardLadders[randN1][randM1]!="___"||BoardSnakes[randN1][randM1]!="___" ||(getTileId(randN1,randM1))==200 );
				
				BoardLadders[randN1][randM1]="LU"+i;  //Τοποθετεί το πάνω μέρος της σκάλας
				ladders[i].setUpStepId(getTileId(randN1,randM1));	
				
				do
				{
					randN2=randN1+randA.nextInt(N-randN1);
					randM2=randB.nextInt(M);
				}while(BoardLadders[randN2][randM2]!="___"||randN2==randN1||BoardSnakes[randN2][randM2]!="___"||(getTileId(randN2,randM2)==1));
					
				BoardLadders[randN2][randM2]="LB"+i; //Τοποθετεί τη βάση της σκάλας
				ladders[i].setDownStepId(getTileId(randN2,randM2));
				
			}
			
			String[][] BoardApples;
			BoardApples = new String [N][M];
			
			Random rand=new Random();
			int randN=0,randM=0;
			
			apples=new Apple[getNumOfApples()];
			
			for (int i=0; i<N; i++)		{Arrays.fill(BoardApples[i], "___");}//Αρχικοποίηση όλων των στοιχείων του πίνακα σκαλών με κάτω παύλες.
			
			for (int i=0; i<numOfApples; i++) {		
				
				apples[i]=new Apple(i+1,i+1,"white");

				int appleColor=rand.nextInt(2); //Δίνει μία τυχαία 0 ή 1 στην μεταβλητή και έτσι επιλέγεται το χρώμα
				
				do
				{
					randN=randA.nextInt(N);
					randM=randB.nextInt(M);
				}while(BoardApples[randN][randM]!="___"||(getTileId(randN,randM)==1));

				apples[i].setAppleTileId(getTileId(randN,randM));
				if(appleColor==0)		{							// Το μηδέν (0) δίνει το ΚΟΚΚΙΝΟ χρώμα
					BoardApples[randN][randM]="AR"+i;
					apples[i].setColor("red");
				}
				else if(appleColor==1) 	{						// Το ένα (1) δίνει το ΜΑΥΡΟ χρώμα
					BoardApples[randN][randM]="AB"+i;
					apples[i].setColor("black");
				}
			}		
		}
	}





