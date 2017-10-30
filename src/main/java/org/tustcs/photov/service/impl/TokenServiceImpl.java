package org.tustcs.photov.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tustcs.photov.config.Config;
import org.tustcs.photov.dao.TokenMapper;
import org.tustcs.photov.entity.Token;
import org.tustcs.photov.service.TokenService;
import org.tustcs.photov.utils.MD5Utils;

import javax.annotation.Resource;

/**
 * @Author L.key.
 * @Date 2017/7/24 16:08
 */
@Service("TokenService")
@Transactional
public class TokenServiceImpl implements TokenService {

    @Resource
    TokenMapper tokenDao;

    public boolean expireToken(Integer id){
        if(null == id){
            return false;
        }
        Token tok = tokenDao.selectByRecId(id);
        Long expireTime = Long.valueOf(tok.getExpireTime());
        if (expireTime < System.currentTimeMillis())
            return false;
        else
            return true;
    }

    public boolean tokenVerify(int recId,int userId,String token) {
        System.out.println(recId+"  dd   "+userId);
        if (null == token){
            return false;
        }
        Token tok = tokenDao.selectByRecId(recId);
        if (userId==tok.getUserId() && token.equals(tok.getToken()))
            return true;
        else
            return false;
    }

    public Token createToken(Integer userId) {
        Token token = tokenDao.selectByUserId(userId);
        if(token == null){
            Token token1 = new Token();
            token1.setUserId(userId);
            token1.setCreateTime(System.currentTimeMillis() + "");
            token1.setExpireTime(System.currentTimeMillis() +

                    ((long)7) * 24 * 60 * 60 * 1000
                    + "");
            token1.setToken(MD5Utils.getMD5(System.currentTimeMillis() + Config.CODEKEY));
            tokenDao.insertSelective(token1);
            Token token2 = tokenDao.selectByToken(token1.getToken());
            return token2;
        }
        token.setExpireTime(System.currentTimeMillis() +

                ((long)7) * 24 * 60 * 60 * 1000
                + "")
        ;
        token.setToken(MD5Utils.getMD5(System.currentTimeMillis() + Config.CODEKEY));
        tokenDao.updateByRecId(token);
        return token;
    }
}
