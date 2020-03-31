package zi.leet.easy;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author balamurugan
 */
public class SerDe {
	public String encode(List<String> strs) {
		byte[][] serStrs = new byte[strs.size()][];
		int total = 4;
		for (int i = 0; i < strs.size(); i++) {
			serStrs[i] = strs.get(i).getBytes(StandardCharsets.US_ASCII);
			total += serStrs[i].length;
			total += 4;
		}
		ByteBuffer b = ByteBuffer.allocate(total);
		b.putInt(strs.size());
		for (int i = 0; i < serStrs.length; i++) {
			b.putInt(serStrs[i].length);
			b.put(serStrs[i]);
		}
		return new String(b.array(), StandardCharsets.US_ASCII);
	}

	public List<String> decode(String s) {
		ByteBuffer b = ByteBuffer.wrap(s.getBytes(StandardCharsets.US_ASCII));
		int len = b.getInt();
		List<String> out = new ArrayList<>(len);
		for (int i = 0; i < len; i++) {
			int curLen = b.getInt();
			byte[] cur = new byte[curLen];
			b.get(cur);
			out.add(new String(cur, StandardCharsets.US_ASCII));
		}
		return out;
	}
}
