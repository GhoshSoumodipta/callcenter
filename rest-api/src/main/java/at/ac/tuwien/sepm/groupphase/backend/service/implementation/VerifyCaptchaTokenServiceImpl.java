package at.ac.tuwien.sepm.groupphase.backend.service.implementation;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.service.VerifyCaptchaTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class VerifyCaptchaTokenServiceImpl implements VerifyCaptchaTokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerifyCaptchaTokenServiceImpl.class);
    @Value("${hcaptcha.secretKey}")
    private String secret;
    public boolean verifyToken(UserDTO UserDTO) throws ServiceException{
        if(UserDTO.getCaptcha()==null || UserDTO.getCaptcha().isEmpty()){
            throw new ServiceException("invalid captcha");
        }
        String strToken = UserDTO.getCaptcha();
        URL url = null;
        HttpURLConnection con = null;
        Reader in = null;
        try{
            url = new URL("https://hcaptcha.com/siteverify");
            con = (HttpURLConnection) url.openConnection();
            String data = "response="+strToken+"&secret="+secret;
            con.setInstanceFollowRedirects(false);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
                wr.write( data.getBytes() );
            }catch (Exception e){
                LOGGER.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage(), e);
            }
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);
            String response = sb.toString();

            JSONObject jsonObj = new JSONObject(response);
            boolean value = jsonObj.getBoolean("success");
            System.out.println(response);
            System.out.println("---------------------------");
            System.out.println(jsonObj);

            int status = con.getResponseCode();
            return value;
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
        }finally {
            if(in!=null){
                try {
                    in.close();
                }catch (Exception e){

                }
            }
            if(con!=null){
                con.disconnect();
            }
        }
        return false;
    }
}
