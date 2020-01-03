package com.edudu.beamsamplepipelines.Transformations.DoFns;

import com.edudu.beamsamplepipelines.Entities.Customer;
import org.apache.beam.sdk.metrics.Counter;
import org.apache.beam.sdk.metrics.Metrics;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseTextEventFn extends DoFn<String, Customer> {
    private static final Logger LOG = LoggerFactory.getLogger(ParseTextEventFn.class);
    private final Counter numParseErrors = Metrics.counter("main", "ParseErrors");

    @ProcessElement
    public void processElement(ProcessContext c){
        String[] data = c.element().split(",", -1);
        try {
            String id = data[0].trim();
            String name = data[1].trim();
            Integer purchases = Integer.parseInt(data[2].trim());
            String birthday = data[3].trim();
            String category = data[4].trim();
            Customer cust = new Customer(id, name, purchases, birthday, category);
            c.output(cust);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            numParseErrors.inc();

        }
    }


}
