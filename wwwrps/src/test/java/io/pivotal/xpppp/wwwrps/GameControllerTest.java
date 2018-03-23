package io.pivotal.xpppp.wwwrps;

import com.awesomeautomation.rps.Game;
import com.awesomeautomation.rps.Throw;
import com.awesomeautomation.rps.UserInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Game game;

	@Test
	public void loadMainPage_loadsMainPageWithGameForm() throws Exception {
		ModelAndView modelAndView =
			mockMvc.perform(get("/"))
					.andReturn().getModelAndView();

		assertThat(modelAndView.getViewName()).isEqualTo("index");
		assertThat(modelAndView.getModel().get("game")).isInstanceOf(FormGame.class);
		assertThat((String) modelAndView.getModel().get("errorText")).isNullOrEmpty();
	}

	@Test
	public void playGame_givenValidInput_returnResultPage() throws Exception {
		Mockito.doAnswer((Answer<Object>) invocation -> {
            invocation.getArgumentAt(2, UserInterface.class).playerOneWins();
            return null;
        }).when(game).runGame(any(Throw.class), any(Throw.class), any(UserInterface.class));

		ModelAndView modelAndView =
				mockMvc.perform(post("/playGame")
						.contentType(APPLICATION_FORM_URLENCODED) //from MediaType
						.param("playerOneThrow", "rock")
						.param("playerTwoThrow", "scissors"))
						.andReturn().getModelAndView();

		assertThat(modelAndView.getViewName()).isEqualTo("result");
		assertThat(modelAndView.getModel().get("resultText")).isEqualTo("Player One Wins!");
	}

	@Test
	public void playGame_givenInvalidInput_returnsInputPageWithGivenFormGameAndErrorMessage() throws Exception {
		ModelAndView modelAndView =
				mockMvc.perform(post("/playGame")
				.contentType(APPLICATION_FORM_URLENCODED)
				.param("playerOneThrow", "rock")
				.param("playerTwoThrow", "sailboat"))
				.andReturn().getModelAndView();

		FormGame resultGame = (FormGame) modelAndView.getModel().get("game");

		assertThat(modelAndView.getViewName()).isEqualTo("index");
		assertThat(resultGame.getPlayerOneThrow()).isEqualTo("rock");
		assertThat(resultGame.getPlayerTwoThrow()).isEqualTo("sailboat");
		assertThat(modelAndView.getModel().get("errorText")).isEqualTo("Invalid Input");

	}

}