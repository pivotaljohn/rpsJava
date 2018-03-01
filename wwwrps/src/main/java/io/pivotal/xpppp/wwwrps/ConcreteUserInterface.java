package io.pivotal.xpppp.wwwrps;

import com.awesomeautomation.rps.UserInterface;
import lombok.Data;

@Data
public class ConcreteUserInterface implements UserInterface {

    private String resultString;

    @Override
    public void playerOneWins() {
        resultString = "Player One Wins!";
    }

    @Override
    public void playerTwoWins() {
        resultString = "Player Two Wins!";
    }

    @Override
    public void draw() {
        resultString = "Tie!";
    }
}
