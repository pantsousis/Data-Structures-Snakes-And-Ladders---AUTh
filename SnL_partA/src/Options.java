import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class Options {
	JFrame frame;
	
	JLabel l11,l12,l13,l21,l22,l23,die1,die2,startPlayer,d1,d2,gameOver;
	JComboBox cb1,cb2;
	JPanel panel1,panel2,player1,player2;
	JPanel apples[]= new JPanel[6];
	JButton b1,b2,b3,b4;
	Player A,B;
	private boolean cont,contGen,contPlay; 
	
	String playerTypes[] = { "None","Random Player", "Heuristic Player", "MinMax Player"};
	
	public Options(JFrame j, Board board) {
		frame = j;
		
		cont=false;
		contGen=false;
		contPlay=false;
		
		player1 = new JPanel();
		panel1 = new JPanel();
		panel1.setVisible(true);
		panel1.setBounds(1050, 150, 800, 350);
		player2 = new JPanel();
		
		int x1=303;
		int y1=940;
		player1.setBounds(x1, y1, 30, 38);
		Color LightBlue = new Color(255,102,0);
		player1.setBackground(LightBlue);
		player1.setVisible(false);
	    player2.setVisible(false);
		j.add(player1);
		
		int x2=337;
		int y2=940;
		player2.setBounds(x2, y2, 30, 38);
		player2.setBackground(Color.MAGENTA);
		j.add(player2);
		
		for(int i=0;i<6;i++) {
			apples[i]=new JPanel();
			apples[i].setBounds(getCoordinates(board.getApple(i).getAppleTileId())[0]+15, getCoordinates(board.getApple(i).getAppleTileId())[1]+10, 30, 30);
			if(board.getApple(i).getColor()=="red") {
				apples[i].setBackground(Color.RED);
			}
			else if(board.getApple(i).getColor()=="black") {
				apples[i].setBackground(Color.BLACK);
			}
			apples[i].setVisible(true);
			j.add(apples[i]);
		}																//("<html>Text color: <font color='red'>red</font></html>")
		
		l11 = new JLabel("<html><span style='font-size:12px'>"+"<font color='Orange'>Player A</font> "+"</span></html>");
		l12 = new JLabel("<html><span style='font-size:12px'>"+"Move Score: 0"+"</span></html>");
		l13 = new JLabel("<html><span style='font-size:12px'>"+"Total Score: 0"+"</span></html>");
		
		l21 = new JLabel("<html><span style='font-size:12px'>"+"<font color='#FF00FF'>Player B</font> "+"</span></html>");
		l22 = new JLabel("<html><span style='font-size:12px'>"+"Move Score: 0"+"</span></html>");
		l23 = new JLabel("<html><span style='font-size:12px'>"+"Total Score: 0"+"</span></html>");
		
		die1 = new JLabel("<html><span style='font-size:12px'>"+"Player A die: " +"</span></html>");
		die2 = new JLabel("<html><span style='font-size:12px'>"+"Player B die: " +"</span></html>");
		startPlayer = new JLabel("<html><span style='font-size:12px'>"+"Starts first: " +"</span></html>");
		
		d1 = new JLabel("<html><span style='font-size:12px'>"+"Player A rolls the die: " +"</span></html>");
		d2 = new JLabel("<html><span style='font-size:12px'>"+"Player B rolls the die: " +"</span></html>");
		
		gameOver = new JLabel("<html><span style='font-size:12px'>"+"GAME OVER!WINNER: "+"</span></html>");
		gameOver.setVisible(false);
		
		cb1=new JComboBox(playerTypes);
		cb2=new JComboBox(playerTypes);
		
		b1 = new JButton("Generate Players");
		b2 = new JButton("Play");
		b3 = new JButton("Quit");
		b4 = new JButton("Next round");
		
		Box buttons = Box.createHorizontalBox();
		buttons.add(b1);
		buttons.add(Box.createRigidArea(new Dimension(10, 0)));
		buttons.add(b2);
		buttons.add(Box.createRigidArea(new Dimension(10, 0)));
		buttons.add(b3);
		
        Box die = Box.createVerticalBox();
        die.add(d1);
        die.add(d2);
        die.setVisible(false);
		
		b1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				b1.setEnabled(false);
				b2.setEnabled(true);
			    cb1.setEnabled(false);
			    cb2.setEnabled(false);
			    player1.setVisible(true);
			    player2.setVisible(true);
			    contGen = true;
			}  
			    }); 
		b2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				b2.setEnabled(false);
			    b4.setEnabled(true);
			    b4.setVisible(true);
			    contPlay = true;
			}  
			    }); 
		
		b2.setEnabled(false);
		
		b3.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				System.exit(0);
			}  
			    }); 
		
		b4.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cont = true;
				die.setVisible(true);
			}  
		
			    }); 
		b1.setEnabled(false);
		cb1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				if((String)cb1.getSelectedItem() == "None"||(String)cb2.getSelectedItem() == "None") {
					b1.setEnabled(false);
				}
				else if((String)cb1.getSelectedItem() == "Random Player") {
					A = new Player(1,"Normal",board);
					b1.setEnabled(true);
				}
				else if((String)cb1.getSelectedItem() == "Heuristic Player") {
					A = new HeuristicPlayer(1,"Heuristic",board);
					b1.setEnabled(true);
				}
				else if((String)cb1.getSelectedItem() == "MinMax Player") {
					A = new MinMaxPlayer(1,"MinMax Player",board);
					b1.setEnabled(true);
				}
			}  
		
			    }); 
		
		cb2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if((String)cb2.getSelectedItem() == "None"||(String)cb1.getSelectedItem() == "None") {
					b1.setEnabled(false);
				}
				if((String)cb2.getSelectedItem() == "Random Player") {
					B = new Player(2,"Normal",board);
					b1.setEnabled(true);
				}
				else if((String)cb2.getSelectedItem() == "Heuristic Player") {
					B = new HeuristicPlayer(2,"Heuristic",board);
					b1.setEnabled(true);
				}
				else if((String)cb2.getSelectedItem() == "MinMax Player") {
					B = new MinMaxPlayer(2,"MinMax Player",board);
					b1.setEnabled(true);
				}
			}  
		
			    }); 
		
		
		
		b4.setEnabled(false);
        
        Box dieStart = Box.createVerticalBox();
        dieStart.add(die1);
        dieStart.add(die2);
        dieStart.add(startPlayer);
        dieStart.setBorder(BorderFactory.createTitledBorder("First dice roll"));
        
		
		
		panel1.setLayout(new GridBagLayout());
		
		
		addComp(panel1,l11,0,0,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
		addComp(panel1,l12,0,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
		addComp(panel1,l13,0,2,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
		
		addComp(panel1,l21,0,0,2,1,GridBagConstraints.EAST,GridBagConstraints.NONE);
		addComp(panel1,l22,0,1,2,1,GridBagConstraints.EAST,GridBagConstraints.NONE);
		addComp(panel1,l23,0,2,2,1,GridBagConstraints.EAST,GridBagConstraints.NONE);
		
		addComp(panel1,cb1,0,3,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
		addComp(panel1,cb2,0,3,1,1,GridBagConstraints.EAST,GridBagConstraints.NONE);
		
		addComp(panel1,buttons,0,4,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		addComp(panel1,b4,0,5,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		addComp(panel1,dieStart,0,6,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
		
		addComp(panel1,die,0,6,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		addComp(panel1,gameOver,0,7,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		j.add(panel1);
		
		
	}
	
	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		
		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(2,2,2,2);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;
		
		thePanel.add(comp, gridConstraints);
		
	}
	
	public boolean getCont() {return cont;}
	public void setCont(boolean x) { cont = x;}
	
	public boolean getContGen() {return contGen;}
	public boolean getContPlay() {return contPlay;}
	
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
