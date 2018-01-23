package domain;

import snake.Board;

interface DotElement {
	void accept(Head h, Board o);
}