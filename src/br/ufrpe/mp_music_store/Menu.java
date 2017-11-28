package br.ufrpe.mp_music_store;

import java.util.Scanner;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;

public class Menu {
	
	private Fachada fachada = Fachada.getInstance();
	private Scanner in = new Scanner(System.in);
	
	public void moldura(){
		System.out.println("**********MP MUSIC STORE**********\n");
	}
	
	public void menuPrincipal(){
	int operacao;
		
	do{
		this.moldura();
		System.out.println("Escolha uma opera��o: ");
		System.out.println("1 - CD's\n"
							+ "2 - Clientes\n"
							+ "3 - Funcion�rios\n"
							+ "4 - Sair\n");
		
		operacao = in.nextInt();
		
		switch(operacao){
			case 1 : 
					
					this.moldura();
					int operadorAuxiliar = 0;
					
					while(operadorAuxiliar != 5){
						System.out.println("MENU DE CD's\n");
						System.out.println("Escolha uma opera��o: ");
						System.out.println("1 - Cadastrar\n"
											+ "2 - Pesquisar"
											+ "\n3 - Remover"
											+ "\n4 - Editar"
											+ "\n5 - Sair\n");
					
					int op = in.nextInt();
					
					switch(op){
						case 1: 
							
							boolean realizado = false;
							String pesquisa;
							
							do{
								in.nextLine();//Limpar buffer
								System.out.println();
								
								System.out.println("T�tulo do CD: ");
								String tituloCd = in.nextLine();
								
								System.out.println("Ano de lan�amento: ");
								int anoLancamentoCd = in.nextInt();
								
								in.nextLine();//Limpar buffer
								
								System.out.println("Artista: ");
								String artistaCd = in.nextLine();
								
								System.out.println("Defina um pre�o ");
								float precoCd = in.nextFloat();
								
								Cd cd = new Cd(tituloCd, anoLancamentoCd, artistaCd, precoCd);
								fachada.adicionarCd(cd);
								
								realizado = true;
								System.out.println("CD catalogado com sucesso!");
								
							}while(realizado == false);
							
							break;
						
						case 2 : 
							
							in.nextLine();//Limpa buffer
							System.out.println("Exibir informa��es de um CD");
							
							System.out.println("Digite o t�tulo do CD: ");
							pesquisa = in.nextLine();
							
							Cd busca = null;
							busca = fachada.buscarCd(pesquisa);
							
							if(busca != null){
								System.out.println("Informa��es gerais: ");
								System.out.println(busca.toString());
							}else{
								System.out.println("CD n�o encontrado!");
							}
							
							break;
					
						case 3: 
							
							in.nextLine();//Limpar buffer
							System.out.println("Remo��o de CD's do cat�logo");
							
							System.out.println("Digite o t�tulo do CD a ser removido: ");
							pesquisa = in.nextLine();
							
							Cd apaga = null;
							apaga = fachada.buscarCd(pesquisa);
							
							if(apaga != null){
								System.out.println("Deseja realmente excluir " 
													+apaga.getTitulo() + "?\n"
													+ "1 - Sim"
													+ "2 - N�o");
								
								int decisao = in.nextInt();
								
								if(decisao == 1){
									fachada.removerCd(apaga.getTitulo());
									System.out.println("CD removido");
								}else{
									System.out.println("CD n�o removido!");
								}
							}
						
							break;
						
						case 4:
							
							in.nextLine();//Limpa buffer
							System.out.println("Atualiza��o de informa��es de um CD");
							
							System.out.println("Digite o t�tulo do CD a ser editado: ");
							pesquisa = in.nextLine();
							
							Cd edita = null;
							edita = fachada.buscarCd(pesquisa);
							
							if(edita != null){
								System.out.println("Insira os novos dados abaixo: ");
								
								System.out.println("T�tulo do CD: ");
								String tituloCd = in.nextLine();
								
								System.out.println("Ano de lan�amento: ");
								int anoLancamentoCd = in.nextInt();
								
								in.nextLine();//Limpar buffer
								
								System.out.println("Artista: ");
								String artistaCd = in.nextLine();
								
								System.out.println("Defina um pre�o ");
								float precoCd = in.nextFloat();
								
								fachada.atualizarCd(tituloCd, anoLancamentoCd, artistaCd, precoCd);
								System.out.println("Dados atualizados!");
							}else{
								System.out.println("CD n�o encontrado.");
							}
							
							break;
						
						case 5:
							
							System.out.println("Retornando ao menu...");
							in.nextLine();
							operadorAuxiliar = 5;
					}
					
				}
			}

		}while(operacao != 5);
		//Ainda a implementar 
	}
}
