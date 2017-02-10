package com.salage.Utils;


public class BaseURL {
	
//	public static String HTTP = "http://lovelane.aapbd.xyz/api/v1/";
	public static String HTTP = "http://www.ict-euro.com/demo/salage/";


	/**
	 * @return the hTTP
	 */
	public static String makeHTTPURL(String param) {
		return BaseURL.HTTP + UrlUtils.encode(param);
	}

	/**
	 * @param hTTP
	 *            the hTTP to set
	 */
	public static void setHTTP(final String hTTP) {
		BaseURL.HTTP = hTTP;
	}

}
