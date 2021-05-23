import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

class MyCanvas extends JComponent {
	
	public int y=100, x=300, rWidth=700, rHeight=880;
	Board b = null;

	public MyCanvas(Board board) {
		y=100;
		x=300;
		rWidth=700;
		rHeight=880;
		b=board;
	}
	
	public MyCanvas() {
		y=100;
		x=300;
		rWidth=700;
		rHeight=880;
	}
	
	public int chooseColor(int i, int k) {
		
		int x;
		x=(i+k)%4;
		return x;
	}
	
	public void paint(Graphics g) {
		g.drawRect (x, y, rWidth, rHeight);
		int j=0,k=0;
		for(int i=0; i<20; i++) 
		{
			g.drawRect(x, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k++;
			
			g.drawRect(x+70, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+70, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k++;
			
			g.drawRect(x+140, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+140, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k+=2;
			
			g.drawRect(x+210, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+210, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k+=7;
			
			g.drawRect(x+280, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+280, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k+=3;
			
			g.drawRect(x+350, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+350, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k++;
			
			g.drawRect(x+420, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+420, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k+=2;
			
			g.drawRect(x+490, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+490, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k++;
			
			g.drawRect(x+560, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+560, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k++;
			
			g.drawRect(x+630, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			j=chooseColor(i,k);
			if(j==0) {g.setColor(Color.YELLOW);}
			else if(j==1) {g.setColor(Color.PINK);}
			else if(j==2) {g.setColor(Color.lightGray);}
			else if(j==3) {g.setColor(Color.BLUE);}
			g.fillRect(x+630, (y+(i*rHeight/20)), rWidth/10, rHeight/20);
			k=k+3;
		}
		for(int i=0; i<20; i++) 
		{
			g.setColor(Color.BLACK);
			
			g.drawRect(x, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+70, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+140, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+210, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+280, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+350, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+420, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+490, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+560, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
			g.drawRect(x+630, (y + (i*rHeight/20)), rWidth/10, rHeight/20);
			
		}
		for(int i=0; i<b.getNumOfSnakes();i++) {
			int hID=b.getSnake(i).getHeadId();
			int tID=b.getSnake(i).getTailId();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(9));
			int x1=getCoordinates(hID)[0];
			int y1=getCoordinates(hID)[1];
			int x2=getCoordinates(tID)[0];
			int y2=getCoordinates(tID)[1];
			g2.drawLine(x1, y1, x2, y2);
		}
		
		for(int i=0; i<b.getNumOfLadders();i++) {
			int uID=b.getLadder(i).getUpStepId();
			int dID=b.getLadder(i).getDownStepId();
			Graphics2D g2 = (Graphics2D) g;
			Color BROWN = new Color(102, 51, 0);
			g2.setColor(BROWN);
			g2.setStroke(new BasicStroke(9));
			int x1=getCoordinates(uID)[0];
			int y1=getCoordinates(uID)[1];
			int x2=getCoordinates(dID)[0];
			int y2=getCoordinates(dID)[1];
			g2.drawLine(x1, y1, x2, y2);
		}
//		g.setColor(Color.RED);
//		int[] coos1=getCoordinates(circle1.getTile());
//		g.drawOval(coos1[0], coos1[1],((rWidth)/10) ,((rHeight)/20));
//		g.setColor(Color.RED);
//		g.fillOval(coos1[0], coos1[1],((rWidth)/10) ,((rHeight)/20));
//		
//		g.setColor(Color.BLACK);
//		int[] coos2=getCoordinates(circle2.getTile());
//		g.drawOval(coos2[0], coos2[1],((rWidth)/10) ,((rHeight)/20));
//		g.setColor(Color.BLACK);
//		g.fillOval(coos2[0], coos2[1],((rWidth)/10) ,((rHeight)/20));
	}
	
	public int[] getCoordinates(int tileId) {
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
		int yCo = y + (20-row) * (rHeight/20);
		int xCo = x + (rWidth/10) * (column - 1);
		coos[0]=xCo+35;
		coos[1]=yCo+22;
		return coos;
	}

}
