package com.butterfly.spotter.android.model;

/** 
*
* @author Nadim
* @since  Dec 15, 2013
*
*/

public enum StatusCode {

    REGISTRATION_REQUEST (1),
    LOGIN_REQUEST (2),
    SEND_MESSAGE_REQUEST (3),
    SEND_MAP_REQUEST (4),
    BLOCK_REQUEST (5),
    PEERS_REQUEST (6),
    PEER_CONFORMATION_REQUEST (7),
    LOGIN_OK(8);

    private int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
