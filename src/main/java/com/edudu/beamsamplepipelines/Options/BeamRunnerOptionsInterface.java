package com.edudu.beamsamplepipelines.Options;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

public interface BeamRunnerOptionsInterface extends PipelineOptions {
    @Description("Path to the data file(s).")
    String getInputFile();

    void setInputFile(String value);

    // Set this required option to specify where to write the output.
    @Description("Path of the file to write to.")
    @Validation.Required
    String getOutput();

    void setOutput(String value);

}
