package chess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Knight;
import chess.pieces.Pawn;

/*
 * Possible knight scenarios
 * Move 2 squares horizontally or vertically, then move 1 square the opposite of the first move 
 * Take piece of opposite color, stop next to piece of same color
 */
public class KnightTest {

private GameState state;
	
	@Before
	public void setUp() throws Exception {
		state = new GameState();

	}

	@Test
	public void testKnightMovement() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
		
		state.placePiece(new Knight(Player.White), new Position("b6"));
    	state.placePiece(new Pawn(Player.White), new Position("c8"));
    	state.placePiece(new Pawn(Player.Black), new Position("d5"));
    	
    	positionsTo.add(new Position("a8"));
    	positionsTo.add(new Position("a4"));
    	positionsTo.add(new Position("c4"));
    	positionsTo.add(new Position("d5"));
    	positionsTo.add(new Position("d7"));

    	actualPossible.put(new Position("b6"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 
    	
    	assertEquals(actualPossible, calcedPossible);
    	
	}
	
	@Test
	public void testBlackKnightMovement() {
		
		state.placePiece(new Knight(Player.Black), new Position("g6"));
    	
    	state.setCurrentPlayer(Player.Black);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 

    	assertEquals(6, calcedPossible.get(new Position("g6")).size());
    	
	}

}
