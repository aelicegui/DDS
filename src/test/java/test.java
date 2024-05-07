import entities.comunidad.Usuario;
import entities.verificador.Sanitizador;
import entities.verificador.Verificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class test {
	private Verificador verificador;
	private Usuario usuario;

	@BeforeEach
	public void init() {
		this.verificador = new Verificador();
	}

	@Test
	@DisplayName("Prueba del Sanitizador.")
	public void borrarEspaciosTest() {
		String contrasenia = "hola    amigo";
		contrasenia = Sanitizador.borrarEspacios(contrasenia);
		Assertions.assertEquals("hola amigo", contrasenia);
	}

	@Test
	@DisplayName("Prueba de la Condicion de Longitud.")
	public void contraseniaCortaTest() {

		Assertions.assertEquals(
				false, verificador.validarContrasenia("Juan", "aaaaaaa"));
	}

	@Test
	@DisplayName("Prueba de la Condicion de Credencial.")
	public void contraseniaSimilarAlNombreTest() {

		Assertions.assertEquals(
				false, verificador.validarContrasenia("Juan", "Juan1234"));
	}

	@Test
	@DisplayName("Prueba de la Condicion de Top10000.")
	public void contraseniaEnElTop10000Test() {

		Assertions.assertEquals(
				false, verificador.validarContrasenia("Juan", "jennifer"));
	}

	@Test
	@DisplayName("Prueba de una contrasenia valida.")
	public void contraseniaValidaTest() {

		Assertions.assertEquals(
				true, verificador.validarContrasenia("Juan", "parapente"));
	}

	@Test
	@DisplayName("Prueba de una contrasenia 2.")
	public void contraseniaValida() {

		Assertions.assertEquals(
				false, verificador.validarContrasenia("1", "1234"));
	}
}

