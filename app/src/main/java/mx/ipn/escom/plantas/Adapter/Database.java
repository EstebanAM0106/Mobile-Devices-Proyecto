package mx.ipn.escom.plantas.Adapter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection connection;

    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"  // For Amazon Postgresql
    private final String host = "192.168.0.106";  // For Google Cloud Postgresql
    private final String database = "plantas";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "admin123";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;
    private String query;
    private int a;

    public Database(String query) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.query=query;
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
                    System.out.println("connected:" + status + " ");

                    Statement statement = connection.createStatement();

                    ResultSet result = statement.executeQuery(query);
                    result.next();
                    System.out.println("SQL says:" + result.getInt("plantaId") +" ");
                    a=result.getInt("plantaId");
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
    public int a(){
        return a;
    }

    public List<PlantasInformacion> getPlantasInfDB() throws SQLException {
        List<PlantasInformacion> plantasInformacionList = new ArrayList<>();
        //List<Plantas> plantasList= new ArrayList<>();

        String query = "SELECT * FROM plantas";

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(query);

        while(result.next()){
            int plantaId = result.getInt("plantaId");
            String nombre = result.getString("nombre");
            String nombreAlt = result.getString("nombreAlt");
            String imagenURL = result.getString("imagenURL");
            String reino = result.getString("reino");
            String division = result.getString("division");
            String clase = result.getString("clase");
            String orden = result.getString("orden");
            String familia = result.getString("familia");
            String genero = result.getString("genero");
            String especie = result.getString("especie");
            int altura = result.getInt("altura");
            String alturaUnidad = result.getString("alturaUnidad");
            int diametro = result.getInt("diametro");
            String diametroUnidad = result.getString("diametroUnidad");
            int ciclo = result.getInt("ciclo");
            String cicloUnidad = result.getString("cicloUnidad");
            boolean maceta = result.getBoolean("maceta");
            int luminosidad = result.getInt("luminosidad");
            String otrasRecomendaciones = result.getString("otrasRecomendaciones");
            String descripcion = result.getString("descripcion");
            int ultimoUsuario = result.getInt("ultimoUsuario");

            plantasInformacionList.add(new PlantasInformacion(plantaId, nombre, nombreAlt, imagenURL, reino, division, clase, orden, familia, genero, especie, altura, alturaUnidad, diametro, diametroUnidad, ciclo, cicloUnidad, maceta, luminosidad, otrasRecomendaciones, descripcion, ultimoUsuario));
        }


        return plantasInformacionList;
    }

    public Connection getExtraConnection(){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }
}
