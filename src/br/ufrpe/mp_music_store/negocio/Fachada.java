package br.ufrpe.mp_music_store.negocio;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.InvalidCadException;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.InvalidPasswordException;
import br.ufrpe.mp_music_store.exceptions.InvalidTeleException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.beans.*;

public class Fachada implements IFachada{
	private CadastroClientes cadastroClientes;
	private CadastroFuncionarios cadastroFuncionarios;
	private CatalogoCds cadastroCds;
	private RegistroVendas registroVendas;
	private static Fachada instance;


	private Fachada() {
		this.cadastroClientes = CadastroClientes.getInstance();
		this.cadastroFuncionarios = CadastroFuncionarios.getInstance();
		this.cadastroCds = CatalogoCds.getInstance();
		this.registroVendas = RegistroVendas.getInstance();
	}

	public static Fachada getInstance() {
		if(instance == null) {
			instance = new Fachada();
		}

		return instance;
	}

	/* Clientes */
	public void adicionarCliente(Cliente c) throws ObjectExistException, InvalidCpfException, InvalidTeleException{
		this.cadastroClientes.adicionarCliente(c);
	}

	public Cliente buscarCliente(long cpf) throws ObjectNotExistException{
		return this.cadastroClientes.buscarCliente(cpf);
	}

	public void removerCliente(long cpf) throws ObjectNotExistException, ErroRemoverException{
		this.cadastroClientes.removerCliente(cpf);
	}

	public void atualizarCliente(long pesquisa, Cliente cliente) throws ObjectNotExistException, ErroAtualizarException, InvalidCpfException, InvalidTeleException{
		this.cadastroClientes.atualizarCliente(pesquisa, cliente);
	}

	public boolean existeCliente(long cpf) {
		return this.cadastroClientes.existeCliente(cpf);
	}

	public ArrayList<Cliente> listarClientes() {
		return this.cadastroClientes.listarCliente();
	}
	
	public void salvarArquivoClientes(){
		this.cadastroClientes.salvarArquivo();
	}

	/* Funcionários */
	public void adicionarFuncionario(Funcionario f) throws ObjectExistException, InvalidCpfException, InvalidTeleException, InvalidCadException, InvalidPasswordException{
		this.cadastroFuncionarios.adicionaFuncionario(f);
	}

	public Funcionario buscarFuncionario(long cpf) throws ObjectNotExistException{
		return this.cadastroFuncionarios.buscarFuncionario(cpf);
	}

	public void atualizarFuncionario(long pesquisa, Funcionario f) throws ObjectNotExistException, ErroAtualizarException, InvalidCpfException, InvalidTeleException, InvalidCadException, InvalidPasswordException{
		this.cadastroFuncionarios.atualizar(pesquisa, f);
	}

	public void removerFuncionario(long cpf) throws ObjectNotExistException, ErroRemoverException{
		this.cadastroFuncionarios.removerFuncionario(cpf);
	}

	public boolean existeFuncionario(long cpf) {
		return this.cadastroFuncionarios.existeFuncionario(cpf);
	}

	public ArrayList<Funcionario> listarFuncionario() {
		return this.cadastroFuncionarios.listarFuncionarios();
	}
	
	public boolean verificaLogin(Usuario u) {
		return this.cadastroFuncionarios.login(u);
	}
	
	public boolean verificaLoginAdm(Usuario u) {
		return this.cadastroFuncionarios.admLogin(u);
	}
	
	public void salvarArquivoFunc(){
		this.cadastroFuncionarios.salvarArquivo();
	}

	/* CDs */
	public void adicionarCd(Cd c) throws ObjectExistException{
		this.cadastroCds.adicionaCds(c);
	}

	public Cd buscarCd(String nome) throws ObjectNotExistException{
		return this.cadastroCds.buscarCds(nome);
	}

	public void removerCd(String nome) throws ObjectNotExistException, ErroRemoverException{
		this.cadastroCds.removerCds(nome);
	}

	public boolean existeCd(String titulo) {
		return this.cadastroCds.existeCd(titulo);
	}

	public void atualizarCd(String pesquisa, Cd c) throws ObjectNotExistException, ErroAtualizarException{
		this.cadastroCds.atualizarCds(pesquisa, c);
	}

	public ArrayList<Cd> listarCds() {
		return this.cadastroCds.listarCds();
	}
	
	public void salvarArquivoCds(){
		this.cadastroCds.salvarArquivo();
	}

	/* Vendas */
	public void registrarVenda(Venda venda) throws ObjectExistException{
		this.registroVendas.registrarVenda(venda);
	}

	public Venda buscarVenda(long codigo) throws ObjectNotExistException{
		return this.registroVendas.buscarVenda(codigo);
	}

	public void removerVenda(long codigo) throws ObjectNotExistException, ErroRemoverException{
		this.registroVendas.remover(codigo);
	}

	public boolean existeVenda(long codigo){
		return this.registroVendas.existeVenda(codigo);
	}

	public ArrayList<Venda> listarVendas() {
		return this.registroVendas.listarVendas();
	}
	
	public void salvarArquivoVendas(){
		this.registroVendas.salvarArquivo();
	}
}
