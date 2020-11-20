package classes.bancodedados;

import java.io.File;
import org.apache.derby.drda.NetworkServerControl;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class iniciaServidorJavaDb
{
     public NetworkServerControl servidor;

     public iniciaServidorJavaDb(boolean iniciaoServidor)
    {
         if(iniciaoServidor == true)
        startupServidor();
     }


     private void startupServidor()
    {
         iniciaServidor();
     }


    public void iniciaServidor()
    {
        try
        {
        //iniciar pelo servidor de ip
        servidor = new NetworkServerControl(InetAddress.getByName("0.0.0.0"), 1527);
        //servidor = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
        servidor.start(null);
        
        
        }catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null, erro);
            JOptionPane.showMessageDialog(null, "Não foi possivel iniciar o servidor do banco de dados.");
        
        }
    }


    public void paraServidor()
    {
         try {
            servidor.shutdown();
            
        } catch (Exception ex){}
    }



public boolean getServidorOnline()
    {
           boolean teste = isServerStarted(servidor, 1);
           return teste;
    }


    private static boolean isServerStarted(NetworkServerControl server, int ntries)
{
        for (int i = 1; i <= ntries; i ++)
        {
            try {
                Thread.sleep(500);
                server.ping();
                return true;
            }
            catch (Exception e) {
                if (i == ntries)
                return false;
            }
        }
    return false;
}


public static void main(String args[])
{
    iniciaServidorJavaDb teste = new iniciaServidorJavaDb(true);

    if (teste.getServidorOnline() == true)
        JOptionPane.showMessageDialog(null, "Servidor online");
    else
        JOptionPane.showMessageDialog(null, "Servidor Offline");
    

               
}




}
