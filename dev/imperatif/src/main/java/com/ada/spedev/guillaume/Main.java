package com.ada.spedev.guillaume;

public class Main {


	public static int[] mettreAZeroPremierElement(int[] t) { 
		int[] copy = new int[t.length];

		for(int i = 0; i < t.length; i++){
			copy[i] = t[i];
		}
		
		copy[0] = 0;
		return copy;
	}


/**
 * 

	int b = 3;

	int a = b;

	int[] c = {9,9};

	int[] d = c;

	String s = "Bonjour";

	String s2 = s;

	d[0] = 10

	c[0]

		....
		xZ14 (tab) [xZ16]
		xZ15 (tab2) [xI50]
		xZ31 (s) [xZ18]

		xH40 (copy) [xI50]

		xI50 [0]
		xI51 [9]


		xZ16 [9]
		xZ17 [9]

		xB12 (b) [xZ18]
		xB13 (a) [xG18]

		xZ18 ['B']
		xZ19 ['o']
		xZ20 ['n']
		xZ21 ['j']
		xZ28 ['o']
		xZ28 ['u']
		xZ28 ['r']

		xG18 [_]
		xG18 ['B']
		xG19 ['o']
		xG20 ['n']
		xG21 ['j']
		xG28 ['o']
		xG28 ['u']
		xG28 ['r']
		xG28 []
 */

	public static void main(String[] args) {
		int[] tab = {9, 9};

		int[] tab2 = mettreAZeroPremierElement(tab);   // => xI50

		System.out.println(tab[0] + "," + tab2[0]);

		String a = "bonjour";

		String b = a;
	}
}