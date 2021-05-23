
//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr 
//Τσούσης Παναγιώτης 	9590	pitsousis@ece.auth.gr	

import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Game {
	
	private static int round=0;
	private static int maxRounds = 20;  //το οριο γυρων του παιχνιδιου
	
	public static void main(String[] args) {
		//δημιουργια ταμπλο
		Board b1 = new Board(20,10,3,3,6);
		b1.createBoard();
		b1.createElementBoard();
		
		GUI graphics = new GUI();
		graphics.GUICreate(b1);
		
		while(graphics.op.getContGen()==false) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//δημιουργια παικτων
		Player p1 = new Player();
		Player minMaxPlayer = new Player();
		
		if((String)graphics.op.cb1.getSelectedItem() == "Random Player") {
			p1 = new Player(1,"Normal",b1);
		}
		else if((String)graphics.op.cb1.getSelectedItem() == "Heuristic Player") {
			p1 = new HeuristicPlayer(1,"Heuristic",b1);
		}
		else if((String)graphics.op.cb1.getSelectedItem() == "MinMax Player") {
			p1 = new MinMaxPlayer(1,"MinMax Player",b1);
		}
		
		
		if((String)graphics.op.cb2.getSelectedItem() == "Random Player") {
			minMaxPlayer = new Player(2,"Normal",b1);
		}
		else if((String)graphics.op.cb2.getSelectedItem() == "Heuristic Player") {
			minMaxPlayer = new HeuristicPlayer(2,"Heuristic",b1);
		}
		else if((String)graphics.op.cb2.getSelectedItem() == "MinMax Player") {
			minMaxPlayer = new MinMaxPlayer(2,"MinMax Player",b1);
		}
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(minMaxPlayer);
		
		while(graphics.op.getContPlay()==false) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		Random rand1 = new Random();
		//δημιουργια χαρτη για τους γυρους
		Map<Integer, Integer> turns = new TreeMap<Integer, Integer>();
		turns=setTurns(players,graphics.op);
		
		int first=(turns.get(((TreeMap<Integer, Integer>) turns).firstKey()))-1; // καθορισμος πρωτου παικτη (0 η 1) αναλογα με ποιο ειναι το πρωτο ID στο χαρτη 
		int second=1-first;		 //καθορισμος δευτερου παικτη (0 η 1) αναλογα τον πρωτο
		
		
		System.out.println("GAME STARTS NOW!");
		int die1,die2;
		
		do {

			while(graphics.op.getCont()==false) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			int tile1before = players.get(first).getTile();
			int tile2before = players.get(second).getTile();
			die1=players.get(first).move(players.get(first).getTile(),rand1.nextInt(6)+1,getRound(),b1,players.get(second).getTile())[6]; //κινηση πρωτου παικτη
			players.get(first).statistics(); //στατιστικα κινησης
			
			die2=players.get(second).move(players.get(second).getTile(),rand1.nextInt(6)+1,getRound(),b1,players.get(first).getTile())[6]; //κινηση δευτερου παικτη
			players.get(second).statistics(); //στατιστικα κινησης
			
			if(p1.getTile()>200) {p1.setTile(200); }
			if(minMaxPlayer.getTile()>200) {minMaxPlayer.setTile(200); }
			
			if(first == 0) {
				graphics.op.l12.setText("<html><span style='font-size:12px'>"+"Move Score: "+ players.get(first).target((players.get(first).getTile()-tile1before), (players.get(first).redEat*5 - players.get(first).blackEat*5)) +"</span></html>");
				graphics.op.l13.setText("<html><span style='font-size:12px'>"+"Total Score: "+ ((players.get(first).target(players.get(first).getTile(), players.get(first).getScore()) )-0.7)+"</span></html>");
				graphics.op.player1.setBounds(getCoordinates(players.get(first).getTile())[0]+2, getCoordinates(players.get(first).getTile())[1]+4, 30, 38);
			}
			else {
				graphics.op.l22.setText("<html><span style='font-size:12px'>"+"Move Score: "+ players.get(first).target((players.get(first).getTile()-tile1before), (players.get(first).redEat*5 - players.get(first).blackEat*5)) +"</span></html>");
				graphics.op.l23.setText("<html><span style='font-size:12px'>"+"Total Score: "+ ((players.get(first).target(players.get(first).getTile(), players.get(first).getScore()))-0.7)+"</span></html>");
				graphics.op.player2.setBounds(getCoordinates(players.get(first).getTile())[0]+35, getCoordinates(players.get(first).getTile())[1]+4, 30, 38);
			}
			
			if(first == 0) {
				graphics.op.l22.setText("<html><span style='font-size:12px'>"+"Move Score: "+ players.get(second).target((players.get(second).getTile()-tile2before), (players.get(second).redEat*5 - players.get(second).blackEat*5)) +"</span></html>");
				graphics.op.l23.setText("<html><span style='font-size:12px'>"+"Total Score: "+ ((players.get(second).target(players.get(second).getTile(), players.get(second).getScore()))-0.7)+"</span></html>");
				graphics.op.d2.setText("<html><span style='font-size:12px'>"+"Player B rolls the die: "+die2+"</span></html>");
				graphics.op.d1.setText("<html><span style='font-size:12px'>"+"Player A rolls the die: "+die1+"</span></html>");
				graphics.op.player2.setBounds(getCoordinates(players.get(second).getTile())[0]+35, getCoordinates(players.get(second).getTile())[1]+4, 30, 38);
			}
			else {
				graphics.op.l12.setText("<html><span style='font-size:12px'>"+"Move Score: "+ players.get(second).target((players.get(second).getTile()-tile2before), (players.get(second).redEat*5 - players.get(second).blackEat*5)) +"</span></html>");
				graphics.op.l13.setText("<html><span style='font-size:12px'>"+"Total Score: "+ ((players.get(second).target(players.get(second).getTile(), players.get(second).getScore()))-0.7)+"</span></html>");
				graphics.op.d1.setText("<html><span style='font-size:12px'>"+"Player B rolls the die: "+die1+"</span></html>");
				graphics.op.d2.setText("<html><span style='font-size:12px'>"+"Player A rolls the die: "+die2+"</span></html>");
				graphics.op.player1.setBounds(getCoordinates(players.get(second).getTile())[0]+2, getCoordinates(players.get(second).getTile())[1]+4, 30, 38);
			}
			
			
			setRound(getRound() + 1); // αυξηση γυρου
			graphics.getRound().setText("<html><span style='font-size:16px'>"+"Rounds: "+ getRound() +"</span></html>");

			System.out.println("____________________________________________");
			
			for(int i=0;i<6;i++) {
				if(b1.getApple(i).getPoints()==0) {
					graphics.op.apples[i].setVisible(false);
				}
			}
			
			graphics.op.setCont(false);
			
			
			
		}while(p1.getTile()<200 && minMaxPlayer.getTile()<200 && round < maxRounds ); //προυποθεσεις για να συνεχιζεται το παιχνιδι
		graphics.op.b4.setEnabled(false);
		graphics.op.gameOver.setText("<html><span style='font-size:16px'>"+"Game Over! Winner: Player "+ endCredits(p1,minMaxPlayer) + "</span></html>");
		graphics.op.gameOver.setVisible(true);
		
		
		
	}
	
	public static int getRound() {
		return round;
	}

	public static void setRound(int a) {
		round = a;
	}
	//ΔΕ ΓΙΝΕΤΑΙ ΧΡΗΣΗ ΤΗΣ ΣΥΝΑΡΤΗΣΗΣ ΑΥΤΗΣ ΣΤΟΝ ΚΩΔΙΚΑ, ΕΙΝΑΙ ΤΗΣ ΠΡΟΗΓΟΥΜΕΝΗΣ ΕΡΓΑΣΙΑΣ
	public static int firstPlayer() { 	
		int die1,die2;					//Συνάρτηση που συγκρίνει την πρώτη ζαρία των 2 παικτών για να 
		Random rand=new Random();		//επιστρέψει το ποιός αρχίζει.
		die1=rand.nextInt(6)+1;
		die2=rand.nextInt(6)+1;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Player 1 rolls the die: " + die1);
		System.out.println("Player 2 rolls the die: " + die2);
		if(die1>die2) {
			System.out.println("Player 1 starts!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			return 0;
		}
		else if(die1<die2) {
			System.out.println("Player 2 starts!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			return 1;
		}
		else {
			return firstPlayer();
		}
		
	}
	//Συναρτηση καθορισμου σειρας που θα παιζουν οι παικτες
	public static Map<Integer,Integer> setTurns(ArrayList<Player> arList,Options options){
		Map<Integer, Integer> map = new TreeMap<Integer,Integer>(); 
		
		int die1,die2;					 
		Random rand=new Random();		
		
		do {
			
			die1=rand.nextInt(6)+1;			//ζαριες των 2 παικτων
			die2=rand.nextInt(6)+1;
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Player 1 rolls the die: " + die1);
			options.die1.setText("<html><span style='font-size:12px'>"+"Player A die: "+ die1 +"</span></html>");
			System.out.println("Player 2 rolls the die: " + die2);
			options.die2.setText("<html><span style='font-size:12px'>"+"Player B die: "+ die2 +"</span></html>");
			
			//εμφανιση σωστου μηνυματος και τοποθετηση τιμων στο map 
			if(die1<die2) {		
				map.put(die1,arList.get(0).getPlayerId());
				map.put(die2,arList.get(1).getPlayerId());
				System.out.println("Player 1 starts!");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				options.startPlayer.setText("<html><span style='font-size:12px'>"+"Starts first: A" +"</span></html>");
			}
			else if(die1>die2) {
				map.put(die1,arList.get(0).getPlayerId());
				map.put(die2,arList.get(1).getPlayerId());
				System.out.println("Player 2 starts!");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				options.startPlayer.setText("<html><span style='font-size:12px'>"+"Starts first: B" +"</span></html>");
			}
		}while(die1==die2);

		return map;
	}
		
	public static String endCredits(Player p1,Player hplayer) {	//στατιστικα για το τελος του παιχνιδιου
		System.out.println("Ending tile of players:");
		if(hplayer.getTile()>200) {hplayer.setTile(200); }
		if(p1.getTile()>200) {hplayer.setTile(200); }
		System.out.println("Player 1 tile: "+p1.getTile());
		System.out.println("Player 2 tile: "+ hplayer.getTile());
		
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Number of rounds: " + getRound());
		System.out.println("Player 1 score: "+ p1.getScore());
		System.out.println("Player 2 score: "+hplayer.getScore());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		if(p1.getTile()>=200) {
			System.out.println("Winner: Player 1" +" ("+p1.getName()+")");
			System.out.println("Method: Reached the end!");
			return "A";
		}
		else if(hplayer.getTile()>=200) {
			System.out.println("Winner: Player 2"+" ("+hplayer.getName()+")");
			System.out.println("Method: Reached the end!");
			return "B";
		}
		else if(hplayer.target(hplayer.getTile(),hplayer.getScore()) > (p1.target(p1.getTile(), p1.getScore()))) {
			System.out.println("Winner: Player 2"+" ("+hplayer.getName()+")");
			System.out.println("Method: Higher target score at the end of turns: " + hplayer.target(hplayer.getTile(),hplayer.getScore()));
			System.out.println("Player 1 target score: " + p1.target(p1.getTile(), p1.getScore()));
			return "B";
		}
		else {
			System.out.println("Winner: Player 1"+" ("+p1.getName()+")");
			System.out.println("Method: Higher target score at the end of turns: " + p1.target(p1.getTile(),p1.getScore()));
			System.out.println("Player 2 target score: " + hplayer.target(hplayer.getTile(), hplayer.getScore()));
			return "A";
		}
	}
	public static int[] getCoordinates(int tileId) {
		int[] coos = new int[2];
		int k = (tileId-1)/10;
		int row = k+1;
		int column = -1;
		if(row%2==1) {
			column = (tileId%10);
			if(column == 0) {column = 10;}
		}
		else if(row%2==0) {
			column = 11 - (tileId%10);
			if (column==11) {column=1;}
		}
		int yCo = 100 + (20-row) * (880/20);
		int xCo = 300 + (700/10) * (column - 1);
		coos[0]=xCo;
		coos[1]=yCo;
		return coos;
	}
	
}
 