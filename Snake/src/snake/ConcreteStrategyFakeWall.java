package snake;

import domain.Dot;


public class ConcreteStrategyFakeWall implements Strategy {
	public void executeStrategy(Dot d, Board o) {
		if (d.getPosY() > o.getBoardHeight()) {
			d.setPosY(0);
		} else if (d.getPosY() < 0) {
			d.setPosY(o.getBoardHeight() + o.getDotSize());
		} else if (d.getPosX() > o.getBoardHeight()) {
			d.setPosX(0);
		} else if (d.getPosX() < 0) {
			d.setPosX(o.getBoardHeight() + o.getDotSize());
		}
	}
}
