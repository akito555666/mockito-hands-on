package com.example.mockito;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
	@Test
	void testFindUser() {
		UserRepository repo = mock(UserRepository.class);
		UserService service = new UserService(repo);

		when(repo.findById("123")).thenReturn(Optional.of(new User("123", "Akito")));

		User result = service.findUser("123");
		assertEquals("Akito", result.getName());
		verify(repo).findById("123");
	}
}