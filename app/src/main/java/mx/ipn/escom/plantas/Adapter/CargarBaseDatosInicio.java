package mx.ipn.escom.plantas.Adapter;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.plantas.ui.inicio.InicioFragment;

public class CargarBaseDatosInicio {

    private Connection connection;

    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"  // For Amazon Postgresql
    private final String host = "192.168.0.106";  // For Google Cloud Postgresql
    private final String database = "plantas";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "admin123";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    private Context context;
    private RecyclerView recyclerView;
    private List<Plantas> plantasList = new ArrayList<>();
    private int usuarioID;

    public CargarBaseDatosInicio(Context context, RecyclerView recyclerView, int usuarioID) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.context = context;
        this.recyclerView = recyclerView;
        this.usuarioID=usuarioID;
        connect();
        //this.disconnect();
        System.out.println("connection status:" + status + " ");
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    String query = "SELECT * FROM plantas";
                    String query2;
                    System.out.println("connected:" + status + " ");

                    Statement statement = connection.createStatement();
                    Statement statement2;
                    ResultSet result = statement.executeQuery(query);
                    ResultSet result2;
                    boolean esFavorito;
                    while(result.next()){
                        int plantaId = result.getInt("plantaId");
                        String nombre = result.getString("nombre");
                        String nombreAlt = result.getString("nombreAlt");
                        String imagenURL = result.getString("imagenURL");
                        query2 = "SELECT count(plantaId) AS esFavorito FROM favoritos WHERE plantaid="+plantaId+"and usuarioid="+usuarioID;
                        statement2 = connection.createStatement();
                        result2 = statement2.executeQuery(query2);
                        result2.next();
                        esFavorito = false;
                        if(result2.getInt("esFavorito")>=1){
                            esFavorito=true;
                        }
                        plantasList.add(new Plantas(plantaId, nombre, nombreAlt, imagenURL,esFavorito));
                    }
                    InicioFragment.cargar(plantasList,context,recyclerView);
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
