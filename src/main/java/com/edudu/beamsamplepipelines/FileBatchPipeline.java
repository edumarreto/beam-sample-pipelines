package com.edudu.beamsamplepipelines;


import com.edudu.beamsamplepipelines.Options.BeamRunnerOptionsInterface;
import com.edudu.beamsamplepipelines.Sinks.WriteTextFile;
import com.edudu.beamsamplepipelines.Transformations.DoFns.ParseTextEventFn;
import com.edudu.beamsamplepipelines.Transformations.PTransforms.SumCustomerPurchasesPT;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;

import java.util.HashMap;
import java.util.Map;

public class FileBatchPipeline {

    protected static Map<String, WriteTextFile.FieldFn<KV<String, Integer>>> configureOutput() {
        Map<String, WriteTextFile.FieldFn<KV<String, Integer>>> config = new HashMap<>();
        config.put("customer", (c, w) -> c.element().getKey());
        config.put("total_purchases", (c, w) -> c.element().getValue());
        return config;
    }

    public static void runPipeline(BeamRunnerOptionsInterface options) throws Exception{
        Pipeline pipeline = Pipeline.create(options);

        pipeline
                .apply(TextIO.read().from(options.getInputFile()))
                .apply("ParseTextoToEntity", ParDo.of(new ParseTextEventFn()))
                .apply("SumCustomersPurchases", new SumCustomerPurchasesPT())
                .apply("WriteResultsToTextFile",new WriteTextFile<>(options.getOutput(), configureOutput(),false));

        pipeline.run().waitUntilFinish();
    }

    public static void main(String[] args) throws Exception{
        System.out.println(args[0] + args[1]+args[2]+args[3]);
        BeamRunnerOptionsInterface opt = PipelineOptionsFactory.fromArgs(args).withValidation().as(BeamRunnerOptionsInterface.class);
        runPipeline(opt);

    }
}
