package classes.dataehora;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class formataData
{


    private Date dataformatodata = null;
    private String dataformatostring = "";


    public formataData(String data, String formatoentrada, String formatoretorno)
    {
        formataStringNoFormatoString(data, formatoentrada, formatoretorno);
    }


    public String getStringFormatada()
    {
        return dataformatostring;
    }

    public String getFormatStringDataformatada(String data, String formatoentrada, String formatoretorno)
    {
        return formataStringNoFormatoString(data, formatoentrada, formatoretorno);
    }


    private String formataStringNoFormatoString(String data, String formatoentrada, String formatoretorno)
    {
           SimpleDateFormat formatoInicial;
        SimpleDateFormat formatoSaida;

        if (formatoentrada.isEmpty())
            formatoInicial = new SimpleDateFormat("dd/MM/yyyy");
        else
            formatoInicial = new SimpleDateFormat(formatoentrada);
        if (formatoretorno.isEmpty())
            formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
        else
            formatoSaida = new SimpleDateFormat(formatoretorno);
        try {
            Date datar = formatoInicial.parse(data);
            dataformatostring = (formatoSaida.format(datar));
        } catch (Exception  ex) {JOptionPane.showMessageDialog(null, ex);}
        return  dataformatostring;
    }






    }










