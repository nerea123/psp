
public class Categoria {

	private String categoria;
	private int id;
	
	public Categoria(String cat, int id){
		
		this.categoria=cat;
		this.id=id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
