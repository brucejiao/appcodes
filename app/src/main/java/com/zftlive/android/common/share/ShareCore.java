/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */

package com.zftlive.android.common.share;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.utils.R;
import cn.sharesdk.framework.ShareSDK;

/**
 * ShareCore是快捷分享的实际出口，此类使用了反射的方式，配合传递进来的HashMap，
 *构造{@link ShareParams}对象，并执行分享，使快捷分享不再需要考虑目标平台
 */
public class ShareCore {
	private ShareContentCustomizeCallback customizeCallback;

	/** 设置用于分享过程中，根据不同平台自定义分享内容的回调 */
	public void setShareContentCustomizeCallback(ShareContentCustomizeCallback callback) {
		customizeCallback = callback;
	}

	/**
	 * 向指定平台分享内容
	 * <p>
	 * <b>注意：</b><br>
	 * 参数data的键值需要严格按照{@link ShareParams}不同子类具体字段来命名，
	 *否则无法反射此字段，也无法设置其值。
	 */
	public boolean share(Platform plat, HashMap<String, Object> data) {
		if (plat == null || data == null) {
			return false;
		}

		try {
			String imagePath = (String) data.get("imagePath");
			Bitmap viewToShare = (Bitmap) data.get("viewToShare");
			if (TextUtils.isEmpty(imagePath) && viewToShare != null && !viewToShare.isRecycled()) {
				String path = R.getCachePath(plat.getContext(), "screenshot");
				File ss = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
				FileOutputStream fos = new FileOutputStream(ss);
				viewToShare.compress(CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
				data.put("imagePath", ss.getAbsolutePath());
			}
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}

		ShareParams sp = new ShareParams(data);
		if (customizeCallback != null) {
			customizeCallback.onShare(plat, sp);
		}

		String[] flags = new String[] {
				"OnekeyShare",
				plat.getContext().getPackageName(),
				String.valueOf(ShareSDK.getSDKVersionCode())
		};
		sp.setCustomFlag(flags);
		plat.share(sp);
		return true;
	}

	/** 判断指定平台是否使用客户端分享 */
	public static boolean isUseClientToShare(String platform) {
		if ("Wechat".equals(platform) || "WechatMoments".equals(platform)
				|| "WechatFavorite".equals(platform) || "ShortMessage".equals(platform)
				|| "Email".equals(platform) || "GooglePlus".equals(platform)
				|| "QQ".equals(platform) || "Pinterest".equals(platform)
				|| "Instagram".equals(platform) || "Yixin".equals(platform)
				|| "YixinMoments".equals(platform) || "QZone".equals(platform)
				|| "Mingdao".equals(platform) || "Line".equals(platform)) {
			return true;
		} else if ("Evernote".equals(platform)) {
			Platform plat = ShareSDK.getPlatform(platform);
			if ("true".equals(plat.getDevinfo("ShareByAppClient"))) {
				return true;
			}
		} else if ("SinaWeibo".equals(platform)) {
			Platform plat = ShareSDK.getPlatform(platform);
			if ("true".equals(plat.getDevinfo("ShareByAppClient"))) {
				Intent test = new Intent(Intent.ACTION_SEND);
				test.setPackage("com.sina.weibo");
				test.setType("image/*");
				ResolveInfo ri = plat.getContext().getPackageManager().resolveActivity(test, 0);
				return (ri != null);
			}
		}

		return false;
	}

	/** 判断指定平台是否可以用来授权 */
	public static boolean canAuthorize(Context context, String platform) {
		if ("Wechat".equals(platform) || "WechatMoments".equals(platform)
				|| "WechatFavorite".equals(platform) || "ShortMessage".equals(platform)
				|| "Email".equals(platform) || "GooglePlus".equals(platform)
				|| "QQ".equals(platform) || "Pinterest".equals(platform)
				|| "Yixin".equals(platform) || "YixinMoments".equals(platform)
				|| "Line".equals(platform)) {
			return false;
		}
		return true;
	}

}
