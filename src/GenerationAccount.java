import database.Card;
import database.OperationCardDAO;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class GenerationAccount {

    final static Logger logger = Logger.getLogger(GenerationAccount.class);

    private final static int COUNT_CARDS = 1000;

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
        OperationCardDAO operationCardDAO = new OperationCardDAO();

        for(int i = 0; i < COUNT_CARDS; i++)
        {
            Card card = new Card();
            operationCardDAO.insert(card);
        }
    }
}
