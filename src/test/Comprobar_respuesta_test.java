package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import mastermind.*;

class Comprobar_respuesta_test {

	@Test
	@DisplayName("Todos los colores y posiciones correctas en indicadores negros correctos")
	void colores_correctos() {
		Combinacion c1,cifrado;
		byte ind_negros,ind_grises;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		ind_negros = 4;
		ind_grises = 0;
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		
		assertEquals(true, c1.calcular_respuesta(cifrado,ind_negros,ind_grises));
	
	}
	
	@Test
	@DisplayName("Algunos colores y posiciones correctas e indicadores grises correctos")
	void algunos_colores_correctos() {
		Combinacion c1,cifrado;
		byte ind_negros,ind_grises;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		ind_negros = 2;
		ind_grises = 2;
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		
		cifrado.cambiar_color_casilla(1, Color.AZUL);
		cifrado.cambiar_color_casilla(2, Color.NEGRO);
		
		assertEquals(true, c1.calcular_respuesta(cifrado,ind_negros,ind_grises));
	}
	
	@Test
	@DisplayName("Ningun color o posicion correcto")
	void ningun_color_correcto() {
		Combinacion c1,cifrado;
		byte ind_negros,ind_grises;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		ind_negros = 0;
		ind_grises = 0;
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, Color.MORADO_CLARO);
		}
		assertEquals(true, c1.calcular_respuesta(cifrado,ind_negros,ind_grises));
	}
	
	@Test
	@DisplayName("Indicadores grises incorrectos")
	void indicadores_grises_incorrectos() {
		Combinacion c1,cifrado;
		byte ind_negros,ind_grises;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		ind_negros = 2;
		ind_grises = 8;
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		
		cifrado.cambiar_color_casilla(1, Color.AZUL);
		cifrado.cambiar_color_casilla(2, Color.NEGRO);
		
		assertEquals(false, c1.calcular_respuesta(cifrado,ind_negros,ind_grises));
	}
	
	@Test
	@DisplayName("Indicadores negros incorrectos")
	void indicadores_negros_incorrectos() {
		Combinacion c1,cifrado;
		byte ind_negros,ind_grises;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		ind_negros = 1;
		ind_grises = 0;
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		
		assertEquals(false, c1.calcular_respuesta(cifrado,ind_negros,ind_grises));
	
	}
	
	@Test
	@DisplayName("Tamaño del cifrado incorrecto")
	void tamaño_incorrecto() {
		Combinacion c1,cifrado;
		byte ind_negros,ind_grises;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		ind_negros = 1;
		ind_grises = 0;
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.MEDIO.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		
		
		assertEquals(false, c1.calcular_respuesta(cifrado,ind_negros,ind_grises));
	}
	
	@Test
	@DisplayName("Algun color a null")
	void color_null() {
		Combinacion c1,cifrado;
		byte ind_negros,ind_grises;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		ind_negros = 1;
		ind_grises = 0;
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.MEDIO.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		cifrado.cambiar_color_casilla(2, null);
		
		assertEquals(false, c1.calcular_respuesta(cifrado,ind_negros,ind_grises));
	}
	
	
	
	
	

}
