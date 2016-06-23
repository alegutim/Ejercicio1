package mx.com.alegutim.ejercicio1.model;

/**
 * Created by Administrator on 22/06/2016.
 */
public class Usuario {
    public int id;
    public String usuario;
    public String password;
    public String ultConexion;

    public Usuario(String usuario, String password, String ultConexion) {
        //this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.ultConexion = ultConexion;
    }

    public Usuario(int id, String usuario, String password, String ultConexion) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.ultConexion = ultConexion;
    }
}
