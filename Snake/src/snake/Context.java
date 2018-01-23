package snake;

import domain.Dot;


class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
 
    public void executeStrategy(Dot d, Board o) {
        strategy.executeStrategy(d, o);
    }
}
