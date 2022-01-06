package io.sruby.github.swift;/*

import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt1xx.MT101;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;

import java.util.Calendar;

/**
 * Example of a simple message creation using a specific MTnnn class.
 * An MT103 is created and converted to the SWIFT FIN format.
 * <p>
 * Running this program produces the following SWIFT FIN message content:
 * <pre>
 * {1:F01FOOSEDR0AXXX0000000000}{2:I103FOORECV0XXXXN}{4:
 * :20:REFERENCE
 * :23B:CRED
 * :32A:141031EUR1234567,89
 * :50A:/12345678901234567890
 * FOOBANKXXXXX
 * :59:/12345678901234567890
 * JOE DOE
 * :71A:OUR
 * -}
 * </pre>
 */

import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;

import java.util.Calendar;

public class MessageCreation1Example {

    /**
     * This example creates a new MT103 using MT and Field helper classes.
     */
    public static void main(String[] args) {
        /*
         * Create the MT class, it will be initialized as an outgoing message
         * with normal priority
         */
        final MT103 m = new MT103();

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
