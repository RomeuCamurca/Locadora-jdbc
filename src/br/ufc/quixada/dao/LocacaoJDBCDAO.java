package br.ufc.quixada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.entity.Cliente;
import br.ufc.quixada.entity.Filme;
import br.ufc.quixada.entity.Locacao;

@SuppressWarnings("unused")
public class LocacaoJDBCDAO implements LocacaoDAO {

		public boolean alugarFilme(Locacao locacao) {
			Connection con = null;
			boolean salvou = false;
			
			try {
				con = ConnectionFactory.getConnection();
				String insert_sql = "insert into locacao (id_filme, cpf_cliente, data_locacao, data_devolucao) values (?,?,?,?)";
				
				PreparedStatement pst;
				pst = con.prepareStatement(insert_sql);
				pst.setInt(1, locacao.getId_filme());
				pst.setString(2, locacao.getCpf_cliente());
				pst.setString(3, locacao.getData_locacao());
				pst.setString(4, locacao.getData_devolucao());
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

	/*Os objetos “Map” confiam seus dados em um algoritmo hash (hash code). Esse algoritmo transforma 
	uma grande quantidade de dados em uma pequena quantidade de informações, sendo que o 
	mecanismo de busca se baseia na construção de índices.*/
	
	private Locacao map(ResultSet rs) throws SQLException {
		Locacao loc = new Locacao();
		
		if (rs != null) {
		loc.setId_filme(rs.getInt("id_filme"));
		loc.setCpf_cliente(rs.getString("cpf_cliente"));
		loc.setData_locacao(rs.getString("data_locacao"));
		loc.setData_devolucao(rs.getString("data_locacao"));
		return loc;
		}
		return null;
	}

	@Override
	public boolean removerFilme(int id_filme, String cpf_cliente) {
		Connection con = null;
		try {
			con=ConnectionFactory.getConnection();
			String sql ="delete from locacao where id_filme=? and cpf_cliente=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_filme);
			pst.setString(2, cpf_cliente);
			if(pst.executeUpdate()>0) {
				return true;
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
		return false;
	}

	@Override
	public List<Filme> find() {//mostrar filmes alugados
		Connection con = null;
		List<Filme> f = new ArrayList<Filme>();
		try {
			con=ConnectionFactory.getConnection();
			String sql = "select * from alugados";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
				f.add(mapFilme(rs));
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

	@Override
	public List <Filme> find(String cpf_cliente) {
	Connection con = null;
	List<Filme> f = new ArrayList<Filme>();
	try {
		con=ConnectionFactory.getConnection();
		String sql ="select f1.id, f1.nome, f1.genero from \n" + 
				" filme f1,locacao l where f1.id=l.id_filme and  l.cpf_cliente=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, cpf_cliente);
		ResultSet rs =pst.executeQuery();
		while(rs.next()) {
			f.add(mapFilme(rs));
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
	private Filme mapFilme(ResultSet rs) throws SQLException {
		Filme fil = new Filme();
		
		if (rs != null) {
		fil.setId(rs.getInt("id"));
		fil.setNome(rs.getString("nome"));
		fil.setGenero(rs.getString("genero"));
		return fil;
		}
		return null;
	}

	@Override
	public Filme find(int id) {
		Connection con =null;
		Filme f=null;
		try {
			con=ConnectionFactory.getConnection();
			String sql ="select f1.id, f1.nome, f1.genero from \n" + 
					" filme f1,locacao l where l.id_filme=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs =pst.executeQuery();
			if(rs.next()) {
				f = mapFilme(rs);
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

	
	
}
