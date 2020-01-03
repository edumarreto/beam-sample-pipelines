package com.edudu.beamsamplepipelines.Transformations.PTransforms;

import com.edudu.beamsamplepipelines.Entities.Customer;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

public class SumCustomerPurchasesPT extends PTransform<PCollection<Customer>, PCollection<KV<String, Integer>>> {


    public SumCustomerPurchasesPT(){};

    public PCollection <KV<String, Integer>> expand (PCollection<Customer> customers){
        return customers
                .apply(
                        MapElements.into(TypeDescriptors.kvs(TypeDescriptors.strings(), TypeDescriptors.integers()))
                                .via((Customer cust) -> KV.of(cust.getId(), cust.getPurchases())))
                .apply(Sum.integersPerKey());

    }
}
