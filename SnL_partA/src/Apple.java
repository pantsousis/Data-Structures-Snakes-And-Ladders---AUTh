//Σαμαράς Δημήτριος 	9605 	dzsamaras@ece.auth.gr 
//Τσούσης Παναγιώτης 	9590	pitsousis@ece.auth.gr

public class Apple {
	private int appleId;
	private int appleTileId;
	private int points;
	private String color;
	
	public int getAppleId() {return appleId;}
	public int getAppleTileId() {return appleTileId;}
	public int getPoints() {return points;}
	public String getColor() {return color;}
	
	public void setAppleId(int x) {appleId=x;}
	public void setAppleTileId(int x) {appleTileId = x;}
	public void setPoints(int x) {points=x;}
	public void setColor(String x) {color=x;}
	
	public Apple() {appleId=0; appleTileId=0; points=5; color = "white";}
	
	public Apple(int a,int b,String c) {
		appleId=a;
		appleTileId=b;
		color = c;
		points = 5;
	}
	
	public Apple(Apple a) {
		this.appleId=a.appleId;
		this.appleTileId=a.appleTileId;
		this.points=a.points;
		this.color=a.color;
	}
}