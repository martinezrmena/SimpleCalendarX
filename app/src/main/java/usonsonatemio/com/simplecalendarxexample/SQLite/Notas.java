package usonsonatemio.com.simplecalendarxexample.SQLite;

public class Notas {

    private String id;
    private String fechanota;
    private String nota;

    public Notas(String id, String fechanota, String nota) {
        this.id = id;
        this.fechanota = fechanota;
        this.nota = nota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechanota() {
        return fechanota;
    }

    public void setFechanota(String fechanota) {
        this.fechanota = fechanota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
