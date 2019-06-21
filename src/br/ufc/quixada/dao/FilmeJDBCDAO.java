package br.ufc.quixada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.ufc.quixada.entity.Filme;

public class FilmeJDBCDAO  implements FilmeDAO{
	
	//Adiciona filme
	public boolean adicionar(Filme filme) {
		Connection con = null;
		boolean salvou = false;
		
		try {
			con = ConnectionFactory.getConnection();
			String insert_sql = "insert into filme (id, nome, genero) values (?,?,?)";
			
			PreparedStatement pst;
			pst = con.prepareStatement(insert_sql);
			pst.setInt(1, filme.getId());
			pst.setString(2, (filme.getNome()));
			pst.setString(3, filme.getGenero());
			
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

	//remove filme pelo id
	public boolean remover(int id) {
		Connection con = null;
		boolean apagou = false;
		try {
		con=ConnectionFactory.getConnection();
		String sql ="delete from filme where id=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, id);
		if(pst.executeUpdate()>0) {
			apagou=true;
		}
		} catch (SQLException e) {
			throw new DAOException("Operacao nao realizada com sucesso.",e);
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

	//lista todos os filmes
	public List<Filme> find() {
		Connection con =null;
		List<Filme>listaf= new ArrayList<Filme>();
		try {
			con=ConnectionFactory.getConnection();
			String sql ="select * from filme";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
				Filme f = map(rs);
				listaf.add(f);
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
		return listaf;
	}

	//lista filme pelo id
	public Filme find(int id) {
		Connection con = null;
		Filme f = null;
		try {
			con=ConnectionFactory.getConnection();
			String sql ="select * from filme where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
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

	//atualiza filme pelo id
	public boolean atualizar(int id, Filme filme) {
		Connection con = null;
		boolean salvou = false;
		
		try {
			con = ConnectionFactory.getConnection();
			String update_sql = "update filme set id=?, nome=?, genero=? where id = ?";
			
			PreparedStatement pst;
			pst = con.prepareStatement(update_sql);
			pst.setInt(1, filme.getId());
			pst.setString(2, filme.getNome());
			pst.setString(3, filme.getGenero());
			pst.setInt(4, id);
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
	
	private Filme map(ResultSet rs) throws SQLException {
		Filme fil = new Filme();
		
		if (rs != null) {
		fil.setId(rs.getInt("id"));
		fil.setNome(rs.getString("nome"));
		fil.setGenero(rs.getString("genero"));
		return fil;
		}
		return null;
	}

}
