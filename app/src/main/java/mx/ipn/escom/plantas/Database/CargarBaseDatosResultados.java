package mx.ipn.escom.plantas.Database;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.plantas.Adapter.Plantas;
import mx.ipn.escom.plantas.ui.resultados.ResultadosActivity;

public class CargarBaseDatosResultados {

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
    private RecyclerView recyclerView;
    private List<Plantas> plantasList = new ArrayList<>();
    private int usuarioID;
    private String frase;

    public CargarBaseDatosResultados(Context context, RecyclerView recyclerView, int usuarioID, String frase) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.context = context;
        this.recyclerView = recyclerView;
        this.usuarioID=usuarioID;
        this.frase = frase.toLowerCase();
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
                    String query = "SELECT * FROM plantas WHERE plantaid IN (SELECT plantaid FROM plantas WHERE  (LOWER(nombre) LIKE '%"+frase+"%')  or (LOWER(nombreAlt) LIKE '%"+frase+"%')  or (LOWER(especie) LIKE '%"+frase+"%') or (LOWER(genero) LIKE '%"+frase+"%') or (LOWER(familia) LIKE '%"+frase+"%') or (LOWER(orden) LIKE '%"+frase+"%') or (LOWER(clase) LIKE '%"+frase+"%') or (LOWER(division) LIKE '%"+frase+"%'))";
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
                    ResultadosActivity.cargar(plantasList,context,recyclerView);
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
