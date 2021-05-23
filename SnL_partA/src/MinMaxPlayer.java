//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr
//Τσούσης Παναγιώτης 	9590	pitsousis@ece.auth.gr

import java.util.ArrayList;

public class MinMaxPlayer extends Player {

	public MinMaxPlayer(){
		super();
	}
	
	public MinMaxPlayer(int a,String b,Board c) {
		super(a,b,c);
	}
	
	ArrayList<int[]> path = new ArrayList<int[]>(); //πληροφοιες για καθε γυρο με σειρα round,die,steps,points,snakes,ladders,reds,blacks
	
	
	//συναρτηση αξιολογησης κινησης για συγκεκριμενο ζαρι
	public double evaluate(int currentPos, int dice, Board board)
	{
		int[] results=new int[2];
		results = super.checkMove(currentPos, dice);
		return super.target(results[0]-currentPos,results[1]);
	}
	
	//Συναρτηση επιλογης κινησης, με βαση την αξιολογηση
//	public int getNextMove(int currentPos,int round, Board board) //ορισμα round, για αποθηκευση στην path
//	{
//		double[] results = new double[6]; //δημιουργια πινακα 6 θεσεων, μια για καθε ζαρια
//		for(int i=1;i<7;i++) //επαναληψη, οπου i ειναι η ζαρια
//		{
//			results[i-1] = evaluate(currentPos,i,board) ; //τοποθετηση αξιολογησης στον πινακα
//		}
//		
//		double max=0; //η μεγιστη αξιολογηση
//		int maxCounter=0; //το ζαρι με τη μεγιστη αξιολογηση
//		
//		for (int counter = 0; counter < results.length; counter++) //ευρεση ζαριας με μεγιστη αξιολογηση
//		{
//		     if (results[counter] > max)
//		     {
//		      max = results[counter]; //Το αποτέλεσμα της συναρτησης στόχου
//		      maxCounter=counter+1;	//O αριθμός του ζαριού
//		     }
//		}
//		
//		int[] stats = new int[5]; //πινακας 5 θεσεων, για την αποθηκευση των 5 μεταβλητων απο την αρχικη move
//		
//		stats = super.move(currentPos, maxCounter,round); // αποθηκευση μεταβλητων της move
//		
//		//στατιστικα κινησης round,die,steps,points,snake,ladder,red,black
//		int[] pathStats = {round,maxCounter,stats[0]-currentPos,stats[3]*5-stats[4]*5,stats[1],stats[2],stats[3],stats[4]};
//
//		path.add(pathStats);
//		
//		return stats[0]; //επιστροφη τελικης θεσης του παικτη
//	}
	
	//Στατιστικα για το γυρο
	public void statistics() {
		char c=176;//η τελιτσα απο πινακα ASCII
		
		int lastRound = path.size() - 1; //πρώτος γύρος = 1
		
		System.out.println("The MinMax player, in round " + (lastRound+1) + " set the die to " + path.get(lastRound)[1]);

		if(path.get(lastRound)[5] != 0) {
			System.out.println(c+"   climbed 1 ladder. ");
		}
		if(path.get(lastRound)[6] != 0) {
			System.out.println(c+"   ate " + path.get(lastRound)[6] + " red apple(s).");
			
		}
		if(path.get(lastRound)[7] != 0) {
			System.out.println(c+"   ate " + path.get(lastRound)[7] + " black apple(s).");
		}
		score = getTotalScore();
	}
	
	//Συναρτηση move που κανει override τη move του Player
	public int[] move(int a,int b, int c, Board board,int d) {  //tile,die,round,board,opponent tile
		int die;
		die=getNextMove(a,d,c, board);
		//επιστροφη κενου πινακα, απλα για να ταιριαζει ο τυπος επιστροφης με τη move του player
		int[] p=new int[7];
		p[6]=die;
		return p;
	}
	
	
	public int getTotalScore() {
		int totalScore = 0;
		for(int i=0; i<path.size(); i++) {
			totalScore = totalScore + path.get(i)[3];
		}
		return totalScore;
}


	public int getNextMove(int currentPos,int opponentCurrentPos,int round, Board board) 
	{
		Node root = new Node(board, 0);
		createMySubTree(root,(root.getNodeDepth()+1) ,currentPos,opponentCurrentPos);
		int die = chooseMinMaxMove(root);
		
		int[] stats = new int[7]; //πινακας 5 θεσεων, για την αποθηκευση των 5 μεταβλητων απο την αρχικη move
		
		stats = super.move(currentPos, die,round,board,opponentCurrentPos); // αποθηκευση μεταβλητων της move
		
		//στατιστικα κινησης round,die,steps,points,snake,ladder,red,black
		int[] pathStats = {round,die,stats[0]-currentPos,(stats[3]*5)-(stats[4]*5),stats[1],stats[2],stats[3],stats[4]};
		int redEat=stats[3];	redEat = redEat*5;
		int blackEat = stats[4]; blackEat = blackEat*(-5);
		int totalScore = (redEat-blackEat);
		this.setScore(totalScore);
		pathStats[3] = totalScore;
		path.add(pathStats);
		
		return die; 
	}
	
