package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Game;

public class Main {

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner (System.in)){
		ArrayList<Game> game = new ArrayList<>();
		int codigo = 1000;
		Connection conexao = null;
		DateTimeFormatter formato =
		DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		System.out.println("================================================");
		System.out.println("\n                 GAME MANAGER");
		System.out.println("\n================================================");
		
	try {	
		String url = "jdbc:mysql://localhost:3306/game_manager";
		String usuario = "root";
		String senha = "136789";
		conexao = DriverManager.getConnection(url, usuario, senha);	
		// CONECTA O JAVA AO BANCO DE DADOS MYSQL
		
		
		System.out.println("              Conectado ao MySQL!");
		
		//DESCOBRE O PROXIMO CODIGO
		
		String sqlCodigo = "SELECT codigo FROM games ORDER BY codigo DESC LIMIT 1";
		//BUSCA O MAIOR CODIGO CADASTRADO PARA GERAR O PROXIMO AUTOMATICAMENTE 
		PreparedStatement psCodigo = conexao.prepareStatement(sqlCodigo);
		
		ResultSet rsCodigo = psCodigo.executeQuery();
		
		if (rsCodigo.next()) {
		    codigo = rsCodigo.getInt("codigo") + 1;
		}
		rsCodigo.close();
		psCodigo.close();
		
	}
	catch(SQLException e){
		e.printStackTrace();
	}
		
	
		while(true) {
		game.clear();
		try {
		    String sql = "SELECT * FROM games";

		    PreparedStatement ps = conexao.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();

		    while (rs.next()) {

		        String nome = rs.getString("nome");
		        double preco = rs.getDouble("preco");
		        String plataforma = rs.getString("plataforma");
		        int estoque = rs.getInt("estoque");
		        long codiguin = rs.getLong("codigo");

		        Game jogoDataBase = new Game(nome, preco, plataforma, estoque, codiguin);

		        game.add(jogoDataBase);
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}
		LocalDateTime agora = LocalDateTime.now();
		System.out.println("\n                    MENU");
		System.out.println("\n           " + agora.format(formato));

		System.out.println("\n1 - Cadastro de jogo");
		System.out.println("2 - Lista de jogo");
		System.out.println("3 - Buscar jogo");
		System.out.println("4 - Comprar jogo");
		System.out.println("5 - Atualizar jogo");
		System.out.println("6 - Remover jogo");
		System.out.println("7 - Ver estoque");
		System.out.println("0 - Sair ");
		
		System.out.println("\n================================================");
		System.out.print("\nEscolha uma Opção: ");
		int r = sc.nextInt();
		
		System.out.println("\n================================================");
		switch(r) {
		
		case 1: 
			System.out.print("\nQuantos jogos serao cadastrados: ");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
			sc.nextLine();
			
			System.out.print("\nNome do jogo: ");
			String nome = sc.nextLine();
			
			System.out.print("Preço: R$");
			double preco = sc.nextDouble();
			
			System.out.print("Plataforma: ");
			String plataforma = sc.next();
			
			System.out.print("Estoque: ");
			int estoque = sc.nextInt();
			
			System.out.print("Codigo: #" + codigo);
			
			
			System.out.println("\n" + agora.format(formato));
			
		
			
			System.out.println("\n================================================");

			Game novoJogo = new Game(nome, preco, plataforma, estoque, codigo);
			game.add(novoJogo);
			try {
			String sql = "INSERT INTO games (codigo, nome, preco, plataforma, estoque) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setLong(1, codigo);
			ps.setString(2, nome);
			ps.setDouble(3, preco);
			ps.setString(4, plataforma);
			ps.setInt(5, estoque);
			
			ps.executeUpdate();
			ps.close();
			
			}
			catch(SQLException e ) {
				e.printStackTrace();
			}
			codigo++;	
			
		}
			continue;		
		
		case 2: 
			
			
			System.out.println("                    Lista de jogos");
			for (Game games : game) {
				System.out.println("Jogo: " + games.getNome());
				System.out.println("Preço: R$" + games.getPreco());
				System.out.println("Plataforma: " + games.getPlataforma());
				System.out.println("Estoque: " + games.getEstoque());
				System.out.println("Codigo: #" + games.getCodigo());
				System.out.println("\n================================================");
				
				} 
			break;
			case 3:
				
				int j = 1;
				for (Game games : game) {
					System.out.println(j + "- " + games.getNome() + " | #" + games.getCodigo());
					j++;
				}
				sc.nextLine();
				System.out.print("Digite o codigo do jogo: #");
				long code = sc.nextLong();
				boolean encontrado = false;
				
				
					for (Game games : game) {
						
						  if (games.getCodigo() == code) {

					            System.out.println("\nNome: " + games.getNome());
					            System.out.println("Preco: R$ " + games.getPreco());
					            System.out.println("Estoque: " + games.getEstoque());
					            System.out.println("Codigo: #" + games.getCodigo());

					            encontrado = true;
					            break;
					        }
						
						}
					System.out.println("\n================================================");
						if (!encontrado) {  
						  System.out.println("\n                    Jogo nao encontrado");
						 }
						break;  
			case 4: 
				
				
				j = 1;
				System.out.println("\n                    Lista de jogos");
				for(Game games : game) {
					
					System.out.println(j  + "- " + games.getNome() + " | R$" + games.getPreco() + "| #" + games.getCodigo());
					j++;
				}
				System.out.println("\n================================================");

				System.out.print("\nCodigo do jogo que deseja comprar: #");
				long escolha = sc.nextLong();
				boolean encontro = false;
					

				for (Game games : game) {
				
					if (games.getCodigo() == escolha) {
			            System.out.println("\nNome: " + games.getNome());
			            System.out.println("Preco: R$ " + games.getPreco());
			            System.out.println("Estoque: " + games.getEstoque());
			            System.out.println("Codigo: #" + games.getCodigo());
			            
			            System.out.print("\nDeseja confirmar compra?(S/N): ");
			            char opcao = sc.next().charAt(0);
			            
			            if(opcao == 'S' || opcao == 's') {
			            	
							System.out.println("\n================================================");
			            	
							 if(games.getEstoque() > 0) {
								 games.setEstoque(games.getEstoque() - 1);
								 try {
						     	 String sql =  "UPDATE games SET estoque = ? WHERE codigo = ? ";
								 PreparedStatement ps = conexao.prepareStatement(sql);
								 
								 ps.setInt(1, games.getEstoque());
								 ps.setLong(2, games.getCodigo());
								 
								 ps.executeUpdate();
								 ps.close();
									
								 }
								 catch(SQLException e ) {
									 e.printStackTrace();
								 }
			            	System.out.println("\n                 Jogo comprado com sucesso !");
			           
			            	
					            System.out.println("\nNome: " + games.getNome() );
					            System.out.println("Preco: R$ " + games.getPreco());
					            System.out.println("Estoque: " + games.getEstoque());
					            System.out.println("Codigo: #" + games.getCodigo());
					            System.out.println("Horario da compra: " + agora.format(formato));
			            }
							 else {
								 System.out.println("\n                 Jogo sem estoque!");
							 }
								System.out.println("\n================================================");

								System.out.println("\n                    Lista Atualizada");
							 	j = 1;
							 	for (Game gam : game) {
							 		System.out.println(j + "- " + gam.getNome() 
							 			+ " | R$" + gam.getPreco() 
							 			+ " | Estoque: " + gam.getEstoque()
							 			+ " | #" + gam.getCodigo());

							 		j++;
							 		
							 	}
			            }
			            	
			            encontro = true;
			            break;
					}
				}
				
					if(!encontro) {
					System.out.println("\nJogo nao encontrado");
					}
				
				break;
			case 5:
				
				j = 1;
				for (Game games : game) {
					System.out.println(j + "- " + games.getNome() + " | #" + games.getCodigo() + "| R$" + games.getPreco()  + "| Estoque: " + games.getEstoque());
					j++;
				}
				System.out.print("DIgite codigo do produto: #" );
				long codiguin = sc.nextLong();
				boolean resposta = false;
					System.out.println("\n1 - Alterar Nome ");
					System.out.println("2 - Alterar preço");
					System.out.println("3 - Alterar plataforma");
					System.out.println("4 - Alterar estoque");
					
					System.out.println("\n================================================");
					
					System.out.print("Resposta: ");
					int e = sc.nextInt();
				
					
					
					for (Game games : game) {
							if (games.getCodigo() == codiguin) {
								resposta = true;
								if (e == 1) {
									
									sc.nextLine();
									
									
									System.out.print("\nDigite o novo Jogo: ");
									String novoNome = sc.nextLine();
									
									games.setNome(novoNome);
									try {

									    String sql = "UPDATE games SET nome = ? WHERE codigo = ?";

									    PreparedStatement ps = conexao.prepareStatement(sql);

									    ps.setString(1, novoNome);
									    ps.setLong(2, codiguin);

									    ps.executeUpdate();

									} catch (SQLException ex) {

									    ex.printStackTrace();

									}
									
								}
								else if (e == 2) {
									System.out.print("\nDigite o novo preço: R$");
									double novoPreco = sc.nextDouble();
									
									games.setPreco(novoPreco);
									try {

									    String sql = "UPDATE games SET preco = ? WHERE codigo = ?";

									    PreparedStatement ps = conexao.prepareStatement(sql);

									    ps.setDouble(1, novoPreco);
									    ps.setLong(2, codiguin);

									    ps.executeUpdate();

									} catch (SQLException ex) {

									    ex.printStackTrace();

									}
								}
								else if(e == 3) {
									System.out.print("\nNova plataforma: ");
									String novaPlataforma = sc.next();
									
									games.setPlataforma(novaPlataforma);
									try {

									    String sql = "UPDATE games SET plataforma = ? WHERE codigo = ?";

									    PreparedStatement ps = conexao.prepareStatement(sql);

									    ps.setString(1, novaPlataforma);
									    ps.setLong(2, codiguin);

									    ps.executeUpdate();

									} catch (SQLException ex) {

									    ex.printStackTrace();

									}
								}
								else if (e == 4) {
									
									System.out.print("Para alterar o estoque é necessario chave de comando !");
									System.out.print("\nChave de comando: ");
									String chave = sc.next();
										if (chave.equals("136789")) {
											System.out.print("\nNovo estoque: ");
											int novoEstoque = sc.nextInt();
											
											games.setEstoque(novoEstoque);
											try {

											    String sql = "UPDATE games SET estoque = ? WHERE codigo = ?";

											    PreparedStatement ps = conexao.prepareStatement(sql);

											    ps.setInt(1, novoEstoque);
											    ps.setLong(2, codiguin);

											    ps.executeUpdate();

											} catch (SQLException ex) {

											    ex.printStackTrace();

											}
										}
										else {
											System.out.println("\nVocê não possui permissão!");
											break;
										}
										
											
								}
								
								
								System.out.println("\n                    Lista Atualizada");
							 	j = 1;
							 	for (Game gam : game) {
							 		System.out.println(j + "- " + gam.getNome() 
							 			+ " | R$" + gam.getPreco() 
							 			+ " | Estoque: " + gam.getEstoque()
							 			+ " | #" + gam.getCodigo());

							 		j++;
							 		
							 	}
							 	break;
							}
							
					}
				
				if(!resposta) {
					System.out.println("\nJogo não encontrado!");
					
									
								}
				break;
				
			case 6: 
				
				n = 1;
				for (Game games : game) {
					System.out.println(n + "- " + games.getNome() + " | #" + games.getCodigo());
					n++;
				}
				System.out.print("Digite o codigo do jogo que deseja remover: #");
				long c = sc.nextLong();
				
				boolean removido = false; 
				
					for (int i = 0; i < game.size(); i++) {
						if (game.get(i).getCodigo() == c) {
							   System.out.println("\n                    Jogo Escolhido");
							   
							   try {
								   String sql = "DELETE FROM games WHERE codigo = ?";
								   PreparedStatement ps = conexao.prepareStatement(sql);
								 
								   ps.setLong(1, c);

								    ps.executeUpdate();
								    ps.close();

								} catch (SQLException ex) {

								    ex.printStackTrace();

								
							   }

					            System.out.println("\nNome: " + game.get(i).getNome()
					                    + " | #" + game.get(i).getCodigo());

					            game.remove(i);

					            removido = true;

					            break;
						}
					}
				 
					if(!removido) {
					System.out.println("\nJogo não encontrado!");
					}
					int t = 1;
					System.out.print("                    Lista atualizada");
					
						
					for (Game games : game) {
						System.out.println("\n" + t + "- " + games.getNome() + "| #" + games.getCodigo() + "| R$" + games.getPreco());
					t++;
					}
				
					System.out.println("\n================================================");
					
					break;
					
			case 7: 
				System.out.println("\n================================================");

				j = 1;
				for (Game games : game) {
					System.out.println(j + "- " + games.getNome() + " | #" + games.getCodigo() + "| R$" + games.getPreco()  + "| Estoque: " + games.getEstoque() + " | Plataforma: " + games.getPlataforma());
					j++;
				}
			
				break;
			case 0: 
				

				System.out.println("\nAdeus! ");
				return;
				}
			
				}
	
			}
			
	}
}
	

