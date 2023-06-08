package fr.ecommerce.test;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.ArticlePanier;
import fr.ecommerce.entity.User;
import fr.ecommerce.utils.Utils;

public class Facturation {

	public static void main(String[] args) {
		int userId = 2 ; 
		User user = getUser(userId);
		
		
		for (ArticlePanier articlePanier  : user.getCartItemList()) {
//			prix unirtaire , quantité = totalbrut, discountarticle, discout catégory , ttola ligne 
//			Utils.trace("%s\n", articlePanier);
			articlePanier.getArticle().getCategorie().getName();
			articlePanier.getArticle().getCategorie().getDiscount();
			articlePanier.getArticle().getCategorie().isDiscountCumulative();
			
			int quantite =articlePanier.getQuantity(); 
			float prixBrut=articlePanier.getArticle().getPrice()  * quantite ;
			float discontArtcicle=articlePanier.getArticle().getDiscount();
			float reductionArticle=quantite*prixBrut *discontArtcicle/100 ;
			float reductionCategory = 0 ;
			if (articlePanier.getArticle().getCategorie().isDiscountCumulative()) {
				float discountCategory = articlePanier.getArticle().getCategorie().getDiscount();	
				reductionCategory= (prixBrut - reductionArticle) * discountCategory/100 ;
			}
			
			float prixAPayer =prixBrut-reductionArticle-reductionCategory; 
			
			Utils.trace("%d x %s PB: %.2f, R1: %.2f R2: %.2f  TP %.2f \n",
					quantite,	
					articlePanier.getArticle().getCategorie().getName(),
					prixBrut,
			reductionArticle,reductionCategory,	prixAPayer);
		}
			
//		1) trouver le user : userid = 1
//		2) vider le panier
//		3) calculer la facture 

	}

//------------------------------------------------------------------------------------------------	
	public static User getUser(int userId) {
		Utils.trace("=========================== read One  ===========================\n");

		User user = new User();
		IUserCtrl userCtrl = new UserCtrl();
		try {
			user = userCtrl.getUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}
}
