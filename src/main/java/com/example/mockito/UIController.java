package com.example.mockito;

import java.util.concurrent.CompletableFuture;

public class UIController {
	private final UserService service;
	private String status = "IDLE";

	public UIController(UserService service) {
		this.service = service;
	}

	public void onButtonClick(String userId) {
		User user = service.findUser(userId);
		if (user != null) {
			status = "LOADED";
		} else {
			status = "ERROR";
		}
	}

	public void onAsyncButtonClick(String userId) {
		service.findUserAsync(userId).thenAccept(user -> {
			if (user != null) {
				status = "LOADED";
			} else {
				status = "ERROR";
			}
		});
	}

	public String getStatus() {
		return status;
	}
}