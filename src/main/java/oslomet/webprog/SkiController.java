package oslomet.webprog;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class SkiController {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(SkiController.class);

    @PostMapping("/lagre")
    public void lagreMelding(Utover utover, HttpServletResponse response) throws IOException {
            String hash = krypterPassord(utover.getPassord());

            String sql = "INSERT INTO utover (fornavn,etternavn,klubb,epost,passord)" +
                    " VALUES(?,?,?,?,?)";
            try{
                db.update(sql,utover.getFornavn(),utover.getEtternavn(),utover.getKlubb(),utover.getEpost(),hash);
            }
            catch(Exception e){
                String errorMessage = "Feil i lagre utøver: " + e;
                logger.error(errorMessage);
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
            }
    }

    private String krypterPassord(String passord){
        String kryptertPassord = BCrypt.hashpw(passord, BCrypt.gensalt(10));
        return kryptertPassord;
    }

    @Autowired private HttpSession session;

    @GetMapping("/login")
    public boolean login(Bruker bruker, HttpServletResponse response) throws IOException {

        String sql = "SELECT * FROM utover WHERE epost=?";
        try{
            Utover dbKunde = db.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(Utover.class),new Object[]{bruker.getEpost(),bruker.getPassord()});
            if(sjekkPassord(bruker.getPassord(),dbKunde.getPassord())){
                session.setAttribute("Innlogget",bruker);
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e) {
            String errorMessage = "Feil i login: " + e;
            logger.error(errorMessage);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
            return false;
        }
    }

    private boolean sjekkPassord( String passord, String hashPassord){
        boolean ok = BCrypt.checkpw(passord,hashPassord);
        return ok;
    }
}
