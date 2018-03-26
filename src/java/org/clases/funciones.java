/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clases;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class funciones {

    private String nombre = "Maria";
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private String fecha = null;
    private Date dFecha = null;
    private Date fec = null;
    private String strFecha = "11/09/2001";
    private Date date1;
    private String fechaFormateada;
    private String text;
    private boolean toggled;
    private String number = "south-street";

    public Integer numeroMes(String mes) {
        Integer num = 0;
        if (mes.equals("Enero")) {
            num = 1;
        }
        if (mes.equals("Febrero")) {
            num = 2;
        }
        if (mes.equals("Marzo")) {
            num = 3;
        }
        if (mes.equals("Abril")) {
            num = 4;
        }
        if (mes.equals("Mayo")) {
            num = 5;
        }
        if (mes.equals("Junio")) {
            num = 6;
        }
        if (mes.equals("Julio")) {
            num = 7;
        }
        if (mes.equals("Agosto")) {
            num = 8;
        }
        if (mes.equals("Septiembre")) {
            num = 9;
        }
        if (mes.equals("Octubre")) {
            num = 10;
        }
        if (mes.equals("Noviembre")) {
            num = 11;
        }
        if (mes.equals("Diciembre")) {
            num = 12;
        }
        return num;
    }

    public long fechasDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
        //java.util.Date hoy = new Date(); //Fecha de hoy 


        //Date fecha = new Date();
        Calendar ahoraCal = Calendar.getInstance();
        //System.out.println("fechaInicial " + fechaInicial);
        //System.out.println("fechaInicial " + fechaFinal);
        ahoraCal.setTime(fechaInicial);
        //diaMes(String.valueOf(ahoraCal.get(Calendar.MONTH) + 1), String.valueOf(ahoraCal.get(Calendar.YEAR)));
        //mes = String.valueOf(ahoraCal.get(Calendar.MONTH) + 1);

        int año = ahoraCal.get(Calendar.YEAR);
        int mes = ahoraCal.get(Calendar.MONTH);
        int dia = ahoraCal.get(Calendar.DAY_OF_MONTH); //Fecha anterior 
        //System.out.println("anio: " + año);
        mes = mes + 1;
        //System.out.println("mes: " + mes);
        //System.out.println("dia: " + dia);
        Calendar calendar = new GregorianCalendar(año, mes - 1, dia);
        java.sql.Date fech = new java.sql.Date(calendar.getTimeInMillis());
        //long diferencia = (hoy.getTime() - fech.getTime()) / MILLSECS_PER_DAY;
        long diferencia = (fechaFinal.getTime() - fech.getTime()) / MILLSECS_PER_DAY;
        //System.out.println(diferencia);

        /*DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
         String fechaInicioString = df.format(fechaInicial);
         try {
         fechaInicial = df.parse(fechaInicioString);
         } catch (ParseException ex) {
         }
         String fechaFinalString = df.format(fechaFinal);
         try {
         fechaFinal = df.parse(fechaFinalString);
         } catch (ParseException ex) {
         }

         long fechaInicialMs = fechaInicial.getTime();
         long fechaFinalMs = fechaFinal.getTime();
         long diferencia = fechaFinalMs - fechaInicialMs;
         System.out.println(fechaInicialMs);
         System.out.println(fechaFinalMs);
         double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
         System.out.println("Dias: " + (int) dias);*/
        return diferencia;
    }

    public int restarFechas(Date fechaMayor, Date fechaMenor) {
        GregorianCalendar d1 = new GregorianCalendar();
        d1.setTime(fechaMenor); //dateIni es el objeto Date
        GregorianCalendar d2 = new GregorianCalendar();
        d2.setTime(fechaMayor); //dateFin es el objeto Date
        int rango = 0;
        //System.out.println(d1.get(Calendar.YEAR) + " " + d2.get(Calendar.YEAR));
        //System.out.println(d1.get(Calendar.YEAR) + " " + d2.get(Calendar.YEAR));

        if (d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR)) {
            rango = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
            //System.out.println("Mismo anio");
        } else {
            int diasAnyo = d1.isLeapYear(d1.get(Calendar.YEAR)) ? 366 : 365;
            int rangoAnyos = d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR);
            rango = (rangoAnyos * diasAnyo) + (d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR));
            //System.out.println("Diferente anio");
        }
        //System.out.println("rango: " + rango);
        return rango;
    }

    public double redondear(Double x) {
        //double x = 0.9999999999;
        NumberFormat mf = NumberFormat.getInstance();
        mf.setMaximumFractionDigits(2);
        String string = mf.format(x);
        System.out.println(string);
        return Double.valueOf(string);
    }

    public BigDecimal redondearBig(String decimal) {
        //String decimal = "3.99999";
        //paso el string a un BigDecimal  
        BigDecimal bigDecimal = new BigDecimal(decimal);
        //con el metodo setScale(int newScale, RoundingMode roundingMode) del BigDecimal  
        //establezco los parametros del redondeo, en este caso 4 el numero de decimales  
        //RoundingMode.HALF_UP el modo de redondeo HALF_UP es de la mitad hacia arriba  
        BigDecimal bigDecimalRedondeado = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        //System.out.println("Decimal redondeado: " + bigDecimalRedondeado);
        return bigDecimalRedondeado;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String obtenerFechaCalendar(Date fecha) {

        Calendar ahoraCal = Calendar.getInstance();
        ahoraCal.setTime(fecha);
        //System.out.println(toFecha.getFechaFormateada());
        //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/DD/YYYY");
        fechaFormateada = ahoraCal.get(Calendar.DATE) + "/" + (ahoraCal.get(Calendar.MONTH) + 1) + "/" + ahoraCal.get(Calendar.YEAR);
        return fechaFormateada;
    }

    public String getFechaFormateada() {
        /*fechaFormateada = String.valueOf(date1);
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         try {
         fec = formato.parse(fechaFormateada);
         } catch (ParseException ex) {
         ex.printStackTrace();
         }
         fechaFormateada = String.valueOf(fec);
         System.out.println("Fecha formateada: " + fechaFormateada);*/

        Calendar ahoraCal = Calendar.getInstance();
        //System.out.println(ahoraCal.getClass());
        ahoraCal.setTime(date1);
        //System.out.println("ANYO: " + ahoraCal.get(Calendar.YEAR));
        //System.out.println("MES: " + ahoraCal.get(Calendar.MONTH)+1);
        //System.out.println("DIA: " + ahoraCal.get(Calendar.DATE));
        //System.out.println("HORA: " + ahoraCal.get(Calendar.HOUR));
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/DD/YYYY");        
        fechaFormateada = ahoraCal.get(Calendar.DATE) + "/" + (ahoraCal.get(Calendar.MONTH) + 1) + "/" + ahoraCal.get(Calendar.YEAR);
        return fechaFormateada;
    }

    public void setFechaFormateada(String fechaFormateada) {
        this.fechaFormateada = fechaFormateada;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public String ok() {
        System.out.println("OK");
        System.out.println(date1);
        //Aqui tenemos que utiliza las funciones de fecha para demenuzarla y lograr el formato dd/MM/yy
        //luego asignarla a una variable y poder guardarla
        return "Well Done";

    }

    public Date getFec() {
        /*Random random = new Random();
         int day = random.nextInt(30);
         int month = random.nextInt(Calendar.DECEMBER + 1);
         int year = random.nextInt(2012);
         GregorianCalendar calendar = new GregorianCalendar(year, month, day);
         System.out.println("DIA " + day);
         System.out.println("Mes " + month);
         System.out.println("Año" + year);*/

        /*SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
         try {
         fec = formatoDelTexto.parse(strFecha);
         } catch (ParseException ex) {
         ex.printStackTrace();
         }*/
        Date date = new Date();
        //return calendar.getTime();
        return date;

        //return fec;
    }

    public Date getdFecha() {
        return dFecha;
    }

    public void setdFecha(Date dFecha) {
        this.dFecha = dFecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void ir(String link) {
        try {
            externalContext.redirect(link.trim());
        } catch (IOException ex) {
            //Logger.getLogger(BeanDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String otraPagina() {
        return "bienvenidos";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public funciones() {
    }

    public int compararFechas(String fechaInicial, String fechaFinal) {
        int resultado = -1;
        try {
            /**
             * Obtenemos las fechas enviadas en el formato a comparar
             */
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(fechaInicial);
            Date fechaDate2 = formateador.parse(fechaFinal);
            if (fechaDate1.before(fechaDate2)) {
                resultado = 1;
            } else {
                if (fechaDate2.before(fechaDate1)) {
                    resultado = 0;
                } else {
                    resultado = 1;
                }
            }
        } catch (ParseException e) {
            System.out.println("Se Produjo un Error!!!  " + e.getMessage());
        }
        return resultado;
    }

    public Date funciones(String sFecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = formatoDelTexto.parse(sFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        finally{
            return date1;
        }
        /*java.util.Date date = new java.util.Date();
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/DD/YYYY");
         fecha = sdf.format(date).toString();*/
        //SimpleDateFormat formatoDelTexto = new SimpleDateFormat("YYYY/MM/dd");
         /*String strFecha = "1984/11/03";
         try {
         fec = formatoDelTexto.parse(strFecha);
         } catch (ParseException ex) {
         ex.printStackTrace();
         }*/
        /*Calendar ahoraCal = Calendar.getInstance();
         System.out.println(ahoraCal.getClass());
         ahoraCal.set(8, 2, 8);
         System.out.println("ANYO: " + ahoraCal.get(Calendar.YEAR));
         System.out.println("MES: " + ahoraCal.get(Calendar.MONTH));
         System.out.println("DIA: " + ahoraCal.get(Calendar.DATE));
         System.out.println("HORA: " + ahoraCal.get(Calendar.HOUR));
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/DD/YYYY");        
         fecha = ahoraCal.get(Calendar.DATE) + "/" + ahoraCal.get(Calendar.MONTH) + "/" + ahoraCal.get(Calendar.YEAR);
         try {
         fec = formatoDelTexto.parse(fecha);
         System.out.println(fecha);
         } catch (ParseException ex) {
         Logger.getLogger(BeanDatos.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }
}
