/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bruno
 */
public class JHelp extends javax.swing.JFrame {

    /**
     * Creates new form JHelp
     */
    public JHelp() {
        initComponents();
        setAparencia();
        this.setTitle("Manual");
    }

    private void setAparencia() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } else {

                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info2 : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Nimbus".equals(info2.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info2.getClassName());
                                break;
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Sem objeto selecionado:\nMouse esquerdo: Verifica existência de objeto, se existir irá seleciona-lo.  \nCaso não exista, será criado um novo objeto gráfrico.\nF1: Exibe essa tela.\n\nCom objeto selecionado:\nMouse direito: Remove seleção do objeto.\nDelete: Remove o objeto selecionado.\nSetas: Movem o poligono selecionado para a direção respectiva.\nV: Retorna o pologono para a sua posição inicial sem as transformações.\nF: Cria poligono filho do poligono selecionado.\nEspaço: Troca a primitiva do poligono entre poligono aberto e poligono fechado.\n1: Reduz o tamanho do poligono pela metade em relação ao centro da sua Bbox.\n2: Dobra o tamanho do poligono em relação ao centor da sua Bbox.\n3: Rotaciona o poligono em 10 graus em relação ao centro da sua Bbox.\nR: Altera a cor do poligono para vermelho.\nG: Altera a cor do poligono para verde.\nB: Altera a cor do poligono para azul.\nT: Altera a cor do poligono para preto.\nP: Exibe os vertices do poligono selecionado.\nM: Exibe a matriz do poligono selecionado.\n");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
