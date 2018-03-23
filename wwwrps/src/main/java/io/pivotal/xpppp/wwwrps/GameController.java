package io.pivotal.xpppp.wwwrps;

import com.awesomeautomation.rps.Game;
import com.awesomeautomation.rps.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GameController {

	@Autowired
	private Game game;

	@GetMapping("/")
	public ModelAndView loadMainPage() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("game", new FormGame());

		return modelAndView;
	}

	@PostMapping("/playGame")
	public ModelAndView playGame(
			@ModelAttribute("game") FormGame formGame) {
		return throwsAreValid(formGame) ? playTheGame(formGame) : reportInvalidInput(formGame);
	}

	private ModelAndView reportInvalidInput(FormGame formGame) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("game", formGame);
		modelAndView.addObject("errorText", "Invalid Input");
		return modelAndView;
	}

	private ModelAndView playTheGame(FormGame formGame) {
		ModelAndView modelAndView = new ModelAndView();
		ConcreteUserInterface concreteUserInterface = new ConcreteUserInterface();
		game.runGame(Throw.valueOf(formGame.getPlayerOneThrow().toUpperCase()), Throw.valueOf(formGame.getPlayerTwoThrow().toUpperCase()), concreteUserInterface);
		modelAndView.setViewName("result");
		modelAndView.addObject("resultText", concreteUserInterface.getResultString());
		return modelAndView;
	}

	private boolean throwsAreValid(FormGame formGame) {
		return Throw.isAThrow(formGame.getPlayerOneThrow()) && Throw.isAThrow(formGame.getPlayerTwoThrow());
	}
}
