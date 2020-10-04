/*
 *  Attack.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2020 Jörg Dippel
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package chess383.attack;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import chess383.ColorEnum;
import chess383.piece.abstraction.Piece;
import chess383.piece.concretion.bishop.Bishop;
import chess383.piece.concretion.king.MovedKing;
import chess383.piece.concretion.knight.Knight;
import chess383.piece.concretion.pawn.MovedBlackPawn;
import chess383.piece.concretion.pawn.MovedWhitePawn;
import chess383.piece.concretion.queen.Queen;
import chess383.piece.concretion.rook.Rook;
import chess383.player.Player;


/**
 * Provides a method to check whether a location is attacked (by the opponent).
 *
 * @author    Jörg Dippel
 * @version   September 2020
 *
 */
public class Attack {
    
    /** ---------  Attributes  -------------------------------- */
    
    private Player own;
    private Player opponent;

    /** ---------  Constructors  ------------------------------ */
    
    private Attack( Player own, Player opponent ) {
        setOwn( own );
        setOpponent( opponent );
    }
    
    /** ---------  Getter and Setter  ------------------------- */
    
    private void   setOwn( Player value )         { this.own = value; }
    private Player getOwn()                       { return this.own; }
    private void   setOpponent( Player value )    { this.opponent = value; }
    private Player getOpponent()                  { return this.opponent; }
    
    /** ---------  Factory  ----------------------------------- */
    
    static public Attack create( Player own, Player opponent ) {
        
        return new Attack( own, opponent );
    }

    /** ------------------------------------------------------- */
    
    public boolean isAttacked( String locationUnderInspection ) {
        
        return isAttackedByKnight( locationUnderInspection ) ||
               isAttackedByRook( locationUnderInspection )   ||
               isAttackedByBishop( locationUnderInspection ) ||
               isAttackedByQueen( locationUnderInspection )  ||
               isAttackedByKing( locationUnderInspection )   ||
               isAttackedByPawn( getOwn().getColour(), locationUnderInspection );
    }
    
    private boolean isAttackedByPawn( ColorEnum color, String locationUnderInspection ) {
        
        if( color == ColorEnum.WHITE ) return isAttackedByBlackPawn( locationUnderInspection );
        if( color == ColorEnum.BLACK ) return isAttackedByWhitePawn( locationUnderInspection );
        throw new InvalidParameterException( String.format( "Color not defined: %s", color ) );
        
    }
    
    private boolean isAttackedByPiece( Piece pieceUnderInspection, Predicate<Piece> isAssociated ) {
        
        String location;
        Piece piece;
        
        Set<List<String>> lines = pieceUnderInspection.getCapturingLines( );
        for( List<String> line : lines ) {
            Iterator<String> iterator = line.iterator();
            if( iterator.hasNext() ) {
                location = iterator.next();   // this is the origin
                piece = getOpponent().getPiece( location );
                if( piece != null ) return false;
            }
            while( iterator.hasNext() ) {
                location = iterator.next();
                piece = getOpponent().getPiece( location );
                if( piece != null ) {
                    if( isAssociated.test( piece ) ) return true;
                    break;
                }
                piece = getOwn().getPiece( location );
                if( piece != null ) break;
            }
        }
        return false;
    }
    
    private boolean isAttackedByKnight( String locationUnderInspection ) {
        
        return isAttackedByPiece( Knight.create( locationUnderInspection ), (piece) -> piece.isKnight() );
    }

    private boolean isAttackedByWhitePawn( String locationUnderInspection ) {
        
        // capturings of MovedBlackPawn and BlackPawn are the same
        return isAttackedByPiece( MovedBlackPawn.create( locationUnderInspection ), (piece) -> piece.isWhitePawn() );
    }

    private boolean isAttackedByBlackPawn( String locationUnderInspection ) {
        
        // capturings of MovedWhitePawn and WhitePawn are the same
        return isAttackedByPiece( MovedWhitePawn.create( locationUnderInspection ), (piece) -> piece.isBlackPawn() );
    }

    private boolean isAttackedByRook( String locationUnderInspection ) {
        
        return isAttackedByPiece( Rook.create( locationUnderInspection ), (piece) -> piece.isRook() );
    }
    
    private boolean isAttackedByBishop( String locationUnderInspection ) {
        
        return isAttackedByPiece( Bishop.create( locationUnderInspection ), (piece) -> piece.isBishop() );
    }

    private boolean isAttackedByQueen( String locationUnderInspection ) {
        
        return isAttackedByPiece( Queen.create( locationUnderInspection ), (piece) -> piece.isQueen() );
    }

    private boolean isAttackedByKing( String locationUnderInspection ) {
        
        return isAttackedByPiece( MovedKing.create( locationUnderInspection ), (piece) -> piece.isKing() );
    }
}

