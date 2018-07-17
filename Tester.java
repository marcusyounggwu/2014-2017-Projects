package Calc_Proj;

import java.util.*;

public class Tester {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please Enter a Polynomial with Whole Number Exponents following each term with a space. Ex(-1x^3 +3x^2 +1x +4): ");
		String eqIn = s.nextLine();
		System.out.println("Please Enter the Lower Domain of the Function. Eg (-100): ");
		double lowDom = Double.parseDouble(s.nextLine()); 
		System.out.println("Please Enter the Upper Domain of the Function. Eg (100): ");
		double upDom = Double.parseDouble(s.nextLine());
		Equation eq = new Equation(lowDom, upDom, eqIn);
		System.out.print("First Derivative: ");
		for(int i = 0; i < eq.findFirstDeriv().size(); i++){
			if(i < eq.findFirstDeriv().size()-2){
				System.out.print("(" + eq.findFirstDeriv().get(i) + "x^" + (eq.findFirstDeriv().size()-i-1) + ")" + " + ");
			}
			else if(i == eq.findFirstDeriv().size()-2){
				System.out.print("(" + eq.findFirstDeriv().get(i) + "x" + ")" + " + ");
			}
			else{
				System.out.print(eq.findFirstDeriv().get(i));
			}
		}
		System.out.println();
		System.out.print("Second Derivative: ");
		for(int i = 0; i < eq.findSecondDeriv(eq.findFirstDeriv()).size(); i++){
			if(i < eq.findSecondDeriv(eq.findFirstDeriv()).size()-2){
				System.out.print("(" + eq.findSecondDeriv(eq.findFirstDeriv()).get(i) + "x^" + (eq.findSecondDeriv(eq.findFirstDeriv()).size()-i-1) + ")" + " + ");
			}
			else if(i == eq.findSecondDeriv(eq.findFirstDeriv()).size()-2){
				System.out.print("(" + eq.findSecondDeriv(eq.findFirstDeriv()).get(i) + "x" + ")" + " + ");
			}
			else{
				System.out.print(eq.findSecondDeriv(eq.findFirstDeriv()).get(i));
			}
		}
	
		System.out.println();
		eq.findExtrema(eq.findCriticalValues(eq.findFirstDeriv()));
		eq.findInflections(eq.findSecondDeriv(eq.findFirstDeriv()));
		
	}

}
