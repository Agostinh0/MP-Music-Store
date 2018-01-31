package br.ufrpe.mp_music_store.negocio;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.dados.RepositorioFuncionarios;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.InvalidCadException;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.InvalidPasswordException;
import br.ufrpe.mp_music_store.exceptions.InvalidTeleException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import br.ufrpe.mp_music_store.negocio.beans.Usuario;

public class CadastroFuncionarios {
	private RepositorioFuncionarios repositorio;
	private static CadastroFuncionarios instance;

	private CadastroFuncionarios() {
		this.repositorio = RepositorioFuncionarios.getInstance();
	}

	public static CadastroFuncionarios getInstance() {
		if(instance == null) {
			instance = new CadastroFuncionarios();
		}

		return instance;
	}

	public void adicionaFuncionario(Funcionario f) throws ObjectExistException, InvalidCpfException, InvalidTeleException, InvalidCadException, InvalidPasswordException{
		if(f == null) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(String.valueOf(f.getCpf()).length() != 11) {
			throw new InvalidCpfException();
		}
		else if(String.valueOf(f.getTelefone()).length() < 11) {
			throw new InvalidTeleException();
		}
		else if(String.valueOf(f.getNumContrato()).length() < 4) {
			throw new InvalidCadException();
		}
		else if(f.getUsuario().getPassword().length() < 6) {
			throw new InvalidPasswordException();
		}
		else if(this.repositorio.existe(f.getCpf())) {
			throw new ObjectExistException();
		}
		else {
			repositorio.cadastrar(f);
		}
	}

	public Funcionario buscarFuncionario(long cpf) throws ObjectNotExistException{
		if(!this.repositorio.existe(cpf)) {
			throw new ObjectNotExistException();
		}

		return this.repositorio.buscar(cpf);
	}

	public void atualizar(long pesquisa, Funcionario f) throws ObjectNotExistException, ErroAtualizarException, InvalidCpfException, InvalidTeleException, InvalidCadException, InvalidPasswordException{
		if(f == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}
		else if(String.valueOf(f.getCpf()).length() != 11) {
			throw new InvalidCpfException();
		}
		else if(String.valueOf(f.getTelefone()).length() < 11) {
			throw new InvalidTeleException();
		}
		else if(String.valueOf(f.getNumContrato()).length() > 4) {
			throw new InvalidCadException();
		}
		else if(f.getUsuario().getPassword().length() < 6) {
			throw new InvalidPasswordException();
		}
		else{
			repositorio.atualizar(pesquisa, f);
		}
	}

	public void removerFuncionario(long cpf) throws ObjectNotExistException, ErroRemoverException{

		repositorio.remover(cpf);
	}

	public boolean existeFuncionario(long cpf) {

		return this.repositorio.existe(cpf);
	}

	public ArrayList<Funcionario> listarFuncionarios() {
		return this.repositorio.listar();
	}
	
	public boolean login(Usuario u) {
		return this.repositorio.loginCheck(u);
	}
	
	public boolean admLogin(Usuario u) {
		return this.repositorio.admLoginCheck(u);
	}
	
	public void salvarArquivo(){
		repositorio.salvarArquivo();
	}
}