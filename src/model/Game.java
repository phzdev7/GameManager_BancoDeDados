package model;

public class Game {
	
	private String nome;
	private double preco;
	private String plataforma;
	private int estoque;
	private long codigo;
	
	
	public Game(String nome, double preco, String plataforma, int estoque, long codigo) {
	    this.nome = nome;
	    this.preco = preco;
	    this.plataforma = plataforma;
	    this.estoque = estoque;
	    this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
    

}
