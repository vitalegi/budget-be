package it.vitalegi.budget.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import it.vitalegi.budget.board.constant.BoardUserRole;
import it.vitalegi.budget.user.dto.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BoardUser {
    @NotNull
    @Schema(description = "User")
    User user;
    @NotNull
    @Schema(description = "User's role")
    BoardUserRole role;
}
