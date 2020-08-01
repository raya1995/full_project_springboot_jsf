package tn.esprit.spring.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Factures;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.repository.FactureRepository;


@Service
public class FacturesService implements IFacturesService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	CommandeRepository commandeRepository;

	@Override
	public long ajouterFacture(Factures facture) {
		factureRepository.save(facture);
		return facture.getId();
	}

	@Override
	public void supprimerFacture(long idFacture) {
		factureRepository.deleteById(idFacture);
	}

	@Override
	public void affecterCommandeAFacture(long idFacture, long idCommande) {
		Commande c = commandeRepository.getOne(idCommande);
		Factures f = factureRepository.findById(idFacture).get();
		
		
		// factureRepository.affecterCommande_A_Facture(id_commande,
		// id_facture);
		f.setMontant(c.getMontant());
		f.setCommande(c);
		f.setType("EN_LINE");
		factureRepository.save(f);

	}

	@Override
	public List<Factures> getAllfactures() {
		return (List<Factures>) factureRepository.findAll();
	}

	@Override
	public Optional<Factures> getfacturesById(long idFacture) {
		return factureRepository.findById(idFacture);
	}

	@Override
	public List<Factures> getAllfacturesByCommande(long cmd) {
		return factureRepository.getAllfacturesByCommande(cmd);
	}

	@Override
	public List<Factures> getAllfacturesByPayementType(String payementType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factures> getAllfacturesByPayementstate(String payementState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factures> getfacturesByDatedepart(Date dateDep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifier_type_facture(String type, long idFacture) {
		// TODO Auto-generated method stub

	}

	@Override
	public String get_payment_type_by_factureID(long idFacture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void facturepdf(long idFacture,long idClient) {
		try {
			Factures f = factureRepository.findById(idFacture).get();
			System.out.println("aa"+f);
		//	long id=f.getCommande().getIdUser().getId();
			//System.out.println("iddd"+id);
			//Commande cmd = commandeRepository.findById(f.getCommande().getId()).get();
			List<List<String>> commandes = commandeRepository.commandeParIdclient(idClient);
			System.out.println("ttttttttttttt" + commandes);
//			String file_name = "C:\Users\youss\Desktop\facturePdf" + 1 + ".pdf";
			String file_name=null;
			file_name="C:\\Users\\youss\\Desktop\\facturePdf\\FACTURE_" + 1 + ".pdf";
			Document document = new Document(PageSize.A4, 15, 15, 45, 30);

			PdfWriter.getInstance(document, new FileOutputStream(file_name));

			document.open();
			////////////////
			Image img = Image.getInstance("C:\\Users\\youss\\Desktop\\facturePdf\\Logo.jpg");
			img.setAlignment(Element.ALIGN_RIGHT);
			img.setIndentationLeft(10);
			img.setIndentationRight(10);
			img.setSpacingAfter(10);
			document.add(img);
			document.add(new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------------"));
			////////////////////////////
			Font font = FontFactory.getFont("Cooper Black", 15, BaseColor.BLUE);
			Date aujourdhui = f.getDate_de_depart();
			document.add(new Paragraph("Destinataire :   "));
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("dd-MM-yy");
			Paragraph nomClient = new Paragraph(
					" " + f.getCommande().getIdUser().getFirstName() + " " + f.getCommande().getIdUser().getLastname(),
					font);
			document.add(nomClient);
			Paragraph adresse = new Paragraph("Adresse : " + f.getCommande().getIdUser().getEmail()
					+ "                                                                                              Date de facturation : "
					+ formater.format(aujourdhui));
			document.add(adresse);
			document.add(new Paragraph(
					"Code Postal : 8080                                                                                                  Echéance : "
							+ formater.format(aujourdhui)));
			Font mainFont = FontFactory.getFont("Cooper Black", 35, BaseColor.BLACK);
			Paragraph para = new Paragraph("FACTURE N° " + f.getId(), mainFont);
			para.setAlignment(Element.ALIGN_CENTER);
			para.setIndentationLeft(10);
			para.setIndentationRight(10);
			para.setSpacingAfter(10);
			document.add(para);
			document.add(new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------------"));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			///////////////////
			PdfPTable table = new PdfPTable(4);
			PdfPTable table2 = new PdfPTable(3);
			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			PdfPCell name = new PdfPCell(new Paragraph("Quantite", tableHeader));
			name.setBorderColor(BaseColor.BLACK);
			name.setPaddingLeft(10);
			name.setHorizontalAlignment(Element.ALIGN_CENTER);
			name.setVerticalAlignment(Element.ALIGN_CENTER);
			name.setBackgroundColor(BaseColor.LIGHT_GRAY);
			name.setExtraParagraphSpace(5f);
			table.addCell(name);

			PdfPCell nameProduit = new PdfPCell(new Paragraph("Furniture", tableHeader));
			nameProduit.setBorderColor(BaseColor.BLACK);
			nameProduit.setPaddingLeft(10);
			nameProduit.setHorizontalAlignment(Element.ALIGN_CENTER);
			nameProduit.setVerticalAlignment(Element.ALIGN_CENTER);
			nameProduit.setBackgroundColor(BaseColor.LIGHT_GRAY);
			nameProduit.setExtraParagraphSpace(5f);
			table.addCell(nameProduit);

			PdfPCell Prix = new PdfPCell(new Paragraph("Prix", tableHeader));
			Prix.setBorderColor(BaseColor.BLACK);
			Prix.setPaddingLeft(10);
			Prix.setHorizontalAlignment(Element.ALIGN_CENTER);
			Prix.setVerticalAlignment(Element.ALIGN_CENTER);
			Prix.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Prix.setExtraParagraphSpace(5f);
			table.addCell(Prix);

			PdfPCell Totale = new PdfPCell(new Paragraph("Totale", tableHeader));
			Totale.setBorderColor(BaseColor.BLACK);
			Totale.setPaddingLeft(10);
			Totale.setHorizontalAlignment(Element.ALIGN_CENTER);
			Totale.setVerticalAlignment(Element.ALIGN_CENTER);
			Totale.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Totale.setExtraParagraphSpace(5f);
			table.addCell(Totale);
			/////////////////////////////
			/////////////////////////////
			/////////////////////////////
			////////////////////////////
			for (List<String> c : commandes) {
				PdfPCell quantiteval = new PdfPCell(new Paragraph(String.valueOf(c.get(2)), tableHeader));
				quantiteval.setBorderColor(BaseColor.BLACK);
				quantiteval.setPaddingLeft(10);
				quantiteval.setHorizontalAlignment(Element.ALIGN_CENTER);
				quantiteval.setVerticalAlignment(Element.ALIGN_CENTER);
				quantiteval.setBackgroundColor(BaseColor.WHITE);
				quantiteval.setExtraParagraphSpace(5f);
				table.addCell(quantiteval);

				PdfPCell produiteval = new PdfPCell(new Paragraph(c.get(1), tableHeader));
				produiteval.setBorderColor(BaseColor.BLACK);
				produiteval.setPaddingLeft(10);
				produiteval.setHorizontalAlignment(Element.ALIGN_CENTER);
				produiteval.setVerticalAlignment(Element.ALIGN_CENTER);
				produiteval.setBackgroundColor(BaseColor.WHITE);
				produiteval.setExtraParagraphSpace(5f);
				table.addCell(produiteval);

				PdfPCell prixval = new PdfPCell(new Paragraph(String.valueOf(c.get(3)), tableHeader));
				prixval.setBorderColor(BaseColor.BLACK);
				prixval.setPaddingLeft(10);
				prixval.setHorizontalAlignment(Element.ALIGN_CENTER);
				prixval.setVerticalAlignment(Element.ALIGN_CENTER);
				prixval.setBackgroundColor(BaseColor.WHITE);
				prixval.setExtraParagraphSpace(5f);
				table.addCell(prixval);

				PdfPCell Totaleval = new PdfPCell(new Paragraph(String.valueOf(c.get(4)), tableHeader));
				Totaleval.setBorderColor(BaseColor.BLACK);
				Totaleval.setPaddingLeft(10);
				Totaleval.setHorizontalAlignment(Element.ALIGN_CENTER);
				Totaleval.setVerticalAlignment(Element.ALIGN_CENTER);
				Totaleval.setBackgroundColor(BaseColor.WHITE);
				Totaleval.setExtraParagraphSpace(5f);
				table.addCell(Totaleval);
			}

			/////////////////////////////////
			PdfPCell PrixTotale = new PdfPCell(new Paragraph("Prix_Totale", tableHeader));
			PrixTotale.setBorderColor(BaseColor.BLACK);
			PrixTotale.setPaddingLeft(10);
			PrixTotale.setHorizontalAlignment(Element.ALIGN_CENTER);
			PrixTotale.setVerticalAlignment(Element.ALIGN_CENTER);
			PrixTotale.setBackgroundColor(BaseColor.LIGHT_GRAY);
			PrixTotale.setExtraParagraphSpace(5f);
			table2.addCell(PrixTotale);

			PdfPCell Pourcentage = new PdfPCell(new Paragraph("Reduction", tableHeader));
			Pourcentage.setBorderColor(BaseColor.BLACK);
			Pourcentage.setPaddingLeft(10);
			Pourcentage.setHorizontalAlignment(Element.ALIGN_CENTER);
			Pourcentage.setVerticalAlignment(Element.ALIGN_CENTER);
			Pourcentage.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Pourcentage.setExtraParagraphSpace(5f);
			table2.addCell(Pourcentage);

			PdfPCell Prixfinal = new PdfPCell(new Paragraph("Prix_finale", tableHeader));
			Prixfinal.setBorderColor(BaseColor.BLACK);
			Prixfinal.setPaddingLeft(10);
			Prixfinal.setHorizontalAlignment(Element.ALIGN_CENTER);
			Prixfinal.setVerticalAlignment(Element.ALIGN_CENTER);
			Prixfinal.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Prixfinal.setExtraParagraphSpace(5f);
			table2.addCell(Prixfinal);

			PdfPCell prix_totalval = new PdfPCell(
					new Paragraph(String.valueOf(f.getCommande().getTotal()), tableHeader));
			prix_totalval.setBorderColor(BaseColor.BLACK);
			prix_totalval.setPaddingLeft(10);
			prix_totalval.setHorizontalAlignment(Element.ALIGN_CENTER);
			prix_totalval.setVerticalAlignment(Element.ALIGN_CENTER);
			prix_totalval.setBackgroundColor(BaseColor.WHITE);
			prix_totalval.setExtraParagraphSpace(5f);
			table2.addCell(prix_totalval);

			PdfPCell Pourcentageval = new PdfPCell(
					new Paragraph(String.valueOf(f.getCommande().getPourcentageDeRemise()), tableHeader));
			Pourcentageval.setBorderColor(BaseColor.BLACK);
			Pourcentageval.setPaddingLeft(10);
			Pourcentageval.setHorizontalAlignment(Element.ALIGN_CENTER);
			Pourcentageval.setVerticalAlignment(Element.ALIGN_CENTER);
			Pourcentageval.setBackgroundColor(BaseColor.WHITE);
			Pourcentageval.setExtraParagraphSpace(5f);
			table2.addCell(Pourcentageval);

			PdfPCell prix_finalval = new PdfPCell(
					new Paragraph(String.valueOf(f.getCommande().getMontant()), tableHeader));
			prix_finalval.setBorderColor(BaseColor.BLACK);
			prix_finalval.setPaddingLeft(10);
			prix_finalval.setHorizontalAlignment(Element.ALIGN_CENTER);
			prix_finalval.setVerticalAlignment(Element.ALIGN_CENTER);
			prix_finalval.setBackgroundColor(BaseColor.WHITE);
			prix_finalval.setExtraParagraphSpace(5f);
			table2.addCell(prix_finalval);

			document.add(table);

			document.add(new Paragraph("  "));

			document.add(table2);
			document.add(new Paragraph("  "));
			document.add(new Paragraph("  "));

			document.add(new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------------"));
			document.add(new Paragraph("  "));
			document.add(new Paragraph("  "));

			document.add(new Paragraph("Telephone  :(+216) 72 755 885   "
					+ "                                                             Adresse : Ariana Soghra /Tunis "));
			document.add(new Paragraph("Fax          :(+216) 72 063 906   "
					+ "                                                                                      Code Postal :4100  "));
			document.add(new Paragraph("Email       :DariTn@esprit.tn  "));

			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
