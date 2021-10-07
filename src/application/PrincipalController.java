package application;

import java.io.File;
import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PrincipalController {
	
	@FXML private ImageView imageView;
	@FXML private Label b1;
	@FXML private Label b2;
	@FXML private Label b3;
	@FXML private Label h1;
	@FXML private Label h2;
	@FXML private Label h3;
	@FXML private Label naiveBayesBart;
	@FXML private Label naiveBayesHomer;
	@FXML private Label j48Bart;
	@FXML private Label j48Homer;

	private DecimalFormat df = new DecimalFormat("##0.0000");
	private double[] caracteristicasImgSel = {0,0,0,0,0,0};

	@FXML
		public void classificar() {
			//*********Naive Bayes
			double[] nb = AprendizagemBayesiana.naiveBayes(caracteristicasImgSel);
			naiveBayesBart.setText("Bart: "+df.format(nb[0])+"%");
			naiveBayesHomer.setText("Homer: "+df.format(nb[1])+"%");
			//*********J48
//			double[] j48 = ArvoresDeDecisao.j48(caracteristicasImgSel);
//			j48Bart.setText("Bart: "+df.format(j48)+"%");
//			j48Homer.setText("Homer: "+df.format(j48[1])+"%");
		}
	
	@FXML
	public void extrair() {
		ExtratorCaracteristicas.extrair(false);
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
				caracteristicasImgSel = ExtratorCaracteristicas.extraiCaracteristicas(f, false);
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
