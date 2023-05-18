package fr.emile.text;

import fr.emile.entity.User;
import fr.emile.utils.DataTest;

public class TUser {
	public static void main(String[] args) {

		User myUser = DataTest.genUser();

		System.out.println(myUser);

	}
}
