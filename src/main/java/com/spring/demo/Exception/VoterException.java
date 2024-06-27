package com.spring.demo.Exception;

public class VoterException extends RuntimeException{
     public VoterException(Long id){super("voter with the "+id+" does not exist"); }

    public VoterException(String email){
        super("voter with the "+email+" does not exist");
    }
}
