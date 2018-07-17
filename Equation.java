package Calc_Proj;


import java.util.*;

public class Equation {
	  private String [] equation;
	  private double lowDom;
	  private double upDom;
	  public Equation(double lowDom, double upDom, String inEquation){
		this.equation =  inEquation.split(" ");
	    this.lowDom = lowDom;
	    this.upDom = upDom;
	  }
	  public ArrayList<Double> findFirstDeriv(){
		  ArrayList<Double> fD = new ArrayList<Double>();
		  ArrayList<String> eq = new ArrayList<String>();
		  ArrayList<String> tempEq = new ArrayList<String>();
		  for(String s : equation){
			  tempEq.add(s);
		  }
		  int length = 0; 
		  if(tempEq.get(0).contains("^")){
			  String [] firstTerm = tempEq.get(0).split("\\^");
			  length = Integer.parseInt(firstTerm[1]) + 1;
			  for(int i = 0; i < length-1; i++){
				  while(tempEq.size() > 0){
					  eq.add(tempEq.get(i));
					  tempEq.remove(i);
				  }
			  }
			  while(eq.size()<length){
				  eq.add("0");
			  }
			  for(int i = 0; i < length; i++){
				  String [] temp = eq.get(i).split("x|x\\^");
				  eq.set(i, temp[0]);
			  }
			  for(int i = 0; i < eq.size()-1; i++){
				  fD.add(Double.parseDouble(eq.get(i)) * (eq.size()-1-i));
			  }
			  
		  }
		  else if(tempEq.get(0).contains("x") || tempEq.get(0).contains("X")){
			 fD.add(Double.parseDouble(tempEq.get(0).split("x")[0]));
			 
		  }
		  else{
			  fD.add(0.0);
		  }
		  return fD;
		  
	  }
	  public ArrayList<Double> findCriticalValues(ArrayList<Double> fD){
		  double prevYVal = 0;
		  double prevXVal = 0;
		  int counter = 0;
		  ArrayList<Double> ogEq = new ArrayList<Double>();
		  ArrayList<String> eq = new ArrayList<String>();
		  ArrayList<String> tempEq = new ArrayList<String>();
		  for(String s : equation){
			  tempEq.add(s);
		  }
		  int length = 0; 
		  if(tempEq.get(0).contains("^")){
			  String [] firstTerm = tempEq.get(0).split("\\^");
			  length = Integer.parseInt(firstTerm[1]) + 1;
			  for(int i = 0; i < length-1; i++){
				  while(tempEq.size() > 0){
					  eq.add(tempEq.get(i));
					  tempEq.remove(i);
				  }
			  }
			  while(eq.size()<length){
				  eq.add("0");
			  }
			  for(int i = 0; i < length; i++){
				  String [] temp = eq.get(i).split("x|x\\^");
				  eq.set(i, temp[0]);
			  }
			 for(int i = 0; i < eq.size(); i++){
				ogEq.add(Double.parseDouble(eq.get(i)));
			 }
		  } 
		  Boolean increasing = false;
		  Boolean decreasing = false;
		 double prevYYVal = 0;
		 for(int j = 0; j < 2; j++){
			 double yyValue = 0;
			 for(int i = 0; i < ogEq.size()-1; i++){
				  yyValue += Math.pow(lowDom+j,(ogEq.size()-1)-i) * ogEq.get(i);
			  }
			  yyValue += ogEq.get(ogEq.size()-1);
			  if(yyValue - prevYYVal > 0){
					 increasing = true;
					 decreasing = false;
				 }
				 else{
					 decreasing = true;
					 increasing = false;
				}
			  prevYYVal = yyValue;
			  
		 }
			
		 double lastI = lowDom;
		  ArrayList<Double> critVals = new ArrayList<Double>();
		  for(double i = lowDom; i <= upDom; i++){
			  double yValue = 0; 
			  for(int j = 0; j < fD.size()-1; j++){
				  yValue += Math.pow(i,(fD.size()-1)-j) * fD.get(j);
			  }
			 yValue += fD.get(fD.size()-1);
			
			 if((prevYVal < 0 && yValue > 0) || (prevYVal > 0 && yValue < 0) || (yValue == 0)){
				 counter++;
				 System.out.println("Critical Value #" + counter + " is Between X = " + prevXVal + " and " + i);
				 critVals.add(prevXVal);
				 critVals.add(i);
				 if(counter == 1){
					 if(increasing == true){
						 System.out.println("Function is Increasing from (" + lastI + ", CriticalValue #" + counter +")");
						 lastI = i;
						 increasing = false;
						 decreasing = true; 
					 }
					 else if (decreasing == true){
						 System.out.println("Function is Decreasing from (" + lastI + ", CriticalValue #" + counter +")");
						 lastI = i;
						 increasing = true;
						 decreasing = false; 
					 }
				 }
				 else if(increasing == true){
					 System.out.println("Function is Increasing from (CriticalValue #" + (counter-1) + "," + " CriticalValue #" + counter +")");
					 lastI = i;
					 increasing = false;
					 decreasing = true; 
				 }
				 else if (decreasing == true){
					 System.out.println("Function is Decreasing from (CriticalValue #" + (counter-1) + "," + " CriticalValue #" + counter +")");
					 lastI = i;
					 increasing = true;
					 decreasing = false; 
				 }
				 
				  
			 }
			 prevYVal = yValue;
			 prevXVal = i;
		  }
		  if(increasing == true){
				 System.out.println("Function is Increasing from (CriticalValue #" + (counter) + ", " + upDom + ")");
			 }
			if (decreasing == true){
				 System.out.println("Function is Decreasing from (CriticalValue #" + (counter) + ", " + upDom + ")");
			 }
		  return critVals; 
	  }
	  public void findExtrema(ArrayList<Double> critVals){
		  class extrema{
			  private double critVal;
			  private int val;
			  public extrema(double critVal, int val){
				  this.critVal = critVal;
				  this.val = val;
			  }
			  
		  }
		  ArrayList<String> eq = new ArrayList<String>();
		  ArrayList<String> tempEq = new ArrayList<String>();
		  ArrayList<Double> ogEq = new ArrayList<Double>();
		  ArrayList<extrema> extrema = new ArrayList<extrema>();
		  for(String s : equation){
			  tempEq.add(s);
		  }
		  int length = 0; 
		  if(tempEq.get(0).contains("^")){
			  String [] firstTerm = tempEq.get(0).split("\\^");
			  length = Integer.parseInt(firstTerm[1]) + 1;
			  for(int i = 0; i < length-1; i++){
				  while(tempEq.size() > 0){
					  eq.add(tempEq.get(i));
					  tempEq.remove(i);
				  }
			  }
			  while(eq.size()<length){
				  eq.add("0");
			  }
			  for(int i = 0; i < length; i++){
				  String [] temp = eq.get(i).split("x|x\\^");
				  eq.set(i, temp[0]);
			  }
			 for(int i = 0; i < eq.size(); i++){
				ogEq.add(Double.parseDouble(eq.get(i)));
			 }
			 double prevYVal = 0;
			 int count = 0;
			 for(int j = 0; j < critVals.size(); j++){
				 double yValue = 0;
				  for(int i = 0; i < ogEq.size()-1; i++){
					  yValue += Math.pow(critVals.get(j),(ogEq.size()-1)-i) * ogEq.get(i);
				  }
				  yValue += ogEq.get(ogEq.size()-1);
				  
				  if(j%2 != 0){
					 count++;
					 extrema.add(new extrema((yValue + prevYVal)/2, count));
				  }
				  prevYVal = yValue;
			 }
			 extrema locMin = new extrema(0,0);
			 extrema locMax = new extrema(0,0);
			 if(extrema.size() > 0){
				  locMax = extrema.get(0);
			 }
			 else{
				 System.out.println("No Extremas were found");
			 }
			 
			for(int i = 0; i < extrema.size(); i++){
				if(extrema.get(i).critVal > locMax.critVal && !extrema.get(i).equals(locMin)){
					int index = extrema.indexOf(extrema.get(i));
					extrema.set(0, extrema.get(i));
					extrema.set(index, locMax);
				}
				
			}
			if(extrema.size() == 1){
				if(ogEq.get(0) > 0){
					System.out.println("Critical Value #" + extrema.get(extrema.size()-1).val + " is a local min");
				}
				else{
					System.out.println("Critical Value #" + extrema.get(0).val + " is a local max");
				}
			}
			else{
				if(locMax.critVal == 0 && locMax.val == 0){
					System.out.println("No Local Max was Found.");
				}
				else{
					System.out.println("Critical Value #" + extrema.get(0).val + " is a local max");
				}
				if(locMin.critVal == 0 && locMax.val == 0){
					System.out.println("No Local Min was Found.");
				}
				else{
					System.out.println("Critical Value #" + extrema.get(extrema.size()-1).val + " is a local min");
				}
			}
			
		  }
	  }
	  public ArrayList<Double> findSecondDeriv(ArrayList<Double> fD){
		  ArrayList<Double> sD = new ArrayList<Double>();
		  for(int i = 0; i < fD.size()-1; i++){
			  sD.add((fD.get(i)) * (fD.size()-1-i));
		  }
		  return sD;
	  }
	  public void findInflections(ArrayList<Double> sD){
		  double prevYVal = 0;
		  double prevXVal = 0;
		  int counter = 0;
		  Boolean conUP = false;
		  Boolean conDown = false;
		 if(sD.get(0) > 0){
			 conDown = true;
		 }
		 else{
			 conUP = true;
		 }
		 double lastI = lowDom;
		  ArrayList<Double> inflectionVals = new ArrayList<Double>();
		  for(double i = lowDom; i <= upDom; i++){
			  double yValue = 0; 
			  for(int j = 0; j < sD.size()-1; j++){
				  yValue += Math.pow(i,(sD.size()-1)-j) * sD.get(j);
			  }
			 yValue += sD.get(sD.size()-1);
			
			 if((prevYVal < 0 && yValue > 0) || (prevYVal > 0 && yValue < 0) || (yValue == 0)){
				 counter++;
				 System.out.println("Inflection Point #" + counter + " is Between X = " + prevXVal + " and " + i);
				 inflectionVals.add(prevXVal);
				 inflectionVals.add(i);
				 if(counter == 1){
					 if(conUP == true){
						 System.out.println("Function is Concave UP from (" + lastI + ", Inflection Point #" + counter +")");
						 lastI = i;
						 conUP = false;
						 conDown = true; 
					 }
					 else if (conDown == true){
						 System.out.println("Function is Concave DOWN from (" + lastI + ", Inflection Point #" + counter +")");
						 lastI = i;
						 conUP = true;
						 conDown = false; 
					 }
				 }
				 else if(conUP == true){
					 System.out.println("Function is Concave UP from (Inflection Point #" + (counter-1) + "," + " Inflection Point #" + counter +")");
					 lastI = i;
					 conUP = false;
					 conDown = true; 
				 }
				 else if (conDown == true){
					 System.out.println("Function is Concave DOWN from (Inflection Point #" + (counter-1) + "," + " Inflection Point #" + counter +")");
					 lastI = i;
					 conUP = true;
					 conDown = false; 
				 }
				 
				  
			 }
			 prevYVal = yValue;
			 prevXVal = i;
		  }
		  if(counter > 0){
		  	if(conUP == true){
				 System.out.println("Function is Concave UP from (Inflection Point #" + (counter) + ", " + upDom + ")");
			 }
			if (conDown == true){
				 System.out.println("Function is Concave DOWN from (Inflection Point #" + (counter) + ", " + upDom + ")");
			 }
		  }
		  else{
			  System.out.println("No inflection points were found.");
		  }  
	  }	  
}