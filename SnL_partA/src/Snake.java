//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr
//Τσούσης Παναγιώτης 	9590	pitsousis@ece.auth.gr

public class Snake {
	
	private int snakeId;
	private int headId;
	private int TailId;
	
	
	public int getSnakeId() {return snakeId;}
	public int getHeadId() {return headId;}
	public int getTailId() {return TailId;}
	public void setSnakeId(int snakeId) {this.snakeId = snakeId;} 
	public void setHeadId(int headId) {this.headId = headId;}
	public void setTailId(int tailId) {TailId = tailId;}
	
	public Snake(int a,int b,int c)
	{
		snakeId = a;
		headId = b;
		TailId = c;
	}
	
	public Snake()
	{
		snakeId=0;
		headId=0;
		TailId=0;
	}
	
	public Snake(Snake s) {
		this.snakeId=s.snakeId;
		this.headId=s.headId;
		this.TailId=s.TailId;
	}

}
