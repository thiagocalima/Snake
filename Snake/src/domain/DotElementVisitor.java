package domain;

import snake.Board;

interface DotElementVisitor {
	void colide(Body b, Board o);
	void colide(Apple a, Board o);
	void colide(Dot dot, Board o);
}