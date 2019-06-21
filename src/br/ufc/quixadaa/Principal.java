package br.ufc.quixadaa;
import java.util.List;
import java.util.Scanner;
import br.ufc.quixada.dao.ClienteJDBCDAO;
import br.ufc.quixada.dao.FilmeJDBCDAO;
import br.ufc.quixada.dao.LocacaoJDBCDAO;
import br.ufc.quixada.entity.Cliente;
import br.ufc.quixada.entity.Filme;
import br.ufc.quixada.entity.Locacao;
import java.lang.Iterable;

@SuppressWarnings("unused")
public class Principal {
	public static Scanner entrada = new Scanner(System.in);
	public static void listarCliente(List<Cliente> listac) {
		if(listac.size()==0) {
			System.out.println("A Lista de Clientes Está Vazia!\n");
		}
		else {
			for(Cliente c: listac) {
				System.out.println(c.toString());
			}
		}
}
	
	public static void listarFilme(List<Filme> listaf) {
		if (listaf.size() ==0) {
			System.out.print("A Lista de Filmes Está Vazia!\n");
		}
		else {
			for(Filme f: listaf) {
				System.out.println(f.toString());
			}
		}
}
	
	public static void listarLocacao(List<Locacao> listal) {
		if (listal.size() ==0) {
			System.out.print("A Lista de Locações Está Vazia!\n");
		}
		else {
			for(Locacao l: listal) {
				System.out.println(l.toString());
			}
		}
}
	
	/*esse metodo é usado para a criacao de clientes, sempre que ele é chamado(invocado) é 
	  criado internamente uma instancia da classe Cliente e logo depois sao exigidos a 
	  entrada dos valores de cada atributo via teclado e no final o metodo retorna o Cliente criado
	 */
	public static Cliente criarCliente() {
		Cliente c = new Cliente();
		System.out.println("CPF:  ");
		String cpf= entrada.nextLine();
		System.out.println("Nome: ");
		String nome= entrada.nextLine();
		System.out.println("Idade: ");
		int idade = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Sexo: ");
		char sexo= entrada.nextLine().charAt(0);
		c.setCpf(cpf);
		c.setNome(nome);
		c.setIdade(idade);
		c.setSexo(sexo);
		return c;
	}
	
	/*esse metodo é usado para a criacao de filmes, sempre que ele é chamado(invocado) é 
	  criado internamente uma instancia da classe Filme e logo depois sao exigidos a 
	  entrada dos valores de cada atributo via teclado e no final o metodo retorna o Filme criado
	 */
	public static Filme criarFilme() {
		Filme f = new Filme();
		System.out.println("Id: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Nome: ");
		String nome = entrada.nextLine();
		System.out.println("Gênero: ");
		String genero = entrada.nextLine();
		f.setId(id);
		f.setNome(nome);
		f.setGenero(genero);
		return f;
	}
	
	/*esse metodo é usado para a criacao de locações, sempre que ele é chamado(invocado) é 
	  criado internamente uma instancia da classe Locacao e logo depois sao exigidos a 
	  entrada dos valores de cada atributo via teclado e no final o metodo retorna a Locacao criada
	 */
	public static Locacao criarLocacao() {
		Locacao l = new Locacao();
		System.out.println("Id Do Filme: ");
		int id_filme = entrada.nextInt();
		entrada.nextLine();
		System.out.println("CPF Do cliente: ");
		String cpf_cliente = entrada.nextLine();
		System.out.println("Data Da Locação: ");
		String data_locacao = entrada.nextLine();
		System.out.println("Data Da Devolução: ");
		String data_devolucao = entrada.nextLine();
		l.setId_filme(id_filme);
		l.setCpf_cliente(cpf_cliente);
		l.setData_locacao(data_locacao);
		l.setData_devolucao(data_devolucao);
		return (Locacao) l;
	}
	
