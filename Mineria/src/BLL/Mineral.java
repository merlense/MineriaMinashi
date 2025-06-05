package BLL;

public class Mineral {

	private String nombre;
	private int cantidad;
	private double precio;
	private  int pureza;
	
	public Mineral(String nombre, int cantidad, double precio, int pureza) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.pureza = pureza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getPureza() {
		return pureza;
	}

	public void setPureza(int pureza) {
		this.pureza = pureza;
	}              
	
	
	
}
