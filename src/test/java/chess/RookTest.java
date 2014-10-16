package chess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Pawn;
import chess.pieces.Rook;

/*
 * Possible rook scenarios
 * Move horizontal or vertically
 * Take piece of opposite color, stop next to piece of same color
 */
public class RookTest {

	private GameState state;
	
	@Before
	public void setUp() throws Exception {
		state = new GameState();

	}

	@Test
	public void testRookMovement() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
		
		state.placePiece(new Rook(Player.White), new Position("d5"));
    	state.placePiece(new Pawn(Player.White), new Position("d8"));
    	state.placePiece(new Rook(Player.Black), new Position("g5"));
    	
    	positionsTo.add(new Position("d1"));
    	positionsTo.add(new Position("d2"));
    	positionsTo.add(new Position("d3"));
    	positionsTo.add(new Position("d4"));
    	positionsTo.add(new Position("e5"));
    	positionsTo.add(new Position("f5"));
    	positionsTo.add(new Position("g5"));
    	positionsTo.add(new Position("a5"));
    	positionsTo.add(new Position("b5"));
    	positionsTo.add(new Position("c5"));
    	positionsTo.add(new Position("d6"));
    	positionsTo.add(new Position("d7"));

    	actualPossible.put(new Position("d5"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.listPossibleMoves(state.getCurrentPlayer());   
    	
    	assertEquals(actualPossible, calcedPossible);
    	
	}

}
