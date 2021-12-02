package design.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class MD5Encryption {
    public String MD5encrypt(String s)
    {
        if(s.isEmpty())
        {
            return "";
        }
        MessageDigest md5;
        try {
            md5=MessageDigest.getInstance("MD5");
            byte[] bytes=md5.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder res= new StringBuilder();
            for(byte b:bytes)
            {
                String temp=Integer.toHexString(b&0xff);
                if(temp.length()==1)
                {
                    temp="0"+temp;
                }
                res.append(temp);
            }
            return res.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}