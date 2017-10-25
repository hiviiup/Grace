package com.evayinfo.grace.zxing.view;

import com.evayinfo.grace.zxing.view.ViewfinderView;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public class ViewfinderResultPointCallback implements ResultPointCallback {

	private final ViewfinderView viewfinderView;

	public ViewfinderResultPointCallback(ViewfinderView viewfinderView) {
		this.viewfinderView = viewfinderView;
	}

	@Override
	public void foundPossibleResultPoint(ResultPoint point) {
		viewfinderView.addPossibleResultPoint(point);
	}

}
