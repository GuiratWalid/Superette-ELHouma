package oo;

public class Controle {

	public static boolean isNumeric(String ch) {
		
		try  
		  {  
		    Long d = Long.parseLong(ch);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true; 
		
	}
	
	public static boolean isDoublePositive(String ch) {
		
		Double d = 0.0 ;
		try  
		  {  
		    d = Double.parseDouble(ch);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		if(d>0)
		  return true; 
		else return false;
		
	}
	
	public static boolean isLetters(String ch) {
		
		for(int i=0;i<ch.length();i++) {
			if(!(Character.isLetter(ch.charAt(i)) || Character.isSpace(ch.charAt(i)) )) {
				return false;
			}
		}
		return true;
		
	}
	
	public static void main (String [] args) {

	}
	
}