	public void createMySubTree(Node parent, int depth, int currentPos, int opponentCurrentPos) {

		double[] results = new double[6]; //δημιουργια πινακα 6 θεσεων, μια για καθε ζαρια
		for(int i=1;i<7;i++) //επαναληψη, οπου i ειναι η ζαρια
		{
			Board boardClone = new Board(parent.getNodeBoard());
			super.setBoard(boardClone);
			results[i-1] = evaluate(currentPos,i,boardClone) ;
			super.move(currentPos, i, 0,boardClone,opponentCurrentPos);
			Node child = new Node(boardClone, depth);
			child.setNodeEvaluation(results[i-1]);
			int[] moveBoard = {super.getTile(),i};		//πινακας για την ανανεωση της μεταβλητης nodeMove του κομβου
			child.setNodeMove(moveBoard);
			parent.addChild(child);
			
			createOpponentSubTree(child, (child.getNodeDepth()+1), currentPos, opponentCurrentPos, child.getNodeEvaluation());
		}
		super.setBoard(parent.getNodeBoard());
		
	}
	
	public void createOpponentSubTree(Node parent, int depth, int currentPos, int opponentCurrentPos, double parentEval) {
		
		double[] results = new double[6]; //δημιουργια πινακα 6 θεσεων, μια για καθε ζαρια
		double[] evaluation = new double[6];
		
		for(int i=1;i<7;i++) //επαναληψη, οπου i ειναι η ζαρια
		{
			Board boardClone = new Board(parent.getNodeBoard());
			super.setBoard(boardClone);
			results[i-1] = evaluate(opponentCurrentPos,i,boardClone) ; //***
			super.move(opponentCurrentPos, i, 0,boardClone,opponentCurrentPos);					   //***
			Node child = new Node(boardClone, depth);
			child.setNodeEvaluation(results[i-1]);
			int[] moveBoard = {super.getTile(),i};		//πινακας για την ανανεωση της μεταβλητης nodeMove του κομβου
			child.setNodeMove(moveBoard);
			parent.addChild(child);	
			
			evaluation[i-1] = (parent.getNodeEvaluation()-child.getNodeEvaluation());
		}
	}
	
	
	public int chooseMinMaxMove(Node root){
		
		
		System.out.println("Running MiniMax algorithm...");
		double max1 = -1000;	//Η μεγιστη (θετικα) διαφορα του παικτη minMax, αν σε καθε περιπτωση ο αντιπαλος κανει την καλυτερη του κινηση 
		double max2 = -1000;	//Καλυτερη κινηση του αντιπαλου για καθε κομβο δικο μας (πιθανη κινηση μας)
		int die = -1;
		
		
		
		for(int i=0; i < root.getChildren().size();i++) {
			Node crNode1 = root.getChildren().get(i);			//Current Node, παιδι του root
			for(int j=0; j < crNode1.getChildren().size(); j++) {
				Node crNode2 = crNode1.getChildren().get(j);	//Current Node, παιδι του παιδιου του root
				
				if(crNode2.getNodeEvaluation() > max2) {
					max2 = crNode2.getNodeEvaluation();			//Καλυτερη κινηση του αντιπαλου για καθε κομβο δικο μας (πιθανη κινηση μας)
				}
				
			}
		
			if(max1 < crNode1.getNodeEvaluation()-max2) {
				max1 = crNode1.getNodeEvaluation()-max2;  		//Η μεγιστη (θετικα) διαφορα του παικτη minMax, αν σε καθε περιπτωση ο αντιπαλος κανει την καλυτερη του κινηση 
				die = i+1;
			}
			
			max2 = -1000;
			
		}
		return die;
	}
	
	public void analyseNode(Node root) {
		System.out.println();
		System.out.println();
		System.out.println("Root Node info:");
		System.out.println("\t Depth: " + root.getNodeDepth());
		System.out.print("\t Number of children: ");
		
		
		if(root.getChildren() != null) {
			System.out.println(root.getChildren().size());
			System.out.println();
			System.out.println();
			
			for(int i=0; i < root.getChildren().size();i++) {
				Node crNode = root.getChildren().get(i); 		//Current node
				System.out.println("Child " + (i+1) + " info:");
				System.out.println("\t Depth: " + crNode.getNodeDepth());
				System.out.println("\t Node Die - Tile: " + crNode.getNodeMove()[1] + " - " + crNode.getNodeMove()[0] );
				System.out.println("\t Node evaluation: " + crNode.getNodeEvaluation());
				System.out.println("\t Number of childer: " + crNode.getChildren().size() );
				System.out.println();
				System.out.println();
			}
			
			for(int i=0; i < root.getChildren().size();i++) {
				Node crNode1 = root.getChildren().get(i);
				for(int j=0; j < crNode1.getChildren().size(); j++) {
					Node crNode2 = crNode1.getChildren().get(j);
					System.out.println("Child " + (i+1) + "." + (j+1) + " info:");
					System.out.println("\t Depth: " + crNode2.getNodeDepth());
					System.out.println("\t Node Die - Tile: " + crNode2.getNodeMove()[1] + " - " + crNode2.getNodeMove()[0] );
					System.out.println("\t Node evaluation: " + crNode2.getNodeEvaluation());
//					System.out.println("\t eval of Node " + (i+1) + " - Node " + (i+1) + "." + (j+1) + ": " + (crNode1.getNodeEvaluation() - crNode2.getNodeEvaluation()));
					System.out.println("\t Number of childer: " + crNode2.getChildren().size() );
					System.out.println();
					System.out.println();
				}
			}
		}
		
		else {
			System.out.println(0);
		}
		
	}
	
}
