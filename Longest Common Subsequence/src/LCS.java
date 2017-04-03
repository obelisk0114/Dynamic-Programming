/*
 * http://www.csie.ntnu.edu.tw/~u91029/LongestCommonSubsequence.html
 * 
 * LIS 轉 LCS：令原序列 A 排序後會變成 B。 A 和 B 的 LCS，就是 A 的 LIS 。
 * 
 * LCS 轉 LIS：將序列 A 和 B 當中的相同字母配對都找出來，呈現成索引值數對，再以特殊規則排序，最後找 LIS，
 * 就是 A 和 B 的 LCS。
 */

public class LCS {
	void LCS_length (char[] s1, char[] s2) {
		int[][] dp_array = new int[s1.length + 1][s2.length + 1];
		String[][] prev = new String[s1.length + 1][s2.length + 1]; // 記錄每一格的的結果是從哪一格而來
		
		// 初始化：當 s1 或 s2 是空集合，則 LCS 是空集合。
	    // array[x][0] = array[0][x] = 0;
		for (int i = 0; i <= s1.length; i++)
			dp_array[i][0] = 0;
		for (int j = 0; j <= s2.length; j++)
			dp_array[0][j] = 0;
		
		// 填表格：依照遞迴公式
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				if (s1[i] == s2[j]) {
					// +1是因為e1的長度為1
					dp_array[i + 1][j + 1] = dp_array[(i + 1) - 1][(j + 1) - 1] + 1;
					prev[i + 1][j + 1] = "左上方";
				} else {
					// dp_array[i + 1][j + 1] = Math.max(dp_array[(i + 1) - 1][j + 1],
					//      dp_array[i + 1][(j + 1) - 1]);
					if (dp_array[(i + 1) - 1][j + 1] < dp_array[i + 1][(j + 1) - 1]) {
						dp_array[i + 1][j + 1] = dp_array[i + 1][(j + 1) - 1];
						prev[i + 1][j + 1] = "左方";
					} else {
						dp_array[i + 1][j + 1] = dp_array[(i + 1) - 1][(j + 1)];
						prev[i + 1][j + 1] = "上方";
					}
				}
			}
		}
		
		System.out.println("length : " + dp_array[s1.length][s2.length]);
		System.out.print("LCS : ");
		print_LCS(s1, prev, s1.length, s2.length);
	}
	
	void print_LCS(char[] s1, String[][] prev, int i, int j) {
		// 第一個或第二個序列為空集合就停止
		if (i == 0 || j == 0)
			return;

		if (prev[i][j] == "左上方") {
			print_LCS(s1, prev, i - 1, j - 1);
			System.out.print(s1[i - 1]); // 印出LCS的元素
		} 
		else if (prev[i][j] == "上方")
			print_LCS(s1, prev, i - 1, j);
		else if (prev[i][j] == "左方")
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
