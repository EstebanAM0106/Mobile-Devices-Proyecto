package mx.ipn.escom.plantas.Adapter;

public class PlantasInformacion {
    private int id;
    private String nombre;
    private String nombreAlt;
    private String imagenURL;
    private String reino;
    private String division;
    private String clase;
    private String orden;
    private String familia;
    private String genero;
    private String especie;
    private int altura;
    private String alturaUnidad;
    private int diametro;
    private String diametroUnidad;
    private int cicloRiego;
    private String cicloRiegoUnidad;
    private boolean maceta;
    private int luminosidad;
    private String otrasRecomendaciones;
    private String descripcion;
    private int ultimoUsuario;

    public PlantasInformacion(int id, String nombre, String nombreAlt, String imagenURL, String reino, String division, String clase, String orden, String familia, String genero, String especie, int altura, String alturaUnidad, int diametro, String diametroUnidad, int cicloRiego, String cicloRiegoUnidad, boolean maceta, int luminosidad, String otrasRecomendaciones, String descripcion, int ultimoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.nombreAlt = nombreAlt;
        this.imagenURL = imagenURL;
        this.reino = reino;
        this.division = division;
        this.clase = clase;
        this.orden = orden;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.altura = altura;
        this.alturaUnidad = alturaUnidad;
        this.diametro = diametro;
        this.diametroUnidad = diametroUnidad;
        this.cicloRiego = cicloRiego;
        this.cicloRiegoUnidad = cicloRiegoUnidad;
        this.maceta = maceta;
        this.luminosidad = luminosidad;
        this.otrasRecomendaciones = otrasRecomendaciones;
        this.descripcion = descripcion;
        this.ultimoUsuario = ultimoUsuario;
    }

    public PlantasInformacion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreAlt() {
        return nombreAlt;
    }

    public void setNombreAlt(String nombreAlt) {
        this.nombreAlt = nombreAlt;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public String getReino() {
        return reino;
    }

    public void setReino(String reino) {
        this.reino = reino;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getAlturaUnidad() {
        return alturaUnidad;
    }

    public void setAlturaUnidad(String alturaUnidad) {
        this.alturaUnidad = alturaUnidad;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public String getDiametroUnidad() {
        return diametroUnidad;
    }

    public void setDiametroUnidad(String diametroUnidad) {
        this.diametroUnidad = diametroUnidad;
    }

    public int getCicloRiego() {
        return cicloRiego;
    }

    public void setCicloRiego(int cicloRiego) {
        this.cicloRiego = cicloRiego;
    }

    public String getCicloRiegoUnidad() {
        return cicloRiegoUnidad;
    }

    public void setCicloRiegoUnidad(String cicloRiegoUnidad) {
        this.cicloRiegoUnidad = cicloRiegoUnidad;
    }

    public boolean getEsMaceta() {
        return maceta;
    }

    public void setEsMaceta(boolean maceta) {
        this.maceta = maceta;
    }

    public int getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(int luminosidad) {
        this.luminosidad = luminosidad;
    }

    public String getOtrasRecomendaciones() {
        return otrasRecomendaciones;
    }

    public void setOtrasRecomendaciones(String otrasRecomendaciones) {
        this.otrasRecomendaciones = otrasRecomendaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(int ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }
    @Override
    public String toString() {
        return "PlantasInformacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombreAlt='" + nombreAlt + '\'' +
                ", imagenURL='" + imagenURL + '\'' +
                ", reino='" + reino + '\'' +
                ", division='" + division + '\'' +
                ", clase='" + clase + '\'' +
                ", orden='" + orden + '\'' +
                ", familia='" + familia + '\'' +
                ", genero='" + genero + '\'' +
                ", especie='" + especie + '\'' +
                ", altura=" + altura +
                ", alturaUnidad='" + alturaUnidad + '\'' +
                ", diametro=" + diametro +
                ", diametroUnidad='" + diametroUnidad + '\'' +
                ", cicloRiego=" + cicloRiego +
                ", cicloRiegoUnidad='" + cicloRiegoUnidad + '\'' +
                ", maceta=" + maceta +
                ", luminosidad=" + luminosidad +
                ", otrasRecomendaciones='" + otrasRecomendaciones + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ultimoUsuario=" + ultimoUsuario +
                '}';
    }
}
