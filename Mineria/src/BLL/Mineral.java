package BLL;

public class Mineral {

	private int idMineria;
	private String tipo;
	private  int unidades;
	private double peso;
	private double pureza;
	private double precio;
	private int descuento;
	
	public Mineral(int idMineria, String tipo, int unidades, double peso, double pureza, double precio, int descuento) {
		super();
		this.idMineria = idMineria;
		this.tipo = tipo;
		this.unidades = unidades;
		this.peso = peso;
		this.pureza = pureza;
		this.precio = precio;
		this.descuento = descuento;
	}

	public int getIdMineria() {
		return idMineria;
	}

	public void setIdMineria(int idMineria) {
		this.idMineria = idMineria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPureza() {
		return pureza;
	}

	public void setPureza(double pureza) {
		this.pureza = pureza;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "Mineral [idMineria=" + idMineria + ", tipo=" + tipo + ", unidades=" + unidades + ", peso=" + peso
				+ ", pureza=" + pureza + ", precio=" + precio + ", descuento=" + descuento + "]";
	}
	
	
	
}
