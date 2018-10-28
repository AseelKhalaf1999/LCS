package application;

public class LCS {
	static int NumOfLightingPWS = 0;
//method to find the leds that will light
	public static int LCSS(int powerSourcesNum, int[] LEDs) {
		powerSourcesNum = powerSourcesNum + 1;
		int[][] lcs = new int[powerSourcesNum][powerSourcesNum];
		int[][] Path = new int[powerSourcesNum][powerSourcesNum];
		for (int i = 0; i < powerSourcesNum; i++) {
			lcs[0][i] = 0;
			lcs[i][0] = 0;

			for (i = 1; i < powerSourcesNum; i++) {
				for (int j = 1; j < powerSourcesNum; j++) {
					//to check if the power source equal the leds num
					if (i == LEDs[j - 1]) {
						lcs[i][j] = lcs[i - 1][j - 1] + 1;
						Path[i][j] = 1;

					} else {
						if (lcs[i - 1][j] > lcs[i][j - 1]) {
							lcs[i][j] = lcs[i - 1][j];
							Path[i][j] = 2;

						}

						else {
							lcs[i][j] = lcs[i][j - 1];
							Path[i][j] = 3;

						}
					}

				}
			}
		}
		System.out.println("LCS Array : ");
		for (int i = 0; i < lcs.length; i++) {
			for (int j = 0; j < lcs.length; j++) {
				System.out.print(lcs[j][i] + " ");

			}
			System.out.println("\n");
		}
		System.out.println("The LED'S that will Light : ");
		int t = Print(Path, LEDs, powerSourcesNum - 1, powerSourcesNum - 1);

		return t;

	}
//method returns the number of lighting powerSources
	public static int Print(int[][] path, int[] LEDs, int numOfPWS, int numOfLeds) {

		if (numOfPWS == 0 || numOfLeds == 0)

			return 0;

		else if (path[numOfPWS][numOfLeds] == 1) {
			NumOfLightingPWS++;

			Print(path, LEDs, numOfPWS - 1, numOfLeds - 1);
			String s = LEDs[numOfPWS - 1] + " ";
			StringBuilder ss = new StringBuilder();
			ss.append(s);

			System.out.println(ss);

		}

		else {
			if (path[numOfPWS][numOfLeds] == 2)

				Print(path, LEDs, numOfPWS - 1, numOfLeds);

			else
				Print(path, LEDs, numOfPWS, numOfLeds - 1);

		}

		return NumOfLightingPWS;
	}
//method to give a random number in range of the  pws num
	public static int[] getRandomNumber(int powerSources) {
		int array[] = new int[powerSources];

		for (int i = 0; i < powerSources; i++) {
			array[i] = (int) (Math.random() * powerSources);
		}
		return array;
	}

}
