package br.com.ifba.usuario.view;

import javax.swing.JOptionPane;
import br.com.ifba.usuario.dao.UsuarioDAO;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import br.com.ifba.usuario.exception.PersistenciaUsuarioException;
import javax.swing.table.DefaultTableModel;

public class TelaListagemUsuario extends javax.swing.JFrame {
    private final UsuarioDAO dao = new UsuarioDAO();

    public TelaListagemUsuario() {
        initComponents();
        setLocationRelativeTo(null);
        atualizarListagem();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuarios = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listagem de Usuarios");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel1.setText("Buscar usuario:");
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed(evt));
        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "Login", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
        tabelaUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tabelaUsuarios);
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(evt -> btnCadastrarActionPerformed(evt));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(evt -> btnEditarActionPerformed(evt));
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(evt -> btnExcluirActionPerformed(evt));
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(evt -> btnFecharActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(18)
                        .addComponent(txtBusca).addGap(18).addComponent(btnBuscar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup().addComponent(btnCadastrar).addGap(18)
                        .addComponent(btnEditar).addGap(18).addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(btnFechar)))
                .addGap(32)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1).addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btnBuscar))
                .addGap(24).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar).addComponent(btnEditar).addComponent(btnExcluir).addComponent(btnFechar))
                .addGap(32)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        atualizarListagem();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        new TelaCadastroUsuario(this::atualizarListagem).setVisible(true);
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            new TelaEdicaoUsuario(dao.buscarPorId(idSelecionado()), this::atualizarListagem).setVisible(true);
        } catch (UsuarioNaoEncontradoException | PersistenciaUsuarioException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(), "Não foi possível editar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            Long id = idSelecionado();
            if (JOptionPane.showConfirmDialog(this, "Deseja excluir o usuário?", "Excluir usuário", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dao.excluir(id);
                atualizarListagem();
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
            }
        } catch (UsuarioNaoEncontradoException | PersistenciaUsuarioException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(), "Não foi possível excluir", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void atualizarListagem() {
        try {
            var usuarios = dao.buscarPorNome(txtBusca.getText());
            DefaultTableModel modelo = (DefaultTableModel) tabelaUsuarios.getModel();
            modelo.setRowCount(0);
            for (var usuario : usuarios) {
                modelo.addRow(new Object[] {usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getStatus()});
            }
        } catch (PersistenciaUsuarioException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(), "Não foi possível listar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Long idSelecionado() {
        int linha = tabelaUsuarios.getSelectedRow();
        if (linha < 0) {
            throw new UsuarioNaoEncontradoException("selecione um usuário na listagem");
        }
        return (Long) tabelaUsuarios.getValueAt(linha, 0);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaListagemUsuario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaUsuarios;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
