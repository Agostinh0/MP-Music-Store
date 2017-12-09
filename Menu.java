package br.ufrpe.mp_music_store;
import java.util.Scanner;
import br.ufrpe.mp_music_store.negocio.beans.Cd;

public class Menu {
	
	public static void main(String[] args){
		
		Cd kek = new Cd("Cast In Steel", 2015 , "a-ha", 22.00f);
		
		System.out.println(kek.toString());
	}
}
