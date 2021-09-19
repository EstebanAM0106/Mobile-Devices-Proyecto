package mx.ipn.escom.plantas.Database;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import mx.ipn.escom.plantas.Adapter.PlantasInformacion;

public class EnviarBaseDatosModificar {

    private Connection connection;
    private Database db = new Database();
    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"  // For Amazon Postgresql
    private final String host = db.getHost();  // For Google Cloud Postgresql
    private final String database = db.getDatabase();
    private final int port = db.getPort();
    private final String user =db.getUser();
    private final String pass = db.getPass();
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    private Context context;
    private View view;
    private Resources resource;
    private PlantasInformacion plantasInformacion;

    public EnviarBaseDatosModificar(PlantasInformacion plantasInformacion) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.plantasInformacion = plantasInformacion;
        this.context = context;
        this.view = view;
        this.resource = resource;

        connect();
        //this.disconnect();
        System.out.println(" connection status:" + status + " ");
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    if(plantasInformacion.getId()>=0){
                        PreparedStatement statement = connection.prepareStatement("UPDATE plantas SET " +
                                "nombre = (?), " +
                                "nombreAlt = (?), " +
                                "imagenURL = (?), " +
                                "reino = (?), " +
                                "division = (?), " +
                                "clase = (?), " +
                                "orden = (?), " +
                                "familia = (?), " +
                                "genero = (?), " +
                                "especie = (?), " +
                                "altura = (?), " +
                                "alturaUnidad = (?), " +
                                "diametro = (?), " +
                                "diametroUnidad = (?), " +
                                "ciclo = (?), " +
                                "cicloUnidad = (?), " +
                                "maceta = (?), " +
                                "luminosidad = (?), " +
                                "otrasRecomendaciones = (?), " +
                                "descripcion = (?), " +
                                "ultimoUsuario = (?) " +
                                "WHERE plantaId = "+plantasInformacion.getId());

                        statement.setString(1, plantasInformacion.getNombre());
                        statement.setString(2, plantasInformacion.getNombreAlt());
                        statement.setString(3, plantasInformacion.getImagenURL());
                        statement.setString(4, plantasInformacion.getReino());
                        statement.setString(5, plantasInformacion.getDivision());
                        statement.setString(6, plantasInformacion.getClase());
                        statement.setString(7, plantasInformacion.getOrden());
                        statement.setString(8, plantasInformacion.getFamilia());
                        statement.setString(9, plantasInformacion.getGenero());
                        statement.setString(10, plantasInformacion.getEspecie());
                        statement.setInt(11, plantasInformacion.getAltura());
                        statement.setString(12, plantasInformacion.getAlturaUnidad());
                        statement.setInt(13, plantasInformacion.getDiametro());
                        statement.setString(14, plantasInformacion.getDiametroUnidad());
                        statement.setInt(15, plantasInformacion.getCicloRiego());
                        statement.setString(16, plantasInformacion.getCicloRiegoUnidad());
                        statement.setBoolean(17, plantasInformacion.getEsMaceta());
                        statement.setInt(18, plantasInformacion.getLuminosidad());
                        statement.setString(19, plantasInformacion.getOtrasRecomendaciones());
                        statement.setString(20, plantasInformacion.getDescripcion());
                        statement.setInt(21, plantasInformacion.getUltimoUsuario());
                        statement.execute();
                    }else {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                                "plantas (nombre,nombreAlt,imagenURL,reino,division,clase,orden,familia,genero,especie,altura,alturaUnidad,diametro,diametroUnidad,ciclo,cicloUnidad,maceta,luminosidad,otrasRecomendaciones,descripcion,ultimoUsuario) " +
                                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        statement.setString(1, plantasInformacion.getNombre());
                        statement.setString(2, plantasInformacion.getNombreAlt());
                        statement.setString(3, plantasInformacion.getImagenURL());
                        statement.setString(4, plantasInformacion.getReino());
                        statement.setString(5, plantasInformacion.getDivision());
                        statement.setString(6, plantasInformacion.getClase());
                        statement.setString(7, plantasInformacion.getOrden());
                        statement.setString(8, plantasInformacion.getFamilia());
                        statement.setString(9, plantasInformacion.getGenero());
                        statement.setString(10, plantasInformacion.getEspecie());
                        statement.setInt(11, plantasInformacion.getAltura());
                        statement.setString(12, plantasInformacion.getAlturaUnidad());
                        statement.setInt(13, plantasInformacion.getDiametro());
                        statement.setString(14, plantasInformacion.getDiametroUnidad());
                        statement.setInt(15, plantasInformacion.getCicloRiego());
                        statement.setString(16, plantasInformacion.getCicloRiegoUnidad());
                        statement.setBoolean(17, plantasInformacion.getEsMaceta());
                        statement.setInt(18, plantasInformacion.getLuminosidad());
                        statement.setString(19, plantasInformacion.getOtrasRecomendaciones());
                        statement.setString(20, plantasInformacion.getDescripcion());
                        statement.setInt(21, plantasInformacion.getUltimoUsuario());
                        statement.execute();
                    }
                    connection.close();
                } catch (Exception e) {
                    status = false;
                    System.out.print("connected:" + status +" ");
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }
}
