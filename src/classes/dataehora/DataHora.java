/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.dataehora;



import classes.outras.escreve;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Moises
 */
public class DataHora
{

    private static String timezone="GMT-2";
    private static String Local="pt_BR";

     public DataHora()
    {

     escreve.eL(timezone);
     escreve.eL(Local);
    }



    public static String getHoraAtual()
    {
           Calendar agora = Calendar.getInstance(TimeZone.getTimeZone(timezone) , new Locale(Local));
	   // horas, minutos e segundos
	   int horas = agora.get(Calendar.HOUR_OF_DAY);
	   int minutos = agora.get(Calendar.MINUTE);
	   int segundos = agora.get(Calendar.SECOND);

	   System.out.println("Hora Atual: " + horas +
              ":" + minutos + ":" + segundos);

           String hora = horas + ":" + minutos + ":" + segundos;
           return hora;

    }


        public static String getHoraDodia()
    {
           Calendar agora = Calendar.getInstance(TimeZone.getTimeZone(timezone) , new Locale(Local));
	   // horas, minutos e segundos
	   int minutos = agora.get(Calendar.HOUR_OF_DAY);
           String hora = String.valueOf(minutos);
           return hora;

    }

    public static String getMinutosDahora()
    {
           Calendar agora = Calendar.getInstance(TimeZone.getTimeZone(timezone) , new Locale(Local));
	   // horas, minutos e segundos
	   int horas = agora.get(Calendar.MINUTE);
           String hora = String.valueOf(horas);
           return hora;

    }

          public static String getSegundosDodia()
    {
           Calendar agora = Calendar.getInstance(TimeZone.getTimeZone(timezone) , new Locale(Local));
	   // horas, minutos e segundos
	   int segundos = agora.get(Calendar.SECOND);
           String hora = String.valueOf(segundos);
           return hora;

    }




    public static String getDataAtual()
    {

        GregorianCalendar calendario = new GregorianCalendar( TimeZone.getTimeZone(timezone) , new Locale(Local));
        //calendario.add(GregorianCalendar.DATE , diasasomar);
        Date dt =  calendario.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String dataatualizada = df.format(dt);
        System.out.println("hoje é  " + dataatualizada);
        return dataatualizada;
    }


    public static void setTimeZoneDefult()
    {
            TimeZone.setDefault(TimeZone.getTimeZone(timezone));
            Date d = new Date();
            System.out.println(d.toString());
            Calendar  c = Calendar.getInstance();
            System.out.println(c.getTime().toString());
    }


public static void main(String args[])
    {


    escreve.eL(getHoraAtual().toString());

}



}
