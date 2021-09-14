package mx.ipn.escom.plantas.Adapter;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.plantas.ui.favoritos.FavoritosFragment;

public class CargarBaseDatosFavoritos {

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

    public CargarBaseDatosFavoritos(Context context, RecyclerView recyclerView,int usuarioID) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.context = context;
        this.recyclerView = recyclerView;
        this.usuarioID = usuarioID;
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
                    String query = "SELECT * FROM plantas JOIN favoritos ON favoritos.plantaId = plantas.plantaId WHERE favoritos.usuarioId="+usuarioID;
                    System.out.println("connected:" + status + " ");

                    Statement statement = connection.createStatement();

                    ResultSet result = statement.executeQuery(query);
                    while(result.next()){
                        int plantaId = result.getInt("plantaId");
                        String nombre = result.getString("nombre");
                        String nombreAlt = result.getString("nombreAlt");
                        String imagenURL = result.getString("imagenURL");

                        plantasList.add(new Plantas(plantaId, nombre, nombreAlt, imagenURL,true));
                    }
                    FavoritosFragment.cargar(plantasList,context,recyclerView);
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
