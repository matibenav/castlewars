package UtilsGraphics;

import java.util.ArrayList;
import java.util.Scanner;

import Engine.Game;
import Utils.Graphicable;

public class Graphics_Console implements Graphicable {
  
	@Override
	public void showIntro() {
		// TODO Auto-generated method stub
		System.out.println("Castle Wars Game");
		int aux = inputInt("Ingrese # opcion:\n1. Crear juego\n2. Cargar juego\n3. Cambiar interfaz gráfica\n",1,3);
		 switch(aux) 
	    {
	    case 1: 
	      this.showSettings();
	      break;
	    case 2:
	      this.loadGame();
	      break;
	    case 3:
	      Game.getInstance().switchGraphicable();
	      
	      break;
	    }
	}

	@Override
	public void showSettings() {
		// TODO Auto-generated method stub
		System.out.println("Settings");
	}

	@Override
	public void showGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
		System.out.println("load");
	}
	
  @Override
  public void saveGame() {
    // TODO Auto-generated method stub
    
  }

  public static int inputInt(String inputString, int min, int max)
  { //        pasando un minimo y un maximo, devuelve int ingresado en ese rango
    boolean isParseable = false;
    boolean inRange = false;
    boolean firstTry = true;
    int outputInt = 0;
    String choiceString;
    Scanner inputScanner = new Scanner(System.in);  
    while (isParseable == false || inRange == false) {
      if (firstTry == false) {
        System.out.println("El valor ingresado es inválido.");
      }
      System.out.println(inputString);
      choiceString = inputScanner.nextLine();
      try{
        outputInt = Integer.parseInt(choiceString); 
        isParseable = true; 
        if(outputInt <= max && outputInt >= min) {
          inRange = true;
        }else {
          inRange = false;
        }   
      } catch(Exception excPars){
        isParseable = false;
        //System.out.println("El elemento ingresado no es un número.");
      }
      firstTry = false;
    }
  return outputInt;
  }
  public static String inputString(String inputString, ArrayList<String> admittedValues)
  { 
  // escanea un string
  // lo compara con elementos de una lista de opciones
  // no puede ser vacío
  boolean firstTry = true;
  boolean admitted = false;
  String outString = "";
  Scanner inScanner = new Scanner(System.in);
  while (outString.equals("") || admitted == false) 
    {
    if(firstTry == false) 
      if(admitted == false)
        System.out.println("El valor ingresado no es admitido. Intente nuevamente");
      firstTry = false; 
      outString = inScanner.next(); 
      if(admittedValues.contains(outString.toLowerCase()))
        admitted = true;
    }
//  inScanner.close();
  return outString;
  }
}
