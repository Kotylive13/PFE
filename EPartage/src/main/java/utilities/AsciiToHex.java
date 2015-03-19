package utilities;

public class AsciiToHex {

	/**
	 *
	 * @param ascii
	 * @return
	 */
	public static String asciiToHex(String ascii) {
		StringBuilder hex = new StringBuilder();

		for (int i = 0; i < ascii.length(); i++) {
			hex.append(Integer.toHexString(ascii.charAt(i)));
		}
		return hex.toString();
	}

	/**
	 *
	 * @param hexString
	 * @return
	 */
	public static String decode(final String hexString) {
		final int len = hexString.length();
		if (len % 2 != 0) {
			throw new RuntimeException("bad length");
		}
		final StringBuilder sb = new StringBuilder(len / 2);
		for (int i = 0; i < len; i += 2) {
			final String code = hexString.substring(i, i + 2);
			sb.append((char) Integer.parseInt(code, 16));
		}
		return sb.toString();
	}

}
