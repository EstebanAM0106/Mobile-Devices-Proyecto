package mx.ipn.escom.plantas.Adapter;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import mx.ipn.escom.plantas.Database.Database;
import mx.ipn.escom.plantas.ui.informacion.ModificarPlantaFragment;

public class CargarBaseDatosModificar {

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
    private EditText edtNombre;
    private EditText edtNombreAlt;
    private EditText edtURL;
    private EditText edtReino;
    private EditText edtDivision;
    private EditText edtClase;
    private EditText edtOrden;
    private EditText edtFamilia;
    private EditText edtGenero;
    private EditText edtEspecie;
    private EditText edtAlturaVal;
    private EditText edtAlturaUni;
    private EditText edtDiametroVal;
    private EditText edtDiametroUni;
    private EditText edtCicloRiegoVal;
    private EditText edtCicloRiegoUni;
    private RadioButton rbtMaceta;
    private RadioButton rbtJardin;
    private RadioButton rbtLuz;
    private RadioButton rbtMediaSombra;
    private RadioButton rbtSombra;
    private EditText edtOtrasRecomendaciones;
    private EditText edtDescripcion;

    public CargarBaseDatosModificar(int plantaId, Context context, View view, Resources resource, ImageView imgPlanta, EditText edtNombre, EditText edtNombreAlt, EditText edtURL, EditText edtReino, EditText edtDivision, EditText edtClase, EditText edtOrden, EditText edtFamilia, EditText edtGenero, EditText edtEspecie, EditText edtAlturaVal, EditText edtAlturaUni, EditText edtDiametroVal, EditText edtDiametroUni, EditText edtCicloRiegoVal, EditText edtCicloRiegoUni, RadioButton rbtMaceta, RadioButton rbtJardin, RadioButton rbtLuz, RadioButton rbtMediaSombra, RadioButton rbtSombra, EditText edtOtrasRecomendaciones, EditText edtDescripcion) {
        this.url = String.format(this.url, this.host, this.port, this.database);
        this.plantaId = plantaId;
        this.context = context;
        this.view = view;
        this.resource = resource;
        this.imgPlanta = imgPlanta;
        this.edtNombre = edtNombre;
        this.edtNombreAlt = edtNombreAlt;
        this.edtURL = edtURL;
        this.edtReino = edtReino;
        this.edtDivision = edtDivision;
        this.edtClase = edtClase;
        this.edtOrden = edtOrden;
        this.edtFamilia = edtFamilia;
        this.edtGenero = edtGenero;
        this.edtEspecie = edtEspecie;
        this.edtAlturaVal = edtAlturaVal;
        this.edtAlturaUni = edtAlturaUni;
        this.edtDiametroVal = edtDiametroVal;
        this.edtDiametroUni = edtDiametroUni;
        this.edtCicloRiegoVal = edtCicloRiegoVal;
        this.edtCicloRiegoUni = edtCicloRiegoUni;
        this.rbtMaceta = rbtMaceta;
        this.rbtJardin = rbtJardin;
        this.rbtLuz = rbtLuz;
        this.rbtMediaSombra = rbtMediaSombra;
        this.rbtSombra = rbtSombra;
        this.edtOtrasRecomendaciones = edtOtrasRecomendaciones;
        this.edtDescripcion = edtDescripcion;
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


                    ModificarPlantaFragment.cargar(
                            plantasInformacion,
                            context,
                            view,
                            resource,
                            imgPlanta,
                            edtNombre,
                            edtNombreAlt,
                            edtURL,
                            edtReino,
                            edtDivision,
                            edtClase,
                            edtOrden,
                            edtFamilia,
                            edtGenero,
                            edtEspecie,
                            edtAlturaVal,
                            edtAlturaUni,
                            edtDiametroVal,
                            edtDiametroUni,
                            edtCicloRiegoUni,
                            edtCicloRiegoVal,
                            rbtMaceta,
                            rbtJardin,
                            rbtLuz,
                            rbtMediaSombra,
                            rbtSombra,
                            edtOtrasRecomendaciones,
                            edtDescripcion);
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
