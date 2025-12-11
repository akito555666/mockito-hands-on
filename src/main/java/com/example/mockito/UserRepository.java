package com.example.mockito;

import java.util.Optional;

public interface UserRepository {
	Optional<User> findById(String id);
}