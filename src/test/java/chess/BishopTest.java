package chess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Bishop;
import chess.pieces.Pawn;

public class BishopTest {

	private GameState state;

	@Before
	public void setUp() throws Exception {
		state = new GameState();

	}

	@Test
	public void testBishopMovement() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
		
		state.placePiece(new Bishop(Player.White), new Position("c6"));
    	state.placePiece(new Pawn(Player.White), new Position("e8"));
    	state.placePiece(new Pawn(Player.Black), new Position("e4"));
    	
    	positionsTo.add(new Position("b7"));
    	positionsTo.add(new Position("a8"));
    	positionsTo.add(new Position("d7"));
    	positionsTo.add(new Position("d5"));
    	positionsTo.add(new Position("e4"));
    	positionsTo.add(new Position("b5"));
    	positionsTo.add(new Position("a4"));


    	actualPossible.put(new Position("c6"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.listPossibleMoves(state.getCurrentPlayer());   
    	
    	assertEquals(actualPossible, calcedPossible);
    	
	}

}
