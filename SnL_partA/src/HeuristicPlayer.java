//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr
//Τσούσης Παναγιώτης 	9590	pitsousis@ece.auth.gr

import java.util.ArrayList;

public class HeuristicPlayer extends Player {
	
	ArrayList<int[]> path = new ArrayList<int[]>(); //πληροφοιες για καθε γυρο με σειρα round,die,steps,points,snake,ladder,red,black
	
	HeuristicPlayer(int a,String b,Board c)
	{
		super(a,b,c);
	}
	
	//συναρτηση αξιολογησης κινησης για συγκεκριμενο ζαρι
	double evaluate(int currentPos, int dice)
	{
		int[] results=new int[2];
		results = super.checkMove(currentPos, dice);
		return super.target(results[0]-currentPos,results[1]);
	}
	
	//Συναρτηση επιλογης κινησης, με βαση την αξιολογηση
	int getNextMove(int currentPos,int round) //ορισμα round, για αποθηκευση στην path
	{
		double[] results = new double[6]; //δημιουργια πινακα 6 θεσεων, μια για καθε ζαρια
		for(int i=1;i<7;i++) //επαναληψη, οπου i ειναι η ζαρια
		{
			results[i-1] = evaluate(currentPos,i) ; //τοποθετηση αξιολογησης στον πινακα
		}
		
		double max=0; //η μεγιστη αξιολογηση
		int maxCounter=0; //το ζαρι με τη μεγιστη αξιολογηση
		
		for (int counter = 0; counter < results.length; counter++) //ευρεση ζαριας με μεγιστη αξιολογηση
		{
		     if (results[counter] > max)
		     {
		      max = results[counter]; //Το αποτέλεσμα της συναρτησης στόχου
		      maxCounter=counter+1;	//O αριθμός του ζαριού
		     }
		}
		
		
		int[] stats = new int[7]; //πινακας 5 θεσεων, για την αποθηκευση των 5 μεταβλητων απο την αρχικη move
		
		stats = super.move(currentPos, maxCounter,round,this.getBoard(),0); // αποθηκευση μεταβλητων της move
		
		//στατιστικα κινησης round,die,steps,points,snake,ladder,red,black
		int[] pathStats = {round,maxCounter,stats[0]-currentPos,stats[3]*5-stats[4]*5,stats[1],stats[2],stats[3],stats[4]};

		path.add(pathStats);
		
		return pathStats[1]; //επιστροφη τελικης θεσης του παικτη
		
	}
	
	//Στατιστικα για το γυρο του Heuristic Player
	public void statistics() {
		char c=176;//η τελιτσα απο πινακα ASCII
		
		int lastRound = path.size() - 1; //ξεκιναμε απο γυρο 0
		
		System.out.println("The Heuristic player, in round " + (lastRound+1) + " set the die to " + path.get(lastRound)[1]);

		if(path.get(lastRound)[5] != 0) {
			System.out.println(c+"   climbed 1 ladder. ");
		}
		if(path.get(lastRound)[6] != 0) {
			System.out.println(c+"   ate " + path.get(lastRound)[6] + " red apple(s).");
		}
		if(path.get(lastRound)[7] != 0) {
			System.out.println(c+"   ate " + path.get(lastRound)[7] + " black apple(s).");
		}
	}
	
	//Συναρτηση move του HeuristicPlayer που κανει override τη move του Player
	public int[] move(int a,int b, int c,Board board,int d) {  //tile,die,round,board
		int die=getNextMove(a,c);
		//επιστροφη κενου πινακα, απλα για να ταιριαζει ο τυπος επιστροφης με τη move του player
		int[] p=new int[7];
		p[6] = die;
		return p;
	}
	
}





