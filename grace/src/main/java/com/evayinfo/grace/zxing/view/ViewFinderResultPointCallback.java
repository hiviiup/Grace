package com.evayinfo.grace.zxing.view;

import com.evayinfo.grace.zxing.view.ViewfinderView;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public class ViewFinderResultPointCallback implements ResultPointCallback {

	private final ViewfinderView viewfinderView;

	public ViewFinderResultPointCallback(ViewfinderView viewfinderView) {
		this.viewfinderView = viewfinderView;
	}

	@Override
	public void foundPossibleResultPoint(ResultPoint point) {
		viewfinderView.addPossibleResultPoint(point);
	}

}
