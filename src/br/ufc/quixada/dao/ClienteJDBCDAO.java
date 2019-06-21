package br.ufc.quixada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.entity.Cliente;

public class ClienteJDBCDAO implements ClienteDAO {

	public ClienteJDBCDAO() {
		super();
	}
	
	//inserir cliente
	public boolean adicionar(Cliente cliente) {
		Connection con = null;
		boolean salvou = false;
		
		try {
			con = ConnectionFactory.getConnection();
			String insert_sql = "insert into Cliente (cpf, nome, idade, sexo) values (?,?,?,?)";
			
			PreparedStatement pst;
			pst = con.prepareStatement(insert_sql);
			pst.setString(1, cliente.getCpf());
			pst.setString(2, cliente.getNome());
			pst.setInt(3, cliente.getIdade());
			pst.setString(4, String.valueOf(cliente.getSexo()));
			if (pst.executeUpdate()>0) {
				salvou = true;
			}
		}catch (SQLException e) {
				throw new DAOException ("Operação não realizada com sucesso.", e);
		}finally {
			try {
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
				throw new DAOException ("Não foi possível fechar a conexão.");
			}
				
		}
		return salvou;
	
	}
	
	//atualiza cliente pelo cpf
	public boolean atualizar(String cpf, Cliente cliente) {
		Connection con = null;
		boolean salvou = false;
		
		try {
			con = ConnectionFactory.getConnection();
			String update_sql = "update cliente set cpf=?, nome=?, idade=?, sexo=? where cpf = ?";
			
			PreparedStatement pst;
			pst = con.prepareStatement(update_sql);
			pst.setString(1, cliente.getCpf());
			pst.setString(2, cliente.getNome());
			pst.setInt(3, cliente.getIdade());
			pst.setString(4, String.valueOf(cliente.getSexo()));
			pst.setString(5, cpf);
			if (pst.executeUpdate()>0) {
				salvou = true;
			}
		}catch (SQLException e) {
				throw new DAOException ("Operação não realizada com sucesso.", e);
		}finally {
			try {
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
				throw new DAOException ("Não foi possível fechar a conexão.");
						

			}
				
		}
		return salvou;
	
	}
	
	//remove cliente pelo cpf
	public boolean remover(String cpf) {
		Connection con = null;
		boolean apagou = false;
		try {
		con=ConnectionFactory.getConnection();
		String sql ="delete from cliente where cpf=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, cpf);
		if(pst.executeUpdate()>0) {
			apagou=true;
		}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e){
				throw new DAOException("Nao foi possivel fechar a conexao.");
			}
		}
		return apagou;
	}
	
	//lista todos os clientes
	public List<Cliente> find() {
		Connection con =null;
		List<Cliente>listac= new ArrayList<Cliente>();
		try {
			con=ConnectionFactory.getConnection();
			String sql ="select * from cliente";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
				Cliente c = map(rs);
				listac.add(c);
			}
		}catch (SQLException e) {
			throw new DAOException("Operacao nao realizada com sucesso",e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e){
				throw new DAOException("Nao foi possivel fechar a conexao.");
			}
		}
		return listac;
	}
	
	//lista cliente pelo cpf
	public Cliente find(String cpf) {
		Connection con =null;
		Cliente f=null;
		try {
			con=ConnectionFactory.getConnection();
			String sql ="select * from cliente where cpf=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs =pst.executeQuery();
			if(rs.next()) {
				f = map(rs);
			}
		
		}catch (SQLException e) {
			throw new DAOException("Operacao nao realizada com sucesso",e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e){
				throw new DAOException("Nao foi possivel fechar a conexao.");
			}
		}
		return f;
	}

	/*Os objetos “Map” confiam seus dados em um algoritmo hash (hash code). Esse algoritmo transforma 
	uma grande quantidade de dados em uma pequena quantidade de informações, sendo que o 
	mecanismo de busca se baseia na construção de índices.*/
	
	private Cliente map(ResultSet rs) throws SQLException {
		Cliente cli = new Cliente();
		if (rs != null) {
		cli.setCpf(rs.getString("cpf"));
		cli.setNome(rs.getString("nome"));
		cli.setIdade(rs.getInt("idade"));
		cli.setSexo(rs.getString("sexo").charAt(0));
		return cli;
		}
		return null;
	}	
}
	
	


