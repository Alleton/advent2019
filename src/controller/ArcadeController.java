package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ArcadeModel;
import service.Solver;
import view.arcade;

public class ArcadeController implements ActionListener{

	ArcadeModel arcademodel ;
	arcade arcadeview ;
	Solver solver ;
	final int DAY_GRID = 5;
	// public int problem ;
	
	public ArcadeController (ArcadeModel arcademodel,arcade arcadeview  ) {
		this.arcademodel = arcademodel;
		this.arcadeview = arcadeview ;
		//this./
//		int problem =   this.problem;
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("***** load *******") ;
		// TODO Auto-generated method stub
		//AdventGetPropertyValues properties = new AdventGetPropertyValues();		
		//String AdventFolder = properties.getPropValue("AdventFolder") ;
		String AdventFolder = "resources" ;
		String sfname ;
		//Solver_data.this.getProblem() =0 ;
		// int this_problem =  problem;
		
		
		if (e.getSource() == arcadeview.Go) {
			System.out.println("******* vue.load ******");
			sfname = AdventFolder + "/AdventProject" +  ( arcademodel.getProblem() ) + ".txt" ;
			System.out.println("Solve " + sfname ) ;
			//solver = new Solver( arcademodel.getProblem() , sfname ) ;
		}

		if (e.getSource() == arcadeview.test ) {
			// null
			System.out.println("******* vue.test ******");
			sfname = AdventFolder + "/AdventProject" +  ( arcademodel.getProblem() ) + "_1.txt" ;
			System.out.println("Solve " + sfname ) ;
			//solver = new Solver( arcademodel.getProblem() , sfname ) ;
		}

		for ( int i = 0 ; i<DAY_GRID ; i++ ) {
			for ( int j = 0 ; j<DAY_GRID; j++ ) {
				// reset all buttons
				arcadeview.allButton[i][j].setBackground(Color.white);
				
				int pb = ( i  *DAY_GRID )  + (j +1   ) ;
				if (e.getSource() == arcadeview.allButton[i][j] ) {
					System.out.println(" probleme  " + pb ) ;	
					//sfname = AdventFolder + "/AdventProject" +  ( pb ) + ".txt" ;
					//System.out.println(" to open " +sfname ) ;
					// set our button red
					arcadeview.allButton[i][j].setBackground(Color.RED);
					//problem = pb;
					arcademodel.setProblem(pb);
					// solver = new Solver( pb , sfname ) ;
				}
			}   // end boucle i
			
		}        // end boucle i
	
	} // end actionPerformed
}
