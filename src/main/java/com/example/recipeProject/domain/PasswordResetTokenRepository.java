package com.example.recipeProject.domain;

import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
	PasswordResetToken findByToken(String token);
}
