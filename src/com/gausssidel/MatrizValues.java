package com.gausssidel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MatrizValues extends javax.swing.JFrame {

    private int filas;
    private int columnas;
    private JTextField[][] celda;
    
    public MatrizValues(){
        
    }
    
    public MatrizValues(int filas, int columnas) {
        
        this.filas = filas;
        this.columnas = columnas;
        initComponents();
        matriz();
        
        
        
        setSize(800, 450);
        
        getContentPane().setBackground(new Color(0xb8b8ff));
        
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
    
    private void matriz() {
        Calculos calculos = new Calculos();
        
        //Componentes de la ventana
        celda = new JTextField[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int c = j;
                c+=1;
                if(j!=(columnas-1)){
                    JLabel label = new JLabel("x" + c);
                    label.setBounds(j * 120 + 112, i * 30 + 50, 100, 20);
                    add(label);
                }else{
                    JLabel label = new JLabel(" = ");
                    label.setBounds(j * 120 + 12, i * 30 + 50, 100, 20);
                    add(label);
                }
                
                celda[i][j] = new JTextField();
                celda[i][j].setBounds(j * 120 + 50, i * 30 + 50, 60, 20);
                add(celda[i][j]);
            }
        }
        

        JLabel tolerancia = new JLabel("Tolerancia de error");
        tolerancia.setBounds(50, (filas + 1)* 30 + 50, 200, 20);
        add(tolerancia);
        
        
        JTextField t  = new JTextField();
        t.setBounds(180, (filas + 1)* 30 + 50, 100, 20);
        add(t);
        
        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(50, (filas + 2) * 30 + 50, 100, 30); 
        add(limpiar);
        
        JButton calcular = new JButton("Calcular");
        calcular.setBounds(180, (filas + 2) * 30 + 50, 100, 30); 
        add(calcular);
        
        
        JTextArea resultados = new JTextArea();
        resultados.setBounds(310, (filas + 1) * 30 + 60, 400, 200);
        add(resultados);
        

        //Eventos de los botones
        calcular.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{ 
                double [][] matriz = new double[filas][columnas];
                double tolerancia = Double.parseDouble(t.getText());
                
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {                   
                        matriz[i][j] = Double.parseDouble(celda[i][j].getText());
                    }
                }
                
                if(!calculos.esDominante(matriz)){
                    JLabel alerta = new JLabel("El sistema no es diagonalmente dominante");
                    alerta.setForeground(Color.RED);
                    alerta.setBounds(115, 300, 300, 20);
                    getContentPane().add(alerta);
                    validate();
                    repaint();     
                };
                
                
                //StringBuilder texto = new StringBuilder();
                //texto.append("Valor en [" + i + "][" + j + "]: " + valor + "\n");
                //resultados.setText(texto.toString());
            
            }catch(Exception i){
                JLabel errorLabel = new JLabel("Advertencia: No letras");
                errorLabel.setForeground(Color.RED);
                errorLabel.setBounds(115, 300, 300, 20);
                getContentPane().add(errorLabel);
                validate();
                repaint();
                
            }

        }
     });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gauss-Sidel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatrizValues().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
