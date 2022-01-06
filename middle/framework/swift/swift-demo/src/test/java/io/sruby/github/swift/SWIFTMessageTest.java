package io.sruby.github.swift;

import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt1xx.MT101;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

/**
 * @description: test
 * @author: sruby
 * @create: 2022-01-06 09:30
 */
public class SWIFTMessageTest {

    @Test
    public void test(){
        SwiftMessage swiftMessage = new SwiftMessage(true);
        swiftMessage.setMUR("aaa");
        boolean ack = swiftMessage.isAck();
        System.out.println(swiftMessage.toJson());
    }

    @Test
    public void testMT101(){
        /*
         * Create the MT class, it will be initialized as an outgoing message
         * with normal priority
         */
        final MT101 m = new MT101();

        /*
         * Set sender and receiver BIC codes
         */
        m.setSender("FOOSEDR0AXXX");
        m.setReceiver("FOORECV0XXXX");

        /*
         * Start adding the message's fields in correct order
         */
        m.addField(new Field20("REFERENCE"));
        m.addField(new Field23B("CRED"));

        /*
         * Add a field using comprehensive setters API
         */
        Field32A f32A = new Field32A()
                .setDate(Calendar.getInstance())
                .setCurrency("EUR")
                .setAmount("1234567,89");
        m.addField(f32A);

        /*
         * Add the orderer field
         */
        Field50A f50A = new Field50A()
                .setAccount("12345678901234567890")
                .setBIC("FOOBANKXXXXX");
        m.addField(f50A);

        /*
         * Add the beneficiary field
         */
        Field59 f59 = new Field59()
                .setAccount("12345678901234567890")
                .setNameAndAddress("JOE DOE");
        m.addField(f59);

        /*
         * Add the commission indication
         */
        m.addField(new Field71A("OUR"));



        /**
         * 设置签名，默认是SHA256 HMAC算法，加密的key为LAU key
         */
        m.setSignature("234234");

        /*
         * Create and print out the SWIFT FIN message string
         */
        System.out.println(m.message());
    }
}
