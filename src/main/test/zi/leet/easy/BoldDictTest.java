package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BoldDictTest {
	private BoldDict solution = new BoldDict();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{"abcxyz123", new String[]{"abc", "123"}, "<b>abc</b>xyz<b>123</b>"},
//				{"aaabbcc", new String[]{"aaa", "aab", "bc"}, "<b>aaabbc</b>c"},
				{"xhhjzbkvpmasiypsqqjobufcqmlhdjffrdohsxgksftaekzhwzydhbfdiylihnvjlvpoptnqigszckimljbepgisnmyszfsxkxyfdfqngytfuihepohapvhbyhqydvroflfnsyjmygtykdejfudrhxxawcewgiguiwsvqrgbxrbdnrvguzjftqcsjbvjlbxfsvzpdpmtlzobwnxrtgisbcqmhugncjwgatfctydryakvbnmlbiftndfefylsmlebzdumefuflwhtwijtrhhhmknklalgqjaoicmnywtvzldbeftkydjsdkkonayhdxhrjazosqloilagcwzeezavnsqelxqhtlzymedxmkrovxhkrgfenyhxgdroeejedbwpnkqbqknalwgxoxweyxngorvrpnfkvagdqkbtuayaihyhwcsdtjzzvxfavrhzgf",
						new String[]{"xh", "hj", "zb", "kv", "pm", "as", "iy", "ps", "qq", "jo", "bu", "fc", "qm", "lh", "dj", "ff", "rd", "oh", "sx", "gk", "sf", "ta", "ek", "zh", "wz", "yd", "hb", "fd", "li", "hn", "vj", "lv", "po", "pt", "nq", "ig", "sz", "ck", "im", "lj", "be", "pg", "is", "nm", "ys", "zf", "kx"}, ""}
		};
	}

	@Test(dataProvider = "data")
	public void testAddBoldTag(String in, String[] dict, String expected) {
		assertEquals(solution.addBoldTag(in, dict), expected);
	}
}
