package chess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.King;
import chess.pieces.Pawn;

/*
 * Possible king scenarios
 * Move one square horizontal, vertically, and diagonally
 * Take piece of opposite color, stop next to piece of same color
 */
public class KingTest {

	private GameState state;

	@Before
	public void setUp() throws Exception {
		state = new GameState();

	}

	@Test
	public void testKingMovement() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
		
		state.placePiece(new King(Player.White), new Position("d8"));
    	state.placePiece(new Pawn(Player.White), new Position("e8"));
    	state.placePiece(new Pawn(Player.Black), new Position("e7"));
    	
    	positionsTo.add(new Position("e7"));
    	positionsTo.add(new Position("d7"));
    	positionsTo.add(new Position("c7"));
    	positionsTo.add(new Position("c8"));

    	actualPossible.put(new Position("d8"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 
    	
    	assertEquals(actualPossible, calcedPossible);
    	
	}
	
	@Test
	public void testBlackKingMovement() {
		
		state.placePiece(new King(Player.Black), new Position("b6"));
    	
    	state.setCurrentPlayer(Player.Black);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 

    	assertEquals(8, calcedPossible.get(new Position("b6")).size());
    	
	}

}
