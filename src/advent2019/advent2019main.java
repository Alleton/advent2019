package advent2019;
/**
 * Solve all advent 2019 code
 */
import model.AdventModel;
import view.AdventView ;
import controller.AdventController;

public class advent2019main {
	int problem ;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int DAY_GRID = 5;
		
		/* **************** */
		/* MVC              */
		/* **************** */
		AdventModel adventmodel = new  AdventModel();
		AdventView adventprojectview = new view.AdventView() ;
		AdventController adventprojectcontroller = new AdventController(adventmodel,adventprojectview );
		
		//addActionListener pour les boutons
		adventprojectview.test.addActionListener(adventprojectcontroller);
		adventprojectview.Go.addActionListener(adventprojectcontroller);

	}

}
