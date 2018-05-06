package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import mastermind.Color;
import mastermind.Combinacion;
import mastermind.Dificultad;

class Calcular_resultado_test {
	
	@Test
	@DisplayName("Todos los colores y posiciones correctas en indicadores negros correctos")
	void colores_correctos() {
		Combinacion c1,cifrado;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}

		assertArrayEquals(new int[] {4,0}, c1.calcular_resultado(cifrado));
	
	}
	
	@Test
	@DisplayName("Algunos colores y posiciones correctas e indicadores grises correctos")
	void algunos_colores_correctos() {
		Combinacion c1,cifrado;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		
		cifrado.cambiar_color_casilla(1, Color.AZUL);
		cifrado.cambiar_color_casilla(2, Color.NEGRO);
		
		assertArrayEquals(new int[] {2,2}, c1.calcular_resultado(cifrado));
	}
	
	@Test
	@DisplayName("Ningun color o posicion correcto")
	void ningun_color_correcto() {
		Combinacion c1,cifrado;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.FACIL.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, Color.MORADO_CLARO);
		}
		assertArrayEquals(new int[] {0,0}, c1.calcular_resultado(cifrado));
	}
	
	@Test
	@DisplayName("Tamaño del cifrado incorrecto")
	void tamaño_incorrecto() {
		Combinacion c1,cifrado;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(2);
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);	
		}
		
		for (int i = 0; i < cifrado.tamanio(); i++) {
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		
		Assertions.assertThrows(Exception.class, () -> {
			c1.calcular_resultado(cifrado);
		});
	}
	
	@Test
	@DisplayName("Algun color a null")
	void color_null() {
		Combinacion c1,cifrado;
		Color colores[] = {Color.ROJO,Color.NEGRO,Color.AZUL,Color.VERDE};
		
		c1 = new Combinacion(Dificultad.FACIL.getCasillas());
		cifrado = new Combinacion(Dificultad.MEDIO.getCasillas());
		
		for (int i = 0; i < colores.length; i++) {
			c1.cambiar_color_casilla(i, colores[i]);
			cifrado.cambiar_color_casilla(i, colores[i]);
		}
		cifrado.cambiar_color_casilla(2, null);
		
		assertArrayEquals(new int[] {3,0}, c1.calcular_resultado(cifrado));
	}
	
	
	
}
