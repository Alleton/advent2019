package service;

public class Solver {
	public Solver ( int problem , String sfname) {
		try {
			System.out.println("");
			System.out.println("Tentative de resolution du projet : " + problem ) ;

			switch (problem)
			{
			case 1: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver1 solver1 = new Solver1 ();
				System.out.println("Solution Solver1 " + solver1.the_solver1(sfname));		
				break;		
			}
			case 2: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver2 solver2 = new Solver2 ();
				System.out.println("Solution Solver2 " + solver2.the_solver2(sfname));		
				break;		
			}
			case 3: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver3 Solver3 = new Solver3();
				System.out.println("Solution Solver3 " + Solver3.solver3 (sfname));
				break;		
			}
			case 4: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver4 Solver4 = new Solver4();
				System.out.println("Solution Solver4 " + Solver4.solver4(sfname));
				break;		
			}
			case 5: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver5 Solver5 = new Solver5();
				System.out.println("Solution Solver5 " + Solver5.solver5(sfname));
				break;		
			}
			case 6: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver6 Solver6 = new Solver6();
				System.out.println("Solution Solver6 " + Solver6.solver6(sfname));
				break;		
			}
			case 7: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver7 Solver7 = new Solver7();
				System.out.println("Solution Solver7 " + Solver7.solver7(sfname));
				break;		
			}
			case 8: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver8 Solver8 = new Solver8();
				System.out.println("Solution Solver8 " + Solver8.solver8(sfname));
				break;		
			}
			case 9: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver9 Solver9 = new Solver9();
				System.out.println("Solution Solver9 " + Solver9.solver9(sfname));
				break;		
			}
			case 10: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver10 Solver10 = new Solver10();
				System.out.println("Solution Solver10 " + Solver10.solver10(sfname));
				break;		
			}
			case 11: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver11 Solver11 = new Solver11();
				System.out.println("Solution Solver11 " + Solver11.solver11(sfname));
				break;		
			}
			case 12: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver12 Solver12 = new Solver12();
				System.out.println("Solution Solver12 " + Solver12.solver12(sfname));
				break;		
			}
			case 13: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver13 Solver13 = new Solver13();
				System.out.println("Solution Solver13 " + Solver13.solver13(sfname));
				//SplashDemo test = new SplashDemo();
				break;		
			}
			case 14: {
				System.out.println("Chargement pb " + problem ) ; 
				Solver14 Solver14 = new Solver14();
				System.out.println("Solution Solver14 " + Solver14.solver14(sfname));
				break;		
			}
			case 15: {
				System.out.println("Chargement pb " + problem ) ; 
//				Solver15 Solver15 = new Solver15();
//				System.out.println("Solution Solver15 " + Solver15.solver15(sfname));
				break;		
			}
			case 16: {
				System.out.println("Chargement pb " + problem ) ; 
//				Solver16 Solver16 = new Solver16();
//				System.out.println("Solution Solver16 " + Solver16.solver16(sfname));
				break;		
			}
			case 17: {
				System.out.println("Chargement pb " + problem ) ; 
//				Solver17 Solver17 = new Solver17();
//				System.out.println("Solution Solver17 : " + Solver17.solver17(sfname));
				break;		
			}
			case 18: {
				System.out.println("Chargement pb " + problem ) ; 
//				Solver18 Solver18 = new Solver18();
//				System.out.println("Solution Solver18 : " + Solver18.solver18(sfname));
				break;		
			}
			case 19: {
				System.out.println("Chargement pb " + problem ) ; 
				//Solver19 Solver19 = new Solver19();
				//System.out.println("Solution Solver19 : " + Solver19.solver19(sfname));
				
				// Solver191 Solver191 = new Solver191();
				// System.out.println("Solution Solver191 : " + Solver191.solver191(sfname));
				
				break;		
			}
			case 20: {
				System.out.println("Chargement pb " + problem ) ; 
				// Solver20 Solver20 = new Solver20();
				// System.out.println("Solution Solver20 : " + Solver20.solver20(sfname));
				break;		
			}

			case 21: {
				System.out.println("Chargement pb " + problem ) ; 
				// Solver21 Solver21 = new Solver21();
				// System.out.println("Solution Solver21 : " + Solver21.solver21(sfname));
				break;		
			}

			case 22: {
				System.out.println("Chargement pb " + problem ) ; 
				// Solver22 Solver22 = new Solver22();
				// System.out.println("Solution Solver22 : " + Solver22.solver22(sfname));
				break;		
			}

			case 23: {
				System.out.println("Chargement pb " + problem ) ; 
				// Solver23 Solver23 = new Solver23();
				// System.out.println("Solution Solver23 : " + Solver23.solver23(sfname));
				break;		
			}
			case 24: {
				System.out.println("Chargement pb " + problem ) ; 
				// Solver24 Solver24 = new Solver24();
				// System.out.println("Solution Solver24 : " + Solver24.solver24(sfname));
				break;		
			}
			case 25: {
				System.out.println("Chargement pb " + problem ) ; 
				// Solver25 Solver25 = new Solver25();
				// System.out.println("Solution Solver25 : " + Solver25.solver25(sfname));
				break;		
			}
			
			default:
				System.out.println("Pas de tentative de resolution du projet : " + problem ) ;
			}

			// fin
			//Runtime.getRuntime().halt (0) ; 
		} catch (Exception e) {
			//throw new IllegalArgumentException("Unable to load " + sfname, e);
			e.printStackTrace();
		}
	}
}
