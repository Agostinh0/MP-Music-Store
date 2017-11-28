package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.negocio.beans.*;

public class Fachada {
	private CadastroClientes cadastroClientes;
	private CadastroFuncionarios cadastroFuncionarios;
	private CatalogoCds cadastroCds;
	private static Fachada instance;
	
	
	private Fachada() {
		this.cadastroClientes = CadastroClientes.getInstance();
		this.cadastroFuncionarios = CadastroFuncionarios.getInstance();
		this.cadastroCds = CatalogoCds.getInstance();
	}
	
	public static Fachada getInstance() {
		if(instance == null) {
			instance = new Fachada();
		}
		
		return instance;
	}
	
	/* Clientes */
	public void adicionarCliente(Cliente c) {
		this.cadastroClientes.adicionarCliente(c);
	}
	
	public Cliente buscarCliente(int cpf) {
		return this.cadastroClientes.buscarCliente(cpf);
	}
	
	public void removerCliente(int cpf) {
		this.cadastroClientes.removerCliente(cpf);
	}
	
	public boolean existeCliente(int cpf) {
		return this.cadastroClientes.existeCliente(cpf);
	}
	
	/* Funcionários */
	public void adicionarFuncionario(Funcionario f) {
		this.cadastroFuncionarios.adicionaFuncionario(f);
	}
	
	public Funcionario buscarFuncionario(int cpf) {
		return this.cadastroFuncionarios.buscarFuncionario(cpf);
	}
	
	public void removerFuncionario(int cpf) {
		this.cadastroFuncionarios.removerFuncionario(cpf);
	}
	
	public boolean existeFuncionario(int cpf) {
		return this.cadastroFuncionarios.existeFuncionario(cpf);
	}
	
	/* CDs */
	public void adicionarCd(Cd c) {
		this.cadastroCds.adicionaCds(c);
	}
	
	public Cd buscarCd(String nome) {
		return this.cadastroCds.buscarCds(nome);
	}
	
	public void removerCd(String nome) {
		this.cadastroCds.removerCds(nome);
	}
	
	public boolean existeCd(String titulo) {
		return this.cadastroCds.existeCd(titulo);
	}
	
	public void atualizarCd(String nome, int anoLancamento, String artista, float preco){
		this.cadastroCds.atualizarCds(nome, anoLancamento, artista, preco);
	}
}
