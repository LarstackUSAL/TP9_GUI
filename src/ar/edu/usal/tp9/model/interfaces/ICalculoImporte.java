package ar.edu.usal.tp9.model.interfaces;

public interface ICalculoImporte {

	public static final double PORCENTAJE_SEGURO = 0.3;
	public static final double PORCENTAJE_ABONO_TRANSPORTE = 0.1;
	public static final double PORCENTAJE_GUIA = 0.3;
	public static final double PORCENTAJE_PENSION_COMPLETA = 0.35;
	
	public double calcularImporte();
}
