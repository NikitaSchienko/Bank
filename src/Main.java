import database.Card;
import database.OperationCardDAO;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigInteger;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

//    public static void main(String[] args)
//    {
//        String parameter = "test";
//        if(logger.isDebugEnabled()){
//            logger.debug("This is debug : " + parameter);
//        }
//
//        if(logger.isInfoEnabled()){
//            logger.info("This is info : " + parameter);
//        }
//
//        logger.warn("This is warn : " + parameter);
//        logger.error("This is error : " + parameter);
//        logger.fatal("This is fatal : " + parameter);
//    }

    public static void main(String[] args)
    {
        Card card1 = new Card();
        card1.setId(new BigInteger("4215729546565"));
        card1.setCode(542);
        card1.setMoney(50.50);

        Card card2 = new Card();
        card2.setId(new BigInteger("55456456454454"));
        card2.setCode(1112);
        card2.setMoney(50.50);

        OperationCardDAO operationCardDAO = new OperationCardDAO();

        operationCardDAO.insert(card2);
        operationCardDAO.insert(card1);



    }
}
