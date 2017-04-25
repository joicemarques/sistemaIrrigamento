package com.joice.sistemaIrrigamento.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;

/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.joice.sistemaIrrigamento.model.ConnectionModel;
import com.joice.sistemaIrrigamento.model.ModeloTabela;

public class AtendimentoView extends JFrame {

	private JPanel contentPane;
	public JTextField tDataAtendimento;
	private JComboBox<String> cbEquipe;
	private JTextField txNomeCli;
	private JTable tbMagueira;
	private JLabel lbData;
	private JLabel lblEquipe;
	private JLabel lblDescrioAtendimento;
	private JLabel lbCliente;
	private JButton btnSalvar;
	private JEditorPane txDescricaoAtendimento;

	private String nome;
	private String id;

	PrincipalView prinView = new PrincipalView();
	ConnectionModel conn = new ConnectionModel();
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// metodo recebe string do nome da empresa da classe PrincipalView
	public AtendimentoView(String nome, String id) throws SQLException {
		this.nome = nome;
		this.id = id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cbEquipe = new JComboBox<String>();
		cbEquipe.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		cbEquipe.setBounds(326, 173, 209, 20);
		contentPane.add(cbEquipe);

		tDataAtendimento = new JTextField();
		tDataAtendimento.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		tDataAtendimento.setBounds(46, 173, 118, 20);
		contentPane.add(tDataAtendimento);
		tDataAtendimento.setColumns(10);

		lbData = new JLabel("Data Atendimento:");
		lbData.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lbData.setBounds(46, 148, 118, 30);
		contentPane.add(lbData);

		lblEquipe = new JLabel("Equipe:");
		lblEquipe.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblEquipe.setBounds(326, 148, 67, 30);
		contentPane.add(lblEquipe);

		lblDescrioAtendimento = new JLabel("Descri\u00E7\u00E3o Atendimento:");
		lblDescrioAtendimento.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblDescrioAtendimento.setBounds(44, 202, 177, 20);
		contentPane.add(lblDescrioAtendimento);

		lbCliente = new JLabel("Cliente:");
		lbCliente.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lbCliente.setBounds(46, 11, 118, 14);
		contentPane.add(lbCliente);

		txNomeCli = new JTextField();
		txNomeCli.setEditable(false);
		txNomeCli.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		txNomeCli.setBounds(46, 25, 489, 20);
		contentPane.add(txNomeCli);
		txNomeCli.setColumns(10);
		txNomeCli.setText(nome);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "INSERT INTO atendimento (data_atendimento, descricao_atendimento, nome_equipe, id_chamado) values(?, ?, ?, ?);";
					gravaDadosAtendimento(sql);
					//chama o metodo gravar dados para salvar o chamado no banco
					prinView.gravaDadosChamado("INSERT INTO chamado (data_chamado, id_cliente) values (?, ?);", tDataAtendimento.getText(), id);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				setVisible(false);
				prinView.setVisible(true);
			}
		});
		btnSalvar.setBounds(446, 327, 89, 23);
		contentPane.add(btnSalvar);

		txDescricaoAtendimento = new JEditorPane();
		txDescricaoAtendimento.setBounds(46, 223, 489, 77);
		contentPane.add(txDescricaoAtendimento);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 56, 489, 90);
		contentPane.add(scrollPane);

		tbMagueira = new JTable();
		scrollPane.setViewportView(tbMagueira);

		this.equipeComboBox();

	}

	// preenche tabela com dados das mangueiras do cliente selecionado
	public void preencherMangueira(String sql) throws SQLException {
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID Mangueira", "Quantidade de Bicos", "Status" };

		pst = conn.getConexao().prepareStatement(sql);
		rs = pst.executeQuery();

		try {
			while (rs.next()) {
				dados.add(new Object[] { rs.getString("id_mangueira"), rs.getString("quantidade_bicos"),
						rs.getBoolean("status_mangueira") });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tbMagueira.setModel(modelo);
		conn.getConexao().close();
	}

	// popula comboBox com dados das equipes
	public void equipeComboBox() throws SQLException {
		String sql = "SELECT * FROM equipe";
		try {
			pst = conn.getConexao().prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				cbEquipe.addItem(rs.getString("nome_equipe"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		conn.getConexao().close();
	}

	// grava dados do atendimento a partir de uma string recebidas
	public void gravaDadosAtendimento(String sql) throws SQLException {
		pst = conn.getConexao().prepareStatement(sql);
		pst.setString(1, tDataAtendimento.getText());
		pst.setString(2, txDescricaoAtendimento.getText());
		pst.setString(3, (String) cbEquipe.getSelectedItem());
		pst.setInt(4, 1);
		pst.execute();
		conn.getConexao().close();
	}

	
	
}
