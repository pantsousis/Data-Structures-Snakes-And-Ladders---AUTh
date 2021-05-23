//������� ��������� 	9605 	dzsamaras@ece.auth.gr
//������� ���������� 	9590	pitsousis@ece.auth.gr

import java.util.ArrayList;

public class HeuristicPlayer extends Player {
	
	ArrayList<int[]> path = new ArrayList<int[]>(); //���������� ��� ���� ���� �� ����� round,die,steps,points,snake,ladder,red,black
	
	HeuristicPlayer(int a,String b,Board c)
	{
		super(a,b,c);
	}
	
	//��������� ����������� ������� ��� ������������ ����
	double evaluate(int currentPos, int dice)
	{
		int[] results=new int[2];
		results = super.checkMove(currentPos, dice);
		return super.target(results[0]-currentPos,results[1]);
	}
	
	//��������� �������� �������, �� ���� ��� ����������
	int getNextMove(int currentPos,int round) //������ round, ��� ���������� ���� path
	{
		double[] results = new double[6]; //���������� ������ 6 ������, ��� ��� ���� �����
		for(int i=1;i<7;i++) //���������, ���� i ����� � �����
		{
			results[i-1] = evaluate(currentPos,i) ; //���������� ����������� ���� ������
		}
		
		double max=0; //� ������� ����������
		int maxCounter=0; //�� ���� �� �� ������� ����������
		
		for (int counter = 0; counter < results.length; counter++) //������ ������ �� ������� ����������
		{
		     if (results[counter] > max)
		     {
		      max = results[counter]; //�� ���������� ��� ���������� ������
		      maxCounter=counter+1;	//O ������� ��� ������
		     }
		}
		
		
		int[] stats = new int[7]; //������� 5 ������, ��� ��� ���������� ��� 5 ���������� ��� ��� ������ move
		
		stats = super.move(currentPos, maxCounter,round,this.getBoard(),0); // ���������� ���������� ��� move
		
		//���������� ������� round,die,steps,points,snake,ladder,red,black
		int[] pathStats = {round,maxCounter,stats[0]-currentPos,stats[3]*5-stats[4]*5,stats[1],stats[2],stats[3],stats[4]};

		path.add(pathStats);
		
		return pathStats[1]; //��������� ������� ����� ��� ������
		
	}
	
	//���������� ��� �� ���� ��� Heuristic Player
	public void statistics() {
		char c=176;//� ������� ��� ������ ASCII
		
		int lastRound = path.size() - 1; //�������� ��� ���� 0
		
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
	
	//��������� move ��� HeuristicPlayer ��� ����� override �� move ��� Player
	public int[] move(int a,int b, int c,Board board,int d) {  //tile,die,round,board
		int die=getNextMove(a,c);
		//��������� ����� ������, ���� ��� �� ��������� � ����� ���������� �� �� move ��� player
		int[] p=new int[7];
		p[6] = die;
		return p;
	}
	
}





