/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */

package com.zftlive.android.zxing.decode;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Pattern;

import android.content.Intent;
import android.net.Uri;
import com.zftlive.android.zxing.Intents;

import com.google.zxing.BarcodeFormat;

final class DecodeFormatManager {

	private static final Pattern COMMA_PATTERN = Pattern.compile(",");

	static final Collection<BarcodeFormat> PRODUCT_FORMATS;
	static final Collection<BarcodeFormat> ONE_D_FORMATS;
	static final Collection<BarcodeFormat> QR_CODE_FORMATS = EnumSet
			.of(BarcodeFormat.QR_CODE);
	static final Collection<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet
			.of(BarcodeFormat.DATA_MATRIX);
	static {
		PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E,
				BarcodeFormat.EAN_13, BarcodeFormat.EAN_8,
				BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED);
		ONE_D_FORMATS = EnumSet.of(BarcodeFormat.CODE_39,
				BarcodeFormat.CODE_93, BarcodeFormat.CODE_128,
				BarcodeFormat.ITF, BarcodeFormat.CODABAR);
		ONE_D_FORMATS.addAll(PRODUCT_FORMATS);
	}

	private DecodeFormatManager() {
	}

	static Collection<BarcodeFormat> parseDecodeFormats(Intent intent) {
		Iterable<String> scanFormats = null;
		CharSequence scanFormatsString = intent
				.getStringExtra(Intents.Scan.FORMATS);
		if (scanFormatsString != null) {
			scanFormats = Arrays.asList(COMMA_PATTERN.split(scanFormatsString));
		}
		return parseDecodeFormats(scanFormats,
				intent.getStringExtra(Intents.Scan.MODE));
	}

	static Collection<BarcodeFormat> parseDecodeFormats(Uri inputUri) {
		List<String> formats = inputUri
				.getQueryParameters(Intents.Scan.FORMATS);
		if (formats != null && formats.size() == 1 && formats.get(0) != null) {
			formats = Arrays.asList(COMMA_PATTERN.split(formats.get(0)));
		}
		return parseDecodeFormats(formats,
				inputUri.getQueryParameter(Intents.Scan.MODE));
	}

	private static Collection<BarcodeFormat> parseDecodeFormats(
			Iterable<String> scanFormats, String decodeMode) {
		if (scanFormats != null) {
			Collection<BarcodeFormat> formats = EnumSet
					.noneOf(BarcodeFormat.class);
			try {
				for (String format : scanFormats) {
					formats.add(BarcodeFormat.valueOf(format));
				}
				return formats;
			}
			catch (IllegalArgumentException iae) {
				// ignore it then
			}
		}
		if (decodeMode != null) {
			if (Intents.Scan.PRODUCT_MODE.equals(decodeMode)) {
				return PRODUCT_FORMATS;
			}
			if (Intents.Scan.QR_CODE_MODE.equals(decodeMode)) {
				return QR_CODE_FORMATS;
			}
			if (Intents.Scan.DATA_MATRIX_MODE.equals(decodeMode)) {
				return DATA_MATRIX_FORMATS;
			}
			if (Intents.Scan.ONE_D_MODE.equals(decodeMode)) {
				return ONE_D_FORMATS;
			}
		}
		return null;
	}

}
