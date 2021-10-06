package application;

import java.io.File;
import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class PrincipalController {
	
//	@FXML
//	public void extrair() {
//		ExtratorCaracteristicas.extrair(true);
//	}
	
	//MÉTODO QUE CHAMA O ALGORITMO

	private double[] caracteristicasImgSel = {0,0,0,0,0,0};
	private DecimalFormat df = new DecimalFormat("##0.0000");

	@FXML
		public void classificar() {
			//*********Naive Bayes
			double[] nb = AprendizagemBayesiana.naiveBayes(caracteristicasImgSel);
			naiveBayesBart.setText("Bart: "+df.format(nb[0])+"%");
			naiveBayesHomer.setText("Homer: "+df.format(nb[1])+"%");
		}
	//gui para carregar imagem na tela (e extrair caracteristicas dela)*********************************
	@FXML
		public void selecionaImagem() {
			File f = buscaImg();
			if(f != null) {
				Image img = new Image(f.toURI().toString());
				imageView.setImage(img);
				imageView.setFitWidth(img.getWidth());
				imageView.setFitHeight(img.getHeight());
				caracteristicasImgSel = ExtractCaracteristicas.extraiCaracteristicas(f, false);
				b1.setText("Laranja Camisa: "+df.format(caracteristicasImgSel[0]));
				b2.setText("Azul Calção: "   +df.format(caracteristicasImgSel[1]));
				b3.setText("Azul Sapato: "   +df.format(caracteristicasImgSel[2]));
				h1.setText("Azul Calça: "    +df.format(caracteristicasImgSel[3]));
				h2.setText("Marrom Boca: "   +df.format(caracteristicasImgSel[4]));
				h3.setText("Preto Sapato: "  +df.format(caracteristicasImgSel[5]));
			}
		}
		
		private File buscaImg() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new 
					   FileChooser.ExtensionFilter(
							   "Imagens", "*.jpg", "*.JPG", 
							   "*.png", "*.PNG", "*.gif", "*.GIF", 
							   "*.bmp", "*.BMP")); 	
			 fileChooser.setInitialDirectory(new File("src/imagens"));
			 File imgSelec = fileChooser.showOpenDialog(null);
			 try {
				 if (imgSelec != null) {
				    return imgSelec;
				 }
			 } catch (Exception e) {
				e.printStackTrace();
			 }
			 return null;
		}
}
