package br.com.desafio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desafio.config.BDConfig;
import br.com.desafio.entidade.Pessoa;

public class PessoaDAO { 

	public List<Pessoa> listarPessoas() throws Exception {
		List<Pessoa> lista = new ArrayList<>();

		Connection con = BDConfig.getConnection();

		String sql = "select * from pessoa";

		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setFirst_name(rs.getString("first_name"));
			pessoa.setLast_name(rs.getString("last_name"));
			pessoa.setAge(rs.getInt("age"));

			lista.add(pessoa);
		}
		return lista;
	}

	public Pessoa listarPessoaId(int id) throws Exception {
		Pessoa pessoa = null;

		Connection con = BDConfig.getConnection();

		String sql = "select * from pessoa where pessoa.id = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setFirst_name(rs.getString("first_name"));
			pessoa.setLast_name(rs.getString("last_name"));
			pessoa.setAge(rs.getInt("age"));
		}

		return pessoa;
	}

	public void inserirPessoa(Pessoa pessoa) throws Exception {
		Connection con = BDConfig.getConnection();

		String sql = "insert into pessoa(first_name, last_name, age) values (?, ?, ?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, pessoa.getFirst_name());
		statement.setString(2, pessoa.getLast_name());
		statement.setInt(3, pessoa.getAge());
		statement.execute();
	}

	public void editarPessoa(Pessoa pessoa, int id) throws Exception {
		Connection con = BDConfig.getConnection();

		String sql = "update pessoa set first_name = ?, last_name = ?, age = ? where pessoa.id = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, pessoa.getFirst_name());
		statement.setString(2, pessoa.getLast_name());
		statement.setInt(3, pessoa.getAge());
		statement.setInt(4, id);
		statement.execute();
	}

	public void removerPessoa(int id) throws Exception {
		Connection con = BDConfig.getConnection();

		String sql = "delete from pessoa where pessoa.id = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		statement.execute();
	}
}
