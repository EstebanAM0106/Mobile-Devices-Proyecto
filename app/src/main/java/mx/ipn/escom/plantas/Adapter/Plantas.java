package mx.ipn.escom.plantas.Adapter;

public class Plantas {
    private int id;
    private String nombre;
    private String nombreAlt;
    private String imagenURL;
    private boolean esFavorito;

    public Plantas(int id, String nombre, String nombreAlt, String imagenURL, boolean esFavorito) {
        this.id = id;
        this.nombre = nombre;
        this.nombreAlt = nombreAlt;
        this.imagenURL = imagenURL;
        this.esFavorito = esFavorito;
    }

    public Plantas(int id) {
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

    public boolean getEsFavorito() { return esFavorito; }

    public void setEsFavorito(boolean esFavorito) { this.esFavorito = esFavorito; }

    @Override
    public String toString() {
        return "Plantas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombreAlt='" + nombreAlt + '\'' +
                ", imagenURL='" + imagenURL + '\'' +
                ", esFavorito=" + esFavorito +
                '}';
    }
}
