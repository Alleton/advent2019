package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;

import model.ArcadeModel;

public class arcade extends JFrame implements Observer{
	static final long serialVersionUID = 1;
	ArcadeModel arcademodel ;
	
	
	public JPanel      arcadepanel;
	private JPanel      GrillePanel ;
	public JButton   [][] allButton;
	
	public JButton Go = new JButton("Go");
	public JButton test = new JButton("Test");
	
	
	final int GRID_WIDTH = 5;
	final int GRID_HEIGHT = 5;
	
	int v_width ;
	int v_height ;
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.arcadepanel.repaint() ;
	}

	
	public  arcade (  ArcadeModel arcadentmodel ,int width , int height) {
		v_width = width;
		v_height = height ;
		//JButton   [][] allButton;
		allButton = new JButton [v_width][v_height];
		
		this.arcademodel = arcadentmodel;
		arcademodel.addObserver(this);

		JPanel adventpanel = new JPanel();
		//this.setContentPane(adventpanel);
		
		setPreferredSize(new Dimension(1800,800 ));
		add(adventpanel, BorderLayout.NORTH);
		// new panel for grid
		
		GrillePanel = new JPanel(new BorderLayout()); //PREFERRED!
		add(GrillePanel, BorderLayout.SOUTH);
		//GrillePanel.setLayout(new GridLayout(v_width,v_height));
		
		//arcadepanel.add(Go);
		// arcadepanel.add(test);
		int buttonnbr;
/*		
		for(int colIndex=0; colIndex < v_width; colIndex++){
			for(int rowIndex=0; rowIndex < v_height; rowIndex++) {
				buttonnbr = ( rowIndex  +1 )  + (colIndex * v_width  ) ;
				allButton[colIndex][rowIndex] = new JButton  (String.valueOf(buttonnbr));
				//GrillePanel.add ( allButton[colIndex][rowIndex]) ;
				//allButton[colIndex][rowIndex].setText ( " " + buttonnbr ) ;
			}
		}
	*/
		
		
		   Object[][] donnees = {
	                {"Johnathan", "Sykes"},
	                {"Nicolas", "Van de Kampf"},
	                {"Damien", "Cuthbert", },
	                {"Corinne", "Valance", },
	                 };
		   
		   String[] entetes = {"Prénom", "Nom"} ;
		   
		   JTable tableau = new JTable(donnees, entetes);
		   GrillePanel.add(tableau.getTableHeader(), BorderLayout.NORTH);
		   GrillePanel.add(tableau, BorderLayout.CENTER);
		pack() ;
		// GrillePanel.d
		//GrillePanel.fillRect(65, 65, 30, 40);
		//paintComponent(GrillePanel) ;
		
				
		add (adventpanel , BorderLayout.CENTER);
		//lesBoutons.add(efface);
		Border border = BorderFactory.createTitledBorder("arcade");
		adventpanel.setBorder(border);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,400);
		
		pack();
		setVisible(true);
	}

	
	public  arcade (  ArcadeModel arcadentmodel) {
		//JButton   [][] allButton;
		allButton = new JButton [v_height][v_width];
		
		this.arcademodel = arcadentmodel;
		arcademodel.addObserver(this);

		JPanel adventpanel = new JPanel();
		setPreferredSize(new Dimension(700,200 ));
		add(adventpanel, BorderLayout.NORTH);
		// new panel for grid
		
		GrillePanel = new JPanel(new BorderLayout()); //PREFERRED!
		add(GrillePanel, BorderLayout.SOUTH);
		GrillePanel.setLayout(new GridLayout(v_height,v_width));
		
	//	arcadepanel.add(Go);
	//	arcadepanel.add(test);
		int buttonnbr;
		
		for(int colIndex=0; colIndex < v_width; colIndex++){
			for(int rowIndex=0; rowIndex < v_height; rowIndex++) {
				buttonnbr = ( rowIndex  +1 )  + (colIndex * v_width  ) ;
				allButton[colIndex][rowIndex] = new JButton  (String.valueOf(buttonnbr));
				GrillePanel.add ( allButton[colIndex][rowIndex]) ;
				allButton[colIndex][rowIndex].setText ( " " + buttonnbr ) ;
			}
		}
		
				
		add (adventpanel , BorderLayout.CENTER);
		//lesBoutons.add(efface);
		Border border = BorderFactory.createTitledBorder("arcade");
		adventpanel.setBorder(border);
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set default close operation
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(100,400);
		
		pack();
		setVisible(true);
	}
	
	
	 public void paintComponent(Graphics g){
		    //x1, y1, width, height
		    g.drawRect(10, 10, 50, 60);
		    g.fillRect(65, 65, 30, 40);
		  }       
}
