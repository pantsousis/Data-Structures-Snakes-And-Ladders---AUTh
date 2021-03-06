//??????? ????????? 	9605 	dzsamaras@ece.auth.gr 
//??????? ?????????? 	9590	pitsousis@ece.auth.gr

public class Player implements Cloneable {
	
	private int playerId;
	private String name;
	public int score = 0;
	private Board board;
	private int tile =1;		//?? ???????? ??? ????????? ? ??????? ??? ???? ??????
	
	//???????? ??? ??????????, ?????? ??? ????? ?? ???? ????
	private int snakeBite=0;
	private int ladderClimb=0;
	public int redEat = 0;
	public int blackEat =0;
	
	public Player(){
		
	}
	
	public Player(int a,String b,Board c){
		playerId=a;
		name=b;
		board = c;
	}
	
	public int getPlayerId() {	return playerId;}
	public String getName()	{	return name;}
	public int getScore() {	return score;}
	public Board getBoard() {	return board;}
	public int getTile() { return tile;}
	
	public void setPlayerId(int a) { playerId =a;}
	public void setName(String a) { name=a;}
	public void setScore(int a) {score = a;}
	public void setBoard(Board a) {	board = a;}
	public void setTile(int a) {tile = a;}
	
	public int[] move(int id,int die,int round,Board board,int d) {
		//?????????? global ????????
		snakeBite=0;
		ladderClimb=0;
		redEat = 0;
		blackEat =0;
		id += die;		//?????? ??? ????????? ???? ?? ?????
		
		boolean a,s,l;
		do {
			//??????? ???????? ??? ????,????? ??? ??????, ??????? ????? ???? ??? loop do-while
			a=false;
			s=false;
			l=false;
			
			for(int i = 0; i < board.getNumOfApples();i++) {	//loop ??? ?????? ????? ??? ??? ????????
				if(id == board.getApple(i).getAppleTileId()) {
					
					if(board.getApple(i).getPoints() == 0) {
						a=false;	//?? ?? ???? ???? "???????", ??? ?????????? ??' ???
					}
					else if(board.getApple(i).getColor() == "red") { 	//??????? ???????? ?????
						score += board.getApple(i).getPoints();		//?? ????? ???????, ?????? ??? ??????
						redEat++;
						a=true;
					}
					else if(board.getApple(i).getColor() == "black"){
						score -= board.getApple(i).getPoints(); 	//?? ????? ?????,?????? ??? ??????
						blackEat++;
						a=true;
					}
					board.getApple(i).setPoints(0);		//?????????? ??? ?????? ??? ?????
					
				}
			}
			
			for(int i = 0; i < board.getNumOfSnakes(); i++) {		//Loop ??? ?????? ?????? ??? ??? ????????
				if(id == board.getSnake(i).getHeadId()) {	//??????? ???????? ????????? ?????? ?? ?????? ??????
					id = board.getSnake(i).getTailId();		//???????? ?????? ??? ???????? ??? ????? ??? ??????
					snakeBite++;
					s=true;
				}
			}
			
			for(int i = 0; i < board.getNumOfLadders(); i++) {		//loop ??? ?????? ?????? ??? ??? ????????
				if(id == board.getLadder(i).getDownStepId() && board.getLadder(i).getBroken() == false) {
					id = board.getLadder(i).getUpStepId();
					ladderClimb++;
					board.getLadder(i).setBroken(true);
					l=true;
				}
			}
		}while(a==true||s==true||l==true); //?? ?????? ?????,??????? ???? ? ???? ????, ?? loop ??????????
		
		tile = id;	//?????????? ??? ???? ?????????6
		int[] results = {tile,snakeBite,ladderClimb,redEat,blackEat,round,die};	//??????? ?? ???? ????????
		
		return results;	//????????? ??????
	}
	
	public int[] checkMove(int id,int die) {
		id += die;		//?????? ??? ????????? ???? ?? ?????
		int tempPoints=0;	//?????? ???????
		

		for(int i = 0; i < board.getNumOfApples();i++) {	//loop ??? ?????? ????? ??? ??? ????????
			if(id == board.getApple(i).getAppleTileId()) {
				
				if(board.getApple(i).getColor() == "red") { 	//??????? ???????? ?????
					tempPoints += board.getApple(i).getPoints();		//?? ????? ???????, ?????? ??? ??????
				}
				else {
					tempPoints -= board.getApple(i).getPoints(); 	//?? ????? ?????,?????? ??? ??????
				}
			}
		}
		
		for(int i = 0; i < board.getNumOfSnakes(); i++) {		//Loop ??? ?????? ?????? ??? ??? ????????
			if(id == board.getSnake(i).getHeadId()) {	//??????? ???????? ????????? ?????? ?? ?????? ??????
				id = board.getSnake(i).getTailId();		//???????? ?????? ??? ???????? ??? ????? ??? ??????
			}
		}
		
		for(int i = 0; i < board.getNumOfLadders(); i++) {		//loop ??? ?????? ?????? ??? ??? ????????
			if(id == board.getLadder(i).getDownStepId() && board.getLadder(i).getBroken() == false) {
				id = board.getLadder(i).getUpStepId();
			}
		}
		
		int[] results = {id,tempPoints};
		return results;	
	}
	
	public double evaluate(int currentPos, int dice, Board board)
	{
		int[] results=new int[2];
		results = checkMove(currentPos, dice);
		return target(results[0]-currentPos,results[1]);
	}
	
	public void statistics() {
		char c=176;		//? ??????? ??? ?????? ASCII
		
		//??????? global ???????? ??? ????????
		System.out.print("Player "+playerId );

		if(snakeBite!=0) {
			System.out.println("\n"+c+"   was bitten by a snake. ");
		}
		if(ladderClimb!=0) {
			System.out.println("\n"+c+"   climbed 1 ladder");
		}
		if(redEat!=0) {
			System.out.println("\n"+c+"   ate " + redEat + " red apple(s).");
		}
		if(blackEat!=0) {
			System.out.println("\n"+c+"   ate " + blackEat + " black apple(s).");
		}
		else if(snakeBite==0&&ladderClimb==0&&redEat==0&&blackEat==0){
			System.out.print(" did ...nothing");
		}
		System.out.println();
	}
	
	public double target(int tiles,int points){
		return (tiles * 0.7) + (points * 0.3);
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	public Player(Player a) {
		this.name = a.name;
		this.playerId=a.playerId;
		this.board= new Board(a.board);
	}
}


 







