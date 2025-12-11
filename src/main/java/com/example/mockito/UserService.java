package com.example.mockito;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class UserService {
	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public User findUser(String id) {
		return repo.findById(id).orElse(null);
	}

	public CompletableFuture<User> findUserAsync(String id) {
		return CompletableFuture.supplyAsync(() -> repo.findById(id).orElse(null));
	}
}