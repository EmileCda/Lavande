package fr.emile.test;

import fr.emile.utils.Utils;

public class TPopulate {
	
	

	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");

//		TUserUnitTest tUserUnitTest = new TUserUnitTest();
//		tUserUnitTest.createMany(10);
//
//		TCostumerUnitTest tCostumerUnitTest = new TCostumerUnitTest();
//		tCostumerUnitTest.createMany(10);
//
//		TAddressUnitTest tAddressUnitTest = new TAddressUnitTest();
//		tAddressUnitTest.createMany(31,10);
//
//		TBankCardUnitTest tBankCardUnitTest = new TBankCardUnitTest();
//		tBankCardUnitTest.createMany(31,10);
////		
//		TCategoryUnitTest tCategoryUnitTest = new TCategoryUnitTest();
//		tCategoryUnitTest.createMany(10);
//
//		TItemUnitTest tItemUnitTest = new TItemUnitTest();
//		tItemUnitTest.createMany(10);
//
//		TCommentUnitTest tCommentUnitTest = new TCommentUnitTest();
//		tCommentUnitTest.createMany(31,10);
//		
//		TOrderUnitTest tOrderUnitTest = new TOrderUnitTest();
//		tOrderUnitTest.createMany(31,10);
//
//		TOrderLineUnitTest tOrderLineUnitTest = new TOrderLineUnitTest();
//		tOrderLineUnitTest.createMany(10);
		
		TCartItemUnitTest tCartItemUnitTest = new TCartItemUnitTest();
		tCartItemUnitTest.createMany(31,10);
		
		Utils.trace("*************************** end ************************************\n");


	}

	// -------------------------------------------------------------------------------------------------

}
