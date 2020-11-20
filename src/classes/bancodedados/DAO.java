package classes.bancodedados;



import classes.outras.escreve;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




/**
 *Classe de Conexão com banco de dados
 * @author Moisés Alves Costa
 * @version 1.0.1
 */

public class DAO
{




    final private String driver = "org.apache.derby.jdbc.ClientDriver";
    final private String url = "jdbc:derby://localhost:1527/produtos";


    public Connection conexão;
    // campo que clia a conexão com o bando de dados
    public Statement statement;
    // abre o caminho ate o banco de dados
    public ResultSet resultset;
    // amazena o resultado das consultas
    private int recorCont =0;
    private boolean conectadoaoBancodedados = false;
    private String nomebd = "";



    public DAO(boolean conectaraoIniciar, boolean iniciarStatantmenteaoIniciar,String ExecultaConsult)
    {


        if (conectaraoIniciar == true)
            conectaaoIniciar();
        if (iniciarStatantmenteaoIniciar == true)
        {
            try {
                statement = conexão.createStatement(resultset.TYPE_SCROLL_SENSITIVE, resultset.CONCUR_READ_ONLY);
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!ExecultaConsult.isEmpty() && ExecultaConsult != null)
            {
                execconsulta(ExecultaConsult);
            }
        }
    }


    /**
     @return Booleam 1 ok 0 erro
     *
     */
    
    private boolean conectaaoIniciar()
    {
        return iniciaConexão();
    }

     /**
      * Inicia a conexão com o banco de dados
      @return Booleam 1 ok 0 erro
      * 
      */
    public boolean iniciaConexão()
    {
        try
        {

            Class.forName(driver).newInstance();
            //Loader para carregar a class
            // metodo estatico que registra o driver automaticamente
            escreve.eL(url);
            conexão = DriverManager.getConnection(url,"usuario","usuario123");
            conectadoaoBancodedados = true;
         }catch(SQLException sqlerro)
         {
         escreve.eL("Erro no acesso a dados: "+   String.valueOf(sqlerro.getErrorCode()));
             if(sqlerro.getErrorCode() == 40000)
             {
                 JOptionPane.showMessageDialog(null, "Usuário ou senha do banco de dados configurados incorretamente.");

                 return false;
             }


             if(sqlerro.getErrorCode()==-1023)
             {
                 JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao banco de dados. Verifique o servidor.\nSe o erro persistir contate o suporte.","Erro",JOptionPane.ERROR_MESSAGE);


             }
             else
             {
                 JOptionPane.showMessageDialog(null, sqlerro.getMessage() + ".Código:" + sqlerro.getErrorCode());
                 
             }

         }

        catch(Exception erro){JOptionPane.showMessageDialog(null, erro);conectadoaoBancodedados = false;}
        finally
        {
            return conectadoaoBancodedados;
        }

    }



    /**
     * Finaliza conexão com banco de dados.
     * @return um <code>integer</code> especificando a hora.
     */
    
    public void finalizaConexão()
    {
        try {
            conexão.close();
            conectadoaoBancodedados = false;
        } catch (SQLException ex) {JOptionPane.showMessageDialog(null, ex);}

    }


      /**
     *
     * @return um <code>integer</code> especificando o numero de registros.
     */
    public int getTotalDadosResultSet()
    {
        return recorCont;
    }

    private void setTotalDadosResultset()
    {
        int contagem = 0;

        try
        {
         resultset.beforeFirst();
         if(resultset.next())
             resultset.last();
            contagem = resultset.getRow();
        }
        catch(Exception erro){}
        finally
        {
            recorCont = contagem;
        }
    }




      /**
     *
     * @return um <code>ResultSet</code> especificando com os dados do banco de dados.
     * @param uma String com o sql de seleção
     */

public ResultSet getResultSet(String Sql)
    {

        try
        {
        statement = conexão.createStatement(resultset.TYPE_SCROLL_SENSITIVE,resultset.CONCUR_READ_ONLY );
        resultset = statement.executeQuery(Sql);
        resultset.beforeFirst();
        setTotalDadosResultset();
        }
        catch(SQLException erro)
        {
            escreve.eL(Sql);
            escreve.eL("Sql erro: " + String.valueOf(erro.getErrorCode()));
            
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }

        return resultset;
    }


