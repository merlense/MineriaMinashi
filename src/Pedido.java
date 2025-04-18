import java.time.LocalDateTime;
import java.util.LinkedList;

public class Pedido {

	private int id_pedido;
	private String tipo;
	private LocalDateTime fecha;
	private String detalle;
    private LinkedList<String> estado = new LinkedList<>();
    
    
	public Pedido(int id_pedido, String tipo, LocalDateTime fecha, String detalle) {
		super();
		this.id_pedido = id_pedido;
		this.tipo = tipo;
		this.fecha = fecha;
		this.detalle = detalle;
	}
	
	public int getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public LinkedList<String> getEstado() {
		return estado;
	}
	public void setEstado(LinkedList<String> estado) {
		this.estado = estado;
	}
    
    
	
}
