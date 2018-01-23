package snake;

import domain.Dot;


public class ConcreteStrategyRealWall implements Strategy{
	public void executeStrategy(Dot d, Board o) {
		if (d.getPosY() > o.getBoardHeight()) {
			o.endGame();
		} else if (d.getPosY() < 0) {
			o.endGame();
		} else if (d.getPosX() > o.getBoardWidth()) {
			o.endGame();
		} else if (d.getPosX() < 0) {
			o.endGame();
		}
	}
}
