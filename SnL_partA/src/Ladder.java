//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr
//Τσούσης Παναγιώτης 	9590	pitsousis@ece.auth.gr

public class Ladder {
	private int ladderId;
	private int upStepId;
	private int downStepId;
	private boolean broken = false;
	
	public int getLadderId() {return ladderId; }
	public int getUpStepId() {return upStepId;}
	public int getDownStepId() {return downStepId;}
	public boolean getBroken() {return broken;}
	
	public void setLadderId(int x) {ladderId=x;}
	public void setUpStepId(int x) {upStepId=x;}
	public void setDownStepId(int x) {downStepId=x;}
	public void setBroken(boolean x) {broken=x;}
	
	public Ladder(int a, int b,int c) {
		ladderId = a;
		upStepId = b;
		downStepId = c;
	}
	
	public Ladder(Ladder l) {
		this.ladderId=l.ladderId;
		this.upStepId = l.upStepId;
		this.downStepId=l.downStepId;
		this.broken = l.broken;
	}
	
	public Ladder() {ladderId=0;upStepId=0;downStepId=0;broken=false;}
	
}
