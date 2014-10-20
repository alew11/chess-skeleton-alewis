package chess;


import chess.pieces.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;
    private Map<Position, Set<Position>> possibleWhiteMoves;
    private Map<Position, Set<Position>> possibleBlackMoves;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
        possibleWhiteMoves = new HashMap<Position, Set<Position>>();
        possibleBlackMoves = new HashMap<Position, Set<Position>>();

    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    public void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
        possibleWhiteMoves.clear();
        possibleBlackMoves.clear();
        possibleWhiteMoves = calculatePossibleMoves(positionToPieceMap, Player.White);
        possibleBlackMoves = calculatePossibleMoves(positionToPieceMap, Player.Black);
        trimIllegalMoves();

    }
    
    /**
     * Return the possible moves of either player
     * @param player The player of which you wish to know the move list
     * @return The move list
     */
    public Map<Position, Set<Position>> getPossibleMoveList(Player player) {
    	//trimIllegalMoves(player);
    	if(player == Player.White) {
    		return possibleWhiteMoves;
    	} else {
    		return possibleBlackMoves;
    	}
    }   
    
    /**
     * Calculate all possible moves on a board for the player, UNTRIMMED OF PINS
     * @param board the location of all the pieces
     * @param player the current player
     * @return list of moves possibly containing illegal moves (king into check, moving pinned piece)
     */
    private Map<Position, Set<Position>> calculatePossibleMoves(Map<Position, Piece> board, Player player) {
    	
    	Map<Position, Set<Position>> listOfMoves = new HashMap<Position, Set<Position>>();
       	for (Position pos : board.keySet()) {
       		Set<Position> positionsTo = board.get(pos).getPossibleMoves(board, pos);
       		if ((positionsTo != null) && (!positionsTo.isEmpty()) && (board.get(pos).getOwner() == player)) {
       			listOfMoves.put(pos, positionsTo);	
       		}
    	}
       	return listOfMoves;
    }
    
    /**
     * Change the current players color
     * @param player color to change to
     */
    public void setCurrentPlayer(Player player) {
    	currentPlayer = player;
    }
    
    public Player getOpposingPlayer() {
    	if (currentPlayer == Player.White) {
    		return Player.Black;
    	} else {
    		return Player.White;
    	}
    }   
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Return the position of the king corresponding to the player's color, else return null (no king present)
     * @param board board that will be searched 
     * @param player color of player that you wish to know the king's position
     * @return the position of the king
     */
    private Position getKingPosition(Map<Position, Piece> board, Player player) {
    	
    	for (Position pos : board.keySet()) {
    		if (board.get(pos) instanceof King && board.get(pos).getOwner() == player) {
    			return pos;
    		}
    	}
    		return null;
    }
    
    /**
     * Check whether the king is safe or not
     * @param board board that will be searched
     * @param possibleMoves possible attacking moves of the opponent
     * @param player current player
     * @return true if king is safe or there is no king, false otherwise
     */
    private boolean kingSafetyCheck(Map<Position, Piece> board, Map<Position, Set<Position>> possibleMoves, Player player) {
    	
    	Position kingPos = getKingPosition(board, player);
    	
    	if (kingPos == null) {
    		return true;
    	}
    	for (Position attackFrom : possibleMoves.keySet()) {
    		for (Position attackTo : possibleMoves.get(attackFrom)) {
    			if (kingPos.equals(attackTo)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    /**
     * Trim moves from the possible move list that expose the king to check
     */
    private void trimIllegalMoves() {
    	
    	Map<Position, Piece> tentativeBoard = new HashMap<Position, Piece>();
    	Map<Position, Set<Position>> actualAllowedMoves = new HashMap<Position, Set<Position>>();
	
		// For all moves in the possible move list
    	for (Position from : possibleWhiteMoves.keySet()) {
        	Set<Position> legalMoves = new HashSet<Position>();
    		legalMoves.clear();
    		for (Position to : possibleWhiteMoves.get(from)) {
    			tentativeBoard.clear();
    			//Move the piece
    	    	tentativeBoard.putAll(positionToPieceMap);
    			tentativeBoard.put(to, tentativeBoard.get(from));
    			tentativeBoard.remove(from);
    			// Calculate the opponents moves on the new board
    			Map<Position, Set<Position>> tentativeMovesForBlack = calculatePossibleMoves(tentativeBoard, Player.Black);
    			// If the king is safe on the new board, add the move
    			if (kingSafetyCheck(tentativeBoard, tentativeMovesForBlack, Player.White)) {
    				legalMoves.add(to);    				
    			}
    		}
    		// If the piece is able to be moved somewhere, add the set of possibilities
    		if (!legalMoves.isEmpty()) {
    			actualAllowedMoves.put(from, legalMoves);
    		}
    	}
    	possibleWhiteMoves.clear();
    	possibleWhiteMoves.putAll(actualAllowedMoves);
    	
    	tentativeBoard.clear();
    	actualAllowedMoves.clear();
	
		for (Position from : possibleBlackMoves.keySet()) {
        	Set<Position> legalMoves = new HashSet<Position>();
    		legalMoves.clear();
    		for (Position to : possibleBlackMoves.get(from)) {
    			tentativeBoard.clear();
    	    	tentativeBoard.putAll(positionToPieceMap);
    			tentativeBoard.put(to, tentativeBoard.get(from));
    			tentativeBoard.remove(from);
    			Map<Position, Set<Position>> tentativeMovesForWhite = calculatePossibleMoves(tentativeBoard, Player.White);
    			if (kingSafetyCheck(tentativeBoard, tentativeMovesForWhite, Player.Black)) {
    				legalMoves.add(to);    				
    			}
    		}
    		if (!legalMoves.isEmpty()) {
    			actualAllowedMoves.put(from, legalMoves);
    		}
    	}
    	possibleBlackMoves.clear();
    	possibleBlackMoves.putAll(actualAllowedMoves);
	}
    
    /**
     * Method for moving piece
     * @param from position to move from
     * @param to position to move to
     * @return True if move is valid, false otherwise
     */
    public boolean movePiece(Position from, Position to) {
    	    	
    	// Test whether there is a piece at the from position
    	if (!positionToPieceMap.containsKey(from)) {
    		return false;
    	}
    	
    	Player player = positionToPieceMap.get(from).getOwner();
    	
    	// Test to make sure from is owned by the current player
    	if (player != getCurrentPlayer()) {
    		return false;
    	}
    	
    	// Make sure the move is valid by comparing to possible move list
    	if (player == Player.White) {
    		if (!possibleWhiteMoves.containsKey(from)) {
    			return false;
    		}
    		if (!possibleWhiteMoves.get(from).contains(to)) {
    			return false;
    		}
    		// move the piece
    		Piece pieceToMove = positionToPieceMap.get(from);
    		positionToPieceMap.remove(from);
    		placePiece(pieceToMove, to);
    		// Switch players
    		setCurrentPlayer(getOpposingPlayer());
    		return true;
    	}
    	
    	if (player == Player.Black) {
    		if (!possibleBlackMoves.containsKey(from)) {
    			return false;
    		}
    		if (!possibleBlackMoves.get(from).contains(to)) {
    			return false;
    		}
    		Piece pieceToMove = positionToPieceMap.get(from);
    		positionToPieceMap.remove(from);
    		placePiece(pieceToMove, to);
    		setCurrentPlayer(getOpposingPlayer());
    		return true;
    	}
    	
    	
    	return false;
    }
    
    /**
     * Check whether the current player is in check or checkmate
     * @return 0 if in checkmate, 1 if in check, 2 otherwise
     */
    public int checkForMate() {
    	
    	Player player = getCurrentPlayer();
    	
    	// If the current player is white, check whether the king is safe relative
    	// to black's possible moves
    	if (player == Player.White) {
    		if (!kingSafetyCheck(positionToPieceMap, possibleBlackMoves, player)) {
    			// If the king is not safe, check the possible moves for white, 
    			// If there are no possible moves, then it is checkmate
    			if (possibleWhiteMoves.size() == 0) {
    				return 0;
    			} else {
    				return 1;
    			}
    		}
    	}
    	if (player == Player.Black) {
    		if (!kingSafetyCheck(positionToPieceMap, possibleWhiteMoves, player)) {
    			if (possibleBlackMoves.size() == 0) {
    				return 0;
    			} else {
    				return 1;
    			}
    		}
    	}
    	return 2;
    }

}
