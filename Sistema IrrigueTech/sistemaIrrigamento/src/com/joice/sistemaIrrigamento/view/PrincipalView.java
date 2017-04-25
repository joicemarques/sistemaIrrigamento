package com.joice.sistemaIrrigamento.view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */

import com.joice.sistemaIrrigamento.model.Cliente;
import com.joice.sistemaIrrigamento.model.ConnectionModel;
import com.joice.sistemaIrrigamento.model.ModeloTabela;

public class PrincipalView extends JFrame {

	private JPanel contentPane;
	private JLabel lMenu;
	private JButton bChamado;
	private JButton bRelatorio;
	private JButton bSair;
	private JButton bPesquisar;
	private JButton bConfirmar;
	private JPanel paRelatorio;
	private JPanel paMenu;
	private JPanel paChamado;
	private JButton btnPesquisar;
	private JTable tableCliente;
	private JButton btAbreChamado;
	private JTextField tDataIni;
	private JTextField tDataFim;
	private JButton bGerarRel;
	private JLabel lbDataInicio;
	private JLabel lbDataFim;
	private JTextField tPesquisa;
	private JScrollPane scrollPane_1;

	// chama conexao com banco
	ConnectionModel conn = new ConnectionModel();
	PreparedStatement pst = null;
	ResultSet rs = null;

	Cliente cli = new Cliente();
	AtendimentoView atend;
	private JScrollPane scrollPane;
	public JTable tbRelatorio;
	private JButton btMenu;

	public PrincipalView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		paMenu = new JPanel();
		contentPane.add(paMenu, "name_230167881840791");
		paMenu.setLayout(null);

		lMenu = new JLabel("MENU");
		lMenu.setBounds(0, 31, 574, 25);
		paMenu.add(lMenu);
		lMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lMenu.setFont(new Font("Century Gothic", Font.BOLD, 14));

