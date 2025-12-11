package com.example.mockito;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UIControllerTest {
	@Test
	void testButtonClickTriggersServiceAndChangesState() {
		UserService service = mock(UserService.class);
		UIController controller = new UIController(service);

		when(service.findUser("123")).thenReturn(new User("123", "ニンジャ＝スレイヤー"));

		controller.onButtonClick("123");

		verify(service).findUser("123");
		assertEquals("LOADED", controller.getStatus());
	}

	@Test
	void testMultipleButtonClicks() {
		UserService service = mock(UserService.class);
		UIController controller = new UIController(service);

		when(service.findUser("123")).thenReturn(new User("123", "ニンジャ＝スレイヤー"));
		when(service.findUser("999")).thenReturn(null);

		controller.onButtonClick("123");
		assertEquals("LOADED", controller.getStatus());

		controller.onButtonClick("999");
		assertEquals("ERROR", controller.getStatus());
	}

	@Test
	void testAsyncButtonClick() {
		UserService service = mock(UserService.class);
		UIController controller = new UIController(service);

		when(service.findUserAsync("123"))
			.thenReturn(CompletableFuture.completedFuture(new User("123", "Akito")));

		controller.onAsyncButtonClick("123");

		verify(service).findUserAsync("123");
		// 非同期なので少し待ってから確認
		try { Thread.sleep(100); } catch (InterruptedException ignored) {}
		assertEquals("LOADED", controller.getStatus());
	}
}