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
		System.out.println("Escolha uma operação: ");
		System.out.println("1 - CD's\n"
							+ "2 - Clientes\n"
							+ "3 - Funcionários\n"
							+ "4 - Sair\n");
		
		operacao = in.nextInt();
		
		switch(operacao){
			case 1 : 
					
					this.moldura();
					int operadorAuxiliar = 0;
					
					while(operadorAuxiliar != 5){
						System.out.println("MENU DE CD's\n");
						System.out.println("Escolha uma operação: ");
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
								
								System.out.println("Título do CD: ");
								String tituloCd = in.nextLine();
								
								System.out.println("Ano de lançamento: ");
								int anoLancamentoCd = in.nextInt();
								
								in.nextLine();//Limpar buffer
								
								System.out.println("Artista: ");
								String artistaCd = in.nextLine();
								
								System.out.println("Defina um preço ");
								float precoCd = in.nextFloat();
								
								Cd cd = new Cd(tituloCd, anoLancamentoCd, artistaCd, precoCd);
								fachada.adicionarCd(cd);
								
								realizado = true;
								System.out.println("CD catalogado com sucesso!");
								
							}while(realizado == false);
							
							break;
						
						case 2 : 
							
							in.nextLine();//Limpa buffer
							System.out.println("Exibir informações de um CD");
							
							System.out.println("Digite o título do CD: ");
							pesquisa = in.nextLine();
							
							Cd busca = null;
							busca = fachada.buscarCd(pesquisa);
							
							if(busca != null){
								System.out.println("Informações gerais: ");
								System.out.println(busca.toString());
							}else{
								System.out.println("CD não encontrado!");
							}
							
							break;
					
						case 3: 
							this.moldura();
							
							in.nextLine();//Limpar buffer
							System.out.println("Remoção de CD's do catálogo");
							
							System.out.println("Digite o título do CD a ser removido: ");
							pesquisa = in.nextLine();
							
							Cd apaga = null;
							apaga = fachada.buscarCd(pesquisa);
							
							if(apaga != null){
								System.out.println("Deseja realmente excluir " 
													+apaga.getTitulo() + "?\n"
													+ "1 - Sim"
													+ "2 - Não");
								
								int decisao = in.nextInt();
								
								if(decisao == 1){
									fachada.removerCd(apaga.getTitulo());
									System.out.println("CD removido");
								}else{
									System.out.println("CD não removido!");
								}
							}
						
							break;
						
						case 4:
							this.moldura();
							
							in.nextLine();//Limpa buffer
							System.out.println("Atualização de informações de um CD");
							
							System.out.println("Digite o título do CD a ser editado: ");
							pesquisa = in.nextLine();
							
							Cd edita = null;
							edita = fachada.buscarCd(pesquisa);
							
							if(edita != null){
								System.out.println("Insira os novos dados abaixo: ");
								
								System.out.println("Título do CD: ");
								String tituloCd = in.nextLine();
								
								System.out.println("Ano de lançamento: ");
								int anoLancamentoCd = in.nextInt();
								
								in.nextLine();//Limpar buffer
								
								System.out.println("Artista: ");
								String artistaCd = in.nextLine();
								
								System.out.println("Defina um preço ");
								float precoCd = in.nextFloat();
								
								fachada.atualizarCd(tituloCd, anoLancamentoCd, artistaCd, precoCd);
								System.out.println("Dados atualizados!");
							}else{
								System.out.println("CD não encontrado.");
							}
							
							break;
						
						case 5:
							
							System.out.println("Retornando ao menu...");
							in.nextLine();
							operadorAuxiliar = 5;
				} 
			}
			case 2: 
					this.moldura();
					operadorAuxiliar = 0;
					
					while(operadorAuxiliar != 5){
						System.out.println("Menu de Clientes\n");
						System.out.println("Escolha uma operação: \n\n");
						System.out.println("1 - Cadastrar\n"
											+ "2 - Pesquisar\n"
						                    + "3 - Remover\n"
											+ "4 - Editar\n"
						                    + "5 - Sair\n");
						
						int op = in.nextInt();
						
						switch(op){
							
							case 1:
									this.moldura();
									boolean realizado = false;
									
									do{
									in.nextLine();//Limpar buffer
									System.out.println("Nome do cliente: ");
									String nomeCliente = in.nextLine();
									
									System.out.println("CPF do cliente: ");
									long cpfCliente = in.nextLong();
									
									in.nextLine();//Limpar buffer
									
									System.out.println("Endereço do cliente: ");
									String enderecoCliente = in.nextLine();
									
									System.out.println("Telefone para contato: ");
									long telefoneCliente = in.nextLong();
									
									in.nextLine();//Limpar buffer
									
									System.out.println("Defina um número de cadastro");
									int numCadastroCliente = in.nextInt();
									
									in.nextLine();//Limpar buffer
									
									Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente,
											telefoneCliente, numCadastroCliente);
									
									fachada.adicionarCliente(cliente);
									realizado = true;
									System.out.println("Cliente cadastrado com sucesso!");
						
									}while(realizado == false);
									
									break;
							
							case 2: 
									this.moldura();
									long pesquisa;
									
									in.nextLine();//Limpar buffer
									System.out.println("Exibir informações de um Cliente");
									
									System.out.println("\nDigite o CPF do cliente: ");
									pesquisa = in.nextLong();
									
									Cliente busca = null;
									busca = fachada.buscarCliente(pesquisa);
									
									if(busca != null){
										System.out.println("Informações gerais: ");
										System.out.println(busca.toString());
									}else{
										System.out.println("Cliente não encontrado!");
									}
									break;
									
							case 3:
									this.moldura();
									in.nextLine();//Limpar buffer
									System.out.println("Remoção de Clientes");
									
									System.out.println("Digite o CPF do cliente a ser removido: ");
									pesquisa = in.nextLong();
									
									Cliente apaga = null;
									apaga = fachada.buscarCliente(pesquisa);
									
									if(apaga != null){
										System.out.println("Deseja realmente excluir " 
															+apaga.getNome() + "?\n"
															+ "1 - Sim "
															+ " 2 - Não");
										
										int decisao = in.nextInt();
										
										if(decisao == 1){
											fachada.removerCliente(apaga.getCpf());
											System.out.println("Cliente removido");
										}else{
											System.out.println("Cliente não removido!");
										}
									}
								
									break;
									
							case 4: 
									this.moldura();
									
									in.nextLine();//Limpa buffer
									System.out.println("Atualização de informações de um Cliente");
									
									System.out.println("Digite o CPF do cliente a ser editado: ");
									pesquisa = in.nextLong();
									
									in.nextLine();//Limpar buffer
									
									Cliente edita = null;
									edita = fachada.buscarCliente(pesquisa);
									
									if(edita != null){
										System.out.println("Insira os novos dados abaixo: ");
										
										System.out.println("Nome: ");
										String nomeCliente = in.nextLine();
										
										System.out.println("CPF: ");
										long cpfCliente = in.nextLong();
										
										in.nextLine();//Limpar buffer
										
										System.out.println("Endereço: ");
										String enderecoCliente = in.nextLine();
										
										System.out.println("Telefone: ");
									    long telefoneCliente = in.nextLong();
									    
									    in.nextLine();
									    
									    System.out.println("Número de Cadastro: ");
									    int numCadastroCliente = in.nextInt();
									    
									    in.nextLine();
										
										fachada.atualizarCliente(nomeCliente, cpfCliente, enderecoCliente,
												telefoneCliente, numCadastroCliente);
										System.out.println("Dados atualizados!");
									}else{
										System.out.println("Cliente não encontrado.");
									}
									
									break;
							case 5:
									System.out.println("Retornando ao menu...");
									in.nextLine();//Limpar buffer
									operadorAuxiliar= 5;
						}
					}
		}

		}while(operacao != 5);
		//Ainda a implementar 
	}
}
