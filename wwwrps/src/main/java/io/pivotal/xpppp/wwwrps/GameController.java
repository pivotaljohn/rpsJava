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
		ConcreteUserInterface concreteUserInterface = new ConcreteUserInterface();
		game.runGame(Throw.valueOf(formGame.getPlayerOneThrow().toUpperCase()), Throw.valueOf(formGame.getPlayerTwoThrow().toUpperCase()), concreteUserInterface);

		ModelAndView modelAndView = new ModelAndView("result");
		modelAndView.addObject("resultText", concreteUserInterface.getResultString());
		return modelAndView;
	}
}
