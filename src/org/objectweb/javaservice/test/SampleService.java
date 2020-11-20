package org.objectweb.javaservice.test;

import classes.bancodedados.DAO;
import classes.bancodedados.iniciaServidorJavaDb;
import classes.dataehora.DataHora;
import classes.outras.escreve;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class SampleService {



    private static Boolean status = false;
    
    
    private static void println(String str) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private static String timestamp() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private static void trace(String str) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public static void main(String[] args) {
        //compiled code
       
        
        while(true)
        {

                                String horaAgora =  DataHora.getHoraAtual();
                             
                                if(!status)
                                {
                                    if (iniciaServidor() == false)
                                    {
                                        status = false;
                                        System.out.println("Servidor Não Iniciado");
                                        System.exit(0);
                                    }
                                    else
                                    {
                                        status = true;
                                        System.out.println("Servidor iniciado ");
                                        fazPrimenriConecao();
                                    }
                                }
                                else
                                {
                                    System.out.println("Servidor Online");
                                    
                                }

                                
                                try {
                                    Thread.sleep(800);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(SampleService.class.getName()).log(Level.SEVERE, null, ex);
                                }

        }
        
}
        

    public static void outputJvmDetails() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public static void outputHeapDetails() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private static String kBytes(long bytes) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public static void serviceStart(String[] args) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public static void serviceStop(String[] args) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    static synchronized SampleService getServiceInstance() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private static void fazPrimenriConecao() 
    {
          DAO d = new DAO( true,true, "SELECT * FROM APP.DADOSSISTEMA"  );
         if(!d.getEstaConectado())
         {
             JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados o sistema sera fechado.");
             System.exit(0);
         }
         d.finalizaConexão();

        
    }

    private SampleService() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private synchronized void setServiceExecuting(boolean executingNow) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    synchronized boolean isServiceExecuting() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private void execute(String[] args) {
        //compiled code
        
        throw new RuntimeException("Compiled Code");
    }

    private void doSomething(boolean allocateMemory) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private void abort() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }
    
    
    private static boolean iniciaServidor()
    {
                /** tente iniciar o servidor de banco de dados 3 vezes se não conseguir sai do sistema */

        for(int i = 0; i<3;i++)
        {
            iniciaServidorJavaDb inicia = new iniciaServidorJavaDb(true);

           if(inicia.getServidorOnline() == true)
           {
               return true;
           }

        }

        return false;

    }


    
    
}