	public static void main(String[] args){
		ClienteJDBCDAO baseCliente = new ClienteJDBCDAO();
		FilmeJDBCDAO baseFilme = new FilmeJDBCDAO();
		LocacaoJDBCDAO baseLocacao = new LocacaoJDBCDAO();
		
		while(true) {
			System.out.println( "ESCOLHA UMA OPÇÃO:\n"+
								"1 - Criar\n"+
								"2 - Ler\n"+
								"3 - Atualizar\n"+
								"4 - Deletar\n"+
								"0 - Sair");
			try {
				String opcao = entrada.nextLine();
				if(opcao.equals("0")) {
					System.out.println("Até a próxima!!!");
					break;
				}else {
					switch(opcao){
					case "1":
						System.out.println( "ESCOLHA O QUE DESEJA CRIAR:\n"+
											"1 - Cliente\n"+
											"2 - Filme\n"+
											"3 - Aluguel\n"+
											"0 - voltar");
						String opcao2 = entrada.nextLine();
						if(opcao2.equals("0")) {
							System.out.println("Voltando!!!");
							break;
						}else {
							switch(opcao2){
							case "1": //inserir cliente
								Cliente c=Principal.criarCliente();
								if(baseCliente.find(c.getCpf())==null && baseCliente.adicionar(c)) {
									System.out.println("Cliente inserido com sucesso.\n");
								}
								else {
									System.out.println("ja existe um Cliente com esse cpf.\n");
								}
								break;
								
							case "2": //Inserir filme
								Filme f = Principal.criarFilme();
								if(baseFilme.find(f.getId())== null && baseFilme.adicionar(f)) {
									System.out.println("Filme inserido com sucesso.\n");
								}
								else {
									System.out.println("Já existe um Filme com esse Id.\n");
								}
								break;
								
							case "3": //alugar filme por id do filme e cpf do cliente
								Locacao l=Principal.criarLocacao();
								if (baseLocacao.find(l.getId_filme())==null && baseCliente.find(l.getCpf_cliente()) != null &&
										baseFilme.find(l.getId_filme())!=null) {
									if (baseLocacao.alugarFilme(l)) {
										System.out.println("Filme alugado com sucesso.\n");
										System.out.println(l.toString());
									}
									else {
										System.out.println("Erro ao tentar alugar o filme.\n");
									}
								}
								else {
									System.out.println("Filme não disponivel.\n");
								}
								break;
		
							default:
								System.out.println("Opção Inválida!\n");
								break;
							}
						}
						break;
						
					case "2":
						System.out.println( "ESCOLHA O QUE DESEJA LER:\n"+
											"1 - Todos Os Clientes\n"+
											"2 - Cliente Pelo CPF\n" +
											"3 - Todos Os Filmes\n"+
											"4 - Filme Pelo ID\n" +
											"5 - Todos os Filmes Alugados\n"+
											"6 - Aluguel Pelo CPF Do Cliente\n" +
											"0 - voltar");
						String opcao3 = entrada.nextLine();
						if(opcao3.equals("0")) {
							System.out.println("Voltando!!!");
							break;
						}else {
							switch(opcao3){
							case "1"://Listar Todos Os Clientes
								List<Cliente> lc =baseCliente.find();
								Principal.listarCliente(lc);
								break;
								
							case "2"://buscar Cliente Pelo CPF
								System.out.println("CPF: ");
								String clientcpf=entrada.nextLine();
								Cliente cli=baseCliente.find(clientcpf);	
								if(cli==null) {
									System.out.println("Cliente não encontrado.\n");
								}
								else {
									System.out.println(cli.toString());
								}
								break;
								
							case "3": //Lista Todos Os Filmes
								List<Filme> lf =baseFilme.find();
								Principal.listarFilme(lf);
								break;
								
							case "4": //Buscar Filme Pelo ID	
								System.out.println("ID: ");
								int filmid = entrada.nextInt();
								entrada.nextLine();
								Filme film = baseFilme.find(filmid);	
								if(film==null) {
									System.out.println("Filme não encontrado!\n");
								}
								else {
									System.out.println(film.toString());
								}
								break;
								
							case "5": // Listar Todos Os Filmes Alugados
								List<Filme>listFilmes=baseLocacao.find();
								Principal.listarFilme(listFilmes);
								break;
								
							case "6": // Listar Filmes Alugados De Um Cliente
								String cpff;
								System.out.println("Digite o cpf do cliente: ");
								cpff=entrada.nextLine();
								List<Filme> listaFilmes=baseLocacao.find(cpff);
								Principal.listarFilme(listaFilmes);
								break;
							
							default:
								System.out.println("Opção Inválida!\n");
								break;
							}
						}
						break;
						
					case "3":
						System.out.println( "ESCOLHA O QUE DESEJA ATUALIZAR:\n"+
											"1 - Cliente Pelo CPF\n"+
											"2 - Filme Pelo ID\n"+
											"0 - voltar");
						String opcao4 = entrada.nextLine();
						if(opcao4.equals("0")) {
							System.out.println("Voltando!!!");
							break;
						}else {
							switch(opcao4){
							case "1": //Atualizar Cliente Pelo CPF
								System.out.println("Digite o cpf do respectivo cliente: ");
								String cpfc = entrada.nextLine();
								if (baseCliente.find(cpfc) != null ) {
									Cliente c2 = Principal.criarCliente();
									if (baseCliente.atualizar(cpfc, c2)) {
										System.out.println("Cliente atualizado com sucesso.\n");
									}
									else {
										System.out.println("Erro: não atualizado.\n");
									}
								}
								else {
									System.out.println("Cpf não encontrado!\n");
								}
								break;
								
							case "2": //Atualizar Filme Pelo ID	
								System.out.println("Digite o id do respectivo filme: ");
								int idf = entrada.nextInt();
								entrada.nextLine();
								if (baseFilme.find(idf) != null ) {
									Filme f2 = Principal.criarFilme();
									if (baseFilme.atualizar(idf, f2)) {
										System.out.println("Filme atualizado com sucesso.\n");
									}
									else {
										System.out.println("Erro: não atualizado.\n");
									}
								}
								else {
									System.out.println("Id não encontrado!\n");
								}
								break;
								
							default:
								System.out.println("Opção Inválida!\n");
								break;
							}
						}			
						break;
						
					case "4":
						System.out.println( "ESCOLHA O QUE DESEJA DELETAR:\n"+
											"1 - Cliente\n"+
											"2 - Filme\n"+
											"3 - Locação\n"+
											"0 - voltar");
						String opcao5 = entrada.nextLine();
						if(opcao5.equals("0")) {
							System.out.println("Voltando!!!");
							break;
						}else {
							switch(opcao5){
							case "1"://Deletar Cliente Pelo CPF
								System.out.println("CPF: ");
								String cpf=entrada.nextLine();
								if(baseCliente.remover(cpf)) {
									System.out.println("Cliente removido com sucesso.\n");
								}
								else {
									System.out.println("Nao foi possivel remover o Cliente.\n");
								}
								break;
								
							case "2": //Deletar Filme Pelo ID
								System.out.println("ID: ");
								int id = entrada.nextInt();
								entrada.nextLine();
								if(baseFilme.remover(id)) {
									System.out.println("Filme removido com sucesso.\n");
								}
								else {
									System.out.println("Nao foi possivel remover o Filme!\n");
								}
								break;
								
							case "3": // Remover Um Filme De Um Cliente Alugado
								System.out.println("ID Do Filme:");
								int id_f =Integer.parseInt(entrada.nextLine());
								System.out.println("CPF Do Cliente: ");
								String cpf_c=entrada.nextLine();
								if(baseLocacao.removerFilme(id_f, cpf_c)) {
									System.out.println("Filme removido com sucesso.\n");
								}else {
									System.out.println("Não foi possível remover.\n");
								}
								break;
								
							default:
								System.out.println("Opção Inválida!\n");
								break;
							}
						}
						break;
					default:
						System.out.println("Opção Inválida!\n");
						break;
					}
				}
			
			
			}catch (Exception e) {
				
			}
		} while (true);
	}

}



