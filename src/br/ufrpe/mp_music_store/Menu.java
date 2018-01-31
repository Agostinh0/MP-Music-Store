package br.ufrpe.mp_music_store;

import java.util.Scanner;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.IFachada;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import br.ufrpe.mp_music_store.negocio.beans.Usuario;
import br.ufrpe.mp_music_store.negocio.beans.Venda;
import br.ufrpe.mp_music_store.enumeracoes.TipoCliente;
import br.ufrpe.mp_music_store.enumeracoes.TipoFuncionario;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu {

	private IFachada fachada = Fachada.getInstance();
	private Scanner in = new Scanner(System.in);

	public void moldura(){
		System.out.println("**********MP MUSIC STORE**********\n");
	}

	public void menuCd(int operadorAuxiliar) throws Exception{
		this.moldura();

		while(operadorAuxiliar != 6){
			System.out.println("MENU DE CD's\n");
			System.out.println("Escolha uma operação: ");
			System.out.println("1 - Cadastrar\n"
					+ "2 - Pesquisar"
					+ "\n3 - Listar"
					+ "\n4 - Remover"
					+ "\n5 - Editar"
					+ "\n6 - Sair\n");

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

					try {
						fachada.adicionarCd(cd);
						fachada.salvarArquivoCds();
					} catch(ObjectExistException e){
						System.out.println(e.getMessage());
						e.printStackTrace();
					}


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
				try {
					busca = fachada.buscarCd(pesquisa);
				} catch(ObjectNotExistException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(busca != null){
					System.out.println("Informações gerais: ");
					System.out.println(busca.toString());
				}else{
					System.out.println("CD não encontrado!");
				}

				break;

			case 3 : 

				in.nextLine();//Limpa buffer
				System.out.println("Exibindo todos os CDs no sistema.");

				ArrayList<Cd> cdList = new ArrayList<>();

				cdList = fachada.listarCds();

				if(!cdList.isEmpty()){
					System.out.println("Lista de CDs:");
					cdList.forEach(System.out::println);
				}else{
					System.out.println("Nenhum CD no sistema.");
				}

				break;

			case 4: 
				this.moldura();

				in.nextLine();//Limpar buffer
				System.out.println("Remoção de CD's do catálogo");

				System.out.println("Digite o título do CD a ser removido: ");
				pesquisa = in.nextLine();

				Cd apaga = null;
				try {
					apaga = fachada.buscarCd(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(apaga != null){
					System.out.println("Deseja realmente excluir " 
							+apaga.getTitulo() + "?\n"
							+ "1 - Sim"
							+ "2 - Não");

					int decisao = in.nextInt();

					if(decisao == 1){
						try {
							fachada.removerCd(apaga.getTitulo());
							fachada.salvarArquivoCds();

						} catch(ErroRemoverException e) {

							System.out.println(e.getMessage());
							e.printStackTrace();
						}

						System.out.println("CD removido");
					}else{
						System.out.println("CD não removido!");
					}
				}

				break;

			case 5:
				this.moldura();

				in.nextLine();//Limpa buffer
				System.out.println("Atualização de informações de um CD");

				System.out.println("Digite o título do CD a ser editado: ");
				pesquisa = in.nextLine();

				Cd edita = null;
				try {
					edita = fachada.buscarCd(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}


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

					Cd c = new Cd(tituloCd, anoLancamentoCd, artistaCd, precoCd);

					try{
						fachada.atualizarCd(pesquisa, c);
						fachada.salvarArquivoCds();

					} catch(ErroAtualizarException e) {

						System.out.println(e.getMessage());
						e.printStackTrace();
					}

					System.out.println("Dados atualizados!");

				}else{
					System.out.println("CD não encontrado.");
				}

				break;

			case 6:

				System.out.println("Retornando ao menu...");
				in.nextLine();
				operadorAuxiliar = 6;
			}
		}
	}

	public void menuClientes(int operadorAuxiliar) throws Exception{
		this.moldura();

		while(operadorAuxiliar != 6){
			System.out.println("Menu de Clientes\n");
			System.out.println("Escolha uma operação: \n\n");
			System.out.println("1 - Cadastrar\n"
					+ "2 - Pesquisar"
					+ "\n3 - Listar"
					+ "\n4 - Remover"
					+ "\n5 - Editar"
					+ "\n6 - Sair\n");

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
					
					System.out.println("Será um cliente Premium? (1 para SIM/2 para NÃO)");
					int opcaoCliente = in.nextInt();
					
					in.nextLine();//Limpar buffer

					System.out.println("Endereço do cliente: ");
					String enderecoCliente = in.nextLine();

					System.out.println("Telefone para contato: ");
					long telefoneCliente = in.nextLong();

					in.nextLine();//Limpar buffer

					System.out.println("Defina um número de cadastro");
					int numCadastroCliente = in.nextInt();

					in.nextLine();//Limpar buffer

					if(opcaoCliente == 1){
					
						Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente,
								telefoneCliente, numCadastroCliente, TipoCliente.PREMIUM);
	
						try{
							fachada.adicionarCliente(cliente);
							fachada.salvarArquivoClientes();
	
						} catch(ObjectExistException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
					
					if(opcaoCliente == 2){
						Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente,
								telefoneCliente, numCadastroCliente, TipoCliente.NORMAL);
	
						try{
							fachada.adicionarCliente(cliente);
							fachada.salvarArquivoClientes();
	
						} catch(ObjectExistException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}

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
				try{
					busca = fachada.buscarCliente(pesquisa);

				} catch(ObjectNotExistException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(busca != null){
					System.out.println("Informações gerais: ");
					System.out.println(busca.toString());
				}else{
					System.out.println("Cliente não encontrado!");
				}
				break;

			case 3 : 

				in.nextLine();//Limpa buffer
				System.out.println("Exibindo todos os Clientes no sistema.");

				ArrayList<Cliente> clientList = new ArrayList<>();

				clientList = fachada.listarClientes();

				if(!clientList.isEmpty()){
					System.out.println("Lista de Clientes:");
					clientList.forEach(System.out::println);
				}else{
					System.out.println("Nenhum Cliente no sistema.");
				}

				break;

			case 4:
				this.moldura();
				in.nextLine();//Limpar buffer
				System.out.println("Remoção de Clientes");

				System.out.println("Digite o CPF do cliente a ser removido: ");
				pesquisa = in.nextLong();

				Cliente apaga = null;
				try{
					apaga = fachada.buscarCliente(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(apaga != null){
					System.out.println("Deseja realmente excluir " 
							+apaga.getNome() + "?\n"
							+ "1 - Sim "
							+ " 2 - Não");

					int decisao = in.nextInt();

					if(decisao == 1){
						try{
							fachada.removerCliente(apaga.getCpf());
							fachada.salvarArquivoClientes();
						} catch(ErroRemoverException e) {

							System.out.println(e.getMessage());
							e.printStackTrace();
						}

						System.out.println("Cliente removido");
					}else{
						System.out.println("Cliente não removido!");
					}
				}

				break;

			case 5: 
				this.moldura();

				in.nextLine();//Limpa buffer
				System.out.println("Atualização de informações de um Cliente");

				System.out.println("Digite o CPF do cliente a ser editado: ");
				pesquisa = in.nextLong();

				in.nextLine();//Limpar buffer

				Cliente edita = null;
				try{
					edita = fachada.buscarCliente(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(edita != null){
					System.out.println("Insira os novos dados abaixo: ");

					System.out.println("Nome: ");
					String nomeCliente = in.nextLine();

					System.out.println("CPF: ");
					long cpfCliente = in.nextLong();

					in.nextLine();//Limpar buffer
					
					System.out.println("Será um cliente Premium? (1 para SIM/2 para NÃO)");
					int opcaoCliente = in.nextInt();
					
					in.nextLine();//Limpar buffer

					System.out.println("Endereço: ");
					String enderecoCliente = in.nextLine();

					System.out.println("Telefone: ");
					long telefoneCliente = in.nextLong();

					in.nextLine();

					System.out.println("Número de Cadastro: ");
					int numCadastroCliente = in.nextInt();

					in.nextLine();//Limpar buffer

					if(opcaoCliente == 1){
						Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente, telefoneCliente, 
								numCadastroCliente, TipoCliente.PREMIUM);
	
						try{
							fachada.atualizarCliente(pesquisa, cliente);
							fachada.salvarArquivoClientes();
	
						} catch(ErroAtualizarException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}if(opcaoCliente == 2){
						Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente, telefoneCliente, 
								numCadastroCliente, TipoCliente.NORMAL);
	
						try{
							fachada.atualizarCliente(pesquisa, cliente);
							fachada.salvarArquivoClientes();
							
						} catch(ErroAtualizarException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}

					System.out.println("Dados atualizados!");
				}else{
					System.out.println("Cliente não encontrado.");
				}

				break;
			case 6:
				System.out.println("Retornando ao menu...");
				in.nextLine();//Limpar buffer
				operadorAuxiliar = 6;
			}
		}
	}

	public void menuFuncionarios(int operadorAuxiliar) throws Exception{
		this.moldura();

		while(operadorAuxiliar != 6){
			System.out.println("Menu de Funcionários\n");
			System.out.println("Escolha uma operação: \n\n");
			System.out.println("1 - Cadastrar\n"
					+ "2 - Pesquisar"
					+ "\n3 - Listar"
					+ "\n4 - Remover"
					+ "\n5 - Editar"
					+ "\n6 - Sair\n");

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
					
					System.out.println("O funcionário será um administrador? (1 para SIM/2 para NÃO)");
					int opcaoFunc = in.nextInt();
					
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
					
					System.out.println("Login: ");
					String loginFunc = in.nextLine();
					
					System.out.println("Senha: ");
					String senhaFunc = in.nextLine();
					
					if(opcaoFunc == 1){
						Funcionario funcionario = new Funcionario(nomeFuncionario, cpfFuncionario, enderecoFuncionario,
								telefoneFuncionario, salarioFuncionario, numContratoFuncionario, 
								new Usuario(loginFunc, senhaFunc), TipoFuncionario.ADM);
	
						try{
							fachada.adicionarFuncionario(funcionario);
							fachada.salvarArquivoFunc();
	
						} catch(ObjectExistException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
					
					if(opcaoFunc == 2){
						Funcionario funcionario = new Funcionario(nomeFuncionario, cpfFuncionario, enderecoFuncionario,
								telefoneFuncionario, salarioFuncionario, numContratoFuncionario, 
								new Usuario(loginFunc, senhaFunc), TipoFuncionario.COMUM);
	
						try{
							fachada.adicionarFuncionario(funcionario);
							fachada.salvarArquivoFunc();
	
						} catch(ObjectExistException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}

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
				try{
					busca = fachada.buscarFuncionario(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(busca != null){
					System.out.println("Informações gerais: ");
					System.out.println(busca.toString());
				}else{
					System.out.println("Funcionário não encontrado!");
				}

				break;

			case 3 : 

				in.nextLine();//Limpa buffer
				System.out.println("Exibindo todos os Funcionários no sistema.");

				ArrayList<Funcionario> funcList = new ArrayList<>();

				funcList = fachada.listarFuncionario();

				if(!funcList.isEmpty()){
					System.out.println("Lista de Funcionários:");
					funcList.forEach(System.out::println);
				}else{
					System.out.println("Nenhum Funcionário no sistema.");
				}

				break;

			case 4 : 
				this.moldura();
				in.nextLine();//Limpar buffer
				System.out.println("Remoção de Funcionários");

				System.out.println("Digite o CPF do funcionário a ser removido: ");
				pesquisa = in.nextLong();

				Funcionario apaga = null;
				try{
					apaga = fachada.buscarFuncionario(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(apaga != null){
					System.out.println("Deseja realmente excluir " 
							+apaga.getNome() + "?\n"
							+ "1 - Sim "
							+ " 2 - Não");

					int decisao = in.nextInt();

					if(decisao == 1){
						try{
							fachada.removerFuncionario(apaga.getCpf());
							fachada.salvarArquivoFunc();

						} catch(ErroRemoverException e) {

							System.out.println(e.getMessage());
							e.printStackTrace();
						}

						System.out.println("Funcionário removido");
					}else{
						System.out.println("Funcionário não removido!");
					}
				}

				break;

			case 5 : 
				this.moldura();

				in.nextLine();//Limpa buffer
				System.out.println("Atualização de informações de um funcionário");

				System.out.println("Digite o CPF do funcionário a ser editado: ");
				pesquisa = in.nextLong();

				in.nextLine();//Limpar buffer

				Funcionario edita = null;
				try{
					edita = fachada.buscarFuncionario(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(edita != null){
					System.out.println("Insira os novos dados abaixo: ");

					System.out.println("Nome: ");
					String nomeFuncionario = in.nextLine();

					System.out.println("CPF: ");
					long cpfFuncionario = in.nextLong();

					in.nextLine();//Limpar buffer
					
					System.out.println("O funcionário será um administrador? (1 para SIM/2 para NÃO)");
					int opcaoFunc = in.nextInt();
					
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
					
					System.out.println("Login: ");
					String loginFunc = in.nextLine();
					
					System.out.println("Senha: ");
					String senhaFunc = in.nextLine();
					
					if(opcaoFunc == 1){
						Funcionario f = new Funcionario(nomeFuncionario, cpfFuncionario, enderecoFuncionario, 
								telefoneFuncionario, salarioFuncionario, numContratoFuncionario, 
								new Usuario(loginFunc, senhaFunc), TipoFuncionario.ADM);
	
						try{
							fachada.atualizarFuncionario(pesquisa, f);
							fachada.salvarArquivoFunc();
	
						} catch(ErroAtualizarException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
					
					if(opcaoFunc == 2){
						Funcionario f = new Funcionario(nomeFuncionario, cpfFuncionario, enderecoFuncionario, 
								telefoneFuncionario, salarioFuncionario, numContratoFuncionario, 
								new Usuario(loginFunc, senhaFunc), TipoFuncionario.COMUM);
	
						try{
							fachada.atualizarFuncionario(pesquisa, f);
							fachada.salvarArquivoFunc();
	
						} catch(ErroAtualizarException e) {
	
							System.out.println(e.getMessage());
							e.printStackTrace();
						} catch(InvalidCpfException e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}

					System.out.println("Dados atualizados!");
				}else{
					System.out.println("Funcionário não encontrado.");
				}

				break;
			case 6:
				System.out.println("Retornando ao menu...");
				in.nextLine();//Limpar buffer
				operadorAuxiliar = 6;
			}
		}
	}

	public void menuVendas (int operadorAuxiliar) throws Exception {
		this.moldura();

		while(operadorAuxiliar != 5){
			System.out.println("Menu de vendas\n");
			System.out.println("Escolha uma operação: \n\n");
			System.out.println("1 - Registrar venda"
					+ "\n2 - Buscar venda nos registros"
					+ "\n3 - Listar todas as vendas"
					+ "\n4 - Remover dos registros"
					+ "\n5 - Sair");

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

						try{
							buscaCliente = fachada.buscarCliente(pesquisaCliente);

						} catch(ObjectNotExistException e) {

							System.out.println(e.getMessage());
							e.printStackTrace();
						}

					}while(buscaCliente == null);

					Cd buscaCd = null;
					in.nextLine();//Limpar buffer

					do{

						System.out.println("Digite o título do CD a ser vendido: ");
						String pesquisaCd = in.nextLine();

						try{
							buscaCd = fachada.buscarCd(pesquisaCd);

						} catch(ObjectNotExistException e) {

							System.out.println(e.getMessage());
							e.printStackTrace();
						}

					}while(buscaCd == null);
					
					System.out.println("A seguir, você irá inserir a data, mês e ano da venda (DD/MM/YYYY). ");
					System.out.println("Data: ");
					int dia = in.nextInt();
					
					in.nextLine();//Limpar buffer
					
					System.out.println("Mês: ");
					int mes = in.nextInt();
					
					in.nextLine();//Limpar buffer
					
					System.out.println("Ano: ");
					int ano = in.nextInt();
					
					in.nextLine();//Limpar buffer
					
					LocalDate dataVenda = LocalDate.of(ano, mes, dia);
					
					System.out.println("A seguir, você irá inserir a hora e minuto da venda: ");
					System.out.println("Hora: ");
					int hora = in.nextInt();
					
					in.nextLine();//Limpar buffer
					
					System.out.println("Minutos: ");
					int minutos = in.nextInt();
					
					in.nextLine();//Limpar buffer
					
					LocalTime horaVenda = LocalTime.of(hora, minutos);
					
					System.out.println("Defina um código para representar esta venda: ");
					long codigoVenda = in.nextLong();

					Venda venda = new Venda(buscaCliente, buscaCd, codigoVenda, dataVenda, horaVenda);
					try{
						fachada.registrarVenda(venda);
						fachada.salvarArquivoVendas();

					} catch(ObjectExistException e) {

						System.out.println(e.getMessage());
						e.printStackTrace();
					}

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
				try{
					busca = fachada.buscarVenda(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(busca != null){
					System.out.println("Informações gerais: ");
					System.out.println(busca.toString());
				}else{
					System.out.println("Venda não encontrada!");
				}

				break;

			case 3: 

				in.nextLine();//Limpa buffer
				System.out.println("Exibindo todas as Vendas no sistema.");

				ArrayList<Venda> vendaList = new ArrayList<>();

				vendaList = fachada.listarVendas();

				if(!vendaList.isEmpty()){
					System.out.println("Lista de Vendas:");
					vendaList.forEach(System.out::println);
				}else{
					System.out.println("Nenhuma Venda no sistema.");
				}

				break;

			case 4:
				this.moldura();
				in.nextLine();//Limpar buffer
				System.out.println("Remoção de vendas do histórico");

				System.out.println("Digite o código da venda a ser removida: ");
				pesquisa = in.nextLong();

				Venda apaga = null;
				try{
					apaga = fachada.buscarVenda(pesquisa);

				} catch(ObjectNotExistException e) {

					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				if(apaga != null){
					System.out.println("Deseja realmente excluir " 
							+apaga.getCodigoVenda() + "?\n"
							+ "1 - Sim "
							+ " 2 - Não");

					int decisao = in.nextInt();

					if(decisao == 1){
						try{
							fachada.removerVenda(apaga.getCodigoVenda());
							fachada.salvarArquivoVendas();

						} catch(ErroRemoverException e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
						}

						System.out.println("Venda removida do histórico");
					}else{
						System.out.println("Venda não removida!");
					}
				}

				break;
			case 5: 
				System.out.println("Retornando ao menu...");
				in.nextLine();//Limpar buffer
				operadorAuxiliar = 5;
			}
		}
	}



	public void menuPrincipal() throws Exception{
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
				try {
					menuCd(operadorAuxiliar);
				} catch(InputMismatchException e) {
					System.out.println("Carácter incorreto.");
					in.nextLine();
				}
				break;

			case 2: 
				try {
					menuClientes(operadorAuxiliar);
				} catch(InputMismatchException e) {
					System.out.println("Carácter incorreto.");
					in.nextLine();
				}
				break;

			case 3 : 
				try {
					menuFuncionarios(operadorAuxiliar);
				} catch(InputMismatchException e) {
					System.out.println("Carácter incorreto.");
					in.nextLine();
				}
				break;

			case 4 : 
				try {
					menuVendas(operadorAuxiliar);
				} catch(InputMismatchException e) {
					System.out.println("Carácter incorreto.");
					in.nextLine();
				}
				break;

			case 5:
				System.out.println("Obrigado por usar nosso sistema.");
			}
		}while(operacao != 5);
	}
}
