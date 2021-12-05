package io.github.sruby.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * @description: first
 * @author: sruby
 * @create: 2021-10-28 12:12
 */
public class First {
    public static void filter(ArrayList<Order> orders) {
        if (orders == null) {
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String currentDay = formatter.format(new Date());
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (!currentDay.equals(order.getTransactionDay())) {
                iterator.remove();
             }
            }
        }
}