      /**
     * Execulta um Update no banco de dados
     * @return um <code>Booleam</code> 1 ok 0 erro
     * @param uma String com o sql de Update
     */

public boolean salvaBancoDados(String sql)
    {

    boolean retorno = false;

        try
        {
            String sqlInsert = sql;
            statement.executeUpdate(sqlInsert);
            retorno = true;

        }catch(SQLException erro)

        {
            escreve.eL(sql);
            escreve.eL("Erro no acesso a dados: "+   String.valueOf(erro.getErrorCode()));


            if(erro.getErrorCode() == 1451)
            {
                JOptionPane.showMessageDialog(null, "Não é possível excluir este registro, ele esta relacionado com outros lançamentos no sistema.","Erro",JOptionPane.ERROR_MESSAGE);
            }
            else
                {

                    if(erro.getErrorCode() == 1062)
                    {
                        JOptionPane.showMessageDialog(null, "Não foi possível salvar ou alterar os dados porque a identificação deve ser única.","Erro",JOptionPane.ERROR_MESSAGE);
                    }
                     else
                    {
                        JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                    }

            }

        }
        catch(Exception e){JOptionPane.showMessageDialog(null, e.getMessage());}
    finally
            {
            return retorno;
         }

    }


      /**
     * Verifica se a tabela existe
     * @return um <code>Booleam</code> 1 existe 0 Não existe
     * @param uma String com o sql de seleção
     */

public boolean getTabelaExiste(String sql)
{
    try
        {
            statement = conexão.createStatement(resultset.TYPE_SCROLL_SENSITIVE,resultset.CONCUR_READ_ONLY );
            resultset = statement.executeQuery(sql);
        }
        catch(SQLException erro){return false;}

        return true;
    }


      /**
     * Cria uma tabela
     * @return um <code>Booleam</code> 1 ok 0 erro
     * @param uma String com o sql de criação de tabela
     */

 public boolean criaTabela(String sql)
    {
        try
            {
            statement = conexão.createStatement(resultset.TYPE_SCROLL_SENSITIVE,resultset.CONCUR_READ_ONLY );
            statement.execute(sql);
            }
            catch(SQLException erro){
                JOptionPane.showMessageDialog(null, erro);
                return false;}
            return true;
    }


          /**
     * Retorna se esta conectado ou não ao banco de dados
     * @return um <code>Booleam</code> 1 Conectado 0 Não conectado
     * @param uma String com o sql de criação de tabela
     */
    public boolean getEstaConectado()
    {
        return conectadoaoBancodedados;
    }


     /**
     * Exeulta uma consulta Query no banco de dados
     * @param uma String com o sql de criação de Consulta Query
     */
    public void execultaConsulta(String Sql)
    {
        execconsulta(Sql);
    }

    private void execconsulta(String Sql)
    {

         try
        {
            resultset = statement.executeQuery(Sql);
            setTotalDadosResultset();
        }
        catch(SQLException erro)
        {
            escreve.eL(Sql);
            escreve.eL("Erro no acesso a dados: "+   String.valueOf(erro.getErrorCode()));
            escreve.eL("Erro no acesso a dados: "+   String.valueOf(erro.getMessage()));
            if(erro.getErrorCode() ==-1)
            {
                JOptionPane.showMessageDialog(null, "Não foi possível encontrar uma tabela do banco de dados.\nO sistema sera fechado.\nSe o erro persistir contate o suporte.");
                System.exit(0);
            }

            escreve.eL("Execulta consulta Sql erro: " + String.valueOf(erro.getErrorCode()));
            JOptionPane.showMessageDialog(null, erro);
        }


    }



}
