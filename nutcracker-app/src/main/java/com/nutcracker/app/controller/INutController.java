package com.nutcracker.app.controller;

import com.nutcracker.app.view.INutView;

public interface INutController {
	default public void setView(INutView view) {
		throw new UnsupportedOperationException();
	}
}