		bChamado = new JButton("Abrir Chamado");
		bChamado.setBounds(224, 82, 123, 33);
		paMenu.add(bChamado);
		bChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// exibe tela de chamado
				paMenu.setVisible(false);
				paChamado.setVisible(true);
			}
		});

		bChamado.setFont(new Font("Century Gothic", Font.PLAIN, 12));

		bRelatorio = new JButton("Relat\u00F3rio");
		bRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// exibe tela de relatorio
				paMenu.setVisible(false);
				paRelatorio.setVisible(true);
			}
		});
		bRelatorio.setBounds(224, 126, 123, 33);
		paMenu.add(bRelatorio);
		bRelatorio.setFont(new Font("Century Gothic", Font.PLAIN, 12));

		bSair = new JButton("Sair");
		bSair.setBounds(224, 286, 123, 33);
		paMenu.add(bSair);
		bSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		bSair.setFont(new Font("Century Gothic", Font.PLAIN, 12));

		paRelatorio = new JPanel();
		contentPane.add(paRelatorio, "name_245579380842179");
		paRelatorio.setLayout(null);

		tDataIni = new JTextField();
		tDataIni.setBounds(124, 27, 86, 20);
		paRelatorio.add(tDataIni);
		tDataIni.setColumns(10);

		tDataFim = new JTextField();
		tDataFim.setBounds(124, 54, 86, 20);
		paRelatorio.add(tDataFim);
		tDataFim.setColumns(10);

		bGerarRel = new JButton("Gerar Relat\u00F3rio");
		bGerarRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					preencherTabelaRelatorio("SELECT * FROM atendimento WHERE data_atendimento BETWEEN '"
							+ tDataIni.getText() + "' AND '" + tDataFim.getText() + "';");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		bGerarRel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		bGerarRel.setBounds(404, 41, 144, 33);
		paRelatorio.add(bGerarRel);

		lbDataInicio = new JLabel("Data Inicial:");
		lbDataInicio.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lbDataInicio.setBounds(28, 29, 86, 14);
		paRelatorio.add(lbDataInicio);

		lbDataFim = new JLabel("Data Final:");
		lbDataFim.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lbDataFim.setBounds(28, 56, 86, 14);
		paRelatorio.add(lbDataFim);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 89, 517, 116);
		paRelatorio.add(scrollPane_1);

		tbRelatorio = new JTable();
		scrollPane_1.setColumnHeaderView(tbRelatorio);

		JButton btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paRelatorio.setVisible(false);
				paMenu.setVisible(true);
			}
		});
		btnMenu.setBounds(459, 317, 89, 23);
		paRelatorio.add(btnMenu);

		paChamado = new JPanel();
		contentPane.add(paChamado, "name_230350538544804");
		paChamado.setLayout(null);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cli.setPesquisa(tPesquisa.getText());
				try {
					// preenche a tabela com os clientes conforme texto digitado
					preencherTabela("Select * from cliente where nomecliente like '%" + cli.getPesquisa()
							+ "%' OR documento_cliente like '%" + cli.getPesquisa() + "%'");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPesquisar.setBounds(339, 40, 89, 23);
		paChamado.add(btnPesquisar);

		btAbreChamado = new JButton("Abrir Chamado");
		btAbreChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// cria variaveis nomeCli e idCli para exibir o nome do
					// cliente selecionado na tela de atendimento
					String nomeCli = "" + tableCliente.getValueAt(tableCliente.getSelectedRow(), 0) + " CPF/CNPJ: "
							+ tableCliente.getValueAt(tableCliente.getSelectedRow(), 1);
					String idCli = (String) tableCliente.getValueAt(tableCliente.getSelectedRow(), 2);

					atend = new AtendimentoView(nomeCli, idCli); // passar como
																	// parametro
																	// o
																	// idchamado
					atend.preencherMangueira("select * from mangueiras where id_cliente = " + idCli);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				atend.setVisible(true);
				setVisible(false);
			}
		});
		btAbreChamado.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btAbreChamado.setBounds(326, 317, 126, 23);
		paChamado.add(btAbreChamado);

		tPesquisa = new JTextField();
		tPesquisa.setBounds(136, 41, 190, 23);
		paChamado.add(tPesquisa);
		tPesquisa.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 107, 530, 81);
		paChamado.add(scrollPane);

		tableCliente = new JTable();
		scrollPane.setViewportView(tableCliente);
		tableCliente.setFont(new Font("Century Gothic", Font.PLAIN, 12));

		btMenu = new JButton("Menu");
		btMenu.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paChamado.setVisible(false);
				paMenu.setVisible(true);
			}
		});
		btMenu.setBounds(462, 317, 89, 23);
		paChamado.add(btMenu);
	}

	// metodo que preenche a tabela com informacoes dos clientes
	public void preencherTabela(String sql) throws SQLException {
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "Nome Cliente", "Documento Cliente", "ID Cliente" };

		pst = conn.getConexao().prepareStatement(sql);
		rs = pst.executeQuery();

		try {
			while (rs.next()) {
				dados.add(new Object[] { rs.getString("nomecliente"), rs.getString("documento_cliente"),
						rs.getString("id_cliente") });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tableCliente.setModel(modelo);
	}

	public void preencherTabelaRelatorio(String sql) throws SQLException {
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "Data Atendimento", "Descricao Atendimento", "Equipe Alocada" };

		pst = conn.getConexao().prepareStatement(sql);
		rs = pst.executeQuery();

		try {
			while (rs.next()) {
				dados.add(new Object[] { rs.getString("data_atendimento"), rs.getString("descricao_atendimento"),
						rs.getString("nome_equipe") });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tbRelatorio.setModel(modelo);
		conn.getConexao().close();
	}

	public void gravaDadosChamado(String sql, String data, String id) throws SQLException {
		pst = conn.getConexao().prepareStatement(sql);
		pst.setString(1, data);
		pst.setInt(2, Integer.parseInt(id));
		pst.execute();
		conn.getConexao().close();
	}
}
