/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */

package com.zftlive.android.zxing.camera;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.util.Log;

/**
 * 该类用于检测手机上摄像头的个数，如果有两个摄像头，则取背面的摄像头
 */
public final class OpenCameraInterface {

	private static final String TAG = OpenCameraInterface.class.getName();

	private OpenCameraInterface() {
	}

	/**
	 * Opens a rear-facing camera with {@link Camera#open(int)}, if one exists,
	 * or opens camera 0.
	 */
	@SuppressLint("NewApi")
	public static Camera open() {

		int numCameras = Camera.getNumberOfCameras();
		if (numCameras == 0) {
			Log.w(TAG, "No cameras!");
			return null;
		}

		int index = 0;
		while (index < numCameras) {
			Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
			Camera.getCameraInfo(index, cameraInfo);
			// CAMERA_FACING_BACK：手机背面的摄像头
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				break;
			}
			index++;
		}

		Camera camera;
		if (index < numCameras) {
			Log.i(TAG, "Opening camera #" + index);
			camera = Camera.open(index);
		} else {
			Log.i(TAG, "No camera facing back; returning camera #0");
			camera = Camera.open(0);
		}

		return camera;
	}

}
