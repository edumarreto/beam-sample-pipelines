# Apache Beam Sample Pipeline - edumarreto


## Description

Apache Beam (Java) sample pipeline for batch (non windowed) customer data summarisation, aggregating the number of purchases for a given csv file.

## Pre-requirements

 - [Apache Maven;](https://maven.apache.org/install.html)
 - Google Cloud Project;
 - [Google Cloud CLI.](https://cloud.google.com/sdk/gcloud);

## Execution

    mvn compile exec:java -Dexec.mainClass=com.edudu.beamsamplepipelines.FileBatchPipeline \  
    -Dexec.args="--runner=DataflowRunner --project=[gcpProject] \  
    --gcpTempLocation=gs://[bucket]/tmp \  
    --inputFile=gs://[bucket]/data/* \  
    --output=gs://[bucket]/counts" \  
    -Pdataflow-runner -e
