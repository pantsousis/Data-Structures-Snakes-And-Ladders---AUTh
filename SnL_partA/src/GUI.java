import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class GUI extends JFrame {
	
	JLabel rounds;
	Options op;
	MyCanvas canvas;
	JFrame window = new JFrame();
	JFrame instructions = new JFrame();
	MyCanvas testCanvas = new MyCanvas();
//	JPanel panel1 = new JPanel();
//	JPanel panel2 = new JPanel();

	public void GUICreate(Board board) {
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(1920,1080);
	    rounds = new JLabel("<html><span style='font-size:16px'>"+"Rounds: 0"+"</span></html>");
	    rounds.setBounds(100, 50, 150, 60);
	    window.add(rounds);
	    op = new Options(window,board);
	    createNumbers();
	    
	    instructions.setSize(800,460);
	    JPanel panel = new JPanel();
	    panel.setVisible(true);
	    panel.setBounds(50, 25, 400, 100);
	    panel.setLayout(new GridBagLayout());
	    JLabel inst = new JLabel("<html><span style='font-size:16px'>"+"WELCOME TO SnL by PANAGIOTIS AND JIM."+"</span></html>");
	    JLabel inst2 = new JLabel("<html><span style='font-size:16px'>"+"INSTRUCTIONS:"+"</span></html>");
	    JLabel inst3 = new JLabel("<html><span style='font-size:16px'>"+"1)CHOOSE A PLAYER TYPE IN BOTH SCROLL DOWN MENUS."+"</span></html>");
	    JLabel inst4 = new JLabel("<html><span style='font-size:16px'>"+"2)PRESS Generate Players AND THEN Play, TO START THE GAME."+"</span></html>");
	    JLabel inst5 = new JLabel("<html><span style='font-size:13px'>"+"The brown lines are the ladders, and the green ones, the snakes."+"</span></html>");
	    JLabel inst6 = new JLabel("<html><span style='font-size:13px'>"+"The red squares are the red apples, and the black ones, the black apples. (DUH!)"+"</span></html>");
	    JButton button = new JButton ("OK! THANKS.");
	    button.setVisible(true);
	    button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				instructions.dispose();
			}  
		
			    }); 
	    
	    addComp(panel,inst,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
	    addComp(panel,inst2,0,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	    addComp(panel,inst3,0,2,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	    addComp(panel,inst4,0,3,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	    addComp(panel,inst5,0,4,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	    addComp(panel,inst6,0,5,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	    addComp(panel,button,0,6,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);


	    canvas = new MyCanvas(board);
	    window.getContentPane().add(new MyCanvas(board));
	    window.setVisible(true);
	    instructions.setVisible(true);
	    instructions.add(panel);
	  }

	public JLabel getRound() {
		return rounds;
	}
	
	public void createNumbers() {
		for(int i =1; i<201 ; i++) {
			int[] coos =testCanvas.getCoordinates(i);
			JLabel number = new JLabel(" "+i);
			number.setBounds(coos[0]-35, coos[1]-22, 50, 50);
			window.add(number);
		}
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
	
}
