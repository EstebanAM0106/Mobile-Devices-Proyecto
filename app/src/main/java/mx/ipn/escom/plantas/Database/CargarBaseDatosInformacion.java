package mx.ipn.escom.plantas.Database;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import mx.ipn.escom.plantas.Adapter.PlantasInformacion;
import mx.ipn.escom.plantas.ui.informacion.InformacionPlantaFragment;

public class CargarBaseDatosInformacion {

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

    private PlantasInformacion plantasInformacion;
    private int plantaId;
    private Context context;
    private View view;
    private Resources resource;
    private ImageView imgPlanta;
    private TextView txtNombre;
    private TextView txtReino;
    private TextView txtDivision;
    private TextView txtClase;
    private TextView txtOrden;
    private TextView txtFamilia;
    private TextView txtGenero;
    private TextView txtEspecie;
    private TextView txtAltura;
    private TextView txtDiametro;
    private TextView txtCicloRiego;
    private ImageView imgDondePlantar;
    private ImageView imgLuminosidad;
    private TextView txtOtrasRecomendaciones;
    private TextView txtDescripcion;
    private TextView txtUltimoCambio;

    public CargarBaseDatosInformacion(int plantaId, Context context, View view,Resources resource, ImageView imgPlanta, TextView txtNombre, TextView txtReino, TextView txtDivision, TextView txtClase, TextView txtOrden, TextView txtFamilia, TextView txtGenero, TextView txtEspecie, TextView txtAltura, TextView txtDiametro, TextView txtCicloRiego, ImageView imgDondePlantar, ImageView imgLuminosidad, TextView txtOtrasRecomendaciones, TextView txtDescripcion, TextView txtUltimoCambio) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.plantaId = plantaId;
        this.context = context;
        this.view = view;
        this.resource = resource;
        this.imgPlanta = imgPlanta;
        this.txtNombre = txtNombre;
        this.txtReino = txtReino;
        this.txtDivision = txtDivision;
        this.txtClase = txtClase;
        this.txtOrden = txtOrden;
        this.txtFamilia = txtFamilia;
        this.txtGenero = txtGenero;
        this.txtEspecie = txtEspecie;
        this.txtAltura = txtAltura;
        this.txtDiametro = txtDiametro;
        this.txtCicloRiego = txtCicloRiego;
        this.imgDondePlantar = imgDondePlantar;
        this.imgLuminosidad = imgLuminosidad;
        this.txtOtrasRecomendaciones = txtOtrasRecomendaciones;
        this.txtDescripcion = txtDescripcion;
        this.txtUltimoCambio = txtUltimoCambio;
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
                    String query = "SELECT * FROM plantas WHERE plantaid = "+plantaId;
                    System.out.println("connected:" + status + " ");
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query);
                    result.next();
                    //int plantaId = result.getInt("plantaId");
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
                    plantasInformacion = new PlantasInformacion(
                            plantaId,
                            nombre,
                            nombreAlt,
                            imagenURL,
                            reino,
                            division,
                            clase,
                            orden,
                            familia,
                            genero,
                            especie,
                            altura,
                            alturaUnidad,
                            diametro,
                            diametroUnidad,
                            ciclo,
                            cicloUnidad,
                            maceta,
                            luminosidad,
                            otrasRecomendaciones,
                            descripcion,
                            ultimoUsuario);
                    String query2 = "SELECT nombre FROM usuarios WHERE usuarioId = "+ultimoUsuario;
                    Statement statement2 = connection.createStatement();
                    ResultSet result2 = statement2.executeQuery(query2);
                    result2.next();
                    String ultimoUsuarioNombre = result2.getString("nombre");

                    InformacionPlantaFragment.cargar(
                            plantasInformacion,
                            context,
                            view,
                            resource,
                            imgPlanta,
                            txtNombre,
                            txtReino,
                            txtDivision,
                            txtClase,
                            txtOrden,
                            txtFamilia,
                            txtGenero,
                            txtEspecie,
                            txtAltura,
                            txtDiametro,
                            txtCicloRiego,
                            imgDondePlantar,
                            imgLuminosidad,
                            txtOtrasRecomendaciones,
                            txtDescripcion,
                            txtUltimoCambio,
                            ultimoUsuarioNombre);
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
