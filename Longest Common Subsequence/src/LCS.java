/*
 * http://www.csie.ntnu.edu.tw/~u91029/LongestCommonSubsequence.html
 * 
 * LIS �� LCS�G�O��ǦC A �Ƨǫ�|�ܦ� B�C A �M B �� LCS�A�N�O A �� LIS �C
 * 
 * LCS �� LIS�G�N�ǦC A �M B �����ۦP�r���t�ﳣ��X�ӡA�e�{�����ޭȼƹ�A�A�H�S��W�h�ƧǡA�̫�� LIS�A
 * �N�O A �M B �� LCS�C
 */

public class LCS {
	void LCS_length (char[] s1, char[] s2) {
		int[][] dp_array = new int[s1.length + 1][s2.length + 1];
		String[][] prev = new String[s1.length + 1][s2.length + 1]; // �O���C�@�檺�����G�O�q���@��Ө�
		
		// ��l�ơG�� s1 �� s2 �O�Ŷ��X�A�h LCS �O�Ŷ��X�C
	    // array[x][0] = array[0][x] = 0;
		for (int i = 0; i <= s1.length; i++)
			dp_array[i][0] = 0;
		for (int j = 0; j <= s2.length; j++)
			dp_array[0][j] = 0;
		
		// ����G�̷ӻ��j����
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				if (s1[i] == s2[j]) {
					// +1�O�]��e1�����׬�1
					dp_array[i + 1][j + 1] = dp_array[(i + 1) - 1][(j + 1) - 1] + 1;
					prev[i + 1][j + 1] = "���W��";
				} else {
					// dp_array[i + 1][j + 1] = Math.max(dp_array[(i + 1) - 1][j + 1],
					//      dp_array[i + 1][(j + 1) - 1]);
					if (dp_array[(i + 1) - 1][j + 1] < dp_array[i + 1][(j + 1) - 1]) {
						dp_array[i + 1][j + 1] = dp_array[i + 1][(j + 1) - 1];
						prev[i + 1][j + 1] = "����";
					} else {
						dp_array[i + 1][j + 1] = dp_array[(i + 1) - 1][(j + 1)];
						prev[i + 1][j + 1] = "�W��";
					}
				}
			}
		}
		
		System.out.println("length : " + dp_array[s1.length][s2.length]);
		System.out.print("LCS : ");
		print_LCS(s1, prev, s1.length, s2.length);
	}
	
	void print_LCS(char[] s1, String[][] prev, int i, int j) {
		// �Ĥ@�өβĤG�ӧǦC���Ŷ��X�N����
		if (i == 0 || j == 0)
			return;

		if (prev[i][j] == "���W��") {
			print_LCS(s1, prev, i - 1, j - 1);
			System.out.print(s1[i - 1]); // �L�XLCS������
		} 
		else if (prev[i][j] == "�W��")
			print_LCS(s1, prev, i - 1, j);
		else if (prev[i][j] == "����")
			print_LCS(s1, prev, i, j - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "ABCBDAB";
		String s2 = "BDCABA";
		char[] s1_c = new char[s1.length()];
		for (int i = 0; i < s1_c.length; i++) {
			s1_c[i] = s1.charAt(i);
		}
		char[] s2_c = new char[s2.length()];
		for (int i = 0; i < s2_c.length; i++) {
			s2_c[i] = s2.charAt(i);
		}
		LCS lcs = new LCS();
		lcs.LCS_length(s1_c, s2_c);

	}

}
