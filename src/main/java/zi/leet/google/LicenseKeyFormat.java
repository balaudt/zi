package zi.leet.google;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/license-key-formatting/
public class LicenseKeyFormat {
	public String licenseKeyFormatting(String S, int K) {
		char[] in = S.toUpperCase().replaceAll("-", "").toCharArray();
		int n = in.length;
		if (n == 0) return "";
		char[] out = new char[n + (n % K == 0 ? n / K - 1 : n / K)];
		int inInd = 0, outInd = 0;
		if (n % K != 0) {
			int firstGroup = n % K;
			for (int i = 0; i < firstGroup; i++) {
				out[outInd++] = in[inInd++];
			}
			if (outInd < out.length) out[outInd++] = '-';
		}
		while (inInd < n) {
			for (int i = 0; i < K; i++) {
				out[outInd++] = in[inInd++];
			}
			if (outInd < out.length) out[outInd++] = '-';
		}
		return new String(out);
	}
}
