package mx.ipn.escom.plantas.Database;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EnviarBaseDatosEstadoFavoritos {

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
    private int idPlanta;
    private int idUsuario;
    private Boolean esFavorito;
    public EnviarBaseDatosEstadoFavoritos(int idPlanta, int idUsuario, Boolean esFavorito) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.idPlanta = idPlanta;
        this.idUsuario = idUsuario;
        this.esFavorito = esFavorito;
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
                    if(esFavorito){
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                                "favoritos (usuarioId,plantaId) " +
                                "VALUES (?,?)" );
                        statement.setInt(1, idUsuario);
                        statement.setInt(2, idPlanta);
                        statement.execute();
                    }else {
                        PreparedStatement statement = connection.prepareStatement("DELETE FROM " +
                                "favoritos "+
                                "WHERE usuarioId = (?) AND plantaId = (?)");
                        statement.setInt(1, idUsuario);
                        statement.setInt(2, idPlanta);

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
