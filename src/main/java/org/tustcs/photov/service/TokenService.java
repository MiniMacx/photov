package org.tustcs.photov.service;


import org.tustcs.photov.entity.Token;

/**
 * Created by polykickshaw on 17-6-18.
 */
public interface TokenService {

    boolean tokenVerify(int recId, int userId, String token);

    Token createToken(Integer userId);

    boolean expireToken(Integer userId);
}