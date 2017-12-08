package br.ufrpe.mp_music_store;

import java.util.Scanner;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import br.ufrpe.mp_music_store.negocio.beans.Venda;

public class Menu {
	
	private Fachada fachada = Fachada.getInstance();
	private Scanner in = new Scanner(System.in);
	
	public void moldura(){
		System.out.println("**********MP MUSIC STORE**********\n");
	}
	
	public void menuCd(int operadorAuxiliar) {
		this.moldura();
		
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
						
						fachada.atualizarCd(pesquisa, tituloCd, anoLancamentoCd, artistaCd, precoCd);
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
	}
	
	public void menuClientes(int operadorAuxiliar) {
		this.moldura();
		
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
								telefoneCliente, numCadastroCliente, true);
						
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
							
							fachada.atualizarCliente(nomeCliente, pesquisa, cpfCliente, enderecoCliente,
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
	
	public void menuFuncionarios(int operadorAuxiliar) {
		this.moldura();
		
		while(operadorAuxiliar != 5){
			System.out.println("Menu de Funcionários\n");
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
				System.out.println("Nome do funcionário: ");
				String nomeFuncionario = in.nextLine();
				
				System.out.println("CPF do funcionário: ");
				long cpfFuncionario = in.nextLong();
				
				in.nextLine();//Limpar buffer
				
				System.out.println("Endereço do funcionário: ");
				String enderecoFuncionario = in.nextLine();
				
				System.out.println("Telefone para contato: ");
				long telefoneFuncionario = in.nextLong();
				
				in.nextLine();//Limpar buffer
				
				System.out.println("Defina o salário do funcionário: ");
				float salarioFuncionario = in.nextFloat();
				
				in.nextLine();//Limpar buffer
				
				System.out.println("Defina um número de contrato(4 dígitos)");
				int numContratoFuncionario = in.nextInt();
				
				in.nextLine();//Limpar buffer
				
				Funcionario funcionario = new Funcionario(nomeFuncionario, cpfFuncionario, enderecoFuncionario,
						telefoneFuncionario, salarioFuncionario, numContratoFuncionario);
				
				fachada.adicionarFuncionario(funcionario);
				realizado = true;
				System.out.println("Funcionario cadastrado com sucesso!");
	
				}while(realizado == false);
				
				break;
				
			case 2: 
					this.moldura();
					long pesquisa;
					
					in.nextLine();//Limpar buffer
					System.out.println("Exibir informações de um funcionário");
					
					System.out.println("Digite o CPF do funcionário: ");
					pesquisa = in.nextLong();
					
					Funcionario busca = null;
					busca = fachada.buscarFuncionario(pesquisa);
					
					if(busca != null){
						System.out.println("Informações gerais: ");
						System.out.println(busca.toString());
					}else{
						System.out.println("Funcionário não encontrado!");
					}
					
					break;
					
			case 3 : 
					this.moldura();
					in.nextLine();//Limpar buffer
					System.out.println("Remoção de Funcionários");
					
					System.out.println("Digite o CPF do funcionário a ser removido: ");
					pesquisa = in.nextLong();
					
				    Funcionario apaga = null;
					apaga = fachada.buscarFuncionario(pesquisa);
					
					if(apaga != null){
						System.out.println("Deseja realmente excluir " 
											+apaga.getNome() + "?\n"
											+ "1 - Sim "
											+ " 2 - Não");
						
						int decisao = in.nextInt();
						
						if(decisao == 1){
							fachada.removerFuncionario(apaga.getCpf());
							System.out.println("Funcionário removido");
						}else{
							System.out.println("Funcionário não removido!");
						}
					}
				
					break;
					
			case 4 : 
					this.moldura();
					
					in.nextLine();//Limpa buffer
					System.out.println("Atualização de informações de um funcionário");
					
					System.out.println("Digite o CPF do funcionário a ser editado: ");
					pesquisa = in.nextLong();
					
					in.nextLine();//Limpar buffer
					
					Funcionario edita = null;
					edita = fachada.buscarFuncionario(pesquisa);
					
					if(edita != null){
						System.out.println("Insira os novos dados abaixo: ");
						
						System.out.println("Nome: ");
						String nomeFuncionario = in.nextLine();
						
						System.out.println("CPF: ");
						long cpfFuncionario = in.nextLong();
						
						in.nextLine();//Limpar buffer
						
						System.out.println("Endereço: ");
						String enderecoFuncionario = in.nextLine();
						
						System.out.println("Telefone: ");
					    long telefoneFuncionario = in.nextLong();
					    
					    in.nextLine();
					    
					    System.out.println("Salário: ");
					    float salarioFuncionario = in.nextFloat();
					    
					    in.nextLine();
					    
					    System.out.println("Número de Contrato (4 dígitos): ");
					    int numContratoFuncionario = in.nextInt();
					    
					    in.nextLine();
						
						fachada.atualizarFuncionario(nomeFuncionario, pesquisa, cpfFuncionario, enderecoFuncionario,
								telefoneFuncionario, salarioFuncionario, numContratoFuncionario);
						System.out.println("Dados atualizados!");
					}else{
						System.out.println("Funcionário não encontrado.");
					}
					
					break;
		case 5:
				System.out.println("Retornando ao menu...");
				in.nextLine();//Limpar buffer
				operadorAuxiliar= 5;
			}
		}
	}
	
	public void menuVendas(int operadorAuxiliar) {
		this.moldura();
		
		while(operadorAuxiliar != 4){
			System.out.println("Menu de vendas\n");
			System.out.println("Escolha uma operação: \n\n");
			System.out.println("1 - Registrar venda"
								+ "\n2 - Buscar venda nos registros"
								+ "\n3 - Remover dos registros"
								+ "\n4 - Sair");
			
			int op = in.nextInt();
			
			switch(op){
				
				case 1:
					
						this.moldura();
						boolean realizado = false;
						
						in.nextLine();//Limpar buffer
						
						do{
							
							Cliente buscaCliente = null;
							
							do{
								
							System.out.println("Digite as informações da venda: ");
							System.out.println("\nDigite o CPF do cliente que está comprando: ");
							long pesquisaCliente = in.nextLong();
							
							buscaCliente = fachada.buscarCliente(pesquisaCliente);
							
							}while(buscaCliente == null);
							
							Cd buscaCd = null;
							in.nextLine();//Limpar buffer
							
							do{
								
							System.out.println("Digite o título do CD a ser vendido: ");
							String pesquisaCd = in.nextLine();
							
							buscaCd = fachada.buscarCd(pesquisaCd);
								
							}while(buscaCd == null);
							
							System.out.println("Defina um código para representar esta venda: ");
							long codigoVenda = in.nextLong();
							
							Venda venda = new Venda(buscaCliente, buscaCd, codigoVenda);
							fachada.registrarVenda(venda);
							System.out.println("Venda registrada! Código da venda: " + venda.getCodigoVenda());
							realizado = true;
							
						}while(realizado == false);
						
						break;
				case 2: 
						this.moldura();
						long pesquisa;
						
						in.nextLine();//Limpar buffer
						System.out.println("Buscar informções sobre uma venda");
						System.out.println("Digite o código da venda: ");
						pesquisa = in.nextLong();
						
						Venda busca = null;
						busca = fachada.buscarVenda(pesquisa);
						
						if(busca != null){
							System.out.println("Informações gerais: ");
							System.out.println(busca.toString());
						}else{
							System.out.println("Venda não encontrada!");
						}
						
						break;
				
				case 3:
						this.moldura();
						in.nextLine();//Limpar buffer
						System.out.println("Remoção de vendas do histórico");
						
						System.out.println("Digite o código da venda a ser removida: ");
						pesquisa = in.nextLong();
						
					    Venda apaga = null;
						apaga = fachada.buscarVenda(pesquisa);
						
						if(apaga != null){
							System.out.println("Deseja realmente excluir " 
												+apaga.getCodigoVenda() + "?\n"
												+ "1 - Sim "
												+ " 2 - Não");
							
							int decisao = in.nextInt();
							
							if(decisao == 1){
								fachada.removerVenda(apaga.getCodigoVenda());
								System.out.println("Venda removida do histórico");
							}else{
								System.out.println("Venda não removida!");
							}
						}
					
						break;
				case 4 : 
						System.out.println("Retornando ao menu...");
						in.nextLine();//Limpar buffer
						operadorAuxiliar = 4;
			}
		}
	}

	
	
	public void menuPrincipal(){
	int operacao;
	int operadorAuxiliar = 0;
		
	do{
		this.moldura();
		System.out.println("Escolha uma operação: ");
		System.out.println("1 - CD's\n"
							+ "2 - Clientes\n"
							+ "3 - Funcionários\n"
							+ "4 - Vendas\n"
							+ "5 - Sair\n");
		
		operacao = in.nextInt();
		
		switch(operacao){
			case 1 : 
					menuCd(operadorAuxiliar);
					break;
					
			case 2: 
					menuClientes(operadorAuxiliar);
					break;
					
			case 3 : 
					menuFuncionarios(operadorAuxiliar);
					break;
					
			case 4 : 
					menuVendas(operadorAuxiliar);
					break;
					
			case 5:
					System.out.println("Obrigado por usar nosso sistema.");
		}

		}while(operacao != 5);
	}
}
