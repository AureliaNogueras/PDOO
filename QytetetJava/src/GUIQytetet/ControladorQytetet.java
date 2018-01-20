/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author aurelia
 */
public class ControladorQytetet extends javax.swing.JFrame {
    private modeloqytetet.Qytetet modeloQytetet;

    /**
     * Creates new form ControladorQytetet
     */
    public ControladorQytetet() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaQytetet1 = new GUIQytetet.VistaQytetet();
        SalirCarcelDado = new javax.swing.JButton();
        SalirCarcelPagando = new javax.swing.JButton();
        Jugar = new javax.swing.JButton();
        AplicarSorpresa = new javax.swing.JButton();
        SiguienteTurno = new javax.swing.JButton();
        ComprarPropiedad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SalirCarcelDado.setText("Salir Cárcel Dado");
        SalirCarcelDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirCarcelDadoActionPerformed(evt);
            }
        });

        SalirCarcelPagando.setText("Salir Cárcel Pagando");
        SalirCarcelPagando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirCarcelPagandoActionPerformed(evt);
            }
        });

        Jugar.setText("Jugar");

        AplicarSorpresa.setText("Aplicar Sorpresa");

        SiguienteTurno.setText("Siguiente turno");

        ComprarPropiedad.setText("Comprar propiedad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(vistaQytetet1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(SalirCarcelDado))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(SalirCarcelPagando)))
                .addGap(46, 46, 46)
                .addComponent(Jugar)
                .addGap(58, 58, 58)
                .addComponent(ComprarPropiedad)
                .addGap(27, 27, 27)
                .addComponent(AplicarSorpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(SiguienteTurno)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(vistaQytetet1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SalirCarcelDado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(SalirCarcelPagando))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Jugar)
                            .addComponent(ComprarPropiedad)
                            .addComponent(AplicarSorpresa)
                            .addComponent(SiguienteTurno))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirCarcelDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirCarcelDadoActionPerformed
        boolean resultado = modeloQytetet.intentarSalirCarcel(modeloqytetet.MetodoSalirCarcel.TIRANDODADO);
        this.SalirCarcelPagando.setEnabled(false);
        this.SalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel");
            this.Jugar.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(this, "NO sales de la cárcel");
            this.SiguienteTurno.setEnabled(true);
        }
        this.vistaQytetet1.actualizar(modeloQytetet);
    }//GEN-LAST:event_SalirCarcelDadoActionPerformed

    private void SalirCarcelPagandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirCarcelPagandoActionPerformed
        boolean resultado = modeloQytetet.intentarSalirCarcel(modeloqytetet.MetodoSalirCarcel.PAGANDOLIBERTAD);
        this.SalirCarcelPagando.setEnabled(false);
        this.SalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel");
            this.Jugar.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(this, "NO sales de la cárcel");
            this.SiguienteTurno.setEnabled(true);
        }
        this.vistaQytetet1.actualizar(modeloQytetet);
    }//GEN-LAST:event_SalirCarcelPagandoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ControladorQytetet controladorQytetet= new ControladorQytetet();
        
        CapturaNombreJugadores capturaNombres = new CapturaNombreJugadores(controladorQytetet, true);
        ArrayList<String> nombres = capturaNombres.obtenerNombres();
        
        Dado.createInstance(controladorQytetet);
        modeloqytetet.Qytetet modelo = modeloqytetet.Qytetet.getInstance();
        modelo.inicializarJuego(nombres);
        
        controladorQytetet.actualizar(modelo);
        
        controladorQytetet.setVisible(true); 
        
    }
    
    public void habilitarComenzarTurno(){
        this.SalirCarcelDado.setEnabled(false);
        this.SalirCarcelPagando.setEnabled(false);
        this.ComprarPropiedad.setEnabled(false);
        this.SiguienteTurno.setEnabled(false);
        this.AplicarSorpresa.setEnabled(false);
        if(modeloQytetet.getJugadorActual().getEncarcelado()){
            this.SalirCarcelPagando.setEnabled(true);
            this.SalirCarcelDado.setEnabled(true);
        }
        else
            this.Jugar.setEnabled(true);
    }
    
    public void actualizar(modeloqytetet.Qytetet qytetet){
        vistaQytetet1.actualizar(qytetet);
        this.modeloQytetet = qytetet;
        habilitarComenzarTurno();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AplicarSorpresa;
    private javax.swing.JButton ComprarPropiedad;
    private javax.swing.JButton Jugar;
    private javax.swing.JButton SalirCarcelDado;
    private javax.swing.JButton SalirCarcelPagando;
    private javax.swing.JButton SiguienteTurno;
    private GUIQytetet.VistaQytetet vistaQytetet1;
    // End of variables declaration//GEN-END:variables
}
