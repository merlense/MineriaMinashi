package BLL;
public class Pedido_cliente {

	private Cliente cliente;

	public Pedido_cliente(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
