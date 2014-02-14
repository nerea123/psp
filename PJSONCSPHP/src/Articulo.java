
public class Articulo {
	
	private int id;
	private float precio;
	private String nombre;
	
	public Articulo(String nombre, float precio, int id){
		this.id=id;
		this.nombre=nombre;
		this.precio=precio;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
