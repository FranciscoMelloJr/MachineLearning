package application;

import javafx.fxml.FXML;

public class PrincipalController {
	
	@FXML
	public void extrair() {
		ExtratorCaracteristicas.extrair(false);
	}
}
