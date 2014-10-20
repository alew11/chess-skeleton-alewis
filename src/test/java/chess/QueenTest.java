package chess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Pawn;
import chess.pieces.Queen;

/*
 * Possible queen scenarios
 * Move horizontal, vertically, and diagonally
 * Take piece of opposite color, stop next to piece of same color
 */
public class QueenTest {

	private GameState state;

	@Before
	public void setUp() throws Exception {
		state = new GameState();

	}

	@Test
	public void testQueenMovement() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
		
		state.placePiece(new Queen(Player.White), new Position("c6"));
    	state.placePiece(new Pawn(Player.White), new Position("e8"));
    	state.placePiece(new Pawn(Player.Black), new Position("c4"));
    	state.placePiece(new Pawn(Player.Black), new Position("e4"));
    	state.placePiece(new Pawn(Player.Black), new Position("e6"));
    	
    	positionsTo.add(new Position("b7"));
    	positionsTo.add(new Position("a8"));
    	positionsTo.add(new Position("c7"));
    	positionsTo.add(new Position("c8"));
    	positionsTo.add(new Position("d7"));
    	positionsTo.add(new Position("d6"));
    	positionsTo.add(new Position("e6"));
    	positionsTo.add(new Position("d5"));
    	positionsTo.add(new Position("e4"));
    	positionsTo.add(new Position("c5"));
    	positionsTo.add(new Position("c4"));
    	positionsTo.add(new Position("b5"));
    	positionsTo.add(new Position("a4"));
    	positionsTo.add(new Position("b6"));
    	positionsTo.add(new Position("a6"));


    	actualPossible.put(new Position("c6"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 
    	
    	assertEquals(actualPossible, calcedPossible);
    	
	}
	
	@Test
	public void testBlackQueenMovement() {
		
		state.placePiece(new Queen(Player.Black), new Position("c2"));
    	state.placePiece(new Pawn(Player.White), new Position("e8"));
    	
    	state.setCurrentPlayer(Player.Black);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 

    	assertEquals(23, calcedPossible.get(new Position("c2")).size());
    	
	}
}
